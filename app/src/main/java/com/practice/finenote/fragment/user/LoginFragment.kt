package com.practice.finenote.fragment.user

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.databinding.FragmentLoginBinding
import com.practice.finenote.fragment.BaseFragment
import com.practice.finenote.modals.UserRequest
import com.practice.finenote.utils.TokenManager
import com.practice.finenote.viewModal.UserViewModal
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
 class LoginFragment : BaseFragment() {
    @Inject
    lateinit var tokenManager: TokenManager
    private lateinit var binding: FragmentLoginBinding
    private val authenticationViewModal by viewModels<UserViewModal>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (tokenManager.getToken()==""){

        }else
        {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()

    }
    private fun observeApiData(view: View) {
        authenticationViewModal.userResponseLiveData.observe(viewLifecycleOwner) { it ->
            dissmissDialogue()
            when (it) {
                is ErrorHandling.Success -> {
                    tokenManager.saveToken(it.data.token)
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
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
        binding.changeToLoginToHomeScreen.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
        binding.signinBtn.setOnClickListener { view->
            try {
                binding.signinBtn.setOnClickListener {
                    val email = binding.signinEmailAddress.text.toString()
                    val password = binding.signinPassword.text.toString()

                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        binding.signinEmailAddress.error = "Email address is not valid"
                        binding.signinEmailAddress.requestFocus()
                        return@setOnClickListener

                    }
                    if (TextUtils.isEmpty(email))
                    {
                        binding.signinEmailAddress.error = "Email address is Empty"
                        binding.signinEmailAddress.requestFocus()
                        return@setOnClickListener

                    }
                    if (TextUtils.isEmpty(password))
                    {
                        binding.signinEmailAddress.error = "Password is Empty"
                        binding.signinEmailAddress.requestFocus()
                        return@setOnClickListener

                    }
                    if (password.length < 6)
                    {
                        binding.signinPassword.error = "Password length is small then 6"
                        binding.signinPassword.requestFocus()
                        return@setOnClickListener
                    }
                    authenticationViewModal.loginUser(UserRequest(email ,password,""))
                    observeApiData(view)
                }

            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

}