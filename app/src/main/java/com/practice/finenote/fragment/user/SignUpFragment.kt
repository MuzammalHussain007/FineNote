package com.practice.finenote.fragment.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.practice.finenote.databinding.FragmentLoginBinding
import com.practice.finenote.databinding.FragmentSignUpBinding
import com.practice.finenote.fragment.BaseFragment
import com.practice.finenote.modals.UserRequest
import com.practice.finenote.viewModal.UserViewModal
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
 class SignUpFragment : BaseFragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val authenticationViewModal by viewModels<UserViewModal>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        listener()
         return binding.root;
    }

    private fun listener() {
        binding.signupBtn.setOnClickListener {
            authenticationViewModal.registerUser(UserRequest("abcded@yahoo.com","1212121212","test"))
        }
    }


}
