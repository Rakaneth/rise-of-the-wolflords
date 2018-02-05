package com.rakaneth.wolflords.ui.screens

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.rakaneth.wolflords.ui.*
import squidpony.squidgrid.gui.gdx.*

class PlayScreen : WolfScreen("play") {
    private val slab = DefaultResources.getSlabFamily().standardSetup()
    private val batch = SpriteBatch()
    private val vport = StretchViewport(fullPixelW, fullPixelH)
    private val stage = Stage(vport, batch)
    private val mapLayers = SparseLayers(mapW, mapH, cellW, cellH,
            slab)
    private val statPanel = SquidPanel(hudW, hudH, slab.copy())
    private val msgPanel = SquidMessageBox(msgW, msgH, slab.copy())
    private val infoPanel = SquidPanel(infoW, infoH, slab.copy())
    private val FW = SColor.FLOAT_WHITE

    init {
        mapLayers.setBoundsXY(0, hudH, mapW, mapH)
        statPanel.setPosXY(0, 0)
        msgPanel.setBoundsXY(hudW, 0, msgW, msgH)
        infoPanel.setPosXY(hudW + msgW, 0)
        arrayOf<Actor>(mapLayers, statPanel, msgPanel, infoPanel).forEach {
            stage.addActor(it)
        }
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
        }
    }

    override fun render() {
        drawMap()
        drawStats()
        drawMsg()
        drawInfo()
        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        vport.update(width, height, false)
    }
}