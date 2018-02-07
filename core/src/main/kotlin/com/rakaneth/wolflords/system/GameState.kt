package com.rakaneth.wolflords.system

import com.rakaneth.wolflords.maps.WolfMap
import squidpony.squidmath.Coord
import java.io.Serializable

object GameState: Serializable {
    var maps: MutableMap<String, WolfMap> = mutableMapOf()
    var drawables: MutableMap<String, Drawable> = mutableMapOf()
    var playerVisible: Array<DoubleArray> = arrayOf()

    fun unitByID(id: String): WolfUnit = drawables[id]!! as WolfUnit
    fun mapByID(id: String): WolfMap = maps[id]!!
    fun inView(x: Int, y: Int): Boolean = playerVisible[x][y] > 0.0
    fun lightVal(x: Int, y: Int): Double = playerVisible[x][y]

}