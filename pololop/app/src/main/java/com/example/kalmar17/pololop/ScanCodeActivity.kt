package com.example.kalmar17.pololop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_user_main.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanCodeActivity : AppCompatActivity() , ZXingScannerView.ResultHandler {




    var ScannerView : ZXingScannerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ScannerView = ZXingScannerView(this)
        setContentView(ScannerView)
    }

    override fun handleResult(p0: Result?) {
        textViewNameUser.text = p0!!.text
//        if (p0 != null) {
//            Toast.makeText(this@ScanCodeActivity, p0.text, Toast.LENGTH_SHORT).show()
//        }else{
//            Toast.makeText(this@ScanCodeActivity, "Fail", Toast.LENGTH_SHORT).show()
//        }
        onBackPressed()
    }
    override fun onPause() {
        super.onPause()
        ScannerView!!.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        ScannerView!!.setResultHandler { this }
        ScannerView!!.startCamera()
    }
}
