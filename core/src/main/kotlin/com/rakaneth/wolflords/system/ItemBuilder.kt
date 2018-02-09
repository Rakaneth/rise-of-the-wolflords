package com.rakaneth.wolflords.system

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import squidpony.squidmath.SquidID
import java.io.Reader
import java.util.*

private data class EquipInfo(
        val name: String = "No name",
        val desc: String = "No desc",
        val glyph: String = "",
        val color: String = "",
        val rarity: Int = 0,
        val slot: Slot = Slot.WEAPON,
        val atk: Int = 0,
        val dfp: Int = 0,
        val dmg: Int = 0,
        val sav: Int = 0,
        val dly: Int = 0,
        val prot: Int = 0,
        val tags: List<String> = listOf()
)

object ItemBuilder: Builder {
    private lateinit var eqBP: Map<String, EquipInfo>

    fun buildEquip(buildID: String): Equipment {
        require(eqBP.containsKey(buildID), {"$buildID is not valid equipment, check spelling or file"})
        val info = eqBP[buildID]!!
        return Equipment(
                SquidID().toString(),
                info.name,
                info.color,
                glyph = if (info.glyph.length > 1) info.glyph.toInt(16).toChar() else info.glyph.first(),
                tags = info.tags.toMutableList(),
                atk = info.atk,
                dfp = info.dfp,
                dmg = info.dmg,
                sav = info.sav,
                dly = info.dly,
                prot = info.prot,
                curProt = info.prot
        )
    }

    override fun readFile(reader: Reader) {
        eqBP = ObjectMapper(YAMLFactory()).readValue(reader)
    }
}