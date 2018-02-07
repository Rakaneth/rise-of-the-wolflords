package com.rakaneth.wolflords

fun <T: Comparable<T>> T.between(low: T, high: T): Boolean {
    return when {
        this < low -> false
        this > high -> false
        else -> true
    }
}