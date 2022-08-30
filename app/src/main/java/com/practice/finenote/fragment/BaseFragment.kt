package com.practice.finenote.fragment

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.practice.finenote.R
import com.practice.finenote.activities.BaseActivity

open class BaseFragment : Fragment() {
    lateinit var baseActivity: BaseActivity
private lateinit var progressDialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity
        innitProgressDialog()
        CreateMessage("Please Wait")
    }
    fun innitProgressDialog() {
        progressDialog = ProgressDialog(baseActivity)
    }

    fun CreateMessage(message: String) {
        progressDialog.setTitle("Dialogue")
        progressDialog.setMessage(message)
    }

    fun showDialogue() {
        progressDialog.show()
    }

    fun dissmissDialogue() {
        progressDialog.dismiss()
    }

    fun showSnackBar(view :View  ,message : String){
        val snack = Snackbar.make(view,message, Snackbar.LENGTH_LONG)
        snack.show()
    }

}