package com.example.calculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var ldigit : Boolean = false
    var ldot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun Click(view: View){
        val txt = findViewById<TextView>(R.id.input) as TextView

        txt.append((view as Button).text)
        ldigit = true

    }
    fun Clear(view: View){
        val txt = findViewById<TextView>(R.id.input) as TextView
        txt.text=""
        ldot = false
        ldigit = false
    }
    fun dot(view: View){
        val txt = findViewById<TextView>(R.id.input) as TextView
        if (ldigit && !ldot){
            txt.append(".")
            ldigit = false
            ldot = true
        }

    }
    fun cal(view: View){
        val txt = findViewById<TextView>(R.id.input) as TextView
        if (ldigit && !opchecck(txt.text.toString())){
            txt.append((view as Button).text)
            ldigit =  false
            ldot = false
        }
    }
    private fun removeZero(result:String):String{
        var value = result
       if (result.contains(".0")){

           value = value.substring(0,result.length-2)

       }
        return  value
    }
    fun equal(view: View){
        val txt = findViewById<TextView>(R.id.input) as TextView
        if (ldigit){
            var va = txt.text.toString()
            var prefix = ""
            try {
                if(va.startsWith("-")){
                    prefix = "-"
                    va = va.substring(1)
                }
               if (va.contains("-")){
                   val splitvalue = va.split("-")
                   var one = splitvalue[0]
                   var two = splitvalue[1]
                   if (!prefix.isEmpty()){
                       one = prefix + one
                   }
                   txt.text = removeZero((one.toDouble() - two.toDouble()).toString())
               }else if (va.contains("+")){
                   val splitvalue = va.split("+")
                   var one = splitvalue[0]
                   var two = splitvalue[1]
                   if (!prefix.isEmpty()){
                       one = prefix + one
                   }
                   txt.text = removeZero((one.toDouble() + two.toDouble()).toString())
               } else if (va.contains("/")){
                   val splitvalue = va.split("/")
                   var one = splitvalue[0]
                   var two = splitvalue[1]
                   if (!prefix.isEmpty()){
                       one = prefix + one
                   }
                   txt.text = removeZero((one.toDouble() / two.toDouble()).toString())
               }else if (va.contains("*")){
                   val splitvalue = va.split("*")
                   var one = splitvalue[0]
                   var two = splitvalue[1]
                   if (!prefix.isEmpty()){
                       one = prefix + one
                   }
                   txt.text = removeZero((one.toDouble() * two.toDouble()).toString())
               }

            }catch (e:java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun opchecck(value : String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
                    value.contains("/") || value.contains("*") ||
                    value.contains("+") || value.contains("-")
        }
    }
}