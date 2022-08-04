import LoginFragmentDirections.LoginFragmentDirections
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.practice.finenote.R
import com.practice.finenote.activities.BaseActivity
import com.practice.finenote.databinding.FragmentLoginBinding
import com.practice.finenote.fragment.BaseFragment

class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        listener()
        return binding.root;
    }

    private fun listener() {
        binding.changeToRegister.setOnClickListener {
         findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
        binding.signupBtn.setOnClickListener {
            CreateMessage("Please Wait")
        }
    }

}