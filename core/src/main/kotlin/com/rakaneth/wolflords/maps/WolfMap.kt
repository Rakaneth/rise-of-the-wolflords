package com.rakaneth.wolflords.maps

import com.rakaneth.wolflords.between
import squidpony.ArrayTools
import squidpony.squidgrid.gui.gdx.MapUtility
import squidpony.squidgrid.gui.gdx.SColor
import squidpony.squidgrid.mapping.DungeonUtility
import squidpony.squidmath.Coord
import squidpony.squidmath.GreasedRegion
import java.io.Serializable

const val WALL = '#'
const val CLOSED = '+'
const val FLOOR = '.'
const val OPEN = '/'
const val OOB = 'x'
private val DOORS = arrayOf('+', '/')
private val walkables = arrayOf('.', '/', ':', ',')

class WolfMap(
        val id: String,
        val name: String,
        private var baseMap: Array<CharArray>,
        var light: Boolean = true
) : Serializable {
    var displayMap: Array<CharArray> = ArrayTools.fill('#', baseMap.size, baseMap[0].size)
    var resistances = DungeonUtility.generateResistances(baseMap)
    var floors: GreasedRegion = GreasedRegion(resistances, 0.8)
    var tempRegion: GreasedRegion = floors.copy()
    val width
        get() = baseMap.size
    val height
        get() = baseMap[0].size

    init {
        for ((x, row) in baseMap.withIndex()) {
            for ((y, col) in row.withIndex()) {
                displayMap[x][y] = when (col){
                    '#' -> WALL
                    '+' -> CLOSED
                    '/' -> CLOSED
                    else -> FLOOR
                }
            }
        }
    }

    private var bgFloats = MapUtility.generateDefaultBGColorsFloat(baseMap)
    private var fgFloats = MapUtility.generateDefaultColorsFloat(baseMap)

    fun isOOB(x: Int, y: Int): Boolean = !(x.between(0, width-1) && y.between(0, height-1))
    fun isOOB(c: Coord): Boolean = isOOB(c.x, c.y)
    fun getTile(x: Int, y: Int, base: Boolean = true): Char {
        return when {
            isOOB(x, y) -> 'x'
            base -> baseMap[x][y]
            else -> displayMap[x][y]
        }
    }
    fun getTile(c: Coord, base: Boolean = true): Char = getTile(c.x, c.y, base)
    fun getColor(x: Int, y: Int, fg: Boolean = true): Float {
        return when {
            isOOB(x, y) -> 0f
            fg -> fgFloats[x][y]
            else -> bgFloats[x][y]
        }
    }
    fun getColor(c: Coord, fg: Boolean = true): Float = getColor(c.x, c.y, fg)
    fun changeMap(c: Coord, base: Char, display: Char, fg: Float? = null, bg: Float? = null) {
        baseMap[c.x][c.y] = base
        displayMap[c.x][c.y] = display
        resistances = DungeonUtility.generateResistances(baseMap)
        floors.refill(resistances, 0.8)
        tempRegion.remake(floors)
        //TODO: Colors
    }


}
