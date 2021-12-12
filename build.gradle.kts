repositories {
  mavenCentral()
  maven("https://jitpack.io")
}
plugins {
  `java-library`
  `maven-publish`
  kotlin("jvm") version "1.6.0"
  kotlin("plugin.serialization") version "1.6.0"
}
dependencies {
  api("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.6")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-cbor:1.3.1")
  testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
  testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")
  testImplementation("io.mockk:mockk:1.12.1")
}
tasks.compileKotlin {
  kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
}
tasks.compileTestKotlin {
  kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
}
tasks.test {
  useJUnitPlatform()
}
publishing {
  publications {
    create<MavenPublication>("channelstorage") {
      from(components["java"])
    }
  }
}

