package com.example.week3_weekend_colornotes.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3_weekend_colornotes.R
import com.example.week3_weekend_colornotes.adapter.ColorRCAdapter
import com.example.week3_weekend_colornotes.model.Hue
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){


    companion object{
        var colorList: MutableList<Hue> = mutableListOf()
        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
    }
    val colorArray: Array<String> = arrayOf("Red", "Blue","Yellow", "Green", "Orange", "Purple", "Brown", "Black")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        colorList.add(Hue(colorArray[0]))
        colorList.add(Hue(colorArray[1]))
        colorList.add(Hue(colorArray[2]))
        colorList.add(Hue(colorArray[3]))
        colorList.add(Hue(colorArray[4]))
        colorList.add(Hue(colorArray[5]))
        colorList.add(Hue(colorArray[6]))
        colorList.add(Hue(colorArray[7]))

        val colorAdapter = ColorRCAdapter(colorList)
        color_listview.adapter = colorAdapter
        val layoutMgr = LinearLayoutManager(this)
        color_listview.layoutManager = layoutMgr
        colorAdapter.notifyDataSetChanged()


        sharedPreferences = this.getSharedPreferences("com.example.week3_weekend_colornotes", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

//        color_listview.setOnClickListener{_->
//            var intent = Intent(this, NotesActivity::class.java)
//            startActivity(intent)
//
//        }


    }
}
