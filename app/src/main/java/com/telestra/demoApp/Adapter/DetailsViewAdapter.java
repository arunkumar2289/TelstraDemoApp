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

import java.util.ArrayList;
import java.util.List;

public class DetailsViewAdapter extends RecyclerView
        .Adapter<DetailsViewAdapter.ImageDetailsHolder> {

    private List<ImageDesc> mDataset; //List of Data from server

    private Context context; //Context from activity

    public static class ImageDetailsHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView desc;
        private ImageView dataImage;

        public ImageDetailsHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            dataImage = (ImageView) itemView.findViewById(R.id.icon);
        }

    }


    public DetailsViewAdapter() {
        mDataset = new ArrayList<>();
    }

    public void updateList(List<ImageDesc> data) {
        mDataset = data;
        notifyDataSetChanged();
    }

    @Override
    public ImageDetailsHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item, parent, false);

        return new ImageDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageDetailsHolder holder, int position) {
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