package com.example.adi.newsapichecktwo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adi on 5/19/2017.
 */

public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.HeadLineViewHolder> {
    Context context;
    ArrayList<News> news;
    public HeadlineAdapter(ArrayList<News> news,Context context){
        this.context=context;
        this.news=news;
    }
    @Override
    public HeadLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_bulllet_card,parent,false);
        HeadLineViewHolder headLineViewHolder=new HeadLineViewHolder(view,context,news);
        return headLineViewHolder;
    }

    @Override
    public void onBindViewHolder(HeadLineViewHolder holder, int position) {
        News n=news.get(position);
        //holder.news_img.setImageResource(R.drawable.i2);
        holder.news_title.setText(n.getTitle());
        holder.news_author.setText(n.getAuthor());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
    public static class HeadLineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView news_img;
        TextView news_title,news_author;
        Context context;
        ArrayList<News> news=new ArrayList<News>();
        public HeadLineViewHolder(View itemView, Context context, ArrayList<News> news) {
            super(itemView);
            this.context=context;
            this.news=news;
            news_img=(ImageView)itemView.findViewById(R.id.news_img);
            news_title=(TextView)itemView.findViewById(R.id.news_title);
            news_author=(TextView)itemView.findViewById(R.id.news_author);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            News news=this.news.get(position);
            Intent intent=new Intent(context,NewsDetailActivity.class);
            intent.putExtra("news_img",news.getImage_id());
            intent.putExtra("news_title",news.getTitle());
            intent.putExtra("news_author",news.getAuthor());
            intent.putExtra("news_description",news.getDiscription());
            context.startActivity(intent);
        }
    }
}
