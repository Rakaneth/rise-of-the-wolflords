package com.rakaneth.wolflords.system

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Colors
import com.rakaneth.wolflords.ui.markup
import squidpony.panel.IColoredString
import squidpony.squidgrid.gui.gdx.SparseLayers
import squidpony.squidmath.Coord

interface Drawable {
    var name: String
    var glyph: Char
    var fg: String
    var bg: String
    fun draw(display: SparseLayers, screenX: Int, screenY: Int) {
        display.put(screenX, screenY, glyph, Colors.get(fg), Colors.get(bg))
    }
    fun markup(): IColoredString<Color> = name.markup(fg)
}

interface Mappable {
    var pos: Coord
    var mapID: String
}

interface Taggable {
    val tags: MutableList<String>
    fun hasTag(tag: String): Boolean = tags.contains(tag)
}

interface Builder {
    fun readFile(reader: java.io.Reader)
}

interface Storeable