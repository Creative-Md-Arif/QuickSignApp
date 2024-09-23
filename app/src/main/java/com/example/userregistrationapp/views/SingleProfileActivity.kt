package com.example.userregistrationapp.views

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.userregistrationapp.R
import com.example.userregistrationapp.model.UserProfile
import com.example.userregistrationapp.viewmodel.UserProfileViewModel

class SingleProfileActivity : AppCompatActivity() {
    private lateinit var userProfile: UserProfile

    private lateinit var nameTxt: TextView
    private lateinit var emailTxt: TextView
    private lateinit var dobTxt: TextView
    private lateinit var districtTxt: TextView
    private lateinit var mobileTxt: TextView

    private lateinit var updateBtn: ImageButton
    private lateinit var deleteBtn: ImageButton

    private lateinit var profileViewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_profile)

        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        userProfile =intent.getSerializableExtra( "USER_PROFILE") as UserProfile


        nameTxt = findViewById(R.id.nameTxt)
        emailTxt = findViewById(R.id.emailTxt)
        dobTxt = findViewById(R.id.dobTxt)
        districtTxt = findViewById(R.id.districtTxt)
        mobileTxt = findViewById(R.id.mobileTxt)

        updateBtn = findViewById(R.id.addBtn)
        deleteBtn = findViewById(R.id.deleteBtn)

        updateBtn.setOnClickListener{
            val intent = Intent(this, UpdateProfileActivity::class.java)
            intent.putExtra("USER_PROFILE", userProfile)
            startActivity(intent)
        }

        deleteBtn.setOnClickListener{
            profileViewModel.deleteUserProfile(userProfile)
            finish()
        }

        populateData()
    }

    private fun populateData() {
        nameTxt.text = userProfile.name
        emailTxt.text = userProfile.email
        dobTxt.text = userProfile.dob
        districtTxt.text = userProfile.district
        mobileTxt.text = userProfile.mobile
    }
}