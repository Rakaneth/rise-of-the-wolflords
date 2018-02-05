package com.rakaneth.wolflords.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.scenes.scene2d.Stage

const val fullW = 80
const val fullH = 40
const val fullWF = fullW.toFloat()
const val fullHF = fullH.toFloat()
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
const val fullPixelW = fullW * cellW
const val fullPixelH = fullH * cellH
const val mapWF = mapW.toFloat()
const val mapHF = mapH.toFloat()

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

    fun initInput(stage: Stage, input: InputProcessor) {
        Gdx.input.inputProcessor = InputMultiplexer(stage, input)
    }

    abstract fun render()
    abstract fun resize(width: Int, height: Int)
}