plugins {
    kotlin("jvm") version "1.9.0"
    `maven-publish`
    id("signing")
}

group = "net.codetreats"
version = "0.3.0-rc.5-SNAPSHOT"

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
    api("net.codetreats:kotlin-rest-client:2.0.0")
    api("org.apache.logging.log4j:log4j-api:2.24.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    implementation("com.squareup.moshi:moshi-adapters:1.15.1")
    implementation("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")
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