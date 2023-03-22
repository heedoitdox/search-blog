import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val asciidoctorExt: Configuration by configurations.creating
val snippetsDir by extra { "build/generated-snippets" }

plugins {
    val kotlinVersion = "1.6.21"
    id("org.springframework.boot") version "2.7.9"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.10"
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

group = "com.heedoitdox"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("com.google.code.gson:gson:2.9.0")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "org.mockito")
    }
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("com.github.tomakehurst:wiremock-jre8:2.35.0")
    testImplementation("com.ninja-squad:springmockk:2.0.3")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("io.kotest:kotest-runner-junit5:5.4.2")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
}

extra["springCloudVersion"] = "2021.0.5"
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
    withType<Test>().configureEach {
        outputs.dir(snippetsDir)
        useJUnitPlatform()
    }
    asciidoctor {
        dependsOn(test)
        configurations(asciidoctorExt.name)
        baseDirFollowsSourceFile()
        inputs.dir(snippetsDir)
        doLast {
            copy {
                from(file("build/docs/asciidoc/index.html"))
                into(file("src/main/resources/static/docs"))
            }
        }
    }
    bootJar {
        dependsOn("asciidoctor")
    }
}
