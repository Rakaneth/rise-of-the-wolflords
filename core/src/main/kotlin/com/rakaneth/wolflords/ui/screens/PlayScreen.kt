package com.rakaneth.wolflords.ui.screens

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.ai.fsm.DefaultStateMachine
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.rakaneth.wolflords.ui.*
import squidpony.squidgrid.gui.gdx.*
import squidpony.squidmath.Coord

class PlayScreen : WolfScreen("play") {
    private val slab = DefaultResources.getSlabFamily().standardSetup()
    private val batch = SpriteBatch()
    private val vport = StretchViewport(fullPixelW, fullPixelH)
    val stage = Stage(vport, batch)
    val mapLayers = SparseLayers(mapW, mapH, cellW, cellH,
            slab)
    val statPanel = SquidPanel(hudW, hudH, slab.copy())
    val msgPanel = SquidMessageBox(msgW, msgH, slab.copy())
    val infoPanel = SquidPanel(infoW, infoH, slab.copy())
    private val FW = SColor.FLOAT_WHITE
    var cursor = Coord.get(0, 0)
    var buttonP = -1
    var menuState = DefaultStateMachine<PlayScreen, MenuState>(this, MenuState.NULL)
    var input = SquidInput()

    init {
        mapLayers.setBoundsXY(0, hudH, mapW, mapH)
        statPanel.setPosXY(0, 0)
        msgPanel.setBoundsXY(hudW, 0, msgW, msgH)
        infoPanel.setPosXY(hudW + msgW, 0)
        arrayOf<Actor>(mapLayers, statPanel, msgPanel, infoPanel).forEach {
            stage.addActor(it)
        }
        initInput(stage, input)
    }

    private fun drawMap() {
        with (mapLayers) {
            clear()
            put(0, 0, "Map".toICString())
        }
    }

    private fun drawStats() {
        val toDraw = "Test Dude 1".markup("Green", "*", "/")
        with (statPanel) {
            erase()
            putBorders(FW, "Stats")
            put(1, 1, toDraw)
            drawBar(toDraw.length() + 2, 1, 10, 85, 100, SColor.CRIMSON, SColor.BLUE)
        }
    }

    private fun drawMsg() {
        with (msgPanel) {
            erase()
            putBorders(FW, "Messages")
        }
    }

    private fun drawInfo() {
        with (infoPanel) {
            erase()
            putBorders(FW, "InfoPanel")
            put(1, 1, "Cursor at $cursor")
            put(1, 2, "Last button pressed: $buttonP")
        }
    }

    private fun drawCursor() {
        mapLayers.put(cursor.x, cursor.y, SColor.LIGHT_BLUE)
    }

    override fun render() {
        drawMap()
        drawStats()
        drawMsg()
        drawInfo()
        drawCursor()
        if (input.hasNext()) input.next()
        stage.act()
        stage.draw()
    }

    override fun enter() {
        menuState.changeState(MenuState.PLAY)
        super.enter()
    }

    override fun resize(width: Int, height: Int) {
        input.mouse.reinitialize(width / fullWF, height / fullHF, fullWF, fullHF, 0, 0)
        vport.update(width, height, false)
    }
}