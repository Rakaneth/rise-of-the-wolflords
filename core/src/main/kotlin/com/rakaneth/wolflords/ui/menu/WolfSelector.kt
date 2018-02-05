package com.rakaneth.wolflords.ui.menu

import squidpony.squidmath.Coord

interface WolfSelector {
    var selected: Int
    val menuItems: List<String>
    var pos: Coord
    fun handleSelected()
    fun nextItem() {
        val toSelect = selected + 1
        selected = if (toSelect >= menuItems.size) 0 else toSelect
    }
    fun prevItem() {
        val toSelect = selected - 1
        selected = if (toSelect < 0) menuItems.size - 1 else toSelect
    }
}