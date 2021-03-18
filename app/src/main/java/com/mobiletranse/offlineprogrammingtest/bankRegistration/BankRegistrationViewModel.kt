package com.mobiletranse.offlineprogrammingtest.bankRegistration

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern


public class BankRegistrationViewModel(application: Application): AndroidViewModel(application) {
    public var mStrPanNumber:String=""
    public var mStrBirthDay:String=""
    public var mStrBirthMonth:String=""
    public var mStrBirthYear:String=""
    lateinit var iBankRegistration: IBankRegistration
    val pan_pattern = "(([A-Z]{5})([0-9]{4})([A-Z]))"
    val r = Pattern.compile(pan_pattern)


    fun onClickNext(view: View){
             if (checkValidation()) {
                 Toast.makeText(getApplication(), "Details submitted successfully.", Toast.LENGTH_SHORT).show()
             }
    }


    fun onClickDate(view: View){
           iBankRegistration.datePicker()
    }

     fun onClickNoPan(view: View){
        Toast.makeText(getApplication(), "I don't have pan.", Toast.LENGTH_SHORT).show()
        iBankRegistration.onClickNoPan()
    }

    fun checkValidation():Boolean {
        if (!regex_matcher(r, mStrPanNumber)) {
            Toast.makeText(getApplication(), "Please enter valid pan number.", Toast.LENGTH_SHORT).show()
            return false
        }else if (mStrBirthDay.equals("")){
            Toast.makeText(getApplication(), "Please enter birthday.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun regex_matcher(pattern: Pattern, string: String): Boolean {
        val m: Matcher = pattern.matcher(string)
        return m.find() && m.group(0) != null
    }

}