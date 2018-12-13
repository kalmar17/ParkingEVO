package com.example.kalmar17.pololop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_add_car.*


class AddCarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)
        buttonEndReg!!.setOnClickListener(){
            onClickButtonEndReg()
        }
    }

    private fun onClickButtonEndReg(){
        editNumberCar.error = null
        editBrand.error = null
        editColorCar.error = null
        editNameCar.error = null
        val NumberCarStr = editNumberCar.text.toString()
        val BrandStr = editBrand.text.toString()
        val ColorCarStr = editColorCar.text.toString()
        val NameCarStr = editNameCar.text.toString()


        var cancel = false
        var focusView : View? = null

        if (TextUtils.isEmpty(BrandStr)) {
            editBrand.error = getString(R.string.error_field_required)
            focusView = editBrand
            cancel = true
        }
        if (TextUtils.isEmpty(NameCarStr)) {
            editNameCar.error = getString(R.string.error_field_required)
            focusView = editNameCar
            cancel = true
        }
        if (TextUtils.isEmpty(ColorCarStr)) {
            editColorCar.error = getString(R.string.error_field_required)
            focusView = editColorCar
            cancel = true
        }
        if (TextUtils.isEmpty(NumberCarStr)) {
            editNumberCar.error = getString(R.string.error_field_required)
            focusView = editNumberCar
            cancel = true
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {

            //занос в базу данных первую машину
            val pictureDialog = AlertDialog.Builder(this)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItems = arrayOf("Добавить еще одну машину", "Завершить регистрацию")
            pictureDialog.setItems(pictureDialogItems
            ) { dialog, which ->
                when (which) {
                    0 -> AddCarFun()
                    1 -> EndRegist()                }
            }
            pictureDialog.show()
        }


    }

    private fun AddCarFun() {
        editNameCar.text = null
        editColorCar.text = null
        editBrand.text = null
        editNumberCar.text = null
    }
    private fun EndRegist(){
            startActivity(Intent(this,UserMainActivity::class.java ))
    }
}
