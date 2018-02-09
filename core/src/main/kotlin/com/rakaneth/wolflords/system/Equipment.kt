package com.rakaneth.wolflords.system

import squidpony.squidmath.Coord
import java.io.Serializable

enum class Slot {
    WEAPON, ARMOR, TRINKET;
}

data class Equipment(
        val id: String,
        override var name: String,
        override var fg: String = "White",
        override var bg: String = "Transparent",
        override var glyph: Char = 'x',
        override val tags: MutableList<String> = mutableListOf(),
        override var pos: Coord = Coord.get(0, 0),
        override var mapID: String = "",
        val atk: Int = 0,
        val dfp: Int = 0,
        val dmg: Int = 0,
        val sav: Int = 0,
        val prot: Int = 0,
        val curProt: Int = 0,
        val dly: Int = 0
) : Drawable, Mappable, Taggable, Storeable, Serializable

fun WolfUnit.dequip(slot: Slot) {
    val theItem: Storeable
    when (slot) {
        Slot.WEAPON -> {
            theItem = wpn
            wpn = myFists
        }
        Slot.ARMOR -> {
            theItem = arm
            arm = myArm
        }
        Slot.TRINKET -> {
            theItem = trk
            trk = myTrk
        }
    }
    discard(theItem)
}

fun WolfUnit.discard(item: Storeable) {
    val un = this
    if (playerUnit) {
        GameState.playerCollection.add(item)
    } else {
        with (item as Mappable) {
            mapID = un.mapID
            pos = un.pos
        }
    }
}

fun WolfUnit.equip(item: Equipment, slot: Slot) {
    when (slot) {
        Slot.WEAPON -> {
            if (wpn != myFists) dequip(slot)
            wpn = item
        }
        Slot.ARMOR -> {
            if (arm != myArm) dequip(slot)
            arm = item
        }
        Slot.TRINKET -> {
            if (trk != myTrk) dequip(slot)
            trk = item
        }
    }
}