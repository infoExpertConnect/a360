package com.pawanjes.numbers.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.pawanjes.numbers.R
import com.pawanjes.numbers.service.util.KeyboardUtil
import kotlinx.android.synthetic.main.activity_phone_numer.*
import java.util.concurrent.TimeUnit

class PhoneNumerScreen : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    val OTP_CODE = "OTP_CODE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_numer)
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
        phone_number_button.setOnClickListener { v ->
            validatePhoneNumber()
        }
        skip.setOnClickListener { v ->
            val intent = Intent(this@PhoneNumerScreen, MainActivity::class.java)
            startActivity(intent)
        }
    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(cred: PhoneAuthCredential?) {
            if (cred != null && cred.smsCode != null) {
                val intent = Intent(this@PhoneNumerScreen, OTPActivity::class.java)
                intent.putExtra(OTP_CODE, cred.smsCode)
                startActivity(intent)
            }
        }

        override fun onVerificationFailed(exception: FirebaseException?) {
            if (exception != null) {
                Toast.makeText(this@PhoneNumerScreen, exception.message, Toast.LENGTH_LONG).show()
            }
        }


        override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
            super.onCodeSent(p0, p1)
        }
    }

    private fun validatePhoneNumber() {
        if (phone_number_edtTxt.text.length < 10 || !TextUtils.isDigitsOnly(phone_number_edtTxt.text.toString())) {
            phone_number_edtTxt.error = "Please Enter Valid Phone Number"
        } else {
            submitNumberAndGetOTP();
        }

    }

    private fun submitNumberAndGetOTP() {
        KeyboardUtil.hideKeyboard(this)
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91" + phone_number_edtTxt.text.toString(),      // Phone number to verify
            60,               // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this,             // Activity (for callback binding)
            callbacks
        )
    }
}
