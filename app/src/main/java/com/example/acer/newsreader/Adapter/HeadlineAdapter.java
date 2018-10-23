package com.example.acer.newsreader.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.newsreader.model.Article;
import com.example.acer.newsreader.R;
import com.example.acer.newsreader.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by acer on 10/18/2018.
 */

public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.MyViewHolder> {
    List<Article> arrayList = new ArrayList<>();
    Context context;

    public HeadlineAdapter(List<Article> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Article article = arrayList.get(position);
        holder.source.setText(article.getSource().getName());
        holder.auther.setText(article.getAuthor());
        holder.title.setText(article.getTitle());
        holder.discription.setText(article.getDescription());
        holder.content.setText(article.getContent());
        Picasso.with(context).load(article.getUrlToImage()).into(holder.imageView);
        holder.holderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.url));
                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView source, content, discription, auther, title;
        CircleImageView imageView;
        View holderView;

        public MyViewHolder(View view) {
            super(view);

            holderView = view;
            source = (TextView) view.findViewById(R.id.source);
            auther = (TextView) view.findViewById(R.id.auther);
            title = (TextView) view.findViewById(R.id.titlee);
            discription = (TextView) view.findViewById(R.id.discription);
            content = (TextView) view.findViewById(R.id.content);
            imageView = (CircleImageView) view.findViewById(R.id.circleimageviewimageview);
        }
    }
}
