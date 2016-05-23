package com.telestra.demoApp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.telestra.demoApp.Pojo.ImageDesc;
import com.telestra.demoApp.R;

import java.util.List;

/**
 * This base adapter is used to load image from url and shows in list
 */
public class DetailsListAdapter extends BaseAdapter {

    Context context;   //Context from activity

    List<ImageDesc> imageData;  //List of data from server

    LayoutInflater inflater;   //Inflater for adapter

    public DetailsListAdapter(Context context, List<ImageDesc> imageData) {
        this.context = context;
        this.imageData = imageData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageData.size();
    }

    @Override
    public ImageDesc getItem(int position) {
        return imageData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageDesc rowItem = getItem(position);
        holder.txtDesc.setText(rowItem.getDescription());
        holder.txtTitle.setText(rowItem.getTitle());
        Picasso
                .with(context)
                .load(rowItem.getImageHref())
                .fit()
                .into(holder.imageView);
        return convertView;

    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
    }


}
