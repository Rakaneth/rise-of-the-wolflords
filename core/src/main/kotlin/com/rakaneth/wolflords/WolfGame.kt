package com.rakaneth.wolflords

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.rakaneth.wolflords.ui.screens.PlayScreen
import com.rakaneth.wolflords.ui.screens.WolfScreen
import squidpony.squidgrid.gui.gdx.SColor
import squidpony.squidgrid.gui.gdx.SquidInput

class WolfGame : ApplicationAdapter() {

    private val bgColor = SColor.DARK_SLATE_GRAY

    override fun create() {
        //TODO: create screen objects here
        PlayScreen()
        WolfScreen.setScreen("play")
    }

    override fun render() {
        Gdx.gl.glClearColor(bgColor.r / 255f, bgColor.g / 255f, bgColor.b / 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        WolfScreen.curScreen?.render()
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        WolfScreen.curScreen?.resize(width, height)
    }
}