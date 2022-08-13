package com.practice.finenote.fragment.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.practice.finenote.databinding.FragmentLoginBinding
import com.practice.finenote.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint

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
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
        binding.signupBtn.setOnClickListener {
            CreateMessage("Please Wait")
        }
    }

}