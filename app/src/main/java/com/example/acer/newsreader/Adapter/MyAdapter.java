package com.example.acer.newsreader.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.newsreader.Headlines;
import com.example.acer.newsreader.R;
import com.example.acer.newsreader.model.Source;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Source> arrayList = new ArrayList<>();
    Context context;

    public MyAdapter(List<Source> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Source object = arrayList.get(position);
        holder.source.setText(object.getName());
        holder.category.setText(object.getCategory());
        holder.discription.setText(object.getDescription());
        holder.holderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Headlines.class);
                intent.putExtra("source", object.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView source, category, discription;
        CircleImageView imageView;
        View holderView;

        public MyViewHolder(View view) {
            super(view);

            holderView = view;
            source = (TextView) view.findViewById(R.id.source_name);
            category = (TextView) view.findViewById(R.id.category);
            discription = (TextView) view.findViewById(R.id.descriptions);
            imageView = (CircleImageView) view.findViewById(R.id.imageview);
        }
    }
}
