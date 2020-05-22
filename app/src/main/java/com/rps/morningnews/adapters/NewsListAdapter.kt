package com.rps.morningnews.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rps.morningnews.R
import com.rps.morningnews.models.ArticleModel


class NewsListAdapter(val articleList:List<ArticleModel>,val articleClickListener:(ArticleModel)->Unit):
    RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsListViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(
        holder: NewsListViewHolder,
        position: Int
    ) {
        if(!articleList.get(position).title.isNullOrEmpty()&&!articleList.get(position).urlToImage.isNullOrEmpty()&&!articleList.get(position).description.isNullOrEmpty()) {
            holder.bind(
                articleList.get(position).title,
                articleList.get(position).urlToImage,
                articleList.get(position).description,
                articleList.get(position),
                articleClickListener
            )
        }
    }
    class NewsListViewHolder (inflater: LayoutInflater, parent: ViewGroup)  :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.news_item_list, parent, false)) {
        private var mTitleView: TextView? = null
        private var mDescriptionView: TextView? = null
        private var mImageView: ImageView? = null
        val mContext = parent.context
        init {
            mTitleView = itemView.findViewById(R.id.text_view_title)
            mImageView = itemView.findViewById(R.id.image_view_list)
        }

        fun bind(
            title: String,
            imageUrl: String,
            description: String,
            article:ArticleModel,
            articleClickListener: (ArticleModel) -> Unit
        ) {
            mTitleView?.text = title!!
            if (imageUrl != null) {
                Glide.with(mContext).load(imageUrl!!).into(mImageView)
            } else {
                mImageView?.setImageResource(R.drawable.ic_launcher_background)
            }
            itemView.setOnClickListener{(articleClickListener(article))}

        }
    }
}