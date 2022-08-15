package com.practice.finenote.fragment.user

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.practice.finenote.databinding.FragmentLoginBinding
import com.practice.finenote.fragment.BaseFragment
import com.practice.finenote.modals.UserRequest
import com.practice.finenote.viewModal.UserViewModal
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint

 class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginBinding
    private val authenticationViewModal by viewModels<UserViewModal>()
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
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
        binding.signinBtn.setOnClickListener {
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
                }

            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

}