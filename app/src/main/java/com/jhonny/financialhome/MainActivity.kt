package com.jhonny.financialhome

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jhonny.financialhome.databinding.ActivityMainBinding
import com.jhonny.financialhome.model.Custo

import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var dateTime :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        setSupportActionBar(binding.toolbar);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.add,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                R.id.add_action -> saveCusto()
                else ->{
                    return super.onOptionsItemSelected(item)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun saveCusto(){
        var custo = Custo(binding.custoET.text.toString().toDouble()
                ,binding.obsET.text.toString()
                ,binding.localET.text.toString()
                ,dateTime)
        val dataBaseRef = FirebaseDatabase.getInstance().reference
        dataBaseRef.child("Jonathan").child(dateTime).setValue(custo)
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
