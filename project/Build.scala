import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "curupira"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
	"org.mybatis" % "mybatis" % "3.1.1",
    "org.mybatis" % "mybatis-guice" % "3.3",
	"postgresql" % "postgresql" % "9.1-901-1.jdbc4",
	"commons-io" % "commons-io" % "1.3.2",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
