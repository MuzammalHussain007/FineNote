package com.practice.finenote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.finenote.databinding.CustomNoteLayoutBinding
import com.practice.finenote.responses.getNote.GetNoteResponse
import com.practice.finenote.responses.getNote.Note
import com.practice.finenote.responses.noteResponse.NoteResponse
import com.practice.finenote.responses.noteResponse.NoteResponseForAdapter


class NoteAdapter(private  val list: ArrayList<Note>, private val onNoteClicked: (NoteResponseForAdapter) -> Unit) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CustomNoteLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CustomNoteLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      with(holder){
          with(list[position]){
              binding.customNoteTitle.text = this.title
              binding.customNoteDescription.text = this.description
              holder.itemView.setOnClickListener{
                  onNoteClicked(NoteResponseForAdapter(this.title,this.description,this._id))
              }
          }
      }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}