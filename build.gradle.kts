plugins {
    kotlin("jvm") version "2.2.21"
    `maven-publish`
    id("signing")
    id("com.diffplug.spotless") version "8.2.1"
}

group = "net.codetreats"
version = "1.0.0"

repositories {
    mavenCentral()
    project.findProperty("snapshot.repo.url")?.let { snapshotUrl ->
        maven {
            url = uri(snapshotUrl.toString())
            isAllowInsecureProtocol = true
        }
    }
}

dependencies {
    val moshi = "1.15.2"
    api("net.codetreats:kotlin-rest-client:3.0.0")
    api("org.apache.logging.log4j:log4j-api:2.25.3")
    implementation("com.squareup.moshi:moshi-kotlin:$moshi")
    implementation("com.squareup.moshi:moshi-adapters:$moshi")
    implementation("com.squareup.moshi:moshi-kotlin-codegen:$moshi")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

signing {
    sign(publishing.publications)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = "net.codetreats"
            artifactId = "sevdesk-kotlin"
            version = project.version as String

            pom {
                name = "sevdesk-kotlin"
                packaging = "jar"
                description = "a kotlin library for the SevDesk Rest-API"
                url = "https://github.com/codetreats/sevdesk-kotlin"
                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }
                developers {
                    developer {
                        id = "martin"
                        name = "Martin"
                        email = "mail@codetreats.net"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/codetreats/sevdesk-kotlin.git"
                    developerConnection = "scm:git:git://github.com/codetreats/sevdesk-kotlin.git"
                    url = "https://github.com/codetreats/sevdesk-kotlin.git"
                }
            }
        }
    }

    repositories {
        maven {
            this.name = "repo"
            this.url = uri(project.findProperty("repo.url")!!.toString())
            this.isAllowInsecureProtocol = true
        }
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

spotless {
    kotlin {
        target("src/**/*.kt")
        ktlint("1.0.1")
            .editorConfigOverride(
                mapOf(
                    "indent_size" to "4",
                    "max_line_length" to "120",
                    "ktlint_standard_no-wildcard-imports" to "disabled",
                ),
            )
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlinGradle {
        target("*.gradle.kts")
        ktlint("1.0.1")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
