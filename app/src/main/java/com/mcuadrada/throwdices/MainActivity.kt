package com.mcuadrada.throwdices

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import com.mcuadrada.throwdices.Adapters.DiceAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val listDices : ArrayList<Int> = ArrayList()
    private lateinit var adapter : DiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcvDices.layoutManager = GridLayoutManager(this, 2,
            GridLayoutManager.VERTICAL, false)
        adapter = DiceAdapter(this, listDices)
        rcvDices.adapter = adapter

        fabAddDice.setOnClickListener {
            addDice()
        }

        btnThrowDices.setOnClickListener {
            throwDices()
            tvSummation.setText(summationDices().toString())
        }
    }

    fun addDice(){
        if (listDices.size < 5) {
            listDices.add(1)
            adapter.updateAdapter(listDices)
            Log.d("Main", "Se agregó un nuevo dado")
        } else {
            Toast.makeText(this, "Se ha alcanzado el límite de dados", Toast.LENGTH_SHORT).show()
        }
    }

    fun generateRandomNumber() : Int = Random.nextInt(1, 7)

    fun throwDices(){
        for (i in listDices.indices){
            listDices[i] = generateRandomNumber()
            Log.d("Main","dado "+ i + " : "+ listDices[i])
        }
        adapter.updateAdapter(listDices)
    }

    fun summationDices() : Int{
        var total : Int = 0
        for(i in listDices.indices){
            total += listDices[i]
        }
        Log.d("Main", "Suma de dados: " + total)
        return total
    }
}
