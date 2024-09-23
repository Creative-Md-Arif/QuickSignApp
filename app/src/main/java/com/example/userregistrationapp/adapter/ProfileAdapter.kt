package com.example.userregistrationapp.adapter

import com.example.userregistrationapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userregistrationapp.model.UserProfile

class ProfileAdapter() : ListAdapter<UserProfile, ProfileAdapter.ProfileViewHolder>(DiffCallback()) {
    private var onItemClickListener: ((UserProfile) -> Unit)? = null
    private var onDeleteClickListener: ((UserProfile) -> Unit)? = null
    private var onEditClickListener: ((UserProfile) -> Unit)? = null

    fun setOnItemClickListener(listener: ((UserProfile) -> Unit)?) {
       onItemClickListener = listener
    }

    fun setOnDeleteClickListener(listener: ((UserProfile) -> Unit)?) {
        onDeleteClickListener = listener
    }
    fun setOnEditClickListener(listener: ((UserProfile) -> Unit)?) {
        onEditClickListener = listener
    }



   inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileName: TextView = itemView.findViewById(R.id.userNameTxt)
        private val profileEmail: TextView = itemView.findViewById(R.id.userEmailTxt)
        private val profileDob: TextView = itemView.findViewById(R.id.userDoblTxt)
        private val profileDistrict: TextView = itemView.findViewById(R.id.userDistrictTxt)
        private val profileMobile: TextView = itemView.findViewById(R.id.userMobileTxt)
        private val updateBtn: ImageButton = itemView.findViewById(R.id.editBtn)
        private val deleteBtn: ImageButton = itemView.findViewById(R.id.imageButton)


        init {
            itemView.setOnClickListener {
               val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val profile = getItem(position)
                    onItemClickListener?.invoke(profile)
                }
            }
            deleteBtn.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val profile = getItem(position)
                    onDeleteClickListener?.invoke(profile)
                    notifyItemRemoved(position)
                }
            }
            updateBtn.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val profile = getItem(position)
                    onEditClickListener?.invoke(profile)
                }
            }

        }

        fun bind(userProfile: UserProfile) {
            profileName.text = userProfile.name
            profileEmail.text = userProfile.email
            profileDob.text = userProfile.dob
            profileDistrict.text = userProfile.district
            profileMobile.text = userProfile.mobile
        }


    }




    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileAdapter.ProfileViewHolder {
        val itemView = LayoutInflater.from((parent.context)).inflate(R.layout.profile_list_layout, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

   class DiffCallback : DiffUtil.ItemCallback<UserProfile>() {
    override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
        return oldItem.id == newItem.id
    }

     override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
           return oldItem == newItem

       }
   }

