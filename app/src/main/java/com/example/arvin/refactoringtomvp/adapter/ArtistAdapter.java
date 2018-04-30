package com.example.arvin.refactoringtomvp.adapter;

//Pop Fragment Adapter

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arvin.refactoringtomvp.R;
import com.example.arvin.refactoringtomvp.model.ArtistResponse;
import com.example.arvin.refactoringtomvp.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    List<Result> results;
    Context mContext;

    public ArtistAdapter(List<Result> results , Context mContext) {
        this.results = results;
        this.mContext = mContext;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_list, parent , false);
        return new ArtistViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        holder.bind(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView tv_collection;
        final TextView tv_name;
        final TextView tv_price;
        final ImageView iv_picture;
        private Result mResponse;


        ArtistViewHolder(View itemView) {
            super(itemView);
            tv_collection = itemView.findViewById(R.id.tv_collection);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            iv_picture = itemView.findViewById(R.id.iv_picture);

            itemView.setOnClickListener(this);
        }

        /**Setting the Artist name , Collection Name , price and album picture on the view**/
        void bind(Result mResponse) {

            this.mResponse = mResponse;
            this.tv_name.setText(mResponse.getArtistName());
            this.tv_collection.setText(mResponse.getCollectionName());
            this.tv_price.setText(String.valueOf(mResponse.getTrackPrice()));

            Picasso.with(this.iv_picture.getContext())
                    .load(mResponse.getArtworkUrl60())
                    .into(this.iv_picture);
        }

        /**When user click on an item Music will play**/
        @Override
        public void onClick(View v) {
            playMusic(results.get(getAdapterPosition()).getPreviewUrl());
        }

    }

    private void playMusic(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setDataAndType(uri, "audio/m4a");
        mContext.startActivity(intent);
    }


}