package com.test.testclientslist.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.testclientslist.R
import com.test.testclientslist.databinding.ActivityLoginBinding
import com.test.testclientslist.utils.LZString
import com.test.testclientslist.utils.LocalStorage
import com.test.testclientslist.viewModel.LoginViewModel

/**
 * By: Oswaldo De la Ossa T.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    lateinit var binding: ActivityLoginBinding
    private lateinit var local: LocalStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding.login = viewModel
        binding.executePendingBindings()

        local = LocalStorage(this)

        viewModel.navigateToList.observe(this, Observer {
            it?.getContentIfNotHandled()?.let {
                local.setToken(LZString.compress(it))
                val intent: Intent = Intent(this, MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        })
    }
}
