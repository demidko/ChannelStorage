package com.github.demidko.telegram

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.Serializable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.System.getenv

/**
 * You need provide BOT_TOKEN and CHANNEL_NAME environment variables for IT test
 */
class MagicStorageIT {

  @Serializable
  data class People(
    val name: String,
    val address: String,
    val bankIdToMoney: Map<Long, Long>
  )

  private lateinit var storage: MagicStorage<String, People>

  @BeforeEach
  fun openChannelStorage() {
    val botToken = getenv("BOT_TOKEN")
    val channelName = getenv("CHANNEL_NAME")
    storage = MagicStorage(botToken, channelName)
  }

  @AfterEach
  fun closeChannelStorage() {
    storage.close()
  }

  @Test
  fun testSave() {
    storage["id"] = People("Elon Musk", "Texas", mapOf(1L to 100L))
  }

  @Test
  fun testDownload() {
    assertThat(storage.get<People>("id"))
      .isEqualTo(People("Elon Musk", "Texas", mapOf(1L to 100L)))
  }
}