package com.rakaneth.wolflords.system

import com.badlogic.gdx.Gdx
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
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
        val dly: Int = 5,
        val prot: Int = 0,
        val tags: List<String> = listOf()
)

object ItemBuilder {
    private const val eqFile = "data/entity/equipment.yaml"
    private val eqBP: Map<String, EquipInfo> = jacksonObjectMapper()
            .readValue(Gdx.files.internal(eqFile).reader())

    fun buildEquip(buildID: String): Equipment {
        require(eqBP.containsKey(buildID), {"$buildID is not valid equipment, check spelling or file"})
        val info = eqBP[buildID]!!
        return Equipment(
                UUID.randomUUID().toString(),
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
}