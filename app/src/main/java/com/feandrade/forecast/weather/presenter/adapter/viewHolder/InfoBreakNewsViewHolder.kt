package com.feandrade.forecast.weather.presenter.adapter.viewHolder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feandrade.forecast.weather.R
import com.feandrade.forecast.weather.data.model.newsmodel.Article

class InfoBreakNewsViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        fun bind(article: Article) {
            val imageNews : ImageView = itemView.findViewById(R.id.imageNews)
            val textTitle : TextView = itemView.findViewById(R.id.textTitle)

            textTitle.text = article.title.toString()

            Glide.with(context)
                .load(article.urlToImage)
                .into(imageNews)
        }

    }