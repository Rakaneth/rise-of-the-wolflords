package com.rakaneth.wolflords.ui

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.rakaneth.wolflords.ui.screens.cellH
import com.rakaneth.wolflords.ui.screens.cellW
import com.rakaneth.wolflords.ui.screens.mapW
import squidpony.panel.IColoredString
import squidpony.squidgrid.gui.gdx.GDXMarkup
import squidpony.squidgrid.gui.gdx.SColor
import squidpony.squidgrid.gui.gdx.SquidPanel
import squidpony.squidgrid.gui.gdx.TextCellFactory

fun SquidPanel.drawBar(x: Int, y: Int, barLength: Int, curValue: Number, maxValue: Number, fillColor: SColor, emptyColor: SColor) {
    require(x + barLength < mapW, {"Bar draws offscreen"})
    (x until x + barLength).forEach {
        put(it, y, emptyColor)
    }
    val toBoxes = curValue.toInt() * barLength / maxValue.toInt()
    (x until x + toBoxes).forEach {
        put(it, y, fillColor)
    }
}

fun String.toICString(): IColoredString<Color> = GDXMarkup.instance.colorString(this)
fun String.markup(vararg markups: String): IColoredString<Color> {
    val raws = markups.map { "[$it]"}.joinToString("")
    return "$raws$this[]".toICString()
}

fun TextCellFactory.setUp(tw: Float, th: Float) = also {
    it.width(cellW).height(cellH).tweakWidth(tw * cellW).tweakHeight(th * cellH).initBySize()
}

fun TextCellFactory.standardSetup() = this.setUp(1.1f, 1.35f)

fun Actor.setPosXY(x: Int, y: Int) {
    this.setPosition(x * cellW, y * cellH)
}

fun Actor.setBoundsXY(xPos: Int, yPos: Int, xWidth: Int, yHeight: Int) {
    this.setBounds(xPos * cellW, yPos * cellH, xWidth * cellW, yHeight * cellH)
}



