plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "org.example.kmp.demo"
version = "1.0.0"
application {
    mainClass.set("org.example.kmp.demo.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty.jvm)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}