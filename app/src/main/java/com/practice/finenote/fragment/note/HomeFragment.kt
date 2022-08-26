package com.practice.finenote.fragment.note

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.practice.finenote.R
import com.practice.finenote.adapter.NoteAdapter
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.databinding.FragmentHomeBinding
import com.practice.finenote.fragment.BaseFragment
import com.practice.finenote.responses.getNote.Note
import com.practice.finenote.responses.noteResponse.NoteResponse
import com.practice.finenote.viewModal.NoteViewModal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var noteList: ArrayList<Note>;

    private val noteViewModal by viewModels<NoteViewModal>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        innit()
        return binding.root
    }

    private fun innit() {
        noteList = ArrayList()
        binding.noteRecyclarView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.deleteNote).isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModal.getNotesList()
        handleUI(view)
        handleClicklistener()
    }

    private fun handleClicklistener() {
        binding.noteaddBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNoteFragment())
        }
    }

    private fun handleUI(view: View) {
        noteViewModal.noteLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ErrorHandling.Loading -> {
                    showDialogue()
                }
                is ErrorHandling.Error -> {
                    dissmissDialogue()
                    showSnackBar(view, "" + it.error)
                    binding.errorMessage.visibility = View.VISIBLE
                }
                is ErrorHandling.Success -> {
                    dissmissDialogue()
                    noteList.addAll(it.data)
                    setUpRecyclarVeiw()
                }
            }
        }
    }

    private fun onNoteClicked(noteResponse: NoteResponse) {
        val bundle = Bundle()
        bundle.putString("note",Gson().toJson(noteResponse))
        findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment,bundle)
        Log.d("Note Title-->", "${noteResponse.title}")
        Log.d("Note Description-->", "${noteResponse.description}")
    }

    private fun setUpRecyclarVeiw() {
        if (noteList.isNotEmpty()) {
            var adapter = NoteAdapter(noteList, ::onNoteClicked)
            binding.noteRecyclarView.adapter = adapter
        } else {
            binding.errorMessage.visibility = View.VISIBLE
        }
    }


}