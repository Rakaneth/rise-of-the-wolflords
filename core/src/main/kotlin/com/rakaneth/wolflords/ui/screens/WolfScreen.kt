package com.rakaneth.wolflords.ui.screens

import com.badlogic.gdx.graphics.g2d.SpriteBatch

const val mapW = 80
const val mapH = 30
const val hudW = 30
const val hudH = 10
const val msgW = 30
const val msgH = 10
const val infoW = 20
const val infoH = 10
const val cellW = 16f
const val cellH = 20f
const val fullPixelW = mapW * cellW
const val fullPixelH = mapH * cellH

abstract class WolfScreen(val screenName: String) {
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
        screens[screenName] = this
    }

    open fun enter() {
        println("Entered $screenName screen.")
    }

    open fun exit() {
        println("Exited $screenName screen.")
    }

    abstract fun render()
    abstract fun resize()
}