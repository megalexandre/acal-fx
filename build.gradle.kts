import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.openjfx.javafxplugin") version "0.0.13"

	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}


group = "br.org"
version = "0.0.1-SNAPSHOT"

val javafxVersion = "21"
val ulidVersion = "1.0.4"
val javafxWeaverVersion = "1.3.0"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.1")
	implementation ("org.springframework.data:spring-data-commons")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	/*java fx */
	implementation("org.openjfx:javafx-base:$javafxVersion")
	implementation("org.openjfx:javafx-controls:$javafxVersion")
	implementation("org.openjfx:javafx-fxml:$javafxVersion")

	implementation("net.rgielen:javafx-weaver-spring-boot-starter:$javafxWeaverVersion")

	//ULID
	implementation("io.azam.ulidj:ulidj:$ulidVersion")

	implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")

	//log
	implementation("ch.qos.logback:logback-classic")
	implementation("org.slf4j:slf4j-api")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

javafx {
	version = "21"
	modules = listOf("javafx.controls", "javafx.fxml")
}
