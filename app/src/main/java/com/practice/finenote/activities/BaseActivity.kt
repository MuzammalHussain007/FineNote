package com.practice.finenote.activities

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.practice.finenote.R

open class BaseActivity : AppCompatActivity() {
    private lateinit var progressDialog: ProgressDialog;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        innitProgressDialog()
    }

    fun innitProgressDialog() {
        progressDialog = ProgressDialog(this)
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
        val snack = Snackbar.make(view,message,Snackbar.LENGTH_LONG)
        snack.show()
    }

}