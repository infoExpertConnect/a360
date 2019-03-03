package com.pawanjes.numbers.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pawanjes.numbers.R
import kotlinx.android.synthetic.main.activity_otp.*

class OTPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        phone_number_button.setOnClickListener { v ->

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
