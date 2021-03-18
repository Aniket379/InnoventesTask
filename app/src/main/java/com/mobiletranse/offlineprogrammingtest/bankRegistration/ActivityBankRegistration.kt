package com.mobiletranse.offlineprogrammingtest.bankRegistration

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mobiletranse.offlineprogrammingtest.R
import com.mobiletranse.offlineprogrammingtest.databinding.ActivityBankRegistrationBinding
import java.text.SimpleDateFormat
import java.util.*


class ActivityBankRegistration : AppCompatActivity(),IBankRegistration {
    lateinit var mBankRegistrationViewModel: BankRegistrationViewModel
    lateinit var mActivityBankRegistrationBinding: ActivityBankRegistrationBinding
    var calender = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setUI()
    }

     fun setUI() {
         mBankRegistrationViewModel= ViewModelProvider.AndroidViewModelFactory(application).create(BankRegistrationViewModel::class.java)
         mBankRegistrationViewModel.iBankRegistration=this
         mActivityBankRegistrationBinding= DataBindingUtil.setContentView(this, R.layout.activity_bank_registration)
         mActivityBankRegistrationBinding.viewModel=mBankRegistrationViewModel

    }

    override fun onClickNoPan() {
        finish()
    }

    override fun datePicker() {
        var date=mActivityBankRegistrationBinding.editDay
        var dateListener = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calender.set(Calendar.YEAR, year)
            calender.set(Calendar.MONTH, monthOfYear)
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            date.text= sdf.format(calender.time)

        }

        val datePickerDialog = DatePickerDialog(this, dateListener, calender
                .get(Calendar.YEAR), calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

}