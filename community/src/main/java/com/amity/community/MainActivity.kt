package com.amity.community

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amity.socialcloud.sdk.AmityCoreClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btnLogin.setOnClickListener {
            if (etUserId.text.isNotEmpty() && etUserName.text.isNotEmpty()) {
                registerDevice(etUserId.text.toString().trim(), etUserName.text.toString().trim())
            } else {
                Toast.makeText(this, "Enter userId and Display Name", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun registerDevice(userId: String, userName: String) {
        AmityCoreClient.registerDevice(userId)
                .displayName(userName)
                .build()
                .submit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    navigateToFeatureList()
                }
                .doOnError {
                    Toast.makeText(this, "Could not register user " + it.message, Toast.LENGTH_LONG)
                            .show()
                }
                .subscribe()
    }

    private fun navigateToFeatureList() {
        val intent = Intent(this, FeatureListActivity::class.java)
        startActivity(intent)
    }

}