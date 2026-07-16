plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // t5/t6 are declared by the learner; the tests verify the `inline` modifier
    // through Kotlin metadata, which plain Java reflection can't see.
    testImplementation(kotlin("reflect"))
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
    testLogging { events("passed", "failed", "skipped") }
}
