package com.app3.imsaiful.rssfeed;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by imsaiful on 2/8/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<FeedItem>feedItems;
    Context context;

    public MyAdapter(ArrayList<FeedItem> feedItems, Context context) {
        this.context = context;
        this.feedItems = feedItems;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.custom_row_news,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        final FeedItem current=feedItems.get(position);
        holder.title.setText(current.getTitle());
        holder.date.setText(current.getPubDate());
        holder.description.setText(current.getDescription());
        Picasso.with(context).load(current.getThumbnailUrl()).into(holder.thumbnail);
        holder.cardView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context,NewsDetails.class);
                        intent.putExtra("Link",current.getLink());
                        context.startActivity(intent);
                    }
                }
        );


    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,date;
        ImageView thumbnail;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.text1);
            title=(TextView)itemView.findViewById(R.id.text2);
            description=(TextView)itemView.findViewById(R.id.text3);
            thumbnail=(ImageView)itemView.findViewById(R.id.img);
            cardView=(CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
