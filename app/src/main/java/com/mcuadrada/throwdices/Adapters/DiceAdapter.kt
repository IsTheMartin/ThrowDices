package com.mcuadrada.throwdices.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mcuadrada.throwdices.R
import kotlinx.android.synthetic.main.dice_item.view.*

class DiceAdapter(val context: Context, val listDices: ArrayList<Int>) :
    RecyclerView.Adapter<DiceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.dice_item, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        var imageResource : Int = 0
        when(listDices.get(p1)){
            1 -> imageResource = R.drawable.dice1
            2 -> imageResource = R.drawable.dice2
            3 -> imageResource = R.drawable.dice3
            4 -> imageResource = R.drawable.dice4
            5 -> imageResource = R.drawable.dice5
            6 -> imageResource = R.drawable.dice6
            else -> R.drawable.diceerror
        }
        p0?.imvDice?.setImageResource(imageResource)
    }

    override fun getItemCount(): Int {
        return listDices.size
    }

    fun updateAdapter(updatedListDices: ArrayList<Int>){
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imvDice = view.imvDice

        init {
            imvDice.setOnClickListener(remove())
        }

        private fun remove() : (View) -> Unit = {
            layoutPosition.also { currentPosition ->
                listDices.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
            }
        }
    }
}