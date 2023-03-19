plugins {
    id("com.google.dagger.hilt.android") version "2.44" apply false
//    id("org.jetbrains.kotlin.android") version "1.7.1" apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("https://jitpack.io")
        }
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
