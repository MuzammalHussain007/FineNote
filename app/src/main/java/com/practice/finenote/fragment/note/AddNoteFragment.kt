package com.practice.finenote.fragment.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.practice.finenote.R
import com.practice.finenote.activities.MainActivity
import com.practice.finenote.databinding.FragmentAddNoteBinding
import com.practice.finenote.fragment.BaseFragment

class AddNoteFragment : BaseFragment() {
     private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        getNote()
        return binding.root
    }

    private fun getNote() {
        val jsonNote = arguments!!.get("note").toString()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as MainActivity
        activity.supportActionBar?.title =""
    }


}