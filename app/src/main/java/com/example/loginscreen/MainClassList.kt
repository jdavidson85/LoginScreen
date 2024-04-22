package com.example.loginscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainClassList : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_class_list)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editText)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val swDegreeCert = findViewById<Switch>(R.id.swDegreeCert)
        val spnDegree = findViewById<Spinner>(R.id.spnDegree)
        val spnCertificate = findViewById<Spinner>(R.id.spnCertificate)
        val txtCerticicate = findViewById<TextView>(R.id.lstCertificate)
        val txtDegree = findViewById<TextView>(R.id.lstDegree)
        val btnNext = findViewById<Button>(R.id.btnNext)

        val firstName = findViewById<EditText>(R.id.txtFirstName)
        val lastName = findViewById<EditText>(R.id.txtLastName)
        val phone = findViewById<EditText>(R.id.txtPhone)

        val spMonth = findViewById<Spinner>(R.id.spnMonth)
        val txtDay = findViewById<EditText>(R.id.txtDay)
        val txtYear = findViewById<EditText>(R.id.txtYear)

        firstName.requestFocus()

        swDegreeCert.setOnCheckedChangeListener() { buttonView, isChecked ->
            if (isChecked) {
                spnDegree.visibility = View.VISIBLE
                txtDegree.visibility = View.VISIBLE
                spnCertificate.visibility = View.GONE
                txtCerticicate.visibility = View.GONE
            } else {
                spnDegree.visibility = View.VISIBLE
                txtDegree.visibility = View.VISIBLE
                spnCertificate.visibility = View.GONE
                txtCerticicate.visibility = View.GONE
            }
        }

        btnNext.setOnClickListener {
            if (checkData()){
                var doBirth = ""
                doBirth = spMonth.selectedItem.toString() + "/" + txtDay.text.toString() + "/" + txtYear.text.toString()

                val nextScreen = Intent(this@MainClassList, ChooseClass::class.java)
                nextScreen.putExtra("FirstName", firstName.text.toString())
                nextScreen.putExtra("LastName", lastName.text.toString())
                nextScreen.putExtra("Phone", phone.text.toString())
                nextScreen.putExtra("BirthDate", doBirth)

                if (spnDegree.visibility == View.VISIBLE){
                    nextScreen.putExtra("isDegreeCert", "Degree")
                    nextScreen.putExtra("degreeCert", spnDegree.selectedItem.toString())
                } else {
                    nextScreen.putExtra("isDegreeCert", "Certificate")
                    nextScreen.putExtra("degreeCert", spnCertificate.selectedItem.toString())
                }

                //Start Activity
                startActivity(nextScreen)
            }
        }
    }
    private fun checkData(): Boolean{
        val firstName = findViewById<EditText>(R.id.txtFirstName)
        val lastName = findViewById<EditText>(R.id.txtLastName)
        val phone = findViewById<EditText>(R.id.txtPhone)
        val txtDay = findViewById<EditText>(R.id.txtDay)
        val txtYear = findViewById<EditText>(R.id.txtYear)

        if (firstName.text.toString().isEmpty()) {
            //Error
            firstName.error = "Invalid First Name"
            firstName.requestFocus()
        }
        if (lastName.text.toString().isEmpty()) {
            //Error
            lastName.error = "Invalid Last Name"
            lastName.requestFocus()
        }
        if (phone.text.toString().isEmpty()) {
            //Error
            phone.error = "Invalid Phone Number"
            phone.requestFocus()
        }
        if (txtDay.text.toString().isEmpty()) {
            //Error
            txtDay.error = "Invalid Day"
            txtDay.requestFocus()
        }
        if (txtYear.text.toString().isEmpty()) {
            //Error
            txtYear.error = "Invalid Year"
            txtYear.requestFocus()
        }

        return true

    }
}