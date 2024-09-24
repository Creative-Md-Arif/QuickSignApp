package com.example.userregistrationapp.views

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userregistrationapp.R
import com.example.userregistrationapp.adapter.ProfileAdapter
import com.example.userregistrationapp.model.UserProfile
import com.example.userregistrationapp.viewmodel.UserProfileViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileListActivity : AppCompatActivity() {
    private lateinit var profileViewModel: UserProfileViewModel
    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var noDataTextView: TextView

    private lateinit var progressDialog: ProgressDialog // ProgressDialog for loading animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_list)


        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        profileAdapter = ProfileAdapter()

        recyclerView.adapter = profileAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        noDataTextView = findViewById(R.id.noDataTextView)


        profileViewModel.getUserProfiles().observe(this, Observer { profiles ->
            profileAdapter.submitList(profiles)
            // Show or hide the no data message based on the list
            if (profiles.isEmpty()) {
                noDataTextView.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                noDataTextView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        })


        profileAdapter.setOnItemClickListener {
            val intent = Intent(this, SingleProfileActivity::class.java)
            intent.putExtra("USER_PROFILE", it)
            startActivity(intent)
        }

        fun showEditConfirmationDialog(userProfile: UserProfile) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Edit Profile")
            builder.setMessage("Are you sure you want to edit this profile?")

            builder.setPositiveButton("Yes") { _, _ ->
                val intent = Intent(this, UpdateProfileActivity::class.java)
                intent.putExtra("USER_PROFILE", userProfile)
                startActivity(intent)
            }

            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()
        }

        profileAdapter.setOnEditClickListener { userProfile ->
            showEditConfirmationDialog(userProfile)
        }

        fun showDeleteConfirmationDialog(userProfile: UserProfile) {
            // Create an AlertDialog Builder
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Profile")
            builder.setMessage("Are you sure you want to delete this profile?")

            // Set up the buttons
            builder.setPositiveButton("Yes") { _, _ ->
                profileViewModel.deleteUserProfile(userProfile) // Delete the profile
            }

            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

            // Show the dialog

            builder.create().show()
        }

        profileAdapter.setOnDeleteClickListener { userProfile ->
            showDeleteConfirmationDialog(userProfile)
        }

        // Initialize the ProgressDialog
        progressDialog = ProgressDialog(this).apply {
            setTitle("Loading")
            setMessage("Please wait...")
            setCancelable(false) // Disable canceling the dialog by tapping outside
        }

        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            // Show the loading animation
            progressDialog.show()

            // Delay for 1 second (1000 milliseconds) before starting AddProfileActivity
            Handler().postDelayed({
                progressDialog.dismiss() // Dismiss the loading animation
                startActivity(Intent(this, AddProfileActivity::class.java))
            }, 1000)
        }

    }
}

