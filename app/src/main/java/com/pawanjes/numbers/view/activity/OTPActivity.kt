package com.pawanjes.numbers.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import com.pawanjes.numbers.R
import com.pawanjes.numbers.service.util.KeyboardUtil
import kotlinx.android.synthetic.main.activity_otp.*

class OTPActivity : AppCompatActivity(), TextWatcher {

    var code = ""
    var enteredCode = ""
    val OTP_CODE = "OTP_CODE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        Edt_otp1.addTextChangedListener(this)
        Edt_otp2.addTextChangedListener(this)
        Edt_otp3.addTextChangedListener(this)
        Edt_otp4.addTextChangedListener(this)
        Edt_otp5.addTextChangedListener(this)
        Edt_otp6.addTextChangedListener(this)
        val intent = intent
        if (intent != null) {
            if (intent.getStringExtra(OTP_CODE) != null) {
                code = intent.getStringExtra(OTP_CODE)
            }
        }
        phone_number_button.setOnClickListener { v ->
            VerifyEnteredOtp()
        }
    }

    private fun VerifyEnteredOtp() {

        var enteredCodee = get6Fields()
        if (enteredCodee != "") {
            KeyboardUtil.hideKeyboard(this)
            if (!TextUtils.isEmpty(code)) {
                if (code.equals(enteredCodee)) {
                    //Go to main activity
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    enteredCode = ""
                    Toast.makeText(this, "Enter Valid OTP", Toast.LENGTH_LONG).show()
                }
            } else {
                enteredCode = ""
                Toast.makeText(this, "Enter Valid OTP", Toast.LENGTH_LONG).show()
            }
        } else {
            enteredCode = ""
            Toast.makeText(this, "Enter Valid OTP", Toast.LENGTH_LONG).show()
        }
        /*if correct otp entered
        check if already registered user bby hitting api,
        if yes --->
                fetch user details and start mainactivity else request reentering of otp
            else
            ----> Open user details screen for getting basic details
        */

    }

    private fun get6Fields(): String {
        when (validate(
            Edt_otp1.text.toString(),
            Edt_otp1.text.toString(),
            Edt_otp1.text.toString(),
            Edt_otp1.text.toString(),
            Edt_otp1.text.toString(),
            Edt_otp1.text.toString()
        )) {
            1 -> {
                Edt_otp1.error = ""
                enteredCode = ""
                return ""
            }
            2 -> {
                Edt_otp2.error = ""
                enteredCode = ""
                return ""
            }
            3 -> {
                Edt_otp3.error = ""
                enteredCode = ""
                return ""
            }
            4 -> {
                Edt_otp4.error = ""
                enteredCode = ""
                return ""
            }
            5 -> {
                Edt_otp5.error = ""
                enteredCode = ""
                return ""
            }
            6 -> {
                Edt_otp6.error = ""
                enteredCode = ""
                return ""
            }
        }
        enteredCode += Edt_otp1.text.toString()
        enteredCode += Edt_otp2.text.toString()
        enteredCode += Edt_otp3.text.toString()
        enteredCode += Edt_otp4.text.toString()
        enteredCode += Edt_otp5.text.toString()
        enteredCode += Edt_otp6.text.toString()
        return enteredCode
    }

    fun validate(edt1: String, edt2: String, edt3: String, edt4: String, edt5: String, edt6: String): Int {
        if (edt1.isEmpty()) {
            return 1
        }
        if (edt2.isEmpty()) {
            return 2
        }
        if (edt3.isEmpty()) {
            return 3
        }
        if (edt4.isEmpty()) {
            return 4
        }
        if (edt5.isEmpty()) {
            return 5
        }
        return if (edt6.isEmpty()) {
            6
        } else 0
    }

    override fun afterTextChanged(s: Editable?) {
        if (Edt_otp1.hasFocus() && Edt_otp1.text.length == 1) {
            Edt_otp2.requestFocus()
        } else if (Edt_otp2.hasFocus() && Edt_otp2.text.length == 1) {
            Edt_otp3.requestFocus()
        } else if (Edt_otp3.hasFocus() && Edt_otp3.text.length == 1) {
            Edt_otp4.requestFocus()
        } else if (Edt_otp4.hasFocus() && Edt_otp4.text.length == 1) {
            Edt_otp5.requestFocus()
        } else if (Edt_otp5.hasFocus() && Edt_otp5.text.length == 1) {
            Edt_otp6.requestFocus()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}
