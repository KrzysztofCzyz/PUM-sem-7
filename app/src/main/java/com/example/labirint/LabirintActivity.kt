package com.example.labirint

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.Button
import java.util.*

class LabirintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_labirint)
    }

//    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onPostCreate(savedInstanceState, persistentState)
//
//    }

    override fun onStart() {
        super.onStart()
        pos = lookForStart()
        ugabuga_binary(lookUpPosition())
    }
    var tbn: Button? = null
    var bbn: Button? = null
    var rbn: Button? = null
    var lbn: Button? = null

    fun getTopButton(): Button {
        if (tbn == null){
            tbn = this.findViewById(R.id.button2)
        }
        return tbn!!
    }

    fun getBottomButton(): Button {
        if (bbn == null){
            bbn = this.findViewById(R.id.button4)
        }
        return bbn!!
    }

    fun getRightButton(): Button {
        if (rbn == null){
            rbn = this.findViewById(R.id.button3)
        }
        return rbn!!
    }

    fun getLeftButton(): Button {
        if (lbn == null){
            lbn = this.findViewById(R.id.button5)
        }
        return lbn!!
    }

    var labirint = listOf(10, 8, 10, 9,
                                28, 1, 0, 12,
                                12, 10, 9, 13,
                                6, 5, 6, 5)
    var pos = lookForStart()
    var score = 0

    fun lookUpPosition(): Int {
        println(pos)
        return labirint[pos]
    }

    fun ugabuga_binary(value: Int) {
        println(value)
        if (value == 0){
            gameOver()
        }
        var temp = value
        var bottom=false
        var top=false
        var right=false
        var left=false
        if (temp > 16){
            temp-=16
        }
        if (temp >= 8){
            bottom = true
            temp-=8
        }
        if (temp >= 4){
            top = true
            temp -=4
        }
        if (temp >= 2) {
            right = true
            temp-=2
        }
        if (temp >= 1){
            left = true
        }

        println(listOf(left, right, top, bottom))
        update(left, right, top, bottom)
    }

    fun gameOver(){
        val intent = Intent(this, GameOverActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, score.toString())
        }
        startActivity(intent)
    }

    fun update(left: Boolean, right: Boolean, top: Boolean, bottom: Boolean){
        getLeftButton().isEnabled=left
        getRightButton().isEnabled=right
        getTopButton().isEnabled=top
        getBottomButton().isEnabled=bottom
    }

    fun lookForStart(): Int {
        score = 0
        var i=0
        labirint.forEach { v -> if (v>16) { println(v); return i} else i++ }
        println("ERROR")
        return -1
    }

    fun setButtons(view: View) {
        when (view.id) {
            getLeftButton().id -> {
                pos--
            }
            getRightButton().id -> {
                pos++
            }
            getTopButton().id -> {
                pos-=4
            }
            getBottomButton().id -> {
                pos+=4
            }
        }
        ugabuga_binary(lookUpPosition())
        score++
    }
}