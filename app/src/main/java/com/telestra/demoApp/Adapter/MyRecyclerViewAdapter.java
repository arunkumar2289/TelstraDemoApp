package com.telestra.demoApp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.telestra.demoApp.Pojo.ImageDesc;
import com.telestra.demoApp.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {

    private List<ImageDesc> mDataset; //List of Data from server

    private Context context; //Context from activity

    public static class DataObjectHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView desc;
        ImageView dataImage;

        public DataObjectHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            dataImage = (ImageView) itemView.findViewById(R.id.icon);
        }

    }


    public MyRecyclerViewAdapter(List<ImageDesc> myDataset,Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getTitle());
        holder.desc.setText(mDataset.get(position).getDescription());
        Picasso
                .with(context)
                .load(mDataset.get(position).getImageHref())
                .fit()
                .into(holder.dataImage);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}