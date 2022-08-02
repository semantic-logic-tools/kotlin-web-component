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
            }
        }
    }
}
