package com.practice.finenote.fragment.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.practice.finenote.R
import com.practice.finenote.SignUpFragmentDirections
import com.practice.finenote.databinding.FragmentLoginBinding
import com.practice.finenote.databinding.FragmentSignUpBinding
import com.practice.finenote.fragment.BaseFragment


class SignUpFragment : BaseFragment() {
    private lateinit var binding: FragmentSignUpBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        innit(view)

    }

    private fun innit(view: View) {
      binding = FragmentSignUpBinding.bind(view)
        binding.changeToLogin.setOnClickListener {
          findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }

    }


}