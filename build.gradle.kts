plugins {
    id ("java-library")
    id ("maven-publish")
}

group = "org.teut2711"
version = "1.0.2"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

}
publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Teut2711/TMBDMovies")
            credentials {
                username =  System.getenv("USERNAME_GITHUB")
                password =  System.getenv("TOKEN_GITHUB")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
tasks.test {
    useJUnitPlatform()
}