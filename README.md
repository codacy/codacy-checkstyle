# Codacy Checkstyle

This is the docker engine we use at Codacy to have [Checkstyle](http://checkstyle.sourceforge.net/) support.
You can also create a docker to integrate the tool and language of your choice!
See the [codacy-engine-scala-seed](https://github.com/codacy/codacy-engine-scala-seed) repository for more information.

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/edc090bb5ed64aa5a009ace701e41d63)](https://www.codacy.com/gh/codacy/codacy-checkstyle?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=codacy/codacy-checkstyle&amp;utm_campaign=Badge_Grade)
[![CircleCI](https://circleci.com/gh/codacy/codacy-checkstyle.svg?style=svg)](https://circleci.com/gh/codacy/codacy-checkstyle)

## Usage

You can create the docker by doing:

```bash
sbt universal:stage
docker build -t codacy-checkstyle .
```

The docker is ran with the following command:

```bash
docker run -it -v $srcDir:/src -v $configFile:/.codacyrc  <DOCKER_NAME>:<DOCKER_VERSION>
```

## Generate Docs

The documentation generator is using the [pandoc](https://pandoc.org/) tool to convert html sections to markdown.
The pandoc version used currently is 2.7.3 which can be downloaded [here](https://github.com/jgm/pandoc/releases/tag/2.7.3).
Having pandoc in `PATH` you can follow these steps:

1. Update the `checkstyleVersion` value in `build.sbt`

2. Run the DocGenerator

```bash
sbt "runMain codacy.checkstyle.DocGenerator"
```

## Test

We use the [codacy-plugins-test](https://github.com/codacy/codacy-plugins-test) to test our external tools integration.
You can follow the instructions there to make sure your tool is working as expected.

## Agent Playbook: Updating This Repository End-to-End

This section is written for an AI coding agent (or a human) tasked with updating this repo — most commonly bumping the wrapped Checkstyle version, but also base image / orb / dependency bumps. Follow it top to bottom; it tells you what to change, how to regenerate derived files, how to test locally, and how to interpret CI so you can iterate on failures without guessing.

### 1. What this repository is

This is a **Codacy engine**: a thin Scala wrapper (`src/main/scala/codacy/Engine.scala`, built on `codacy-engine-scala-seed`) that packages [Checkstyle](https://checkstyle.org/) as a Docker image Codacy's platform can run against a customer's source code. The `docs/` directory is not just documentation — it is **machine-consumed configuration**:

- `docs/patterns.json` — the full list of Checkstyle rules ("patterns") Codacy knows about, their parameters/defaults, and which are enabled out of the box. Generated file, do not hand-edit.
- `docs/description/description.json` + `docs/description/*.md` — human-readable titles/descriptions per pattern, used in the Codacy UI. Generated file, do not hand-edit.
- `docs/tests/*` and `docs/multiple-tests/*` — fixtures used by `codacy-plugins-test` to validate the engine actually produces the results it claims to for real code samples.
- `docs/tool-description.md` — short blurb about the tool, hand-maintained.

All three generated artifacts above come from **`DocGenerator`** (`src/main/scala/codacy/checkstyle/DocGenerator.scala`), which clones the real `checkstyle/checkstyle` GitHub repo at the tag `checkstyle-<checkstyleVersion>` and scrapes its `src/site/xdoc/*.xml` documentation to build the pattern list. This means the generator needs **network access**, **git**, and **pandoc** installed locally.

### 2. Files that encode versions — check all of these on every update

| File | What it controls | What to check |
|---|---|---|
| `build.sbt` → `checkstyleVersion` | Which Checkstyle release is bundled and which git tag `DocGenerator` clones | Bump to the target version. Confirm a matching `checkstyle-<version>` tag exists in [checkstyle/checkstyle](https://github.com/checkstyle/checkstyle/tags). |
| `build.sbt` → `codacy-engine-scala-seed` dependency | Codacy's engine SDK/base library | Check [Maven Central](https://mvnrepository.com/artifact/com.codacy/codacy-engine-scala-seed) for newer versions if asked to update it; not tied to Checkstyle bumps. |
| `.circleci/config.yml` → `codacy/base` orb | Shared CircleCI steps (checkout, versioning, sbt build, docker publish, tagging) | Check the latest published version (CircleCI orb registry, or ask — WebFetch may not render the JS page; `git log -p .circleci/config.yml` shows the bump history as a fallback reference). |
| `.circleci/config.yml` → `codacy/plugins-test` orb | Runs `codacy-plugins-test` in CI after the image is built | Same as above. |
| `Dockerfile` → base image (`eclipse-temurin:11-jre-alpine`) | JRE the packaged app runs on | Only bump if the new Checkstyle version raises its minimum JDK requirement (check Checkstyle's release notes) or if asked explicitly — don't bump opportunistically. |
| `project/build.properties` / `project/plugins.sbt` | sbt version / sbt plugins | Rarely needs touching; check only if the build itself fails to load. |

Look at recent bump commits for the shape of a typical diff: `git log --oneline --all | grep -i bump`, then `git show <hash>`. Recent examples touch `build.sbt` (checkstyleVersion), `.circleci/config.yml` (orb versions), and the regenerated `docs/*` files together.

### 3. Step-by-step update procedure

1. **Bump the version(s)** in `build.sbt` (and `.circleci/config.yml` orbs, if applicable) as scoped by the task.
2. **Regenerate the docs.** Requires `pandoc` (tested with 2.7.3, see download link above) on `PATH`, plus git/network access:
   ```bash
   sbt "runMain codacy.checkstyle.DocGenerator"
   ```
   This clones `checkstyle/checkstyle` at the new tag into a temp dir and rewrites `docs/patterns.json`, `docs/description/description.json`, and `docs/description/*.md`. Review the diff:
   - New patterns appear — decide if any should be added to `defaultPatterns` in `DocGenerator.scala` (rules enabled by default; historically only a small curated set is enabled out of the box — don't enable new ones unless asked).
   - Removed/renamed patterns — check whether they're referenced in `docs/tests/`, `docs/multiple-tests/*/patterns.xml`, or `docs/multiple-tests/*/results.xml`; stale references will fail `codacy-plugins-test`.
   - `Header.md`/`RegexpHeader.md`-style deletions have happened before when Checkstyle merges/renames checks — this is expected, not a bug, as long as it matches upstream's changelog.
3. **Compile and format:**
   ```bash
   sbt "set ThisBuild / scalafmtUseIvy := false; scalafmt::test; test:scalafmt::test; sbt:scalafmt::test; universal:stage"
   ```
   This mirrors the CI `publish_docker_local` job exactly. If formatting fails, run the same commands without `::test` (i.e. `sbt scalafmt`) to auto-fix, then re-run the check.
4. **Build the Docker image** (same as CI):
   ```bash
   docker build -t codacy-checkstyle .
   ```
5. **Run `codacy-plugins-test` locally** before pushing — this is the real correctness check (there is no `src/test` unit test suite in this repo; verification is entirely through this external tool against fixtures in `docs/`). Clone https://github.com/codacy/codacy-plugins-test and run, from that repo, against your local image tag:
   ```bash
   sbt "runMain codacy.plugins.DockerTest pattern codacy-checkstyle:latest"
   sbt "runMain codacy.plugins.DockerTest json codacy-checkstyle:latest"
   sbt "runMain codacy.plugins.DockerTest multiple codacy-checkstyle:latest"
   ```
   Useful flags: `-Dcodacy.tests.languages=java` to scope languages, `--only <folderName>` to target a single `docs/multiple-tests/<folderName>` case while iterating, `-Dcodacy.tests.ignore.descriptions` to skip description-completeness checks while debugging something unrelated.
6. **Iterate on failures** (see table below), re-running only the relevant `DockerTest` command after each fix — no need to rebuild the whole loop from step 1 unless `build.sbt`/source changed.
7. **Commit** the version bump(s) together with the regenerated `docs/` files in one change (this matches historical commits like `dc394fc`, `d104910`).
8. **Push and open a PR.** CI (`.circleci/config.yml`) runs, in order: `codacy/checkout_and_version` → `publish_docker_local` (scalafmt + `universal:stage` + `docker build` + `docker save`) → `plugins_test` (`codacy_plugins_test/run`, `run_multiple_tests: true`) → `codacy/publish_docker` (master only) → `codacy/tag_version`. A failure in `publish_docker_local` is almost always scalafmt or a compile error; a failure in `plugins_test` means the generated patterns/docs don't match what the engine actually outputs for the fixtures.
9. **Poll the PR's real CI checks until they all pass — local validation is NOT the finish line.** After every push, run `gh pr checks <pr-url>` and keep re-polling (short sleep while any check is `pending`) until all checks finish. If a check fails, fetch its actual log (CircleCI API/UI for the failing job — don't guess), find the true root cause, fix it, push again (never `--no-verify`, never force-push), and re-poll. Repeat until every check is green. **The CI environment's toolchain can differ from your local one**, so a clean local run does not guarantee CI passes. Concrete example this playbook has already hit: the 13.x bump compiled fine locally under a manually-installed JDK 21, but `publish_docker_local` went red with `Class java.lang.Record not found` because the CircleCI `codacy/sbt` job's *build* JDK defaulted to Java 8 — a different concern from the Dockerfile's *runtime* JDK. It was only caught by polling the real CI check, and fixed by passing `openjdk_version: "21"` to the job (and bumping the `codacy/base` orb to a version that supports it). Only stop iterating when every check passes, or you hit a genuine product/infra decision that needs a human — in which case explain it in the PR rather than guessing.

### 4. Common failure modes and fixes

| Symptom | Likely cause | Fix |
|---|---|---|
| `scalafmt::test` fails in CI/locally | Generated or hand-edited Scala file not formatted | Run `sbt scalafmt` (drop the `::test` suffix) then re-run the check command |
| `DocGenerator` run fails to clone | Wrong/nonexistent `checkstyle-<version>` tag | Verify the tag exists upstream; Checkstyle tags are exactly `checkstyle-X.Y.Z` |
| `DocGenerator` run fails needing `pandoc` | pandoc missing from `PATH` | Install pandoc 2.7.3 (link at top of README) |
| `pattern`/`json` DockerTest fails: pattern present in `docs/patterns.json` but tool doesn't emit it (or vice versa) | Checkstyle rule renamed/removed/added upstream between versions | Re-run `DocGenerator`; if a pattern legitimately vanished upstream, its removal from `docs/patterns.json`/`description.json` is correct — just confirm no fixture still references it |
| `multiple` DockerTest fails on a specific folder under `docs/multiple-tests/` | `results.xml` or `patterns.xml` expectations are stale for the new Checkstyle behavior | Regenerate/update the expected `results.xml` for that folder to match the new (correct) tool output — verify the new output is actually correct, don't just rubber-stamp a diff |
| Docker image runs but engine crashes on startup | JDK in `Dockerfile` too old for new Checkstyle version, or `codacy-engine-scala-seed` incompatibility | Check Checkstyle's release notes for its minimum JDK; bump the `eclipse-temurin` tag in `Dockerfile` only if actually required |
| CI `publish_docker` / `tag_version` steps don't run on your branch | Expected — those are gated to `master` only (`filters: branches: only: master`) via `requires: codacy/publish_docker` | Nothing to fix; this is only exercised after merge |

### 5. Definition of done

- `build.sbt` (and orbs, if in scope) reflect the target versions.
- `docs/patterns.json`, `docs/description/description.json`, and `docs/description/*.md` are regenerated and committed, with any fixture/test-data inconsistencies resolved.
- `sbt scalafmt::test` and `sbt universal:stage` pass locally.
- `docker build -t codacy-checkstyle .` succeeds.
- `codacy-plugins-test`'s `pattern`, `json`, and `multiple` commands all pass locally against the freshly built image.
- The diff looks like previous version-bump commits in shape (`git log --oneline --all | grep -i bump` for reference).
- **After pushing and opening/updating the PR, every CI check on it is green.** Poll `gh pr checks <pr-url>` and iterate on any failure (fetch the real CI log, fix, push, re-poll) until all pass — a passing local build is not sufficient, because the CI toolchain (e.g. the CircleCI build JDK) can differ from your local one (see step 9).

## What is Codacy

[Codacy](https://www.codacy.com/) is an Automated Code Review Tool that monitors your technical debt, helps you improve your code quality, teaches best practices to your developers, and helps you save time in Code Reviews.

### Among Codacy’s features

- Identify new Static Analysis issues
- Commit and Pull Request Analysis with GitHub, BitBucket/Stash, GitLab (and also direct git repositories)
- Auto-comments on Commits and Pull Requests
- Integrations with Slack, HipChat, Jira, YouTrack
- Track issues in Code Style, Security, Error Proneness, Performance, Unused Code and other categories

Codacy also helps keep track of Code Coverage, Code Duplication, and Code Complexity.

Codacy supports PHP, Python, Ruby, Java, JavaScript, and Scala, among others.

### Free for Open Source

Codacy is free for Open Source projects.
