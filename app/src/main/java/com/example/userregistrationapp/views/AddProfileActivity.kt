package com.example.userregistrationapp.views

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.userregistrationapp.R
import com.example.userregistrationapp.model.UserProfile
import com.example.userregistrationapp.viewmodel.UserProfileViewModel

class AddProfileActivity : AppCompatActivity() {
    private lateinit var profileViewModel: UserProfileViewModel

    private lateinit var nameTxt: EditText
    private lateinit var emailTxt: EditText
    private lateinit var dobTxt: EditText
    private lateinit var districtTxt: EditText
    private lateinit var mobileTxt: EditText
    private lateinit var saveBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_profile)

        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        nameTxt = findViewById(R.id.profileNameEt)
        emailTxt = findViewById(R.id.profileEmailEt)
        dobTxt = findViewById(R.id.profileDobEt)
        districtTxt = findViewById(R.id.profileDistrictEt)
        mobileTxt = findViewById(R.id.profileMobileEt)


        saveBtn = findViewById(R.id.addBtn)



        saveBtn.setOnClickListener {
            val name = nameTxt.text.toString().toString().trim()
            val email = emailTxt.text.toString().toString().trim()
            val dob = dobTxt.text.toString().toString().trim()
            val district = districtTxt.text.toString().toString().trim()
            val mobile = mobileTxt.text.toString().toString().trim()

            if (name.isEmpty() || email.isEmpty() || dob.isEmpty() || district.isEmpty() || mobile.isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            } else {

                val userProfile = UserProfile(
                    name = name,
                    email = email,
                    dob = dob,
                    district = district,
                    mobile = mobile
                )
                profileViewModel.insertUserProfile(userProfile)
                finish()
            }
        }
    }

}