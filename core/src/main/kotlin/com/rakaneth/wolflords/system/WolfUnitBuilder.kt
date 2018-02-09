package com.rakaneth.wolflords.system

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import squidpony.squidmath.SquidID
import java.io.Reader

private data class WolfUnitInfo(
        val name: String = "No name",
        val desc: String = "No desc",
        val str: Int = 1,
        val stam: Int = 1,
        val spd: Int = 1,
        val skl: Int = 1,
        val glyph: String = "@",
        val color: String = "White",
        val wpn: String = "",
        val arm: String = "",
        val trk: String = "",
        val vision: Int = 6,
        val worthXP: Int = 0,
        val ai: String = "hunt",
        val tags: List<String> = listOf(),
        val skills: List<String> = listOf()
)


object WolfUnitBuilder: Builder {
    private lateinit var unitBP: Map<String, WolfUnitInfo>

    override fun readFile(reader: Reader) {
        unitBP = ObjectMapper(YAMLFactory()).readValue(reader)
    }

    fun buildUnit(buildID: String): WolfUnit {
        require(unitBP.containsKey(buildID), {"$buildID is not a valid unit"})
        val info = unitBP[buildID]!!
        val foetus = WolfUnit(SquidID().toString(), "nomap")
        val wpn = info.wpn
        val arm = info.arm
        val trk = info.trk
        val glyph = if (info.glyph.length > 1) info.glyph.toInt(16).toChar() else info.glyph.first()
        foetus.str = info.str
        foetus.stam = info.stam
        foetus.skl = info.skl
        foetus.spd = info.spd
        foetus.name = info.name
        foetus.desc = info.desc
        if (wpn != "") foetus.wpn = ItemBuilder.buildEquip(wpn)
        if (arm != "") foetus.arm = ItemBuilder.buildEquip(arm)
        if (trk != "") foetus.trk = ItemBuilder.buildEquip(trk)
        foetus.setAITree(info.ai)
        foetus.tags.addAll(info.tags)
        foetus.fg = info.color
        foetus.glyph = glyph
        return foetus
    }
}