package com.rps.morningnews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rps.morningnews.R
import com.rps.morningnews.eventhandlers.BottomSheetListener

class BottomSheetDialogAdapter (val bottomSheetItemList: List<String>,val bottomSheetItemListener: (String) -> Unit):RecyclerView.Adapter<BottomSheetDialogAdapter.BottomSheetViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomSheetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BottomSheetViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = bottomSheetItemList.size

    override fun onBindViewHolder(
        holder: BottomSheetViewHolder,
        position: Int
    ) {
        val data: String = bottomSheetItemList.get(position)
        holder.bind(data,bottomSheetItemListener)
    }

    class BottomSheetViewHolder (inflater: LayoutInflater, parent: ViewGroup)  :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.bottom_sheet_view, parent, false)) {
        private var mTitleView: TextView? = null
        init {
            mTitleView = itemView.findViewById(R.id.tv_botomsheet_textview)
        }

        fun bind(data: String, bottomSheetItemListener: (String) -> Unit) {
            mTitleView?.text = data
            itemView.setOnClickListener{(bottomSheetItemListener(mTitleView?.text as String))}
        }
    }


}