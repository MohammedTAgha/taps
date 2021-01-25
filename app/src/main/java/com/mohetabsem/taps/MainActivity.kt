package com.mohetabsem.taps

import android.app.Activity
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log.wtf
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask
import android.os.CountDownTimer
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.res.ResourcesCompat
import com.ib.custom.toast.CustomToastView
import www.sanju.motiontoast.MotionToast


class MainActivity : AppCompatActivity() {
    var isStarted = false
    var counter = 0

    fun post_h_score(h_score:Int){
        var localStorge:SharedPreferences?=getSharedPreferences("data",0)
        var editor:SharedPreferences.Editor=localStorge!!.edit()
        editor.putInt("10",h_score).commit()
    }

    fun get_h_score():Int{
        var databack:SharedPreferences?=getSharedPreferences("data",0)

        return databack!!.getInt("10",0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fun mainFun() {
            // set defult values
            count2.isActivated = false // i dont know what is this
            count2.isEnabled = true
            hs.text = get_h_score().toString()  // get highest score
            var red_color=250
            var time = 10 // time count
            timer.text = time.toString() // set time txt to time
            count2.setOnClickListener {
                // chick if timer not is started
                if (!isStarted) {
                    hs.text = get_h_score().toString()
                    start_counter(time)
                    isStarted = true
                }// this start the timer if it not started
                counter = counter + 1 //evry click add one point
                counterTxt.text = counter.toString() //set counter text to count

//                red_color-=5
//                incrementcolor(red_color)
            }
        }
        mainFun()
    }

    // on create end

    fun start_counter(timee:Int) {
        var time = timee
        //*****
        val timer = object : CountDownTimer(time * 1000.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) { // remove a second from your age
                time -= 1
                timer.text = time.toString()
            }
            override fun onFinish() {
                //Toast.makeText(applicationContext,"end",Toast.LENGTH_SHORT).show()
                //CustomToast.makeText(this, T
                // oast.LENGTH_SHORT,CustomToast.DEFAULT, "Toast is working",false).show()
                count2.setBackgroundColor(Color.rgb(200,200,200))
                isStarted = false
                count2.isEnabled=false
                showResult(counter)
                counter=0
                enable_it()
            }
        }
        //*******
        timer.start()
    }
    // ******
    fun enable_it(){
        val timer = object : CountDownTimer( 5000.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                count2.setBackgroundColor(Color.rgb(255,255,255))
                count2.isEnabled=true
            }
        }
        timer.start()
    }

    fun showResult(result:Int){
        //CustomToastView.makeText(this, Toast.LENGTH_SHORT,CustomToastView.DEFAULT, "Toast is working",false).show();
        if (result < get_h_score()){
            CustomToastView.makeInfoToast(this,"s is $result",Toast.LENGTH_SHORT).show()
        }else{
            CustomToastView.makeSuccessToast(this,"s is $result",Toast.LENGTH_SHORT).show()
            post_h_score(counter)
        }
    }
    fun incrementcolor(red:Int){

        count2.setBackgroundColor(Color.rgb(250,red,red))
    }
}
