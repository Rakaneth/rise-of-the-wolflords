package com.rakaneth.wolflords.ui.screens

import com.badlogic.gdx.graphics.g2d.SpriteBatch

const val mapW = 80
const val mapH = 30
const val hudW = 26
const val hudH = 10
const val msgW = 28
const val msgH = 10
const val infoW = 26
const val infoH = 10
const val cellW = 16f
const val cellH = 20f
const val fullPixelW = 80 * cellW
const val fullPixelH = 40 * cellH

abstract class WolfScreen(private val screenName: String) {
    companion object {
        var curScreen: WolfScreen? = null
        val screens: MutableMap<String, WolfScreen> = mutableMapOf()
        fun setScreen(screenName: String) {
            curScreen?.exit()
            curScreen = screens[screenName]
            curScreen?.enter()
        }
    }
     init {
         addToList()
     }

    private fun addToList() {
        screens[screenName] = this
    }

    open fun enter() {
        println("Entered $screenName screen.")
    }

    open fun exit() {
        println("Exited $screenName screen.")
    }

    abstract fun render()
    abstract fun resize(width: Int, height: Int)
}