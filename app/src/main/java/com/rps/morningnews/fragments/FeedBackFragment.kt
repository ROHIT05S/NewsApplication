package com.rps.morningnews.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rps.morningnews.R
import com.rps.morningnews.databinding.FeedbackFragmentBinding

class FeedBackFragment: Fragment() {

    lateinit var feedbackFragmentBinding: FeedbackFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        feedbackFragmentBinding = DataBindingUtil.inflate<FeedbackFragmentBinding>(
            inflater,
            R.layout.feedback_fragment,
            container,
            false
        )

        return feedbackFragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFeedbackFromUser()
    }

    private fun getFeedbackFromUser() {
        feedbackFragmentBinding.btnSubmit.setOnClickListener(View.OnClickListener {
            val ratings = feedbackFragmentBinding.ratingBar.rating.toString()
            val userName = feedbackFragmentBinding.edName.text.toString()
            val userEmailId = feedbackFragmentBinding.edEmail.text.toString()
            val switchData = feedbackFragmentBinding.switchNewsRelevant.isChecked
            val overAllComments = feedbackFragmentBinding.edOverallComments.text.toString()
            if(userName.isNullOrEmpty()){
                feedbackFragmentBinding.edName.error = "Please fill your name"
            }
            else if (!checkIfEmailIsValid(userEmailId)){
                feedbackFragmentBinding.edEmail.error = "Please Enter a Valid Email Address"
            }
            else if(overAllComments.isNullOrEmpty()){
                feedbackFragmentBinding.edOverallComments.error ="Please give your comments"
            }else {
                sendDataToDatabase(ratings, userName, userEmailId, switchData, overAllComments);
            }


        })

    }

    private fun sendDataToDatabase(ratings: String, userName: String, userEmailId: String, switchData: Boolean, overAllComments: String) {
        feedbackFragmentBinding.ratingBar.rating = 0.0f
        feedbackFragmentBinding.edName.text = Editable.Factory.getInstance().newEditable("")
       feedbackFragmentBinding.edEmail.text = Editable.Factory.getInstance().newEditable("")
        feedbackFragmentBinding.switchNewsRelevant.isChecked = false
        feedbackFragmentBinding.edOverallComments.text = Editable.Factory.getInstance().newEditable("")
       Toast.makeText(context,"Thanks for your valuable feedback ",Toast.LENGTH_SHORT).show()
    }


    fun checkIfEmailIsValid(userEmail:String): Boolean {
        if (!userEmail.isNullOrEmpty()) {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                return true
            } else {
                return false
            }
        }
        return  false
    }

    companion object {
        @JvmStatic
        fun newInstance(): FeedBackFragment = FeedBackFragment()
    }

}