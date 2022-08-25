package com.practice.finenote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.finenote.databinding.CustomNoteLayoutBinding
import com.practice.finenote.responses.getNote.GetNoteResponse
import com.practice.finenote.responses.getNote.Note
import com.practice.finenote.responses.noteResponse.NoteResponse


class NoteAdapter(private  val list: ArrayList<Note>, private val onNoteClicked: (NoteResponse) -> Unit) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

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
                  onNoteClicked(NoteResponse(this.title,this.description))
              }
          }
      }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}