package com.rps.morningnews.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rps.morningnews.R
import com.rps.morningnews.adapters.BottomSheetDialogAdapter
import com.rps.morningnews.databinding.FragmentBottomSheetBinding
import com.rps.morningnews.eventhandlers.BottomSheetListener

class BottomSheetFragment : BottomSheetDialogFragment() {
    var mBottomSheetListener: BottomSheetClickedListener? = null
    lateinit var bottomSheetBinding: FragmentBottomSheetBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mBottomSheetListener = context as BottomSheetClickedListener?
        }
        catch (e: ClassCastException){
            throw ClassCastException(context!!.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bottomSheetItemList: List<String> =
            resources.getStringArray(R.array.news_category).toList()
        bottomSheetBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false)
        bottomSheetBinding.rvBottomsheet.adapter = BottomSheetDialogAdapter(bottomSheetItemList,
            { data -> onBottomSheetItemClicked(data) })

        return bottomSheetBinding.root
    }

    fun onBottomSheetItemClicked(data: String) {
        mBottomSheetListener!!.onBottomSheetItemClicked1(data)
        dismiss()
        Log.d("BottomSheetItemClicked", data)

    }

    interface BottomSheetClickedListener {
        fun onBottomSheetItemClicked1(data: String)
    }





}