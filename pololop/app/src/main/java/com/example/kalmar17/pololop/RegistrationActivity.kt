package com.example.kalmar17.pololop

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import android.Manifest.permission
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils
import android.widget.DatePicker
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*
import java.time.LocalDate
import java.util.regex.Pattern


class RegistrationActivity : AppCompatActivity() {
    private var btn: Button? = null
    private var imageview: ImageView? = null
    private var btnPrava: Button? = null
    private val GALLERY = 1
    private val CAMERA = 2
    var focusViewDate: View? = null

    //val listener = MaskedTextChangedListener("+38 [000] [000] [00] [00]", editPhoneUser)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val REQUEST_PERMISSION = 100
        val cameraPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.checkSelfPermission(Manifest.permission.CAMERA)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_PERMISSION
                )
            }
        }
//        editPhoneUser.addTextChangedListener(listener)
//        editPhoneUser.onFocusChangeListener = listener
        btn = findViewById<View>(R.id.buttonPhoto) as Button
        btnPrava = findViewById<Button>(R.id.buttonPrava)

        btn!!.setOnClickListener { showPictureDialog() }
        btnPrava!!.setOnClickListener { showPicturePrava() }
        textViewDate.setOnClickListener{
            this.onClickTVD()
        }
        buttonContRegist.setOnClickListener{
            this.ContRegClick()
        }

    }

    private fun onClickTVD(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox

            textViewDate.text = "" + dayOfMonth + "." + monthOfYear + "." + year
        }, year-18, month, day)
        dpd.show()
        }

    private fun showPictureDialog() {
        imageview = findViewById<View>(R.id.imageUser) as ImageView
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }
    private fun showPicturePrava() {
        imageview = findViewById<View>(R.id.imagePrava) as ImageView
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }
    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        /* if (resultCode == this.RESULT_CANCELED)
         {
         return
         }*/
        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val path = saveImage(bitmap)
                    Toast.makeText(this@RegistrationActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
                    imageview!!.setImageBitmap(bitmap)

                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@RegistrationActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        }
        else if (requestCode == CAMERA)
        {

            if (data!!.extras.get("data") != null)
            {
            val thumbnail :Bitmap = data.extras.get("data") as Bitmap
            imageview!!.setImageBitmap(thumbnail)
            saveImage(thumbnail)
            Toast.makeText(this@RegistrationActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {

            wallpaperDirectory.mkdirs()
        }

        try
        {
            Log.d("heel",wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .timeInMillis).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                arrayOf(f.path),
                arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.absolutePath)

            return f.absolutePath
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {
        private val IMAGE_DIRECTORY = "/demonuts"
    }
    private fun ContRegClick(){
        editNameUser.error = null
        editSurnameUser.error = null
        textViewDate.error = null
        editEmailUser.error = null
        editPhoneUser.error = null
        editPasswordUser.error = null
        buttonPhoto.error = null
        buttonPrava.error = null
        val NameUserstr  = editNameUser.text.toString()
        val SurnameUserstr  = editSurnameUser.text.toString()
        val DateUserstr  = textViewDate.text.toString()
        val EmailUserstr  = editEmailUser.text.toString()
        val PhoneUserstr  = editPhoneUser.text.toString().trim()
        val PasswordUserstr  = editPasswordUser.text.toString()

            var cancel = false
            var focusView :View? = null

        if(!isPhotoValid(imagePrava)){
            buttonPrava.error = getString(R.string.error_field_required)
            focusView = buttonPrava
            cancel = true
        }
        if (TextUtils.isEmpty(PasswordUserstr)||!isPasswordValid(PasswordUserstr)||!Validator(PasswordUserstr,PASSWORD_PATTERN)) {
            editPasswordUser.error = getString(R.string.error_field_required)
            focusView = editPasswordUser
            cancel = true
        }
        if (TextUtils.isEmpty(PhoneUserstr)||!isPhoneValid(PhoneUserstr)||!Validator(PhoneUserstr,PHONE_PATTERN)) {
            editPhoneUser.error = getString(R.string.error_field_required)
            focusView = editPhoneUser
            cancel = true
        }
        if (TextUtils.isEmpty(EmailUserstr)||!Validator(EmailUserstr,EMAIL_PATTERN)) {
            editEmailUser.error = getString(R.string.error_field_required)
            focusView = editEmailUser
            cancel = true
        }
        if (TextUtils.isEmpty(DateUserstr)) {
            textViewDate.error = getString(R.string.error_field_required)
            focusView = textViewDate
            cancel = true
        }
        if (TextUtils.isEmpty(SurnameUserstr)||!Validator(SurnameUserstr,NAME_PATTERN)) {
            editSurnameUser.error = getString(R.string.error_field_required)
            focusView = editSurnameUser
            cancel = true
        }
        if (TextUtils.isEmpty(NameUserstr)||!Validator(NameUserstr,NAME_PATTERN)) {
            editNameUser.error = getString(R.string.error_field_required)
            focusView = editNameUser
            cancel = true
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            startActivity(Intent(this,AddCarActivity::class.java ))
        }

    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 8
    }
    private fun isPhoneValid(phone: String): Boolean {
        //TODO: Replace this with your own logic
        return phone.length > 13
    }
    private val EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    private val NAME_PATTERN =
        "^[A-ZА-Я][a-zа-я]+$"
    private val PASSWORD_PATTERN =
        "^[A-Za-z0-9]+$"
    private val PHONE_PATTERN = "^[+][0-9]+$"
    private fun isPhotoValid(imageView: ImageView):Boolean{
        return imageView.drawable == null
    }
    fun Validator(hex: String , namePattern: String): Boolean  {
        val pattern = Pattern.compile(namePattern)
        val matcher = pattern.matcher(hex)
        return matcher.matches()
    }

}
