package com.example.myloginapplication.app

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.myloginapplication.MainActivity
import java.util.regex.Pattern


object util {
    fun isValidEmail(target: CharSequence?): Boolean {
        return if(target==null || target.length==0){
            false
        }else{
            EMAIL_ADDRESS_PATTERN.matcher(target).matches()
        }
    }

    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
    "\\@" +
    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
    "(" +
    "\\." +
    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
    ")+"
    )
    val PASSWORD_PATTERN = Pattern.compile(
"(/^(?=.*\\d)(?=.*[A-Z])$/)"    )

    @SuppressLint("UseRequireInsteadOfGet")
     fun MainActivity.showToast(s: String) {
        Toast.makeText(
            this,
            s,
            Toast.LENGTH_SHORT
        ).show()
    }

   /* fun isValidPassword(value:String):Boolean{
        return if(value==null || value.length==0){
            false
        }else if(value.length<6){
            false
        }else{
            PASSWORD_PATTERN.matcher(value).matches()
        }
    }*/

    fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }
}