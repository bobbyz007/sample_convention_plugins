// Define Java conventions for this organization.
// Projects need to use the Java, Checkstyle and Spotbugs plugins.

plugins {
    java
    checkstyle

    // NOTE: external plugin version is specified in implementation dependency artifact of the project's build file
    id("com.github.spotbugs")
}

// Projects should use Maven Central for external dependencies
// This could be the organization's private repository
repositories {
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://mirrors.huaweicloud.com/repository/maven/")
    // 或者公司本地仓库
    mavenCentral()
}

// Use the Checkstyle rules provided by the convention plugin
// Do not allow any warnings
checkstyle {
    config = resources.text.fromString(com.example.CheckstyleUtil.getCheckstyleConfig("/checkstyle.xml"))
    maxWarnings = 0
}

// Enable deprecation messages when compiling Java code
tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xlint:deprecation")
}
