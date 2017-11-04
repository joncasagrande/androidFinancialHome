package com.jhonny.financialhome

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jhonny.financialhome.databinding.ActivityMainBinding

import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var dateTime :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
    }

    fun showDataDialog(view: View){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            dateTime.plus(dayOfMonth)
                .plus("/")
                .plus(monthOfYear)
                .plus("/")
                .plus(year)
            binding.dateBT.text = dateTime
            showTimePicker(view)
        }, year, month, day)
        dpd.show()
    }
    fun showTimePicker(view: View){
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener{
            view,hour,minute ->
            dateTime.plus(" ")
                    .plus(hour)
                    .plus(":")
                    .plus(minute)
            binding.dateBT.text = dateTime
        },hour,minute,false)
    }
}
