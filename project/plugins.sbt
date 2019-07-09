resolvers += Resolver.sonatypeRepo("releases")
addSbtPlugin("com.codacy" % "codacy-sbt-plugin" % "17.0.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.15")

libraryDependencies += "com.lihaoyi" %% "ujson" % "0.7.5" // to parse Json in the build.sbt file