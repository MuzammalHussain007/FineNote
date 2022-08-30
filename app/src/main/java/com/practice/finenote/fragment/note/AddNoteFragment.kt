package com.practice.finenote.fragment.note

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.practice.finenote.R
import com.practice.finenote.activities.MainActivity
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.databinding.FragmentAddNoteBinding
import com.practice.finenote.fragment.BaseFragment
import com.practice.finenote.responses.noteResponse.NoteResponse
import com.practice.finenote.responses.noteResponse.NoteResponseForAdapter
import com.practice.finenote.utils.TokenManager
import com.practice.finenote.viewModal.NoteViewModal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteFragment : BaseFragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private val noteViewModal by viewModels<NoteViewModal>()
    @Inject
    lateinit var tokenManager: TokenManager
    private lateinit var noteid: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        getNote()
        return binding.root
    }


    private fun getNote() {
        val jsonNote = arguments!!.get("note").toString()
        if (jsonNote != null) {
            val note = Gson().fromJson(jsonNote, NoteResponseForAdapter::class.java)
            if (note != null) {
                noteid = note.id
                binding.noteTitle.setText(note.title)
                binding.noteDescription.setText(note.description)
                val activity = activity as MainActivity
                activity.supportActionBar?.title = "Edit Note"
                binding.addNoteBtn.text = "Update Note"
            }
        } else {
            val activity = activity as MainActivity
            activity.supportActionBar?.title = "Add Note"
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val jsonNote = arguments!!.get("note").toString()
        if (jsonNote != null) {
            val note = Gson().fromJson(jsonNote, NoteResponseForAdapter::class.java)
            if (note != null) {
                menu.findItem(R.id.deleteNote).isVisible = true
            } else {
                menu.findItem(R.id.deleteNote).isVisible = false

            }
        }

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNoteBtn.setOnClickListener {
            val jsonNote = arguments!!.get("note").toString()
            if (jsonNote != null) {
                val note = Gson().fromJson(jsonNote, NoteResponseForAdapter::class.java)
                if (note != null) {

                    Log.d("updated____",""+noteid)
                    handleValidationForUpdate()
                    handleUI(view)

                } else {
                    handleValidation()
                    handleUI(view)
                }
            }

        }

    }

    private fun handleValidationForUpdate() {
        if (TextUtils.isEmpty(binding.noteTitle.text.toString())) {
            binding.noteTitle.error = "Enter some title"
            binding.noteTitle.requestFocus()
            return
        }
        if (TextUtils.isEmpty(binding.noteDescription.text.toString())) {
            binding.noteTitle.error = "Enter some Description"
            binding.noteTitle.requestFocus()
            return
        }
        handleApiCallUpdate()
    }

    private fun handleApiCallUpdate() {
        val noteTitle = binding.noteTitle.text.toString()
        val noteDescription = binding.noteDescription.text.toString()
        val noteRequest = NoteResponse(noteTitle, noteDescription)
        noteViewModal.updateNote(noteid, noteRequest)
    }

    private fun handleUI(view: View) {
        noteViewModal.noteSuccessStatus.observe(viewLifecycleOwner) {
            showSnackBar(view, it)
            findNavController().navigate(AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment())
        }
    }

    private fun handleValidation() {
        if (TextUtils.isEmpty(binding.noteTitle.text.toString())) {
            binding.noteTitle.error = "Enter some title"
            binding.noteTitle.requestFocus()
            return
        }
        if (TextUtils.isEmpty(binding.noteDescription.text.toString())) {
            binding.noteTitle.error = "Enter some Description"
            binding.noteTitle.requestFocus()
            return
        }
        handleApiCall()
    }

    private fun handleApiCall() {
        val noteTitle = binding.noteTitle.text.toString()
        val noteDescription = binding.noteDescription.text.toString()
        val noteRequest = NoteResponse(noteTitle, noteDescription)
        noteViewModal.addNote(noteRequest)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                findNavController().navigate(AddNoteFragmentDirections.actionGlobalLoginFragment())
                tokenManager.destoryAll()
            }
            R.id.deleteNote->{
                try {
                     noteViewModal.deleteNote(noteid)
                    noteViewModal.noteSuccessStatus.observe(viewLifecycleOwner) {
                        findNavController().navigate(AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment())
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}