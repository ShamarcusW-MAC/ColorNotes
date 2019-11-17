package com.example.week3_weekend_colornotes.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3_weekend_colornotes.R
import com.example.week3_weekend_colornotes.adapter.NotesRCAdapter
import com.example.week3_weekend_colornotes.database.MyDatabase
import com.example.week3_weekend_colornotes.model.Notes
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.note_list_layout.*

class NotesActivity : AppCompatActivity(), NotesRCAdapter.NotesAdapterDelegate {

    companion object{
        var noteList = mutableListOf<Notes>()
        lateinit var myDatabase: MyDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        var intent = intent
        var note:String? = MainActivity.sharedPreferences.getString("Color", "")
        myDatabase = MyDatabase(this)

        if(note == "Red"){
            notes_edittext.setTextColor(Color.RED)
        }else if (note == "Blue")
        {
            notes_edittext.setTextColor(Color.BLUE)
        }else if (note == "Yellow")
        {
            notes_edittext.setTextColor(Color.YELLOW)
        }else if (note == "Green")
        {
            notes_edittext.setTextColor(Color.GREEN)
        }else if (note == "Orange")
        {
            notes_edittext.setTextColor(Color.parseColor("#FFA500"))
        }else if (note == "Purple")
        {
            notes_edittext.setTextColor(Color.parseColor("#5E00F8"))
        }else if (note == "Brown")
        {
            notes_edittext.setTextColor(Color.parseColor("#854B04"))
        }else if (note == "Black")
        {
            notes_edittext.setTextColor(Color.BLACK)
        }


        save_button.setOnClickListener {
            saveToDatabase()
        }
    }

    override fun onResume() {
        super.onResume()
        readFromDatabase()
    }

    private fun saveToDatabase(){
        val note = notes_edittext.text.toString()
        val newNote = Notes(note)
        myDatabase.insertNote(newNote)
        Toast.makeText(this, "Note added to database!", Toast.LENGTH_SHORT).show()
        clearFields()
        readFromDatabase()
    }

    private fun readFromDatabase(){
        noteList = mutableListOf()

        val cursor = myDatabase.readAllNotes()
        cursor.moveToFirst()

        if (cursor.count > 0) {
            val note = Notes(
                cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_NAME))
            )
            noteList.add(note)
        }

        while (cursor.moveToNext()) {
            val noteName = cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_NAME))

            val readNote = Notes(noteName)
            noteList.add(readNote)

        }

        displayNotes()
    }

    private fun clearFields(){
        notes_edittext.text.clear()
    }

    private fun displayNotes(){

        val recyclerAdapter = NotesRCAdapter(noteList, this)
        notes_listview.adapter = recyclerAdapter

        val layoutMgr = LinearLayoutManager(this)
        notes_listview.layoutManager = layoutMgr
        recyclerAdapter.notifyDataSetChanged()
    }

    override fun deleteBooking(notePosition: Int) {
            deleteNote(notePosition)
    }

    private fun deleteNote(position: Int){
        noteList.removeAt(position)

        (notes_listview.adapter as NotesRCAdapter).notifyDataSetChanged()
        readFromDatabase()
        Toast.makeText(this, "Note " + (position + 1) + " deleted.", Toast.LENGTH_SHORT).show()
    }

}
