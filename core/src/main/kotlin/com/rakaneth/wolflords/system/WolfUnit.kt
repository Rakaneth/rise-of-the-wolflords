package com.rakaneth.wolflords.system

import com.badlogic.gdx.ai.btree.BehaviorTree
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibraryManager
import squidpony.squidmath.Coord
import java.io.Serializable

class WolfUnit(val id: String): Drawable, Mappable, Taggable, Serializable {
    override var pos: Coord = Coord.get(0, 0)
    override var mapID: String = ""
    override var glyph: Char = '@'
    override var fg: String = "White"
    override var bg: String = "Transparent"
    override val tags: MutableList<String> = mutableListOf()
    override var name: String = ""
    var str: Int = 1
    var stam: Int = 1
    var spd: Int = 1
    var skl: Int = 1
    var alive: Boolean =  true
    var curVit: Float = 0f
    val maxVit
        get() = stam * 10
    var curEnd: Float = 0f
    val maxEnd
        get() = (str + stam + spd + skl)
    var xp: Int = 0

    private var aiTree: String = "data/ai/hunt.tree"
    //var wpn: Equipment = ItemBuilder.buildEquip("fists");
    //var arm: Equipment = ItemBuilder.buildEquip("clothes")
    //var trk: Equipment = ItemBuilder.buildEquip("nobling")

    @Transient
    lateinit var bTree: BehaviorTree<WolfUnit>

    fun setAITree(treeShortName: String) {
        aiTree = "data/ai/$treeShortName.tree"
        bTree = BehaviorTreeLibraryManager.getInstance().library.createBehaviorTree(aiTree, this)
    }

}