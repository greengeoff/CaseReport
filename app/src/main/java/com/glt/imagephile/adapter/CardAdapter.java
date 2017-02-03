package com.glt.imagephile.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.glt.imagephile.R;
import com.glt.imagephile.model.AVnote;
import com.glt.imagephile.util.ImageLoaderTask;
import com.glt.imagephile.util.ImageUtil;

import java.util.ArrayList;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;

/**
 * Created by gltrager on 2/2/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder>{
    private ArrayList<AVnote> avNoteList;
    private Context context;


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView cv;
        public ImageView mImageView;
        public TextView mTextView;

        public MyViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            mImageView = (ImageView)itemView.findViewById(R.id.damage_photo);
            mTextView = (TextView)itemView.findViewById(R.id.info_text);
        }
    }
    public CardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardview, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        // context needed for async image task
        context = parent.getContext();
        return mvh;
    }

    @Override
    public void onBindViewHolder(CardAdapter.MyViewHolder holder, int position) {
        holder.mTextView.setText(avNoteList.get(position).getDetails());

        ImageLoaderTask insertImage =
                new ImageLoaderTask(context,holder.mImageView);
        insertImage.execute (avNoteList.get(position).getImagePath());

    }

    @Override
    public int getItemCount() {
        return avNoteList.size();
    }
}
