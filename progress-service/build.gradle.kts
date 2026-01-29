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
	implementation("org.springframework.cloud:spring-cloud-starter-gateway")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
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
