package com.rakaneth.wolflords.system

import squidpony.squidmath.Coord

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
        val curProt: Int = 0
) : Drawable, Mappable, Taggable {
}