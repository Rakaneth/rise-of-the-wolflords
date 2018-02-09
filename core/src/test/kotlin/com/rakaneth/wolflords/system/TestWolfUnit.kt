package com.rakaneth.wolflords.system

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.FileInputStream


class TestWolfUnit {
    init {
        ItemBuilder.readFile(FileInputStream("src/test/res/entity/equipment.yaml").reader())
    }
    private val testUnit = WolfUnit("Test", "noMap")
    init {
        with (testUnit.tags) {
            add("O-res")
            add("OO-res")
            add("XXX-weak")
            add("XX-weak")
            add("Tag")
        }
    }

    @Test
    fun testPrefix() {
        val resists = testUnit.hasResistance("res")
        val weaks = testUnit.hasWeakness("weak")
        assertEquals(3, resists)
        assertEquals(5, weaks)
    }

    @Test
    fun testTag() {
        assertTrue(testUnit.hasTag("Tag"))
    }
}