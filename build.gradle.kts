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
    jvm {}
    js(BOTH) {
        browser {
            commonWebpackConfig {
            }
        }
    }
    sourceSets {
        val jsMain by getting
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