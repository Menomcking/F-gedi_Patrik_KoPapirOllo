package com.example.kopapirollo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    lateinit var papir : Button
    lateinit var ko : Button
    lateinit var ollo : Button
    lateinit var embervalasz : ImageView
    lateinit var pcvalasz : ImageView
    var embervalasztas : Int = 0
    var embernyert = 0
    var gepnyert = 0
    lateinit var eredmenyjelzo : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        papir.setOnClickListener{
            embervalasz.setImageDrawable(getDrawable(R.drawable.paper))
            gepvalasztas()
            embervalasztas = 0
            kigyozott()
            eredmenyjelzes()
            winner()
        }
        ko.setOnClickListener{
            embervalasz.setImageDrawable(getDrawable(R.drawable.rock))
            gepvalasztas()
            embervalasztas = 1
            kigyozott()
            eredmenyjelzes()
            winner()
        }
        ollo.setOnClickListener{
            embervalasz.setImageDrawable(getDrawable(R.drawable.scissors))
            gepvalasztas()
            embervalasztas = 2
            kigyozott()
            eredmenyjelzes()
            winner()
        }



    }
    fun ujjateke() {
        AlertDialog.Builder(this)
            .setTitle(gameover())
            .setMessage("Szeretne új játékot játszani?")
            .setNegativeButton("Nem") { _, _ ->
                finish()
            }.setPositiveButton("Igen") { _, _ ->
                embernyert = 0
                gepnyert = 0
                eredmenyjelzes()
            }.show()
    }
    fun winner(){
        if (gepnyert == 3 || embernyert == 3){
            ujjateke()
        }
    }
    fun gameover(): String{
        var gyoztes = ""
        if(gepnyert == 3){
            gyoztes = "Vereség"
        }else if(embernyert == 3){
            gyoztes = "Győzelem"

        }
        return gyoztes
    }
    fun eredmenyjelzes(){
        eredmenyjelzo.text = ("Eredmény: Ember: $embernyert Computer: $gepnyert")
    }
    fun toast(msg: String) {
        Toast.makeText(this, msg,Toast.LENGTH_LONG).show()
    }
    fun kigyozott(){
        var eredmeny : Toast
        var geperedmeny = gepvalasztas()
        if (geperedmeny == embervalasztas){
            toast("Dönteltlen lett a kör eredménye")
        }else if((geperedmeny == 1 && embervalasztas == 0) || (geperedmeny == 0 && embervalasztas == 2) || (geperedmeny == 2 && embervalasztas == 1)){
            toast("A kört ön nyerte")
            embernyert++
        }else if((geperedmeny == 1 && embervalasztas == 2) || (geperedmeny == 0 && embervalasztas == 1) || (geperedmeny == 2 && embervalasztas == 0)){
            toast("A kört a gép nyerte")
            gepnyert++
        }
    }
    fun gepvalasztas(): Int{
        var gepvalasztas = Random.nextInt(0..2)
        if (gepvalasztas == 0){
            pcvalasz.setImageDrawable(getDrawable(R.drawable.paper))
        }else if(gepvalasztas == 1){
            pcvalasz.setImageDrawable(getDrawable(R.drawable.rock))
        }else if(gepvalasztas == 2){
            pcvalasz.setImageDrawable(getDrawable(R.drawable.scissors))
        }
        return gepvalasztas
    }
    fun init(){
        papir = findViewById(R.id.paper)
        ko = findViewById(R.id.rock)
        ollo = findViewById(R.id.scissors)
        embervalasz = findViewById(R.id.playerchoice)
        pcvalasz = findViewById(R.id.pcchoice)
        eredmenyjelzo = findViewById((R.id.counter))
    }
}