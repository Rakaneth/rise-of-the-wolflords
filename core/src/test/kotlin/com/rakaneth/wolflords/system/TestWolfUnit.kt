package com.rakaneth.wolflords.system

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.FileInputStream


class TestWolfUnit {
    private val testEQFile = "src/test/res/entity/equipment.yaml"
    private val testUnitFile = "src/test/res/entity/units.yaml"
    init {
        ItemBuilder.readFile(FileInputStream(testEQFile).reader())
        WolfUnitBuilder.readFile(FileInputStream(testUnitFile).reader())
    }
    private val testUnit = WolfUnitBuilder.buildUnit("heroFighter")
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
    fun testDefaults() {
        with (testUnit) {
            assertEquals("No name", name)
            assertEquals(1, spd)
            assertEquals(1, skl)
            assertTrue(alive)
            assertEquals(6.0, vision)
        }
    }

    @Test
    fun testBuilders() {
        with (testUnit) {
            assertEquals("Iron Sword", wpn.name)
            assertEquals("Chainmail", arm.name)
            assertEquals(6, maxEnd)
            assertEquals(3, atk)
            assertEquals(3, dmg)
            assertEquals(1, dfp)
            assertEquals(2, sav)
            assertEquals(9, maxVit)
            assertEquals(9, atkDly)
            assertEquals(11, movDly)
            assertEquals(25, prot)
            assertEquals(25, curProt)
            assertTrue(hasTag("warrior"))
        }
    }

    @Test
    fun testPrefix() {
        val resists = testUnit.hasResistance("res")
        val weaks = testUnit.hasWeakness("weak")
        val shouldBeZero = testUnit.hasResistance("Tag")
        val shouldAlsoBeZero = testUnit.hasWeakness("Tag")
        val thisTooShouldBeZero = testUnit.hasResistance("Nonexistent")
        val oneMoreZeroForGoodMeasure = testUnit.hasWeakness("Nonexistent")
        assertEquals(3, resists)
        assertEquals(5, weaks)
        assertEquals(0, shouldBeZero)
        assertEquals(0, shouldAlsoBeZero)
        assertEquals(0, thisTooShouldBeZero)
        assertEquals(0, oneMoreZeroForGoodMeasure)
    }

    @Test
    fun testTag() {
        assertTrue(testUnit.hasTag("Tag"))
    }
}