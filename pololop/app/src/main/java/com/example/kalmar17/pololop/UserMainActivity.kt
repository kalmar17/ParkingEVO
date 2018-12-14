package com.example.kalmar17.pololop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_main.*
import MyFragment



class UserMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)
        ScanButton.setOnClickListener {
            startActivity(Intent(this,ScanCodeActivity::class.java ))
        }
        buttonMap.setOnClickListener(){
            startActivity(Intent(this,MapActivity::class.java))
        }
        val manager = supportFragmentManager

        val fragmentTransaction = manager.beginTransaction()

        // добавляем фрагмент
        val myFragment = MyFragment()
        fragmentTransaction.add(R.id.containerView, myFragment)
        val commit = fragmentTransaction.commit()


    }

}


