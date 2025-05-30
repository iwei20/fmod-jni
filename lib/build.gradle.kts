import net.ltgt.gradle.errorprone.errorprone

plugins {
    `java-library`
    id("com.diffplug.spotless") version "7.+"
    id("net.ltgt.errorprone") version "4.+"
    id("net.ltgt.nullaway") version "2.+"
    // Consider adding spotbugs.
    `maven-publish`
    pmd
}

repositories {
    mavenCentral()
}

dependencies {
    errorprone("com.uber.nullaway:nullaway:0.12.7")
    api("org.jspecify:jspecify:1.0.0")
    errorprone("com.google.errorprone:error_prone_core:2.38.0")

    testImplementation(libs.junit.jupiter)
    implementation("commons-io:commons-io:2.18.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    testLogging.showStandardStreams = true;
    jvmArgs("--enable-preview", "--enable-native-access=ALL-UNNAMED")
}

tasks.withType<JavaCompile> {
    options.getCompilerArgs().add("--enable-preview")
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 23
    options.errorprone.excludedPaths.set(".*/src/generated/.*\\.java")
}

nullaway {
    annotatedPackages.add("com.iwei20.fmod")
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    sourceSets{
        named("main") {
            java {
                srcDir("src/generated/java")
            }

            compileClasspath += files("src/generated/java")
            runtimeClasspath += files("src/generated/java")
        }
    }

}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}

spotless {
    java {
        importOrder()
        removeUnusedImports()

        palantirJavaFormat()
        leadingTabsToSpaces(4)

        formatAnnotations()

        targetExclude("**/generated/**/*.java")
    }
    kotlinGradle {
        trimTrailingWhitespace()
        leadingTabsToSpaces(4)
        endWithNewline()
    }
}
