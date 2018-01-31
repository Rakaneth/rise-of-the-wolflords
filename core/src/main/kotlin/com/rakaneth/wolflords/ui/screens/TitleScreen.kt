package com.rakaneth.wolflords.ui.screens

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.rakaneth.wolflords.ui.setBoundsXY
import com.rakaneth.wolflords.ui.setPosXY
import com.rakaneth.wolflords.ui.setUp
import com.rakaneth.wolflords.ui.standardSetup
import squidpony.squidgrid.gui.gdx.DefaultResources
import squidpony.squidgrid.gui.gdx.SparseLayers
import squidpony.squidgrid.gui.gdx.SquidMessageBox
import squidpony.squidgrid.gui.gdx.SquidPanel

class TitleScreen : WolfScreen("title") {
    private val slab = DefaultResources.getSlabFamily()
    private val batch = SpriteBatch()
    private val vport = StretchViewport(fullPixelW, fullPixelH)
    private val stage = Stage(vport, batch)
    private val mapLayers = SparseLayers(mapW, mapH, cellW, cellH,
            slab.copy().standardSetup())
    private val statPanel = SquidPanel(hudW, hudH, slab.copy().standardSetup())
    private val msgPanel = SquidMessageBox(msgW, msgH, slab.copy().standardSetup())
    private val infoPanel = SquidPanel(infoW, infoH, slab.copy().standardSetup())

    init {
        mapLayers.setBoundsXY(0, hudH, mapW, mapH)
        statPanel.setPosXY(0, 0)
        msgPanel.setBoundsXY(hudW, 0, msgW, msgH)
        infoPanel.setPosXY(hudW + msgW, 0)
        arrayOf<Actor>(mapLayers, statPanel, msgPanel, infoPanel).forEach {
            stage.addActor(it)
        }
    }



    override fun render() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resize() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}