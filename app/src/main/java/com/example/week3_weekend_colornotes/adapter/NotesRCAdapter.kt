package com.example.week3_weekend_colornotes.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week3_weekend_colornotes.R
import com.example.week3_weekend_colornotes.database.MyDatabase
import com.example.week3_weekend_colornotes.model.Hue
import com.example.week3_weekend_colornotes.model.Notes
import com.example.week3_weekend_colornotes.view.DisplayNoteActivity
import com.example.week3_weekend_colornotes.view.MainActivity
import com.example.week3_weekend_colornotes.view.NotesActivity
import kotlinx.android.synthetic.main.activity_notes.*

class NotesRCAdapter(private val notesList: List<Notes>, private val delegate: NotesAdapterDelegate):
    RecyclerView.Adapter<NotesRCAdapter.MyCustomViewHolder>() {

    interface NotesAdapterDelegate{
        fun deleteBooking(notePosition: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.note_list_layout, parent, false)



        return MyCustomViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {

        holder.apply {
            noteEditText.text = notesList[position].note
            var note:String? = MainActivity.sharedPreferences.getString("Color", "")
            if(note == "Red"){
                noteEditText.setTextColor(Color.RED)
            }else if (note == "Blue")
            {
                noteEditText.setTextColor(Color.BLUE)
            }else if (note == "Yellow")
            {
                noteEditText.setTextColor(Color.YELLOW)
            }else if (note == "Green")
            {
                noteEditText.setTextColor(Color.GREEN)
            }else if (note == "Orange")
            {
                noteEditText.setTextColor(Color.parseColor("#FFA500"))
            }else if (note == "Purple")
            {
                noteEditText.setTextColor(Color.parseColor("#5E00F8"))
            }else if (note == "Brown")
            {
                noteEditText.setTextColor(Color.parseColor("#854B04"))
            }else if (note == "Black")
            {
                noteEditText.setTextColor(Color.BLACK)
            }


            itemView.findViewById<ImageView>(R.id.deleteNote_imageView).setOnClickListener {
                delegate.deleteBooking(position)
            }

            holder.itemView.setOnClickListener {
                var intent = Intent(it.context, DisplayNoteActivity::class.java)
                MainActivity.editor.putString("Note", noteEditText.text.toString())
                MainActivity.editor.commit()
                it.context.startActivity(intent)
            }

        }


    }


    override fun getItemCount(): Int {
        return notesList.size

    }

    inner class MyCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val noteEditText= itemView.findViewById<TextView>(R.id.notes_textview)
    }
}