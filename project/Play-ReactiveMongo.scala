import sbt._
import sbt.Keys._

object PlayReactiveMongoBuild extends Build {

  import uk.gov.hmrc.DefaultBuildSettings

  lazy val pluginName = "Play-ReactiveMongo"
  val pluginVersion = "2.6.0-SNAPSHOT"

  lazy val pluginDependencies = Seq(
    "uk.gov.hmrc" %% "simple-reactivemongo" % "1.3.0" % "provided" cross CrossVersion.binary,
    "uk.gov.hmrc" %% "simple-reactivemongo" % "1.3.0" % "test" cross CrossVersion.binary classifier "tests",

    "com.typesafe.play" %% "play" % "[2.2.1,2.2.3]" % "provided" cross CrossVersion.binary,
    "com.typesafe.play" %% "play-test" % "[2.2.1,2.2.3]" % "test" cross CrossVersion.binary,

    "org.scalatest" %% "scalatest" % "2.2.0" % "test" cross CrossVersion.binary,
    "org.pegdown" % "pegdown" % "1.4.2" % "test" cross CrossVersion.Disabled
  )

  lazy val pluginProject = Project(pluginName, file("."), settings = DefaultBuildSettings(pluginName, pluginVersion, targetJvm = "jvm-1.7")() ++ Seq(
    libraryDependencies ++= pluginDependencies,
    resolvers ++= Seq(
      "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/",
      "typesafe-snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
      Opts.resolver.sonatypeReleases,
      Opts.resolver.sonatypeSnapshots
    )
  ) ++ SonatypeBuild()
  )

}

object SonatypeBuild {

  import xerial.sbt.Sonatype._

  def apply() = {
    sonatypeSettings ++ Seq(
      pomExtra := (<url>https://www.gov.uk/government/organisations/hm-revenue-customs</url>
        <licenses>
          <license>
            <name>Apache 2</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
          </license>
        </licenses>
        <scm>
          <connection>scm:git@github.com:hmrc/Play-ReactiveMongo.git</connection>
          <developerConnection>scm:git@github.com:hmrc/Play-ReactiveMongo.git</developerConnection>
          <url>git@github.com:hmrc/Play-ReactiveMongo.git</url>
        </scm>
        <developers>
          <developer>
            <id>sgodbillon</id>
            <name>Stephane Godbillon</name>
            <url>http://stephane.godbillon.com</url>
          </developer>
          <developer>
            <id>mandubian</id>
            <name>Pascal Voitot</name>
            <url>http://mandubian.com</url>
          </developer>
          <developer>
            <id>xnejp03</id>
            <name>Petr Nejedly</name>
            <url>http://www.equalexperts.com</url>
          </developer>
          <developer>
            <id>duncancrawford</id>
            <name>Duncan Crawford</name>
            <url>http://www.equalexperts.com</url>
          </developer>
        </developers>)
    )
  }
}