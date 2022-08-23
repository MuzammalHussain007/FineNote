import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.practice.finenote.R
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.api.NoteAPI
import com.practice.finenote.databinding.FragmentHomeBinding
import com.practice.finenote.fragment.BaseFragment
import com.practice.finenote.responses.getNote.GetNoteResponse
import com.practice.finenote.viewModal.NoteViewModal
import com.practice.finenote.viewModal.UserViewModal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

 class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val noteViewModal by viewModels<NoteViewModal>()
    private lateinit var noteList: ArrayList<GetNoteResponse>
    @Inject
    lateinit var  noteAPI : NoteAPI


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        innit()
//        noteViewModal.noteResponseLiveData.observe(viewLifecycleOwner){
//            when(it){
//                is ErrorHandling.Success->{
//
//                }
//                is ErrorHandling.Error->{
//                    showSnackBar(view,it.error.toString())
//                }
//                is ErrorHandling.Loading->{
//                    showDialogue()
//                }
//            }
//        }
    }

    private fun innit() {
        binding.noteRecyclarView.layoutManager = StaggeredGridLayoutManager(2, 1)
        noteList = ArrayList()
//        CoroutineScope(Dispatchers.IO).launch{
//         val response =    noteAPI.getNote()
//            Log.d("abcd______",""+response.body().toString())
//        }

    }


}