package com.practice.finenote.fragment.user

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.databinding.FragmentLoginBinding
import com.practice.finenote.databinding.FragmentSignUpBinding
import com.practice.finenote.fragment.BaseFragment
import com.practice.finenote.modals.UserRequest
import com.practice.finenote.utils.TokenManager
import com.practice.finenote.viewModal.UserViewModal
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
 class SignUpFragment : BaseFragment() {
    private lateinit var binding: FragmentSignUpBinding
    @Inject
    lateinit var tokenManager: TokenManager
    private val authenticationViewModal by viewModels<UserViewModal>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeApiData(view)
        listener()
    }

    private fun observeApiData(view: View) {
        authenticationViewModal.userResponseLiveData.observe(viewLifecycleOwner) { it ->
            dissmissDialogue()
            when (it) {
                is ErrorHandling.Success -> {

                    tokenManager.saveToken(it.data.token)
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
                }
                is ErrorHandling.Error -> {
                    showSnackBar(view, it.toString())
                }
                is ErrorHandling.Loading -> {
                    showDialogue()
                }
            }

        }
    }

    private fun listener() {
        try {
            binding.signupBtn.setOnClickListener {
                val username = binding.username.text.toString()
                val email = binding.signupEmailAddress.text.toString()
                val password = binding.signupPassword.text.toString()
                if (TextUtils.isEmpty(username))
                {
                    binding.username.error = "User Name is Empty"
                    binding.username.requestFocus()
                    return@setOnClickListener

                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.signupEmailAddress.error = "Email address is not valid"
                    binding.signupEmailAddress.requestFocus()
                    return@setOnClickListener

                }
                if (TextUtils.isEmpty(email))
                {
                    binding.signupEmailAddress.error = "Email address is Empty"
                    binding.signupEmailAddress.requestFocus()
                    return@setOnClickListener

                }
                if (TextUtils.isEmpty(password))
                {
                    binding.signupEmailAddress.error = "Password is Empty"
                    binding.signupPassword.requestFocus()
                    return@setOnClickListener

                }
                if (password.length < 6)
                {
                    binding.signupPassword.error = "Password length is small then 6"
                    binding.signupPassword.requestFocus()
                    return@setOnClickListener
                }
                authenticationViewModal.registerUser(UserRequest(email,password,username))
            }

        }catch (e : Exception){
            e.printStackTrace()
        }
    }


}
