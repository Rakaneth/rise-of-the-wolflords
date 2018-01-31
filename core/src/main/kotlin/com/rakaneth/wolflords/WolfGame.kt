package com.rakaneth.wolflords

import com.badlogic.gdx.ApplicationAdapter
import com.rakaneth.wolflords.ui.screens.WolfScreen

class WolfGame : ApplicationAdapter() {


    override fun create() {

    }

    override fun render() {
        WolfScreen.curScreen?.render()
    }

    override fun resize(width: Int, height: Int) {
        WolfScreen.curScreen?.resize()
        super.resize(width, height)
    }
}