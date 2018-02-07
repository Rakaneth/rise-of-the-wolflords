package com.rakaneth.wolflords.ui.screens

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.rakaneth.wolflords.ui.menu.WolfSelector
import squidpony.squidgrid.gui.gdx.SquidInput
import squidpony.squidgrid.gui.gdx.SquidMouse
import squidpony.squidgrid.mapping.Rectangle
import squidpony.squidmath.Coord

private val mapRanges = ((0 until mapW) to (0 until mapH))
private val statRanges = ((0 until hudW) to (mapH until mapH + hudH))
private val msgRanges = ((hudW until hudW + msgW) to (mapH until mapH + msgH))
private val infoRanges = ((hudW + msgW until hudW + msgW + infoW) to (mapH until mapH + infoH))

fun inPanelRange(c: Coord, r: Pair<IntRange, IntRange>) = r.first.contains(c.x) && r.second.contains(c.y)
fun inMap(c: Coord) = inPanelRange(c, mapRanges)
fun inStat(c: Coord) = inPanelRange(c, statRanges)
fun inInfo(c: Coord) = inPanelRange(c, infoRanges)
fun statRow(c: Coord): Int = c.y - mapH
enum class MenuState(val theMenu: WolfSelector?) : State<PlayScreen> {

    NULL(null) {
        override fun update(entity: PlayScreen?) {
        }

        override fun enter(entity: PlayScreen?) {
        }

        override fun exit(entity: PlayScreen?) {
        }
    },

    PLAY(null) {
        override fun update(entity: PlayScreen?) {
        }

        override fun enter(entity: PlayScreen?) {
            val mouse = SquidMouse(cellW, cellH, fullWF, fullHF, 0, 0,
                    object : InputAdapter() {
                        override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
                            val c = Coord.get(screenX, screenY)
                            when {
                                inMap(c) -> entity!!.cursor = c
                                inStat(c) -> println("stats clicked at row ${statRow(c)}")
                                inInfo(c) -> println("info clicked at row ${statRow(c)}")
                                else -> println("something else clicked")
                            }
                            return false
                        }
                    })
            val kbd = SquidInput.KeyHandler { key, alt, ctrl, shift ->
                println("Keyboard was pressed")
            }
            entity!!.input = SquidInput(kbd, mouse)
            entity.initInput(entity.stage, entity.input)
        }

        override fun exit(entity: PlayScreen?) {
        }

    };

    override fun onMessage(entity: PlayScreen?, telegram: Telegram?): Boolean {
        return false
    }
}