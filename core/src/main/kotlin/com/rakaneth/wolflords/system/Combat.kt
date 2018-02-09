package com.rakaneth.wolflords.system

private fun WolfUnit.countPrefix(pattern: String): Int {
    val p = pattern.toRegex()
    return tags.map {
        p.matchEntire(it)?.destructured?.let { (pref) ->
            pref.length
        } ?: 0
    }.sum()
}

fun WolfUnit.hasWeakness(tag: String): Int = countPrefix("""(X+)-$tag""")
fun WolfUnit.hasResistance(tag: String): Int = countPrefix("""(O+)-$tag""")