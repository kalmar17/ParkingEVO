package com.example.kalmar17.pololop

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.telephony.TelephonyManager
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.ContextCompat.getSystemService
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

    }

    fun onMyButtonClick(view: View) {
        // выводим сообщение
        attemptLogin()
    }
    fun RegistButton(view: View){
        val regIntent = Intent(this,RegistrationActivity::class.java)
        startActivity(regIntent)
    }
     fun attemptLogin(){

        // Reset errors.
        phoneNumber.error = null
        password.error = null

        // Store values at the time of the login attempt.
        val phoneStr = phoneNumber.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if(!isPasswordValid(passwordStr)) {
            password.error = getString(R.string.error_invalid_password)
            focusView = password
            cancel = true
        }
        if (TextUtils.isEmpty(passwordStr)||!Validator(passwordStr,PASSWORD_PATTERN)) {
            password.error = getString(R.string.error_field_required)
            focusView = password
            cancel = true
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(phoneStr)) {
            phoneNumber.error = getString(R.string.error_field_required)
            focusView = phoneNumber
            cancel = true
        } else if (!isPhoneValid(phoneStr)) {
            phoneNumber.error = getString(R.string.error_field_required)
            focusView = phoneNumber
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
//            mAuthTask = UserLoginTask(phoneStr, passwordStr)
//            mAuthTask!!.execute(null as Void?)
            startActivity(Intent(this,UserMainActivity::class.java ))
        }
    }

    private val PASSWORD_PATTERN =
        "^[A-Za-z0-9]+$"

    fun Validator(hex: String , namePattern: String): Boolean  {
        val pattern = Pattern.compile(namePattern)
        val matcher = pattern.matcher(hex)
        return matcher.matches()
    }

    private fun isPhoneValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.length >= 10
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 8
    }
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

//            login_form.visibility = if (show) View.GONE else View.VISIBLE
//            login_form.animate()
//                .setDuration(shortAnimTime)
//                .alpha((if (show) 0 else 1).toFloat())
//                .setListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationEnd(animation: Animator) {
//                        login_form.visibility = if (show) View.GONE else View.VISIBLE
//                    }
//                })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_progress.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            //login_form.visibility = if (show) View.GONE else View.VISIBLE

        }
    }
}
