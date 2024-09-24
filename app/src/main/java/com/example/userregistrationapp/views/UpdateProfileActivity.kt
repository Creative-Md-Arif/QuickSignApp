package com.example.userregistrationapp.views
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.userregistrationapp.R
import com.example.userregistrationapp.model.UserProfile
import com.example.userregistrationapp.viewmodel.UserProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var profileViewModel: UserProfileViewModel
    private lateinit var userProfile: UserProfile

    private lateinit var nameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var dobEt: EditText
    private lateinit var districtEt: EditText
    private lateinit var mobileEt: EditText
    private lateinit var updateBtn: Button


    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        userProfile = intent.getSerializableExtra("USER_PROFILE") as UserProfile

        nameEt = findViewById(R.id.nameEditTxt)
        emailEt = findViewById(R.id.emailEditTxt)
        dobEt = findViewById(R.id.dobEditTxt)
        districtEt = findViewById(R.id.districtEditTxt)
        mobileEt = findViewById(R.id.mobileEditTxt)
        updateBtn = findViewById(R.id.updateBtn)

        setupLoadingDialog()

        populateData()

        updateBtn.setOnClickListener {
            updateUserProfile()
        }
    }

    private fun setupLoadingDialog() {
        loadingDialog = Dialog(this)
        loadingDialog.setContentView(R.layout.dialog_loading)
        loadingDialog.setCancelable(false) // Prevent dismissing on back press
    }

    private fun updateUserProfile() {
        val name = nameEt.text.toString().trim()
        val email = emailEt.text.toString().trim()
        val dob = dobEt.text.toString().trim()
        val district = districtEt.text.toString().trim()
        val mobile = mobileEt.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || dob.isEmpty() || district.isEmpty() || mobile.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
        } else {
            // Show ProgressBar when updating starts
            loadingDialog.show()
            updateBtn.isEnabled = false   // Disable button during update

            // Use CoroutineScope to call suspend function
            CoroutineScope(Dispatchers.Main).launch {
                val updatedProfile = UserProfile(
                    id = userProfile.id,
                    name = name,
                    email = email,
                    dob = dob,
                    district = district,
                    mobile = mobile
                )

                delay(1000L)


                loadingDialog.dismiss()
                updateBtn.isEnabled = true

                Toast.makeText(this@UpdateProfileActivity, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
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
