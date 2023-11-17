// Define Java Library conventions for this organization.
// Projects need to use the organization's Java conventions and publish using Maven Publish

plugins {
    `java-library`
    `maven-publish`
    id("myproject.java-conventions")
}

repositories {
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://mirrors.huaweicloud.com/repository/maven/")
    // 或者公司本地仓库
    mavenCentral()
}

// Projects have the 'com.example' group by convention
group = "com.example"

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "myOrgPrivateRepo"
            url = uri("build/my-repo")
        }
    }
}

// The project requires libraries to have a README containing sections configured below
val readmeCheck by tasks.registering(com.example.ReadmeVerificationTask::class) {
    readme = layout.projectDirectory.file("README.md")
    readmePatterns = listOf("^## API$", "^## Changelog$")
}

tasks.named("check") { dependsOn(readmeCheck) }
