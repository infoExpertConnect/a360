package com.pawanjes.numbers.view.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.pawanjes.numbers.R
import com.pawanjes.numbers.service.model.Error
import com.pawanjes.numbers.service.model.RetrofitResponse
import com.pawanjes.numbers.service.util.ErrorHandler


abstract class BaseActivity : AppCompatActivity() {
    protected var dialog: ProgressDialog? = null
    protected var toolbarTitle: TextView? = null
    protected var subtitle: TextView? = null
    protected var imageView: ImageView? = null
    protected var followedBy: CheckBox? = null
    protected var mFirebaseAnalytics: FirebaseAnalytics? = null
    protected var toolbar: Toolbar? = null




    abstract fun isHomeAsUpEnabled(): Boolean

    abstract fun isToolbarRequired(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //Appsee.start();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        //UXCam.startWithKey("a2f89ea558274c7")
        if (isToolbarRequired()) {
            toolbar = findViewById(R.id.toolbar)
            if (toolbar != null) {
                setSupportActionBar(toolbar)
                if (supportActionBar != null) {
                    supportActionBar!!.setDisplayHomeAsUpEnabled(isHomeAsUpEnabled())
                    toolbar!!.setNavigationOnClickListener { view -> onBackPressed() }
                    toolbarTitle = toolbar!!.findViewById(R.id.title)
                    toolbarTitle!!.text = toolbar!!.title
//                    subtitle = toolbar!!.findViewById(R.id.subtitle)
                    subtitle!!.text = toolbar!!.subtitle
//                    imageView = toolbar!!.findViewById(R.id.user_image)
//                    followedBy = toolbar!!.findViewById(R.id.followed_by)
                    supportActionBar!!.setDisplayShowTitleEnabled(false)
                }
            }
        }
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showProgressDialog(activity: Activity) {
        try {
            if (dialog != null && dialog!!.isShowing) {
                dialog!!.dismiss()
                dialog = null
            }
            if (dialog == null) {
                dialog = ProgressDialog(activity)
            }

            dialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dialog!!.setMessage(activity.getString(R.string.loading))
            dialog!!.isIndeterminate = true
            dialog!!.setCanceledOnTouchOutside(false)
            dialog!!.show()
            if (!activity.isFinishing) dialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun cancelProgressDialog() {
        if (dialog != null) {
            dialog!!.cancel()
            dialog = null
        }
    }

    abstract override fun onBackPressed()

    //    public void logScreen(String screenName) {
    //        mFirebaseAnalytics.setCurrentScreen(this, screenName, null);
    //    }


    //    public void logEvent(String key, Bundle value) {
    //        mFirebaseAnalytics.logEvent(key, value);
    //    }


    fun showToastMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    open fun showNoNetWorkConnectionBar(finishActivity: Boolean) {
        val error = Error()
        error.errorMsg = getString(R.string.no_internet_connection)
        val retrofitResponse = RetrofitResponse<Any>()
        retrofitResponse.error = error
        ErrorHandler.showErrorDialog(retrofitResponse, this, DialogInterface.OnClickListener { dialogInterface, i ->
            if (finishActivity) {
                finish()
            }
        })
    }

    fun makeFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
