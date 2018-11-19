package com.test.testclientslist.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.testclientslist.R
import com.test.testclientslist.utils.LocalStorage

class LauncherActivity : AppCompatActivity() {

    private lateinit var local: LocalStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        local = LocalStorage(this)

        val logoTimer = object : Thread() {
            override fun run() {
                var logoTimer = 0
                while (logoTimer < 3000) {
                    Thread.sleep(100)
                    logoTimer += 100
                }

                if (local.token != ""){
                    openMain()
                }else{
                    openLogin()
                }

            }
        }

        logoTimer.start()
    }


    fun openMain(){
        val intent = Intent(this, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun openLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
