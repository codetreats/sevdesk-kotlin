plugins {
    kotlin("jvm") version "1.9.0"
    `maven-publish`
    id("signing")
}

group = "net.codetreats"
version = "0.0.6"

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
    implementation("net.codetreats:kotlin-rest-client:0.0.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")
}

tasks.test {
    useJUnitPlatform()
}


kotlin {
    jvmToolchain(17)
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
        if (project.version.toString().endsWith("-SNAPSHOT")) {
            configureRepo("snapshot", false)
        } else {
            configureRepo("release", true)
        }
    }
}

fun RepositoryHandler.configureRepo(type: String, secureProtocol: Boolean) {
    maven {
        this.name = type
        this.url = uri(project.findProperty("$type.repo.url")!!.toString())
        this.isAllowInsecureProtocol = !secureProtocol
        credentials {
            this.username = project.findProperty("$type.repo.user")!!.toString()
            this.password = project.findProperty("$type.repo.password")!!.toString()
        }
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}