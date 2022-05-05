package com.metromart.repositoriesapp.extensions

import android.graphics.Color
import android.view.View
import java.util.Random

fun View.setRandomColor() {
    val random = Random()
    this.setBackgroundColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)))
}