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

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var profileViewModel: UserProfileViewModel
    private lateinit var userProfile: UserProfile

    private lateinit var nameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var dobEt: EditText
    private lateinit var districtEt: EditText
    private lateinit var mobileEt: EditText
    private lateinit var updateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        userProfile = intent.getSerializableExtra("USER_PROFILE") as UserProfile

        nameEt = findViewById(R.id.profileNameEt)
        emailEt = findViewById(R.id.profileEmailEt)
        dobEt = findViewById(R.id.profileDobEt)
        districtEt = findViewById(R.id.profileDistrictEt)
        mobileEt = findViewById(R.id.profileMobileEt)
        updateBtn = findViewById(R.id.addBtn)

        populateData()

        updateBtn.setOnClickListener {
            updateUserProfile()
        }

    }
    private fun updateUserProfile() {
        val name = nameEt.text.toString().toString().trim()
        val email = emailEt.text.toString().toString().trim()
        val dob = dobEt.text.toString().toString().trim()
        val district = districtEt.text.toString().toString().trim()
        val mobile = mobileEt.text.toString().toString().trim()

        if (name.isEmpty() || email.isEmpty() || dob.isEmpty() || district.isEmpty() || mobile.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
        } else {
            val userProfile = UserProfile(
                id = userProfile.id,
                name = name,
                email = email,
                dob = dob,
                district = district,
                mobile = mobile
            )
            profileViewModel.updateUserProfile(userProfile)
            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun populateData() {
       nameEt.setText(userProfile.name)
       emailEt.setText(userProfile.email)
       dobEt.setText(userProfile.dob)
       districtEt.setText(userProfile.district)
       mobileEt.setText(userProfile.mobile)
    }


}