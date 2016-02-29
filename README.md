[![Codacy Badge](https://api.codacy.com/project/badge/grade/d0f04e048bba4a01a7ef0166bf5b8d32)](https://www.codacy.com/app/Codacy/codacy-checkstyle)
[![Build Status](https://circleci.com/gh/codacy/codacy-checkstyle.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/codacy/codacy-checkstyle)

# Codacy Checkstyle

This is the docker engine we use at Codacy to have [Checkstyle](http://checkstyle.sourceforge.net/) support.
You can also create a docker to integrate the tool and language of your choice!
Check the **Docs** section for more information.

## Usage

You can create the docker by doing:

```
sbt docker:publishLocal
```

The docker is ran with the following command:

```
docker run -it -v $srcDir:/src  <DOCKER_NAME>:<DOCKER_VERSION>
```

## Docs

[Tool Developer Guide](http://docs.codacy.com/v1.5/docs/tool-developer-guide)

[Tool Developer Guide - Using Scala](http://docs.codacy.com/v1.5/docs/tool-developer-guide-using-scala)

## Test

We use the [codacy-plugins-test](https://github.com/codacy/codacy-plugins-test) to test our external tools integration.
You can follow the instructions there to make sure your tool is working as expected.
