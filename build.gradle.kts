plugins {
    kotlin("multiplatform") version "1.7.10"
    `maven-publish`
}

group = "logic.tools"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    js(BOTH) {
        browser {
            commonWebpackConfig {
            }
        }
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":proto-web-component"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}


/*publishing {
    publications {
        create<MavenPublication>("maven") {
        }
    }
}

*/

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mvanassche/kotlin-web-component")
            credentials {
                username = project.findProperty("github.user") as? String
                password = project.findProperty("github.key") as? String
            }
        }
    }
}