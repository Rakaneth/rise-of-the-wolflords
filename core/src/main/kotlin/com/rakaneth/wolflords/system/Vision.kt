package com.rakaneth.wolflords.system

import squidpony.ArrayTools
import squidpony.squidgrid.FOV

fun WolfUnit.updateFOV() {
    FOV.reuseFOV(this.wolfMap.resistances, this.visible, this.pos.x, this.pos.y, this.vision)
}

fun WolfUnit.setFOV() {
    this.visible = ArrayTools.fill(0.0, this.wolfMap.width, this.wolfMap.height)
}

fun WolfUnit.visible(other: Mappable): Boolean = this.visible[other.pos.x][other.pos.y] > 0.0