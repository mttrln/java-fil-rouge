plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}
val springCloudVersion by extra("2024.0.0")

group = "edu.esiea"
version = "0.0.1-SNAPSHOT"
description = "TP Fil rouge Spring boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.security:spring-security-oauth2-jose")

	implementation("org.xerial:sqlite-jdbc:3.49.1.0")
	implementation("org.hibernate.orm:hibernate-community-dialects")
	implementation("org.mapstruct:mapstruct:1.6.3")
	implementation("org.postgresql:postgresql")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")


	testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.postgresql:postgresql")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	compileOnly("org.projectlombok:lombok:1.18.42")
	annotationProcessor("org.projectlombok:lombok:1.18.42")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

	testCompileOnly("org.projectlombok:lombok:1.18.42")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.42")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register("run") {
    group = "application"
    dependsOn("bootRun")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
	}
}

