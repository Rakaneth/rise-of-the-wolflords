package com.rakaneth.wolflords.ui

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Actor
import com.rakaneth.wolflords.maps.WolfMap
import com.rakaneth.wolflords.system.GameState
import com.rakaneth.wolflords.ui.screens.cellH
import com.rakaneth.wolflords.ui.screens.cellW
import com.rakaneth.wolflords.ui.screens.mapH
import com.rakaneth.wolflords.ui.screens.mapW
import squidpony.panel.IColoredString
import squidpony.squidgrid.gui.gdx.*
import squidpony.squidmath.Coord

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

fun cam(center: Coord, wolfMap: WolfMap): Coord {
    val calc: (Int, Int, Int) -> Int = { p, md, s -> MathUtils.clamp(p - s/2, 0, maxOf(md-s, 0))}
    val left = calc(center.x, wolfMap.width, mapW)
    val top = calc(center.y, wolfMap.height, mapH)
    return Coord.get(left, top)
}

fun WolfMap.draw(display: SparseLayers, center: Coord) {
    val offset = cam(center, this)
    val lf: Float = SColor.CW_BRIGHT_ORANGE.toFloatBits()
    val flicker = 0.0015f
    for (x in 0.until(mapW)) {
        for (y in 0.until(mapH)) {
            val wx = x + offset.x
            val wy = y + offset.y
            val t = getTile(wx, wy)
            val d = getTile(wx, wy, false)
            if (t != 'x') {
                val fg = getColor(wx, wy)
                val bg = getColor(wx, wy, false)
                val lAmt = GameState.lightVal(wx, wy)
                display.putWithConsistentLight(x, y, d, fg, bg, lf, lAmt.toFloat(), flicker)
            }
        }
    }
}



