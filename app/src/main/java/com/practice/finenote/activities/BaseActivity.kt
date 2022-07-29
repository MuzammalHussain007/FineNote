package com.practice.finenote.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practice.finenote.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}