package com.example.alberto.prueba_m2o2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alberto.prueba_m2o2.R;
import com.example.alberto.prueba_m2o2.activities.mainActivity.RecyclerItemClickListener;
import com.example.alberto.prueba_m2o2.model.Receipe;
import com.example.alberto.prueba_m2o2.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReceipeAdapter extends RecyclerView.Adapter<ReceipeAdapter.ReceipeViewHolder> {

    private ArrayList<Receipe> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;
    private Context mContext;

    public ReceipeAdapter(ArrayList<Receipe> dataList , RecyclerItemClickListener recyclerItemClickListener, Context ctx) {
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
        this.mContext = ctx;
    }


    @Override
    public ReceipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row, parent, false);
        return new ReceipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReceipeViewHolder holder, final int position) {
        final Receipe receipe = dataList.get(position);
        if(receipe.getTitle()!= null && !receipe.getTitle().isEmpty())
            holder.title.setText(receipe.getTitle());
        if(receipe.getIngredients()!=null && !receipe.getIngredients().isEmpty())
            holder.ingredients.setText(receipe.getIngredients());
        if(receipe.getHref()!=null && !receipe.getHref().isEmpty())
            holder.link.setText(receipe.getHref());
        Log.e("Receipe", receipe.toString());
        if(receipe.getThumbnail()!=null && !receipe.getThumbnail().isEmpty())
            Picasso.get().load(receipe.getThumbnail()).resize(Utils.convertDpToPixel(80, mContext), Utils.convertDpToPixel(80, mContext)).centerCrop().into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position));
            }
        });

        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerItemClickListener.onLinkClick(receipe.getHref());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ReceipeViewHolder extends RecyclerView.ViewHolder {

        TextView title, ingredients, link;
        ImageView image;

        ReceipeViewHolder(View itemView) {
            super(itemView);
            title =  itemView.findViewById(R.id.titleTV);
            ingredients = itemView.findViewById(R.id.ingredientsTV);
            link = itemView.findViewById(R.id.linkTV);
            image = itemView.findViewById(R.id.imageIV);
        }
    }
}