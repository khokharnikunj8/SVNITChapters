package com.example.nikunj.svnitchapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class Drishti extends Fragment {
    public  RecyclerView recycle;
    public DatabaseReference databaseReference3;
    public FirebaseRecyclerAdapter<publish,BlogViewHolder> firebaseRecyclerAdapter3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_announcements, container, false);
        recycle=(RecyclerView)rootView.findViewById(R.id.recycle);

        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference3= FirebaseDatabase.getInstance().getReference().child("GDG");
        get();

        recycle.setAdapter(firebaseRecyclerAdapter3);
        firebaseRecyclerAdapter3.notifyDataSetChanged();
        databaseReference3.keepSynced(true);
        recycle.invalidate();
        return rootView;
    }
    public void get()
    {
        databaseReference3.keepSynced(true);
        firebaseRecyclerAdapter3 = new FirebaseRecyclerAdapter<publish, BlogViewHolder>(
                publish.class,
                R.layout.framework,
                BlogViewHolder.class,
                databaseReference3
        ) {


            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, final publish model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setdesc(model.getDesc());
                viewHolder.setimg(getActivity().getApplicationContext(),model.getImage());
                viewHolder.setDate(model.getDate());
                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(getActivity(),activepost.class).putExtra("publish", model));

                    }
                });

            }
        };
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {

        View view;
        public BlogViewHolder(View itemView) {
            super(itemView);
            view=itemView;
        }
        public void setTitle(String Title)
        {
            TextView posttitle = (TextView)view.findViewById(R.id.posttitle);
            posttitle.setText(Title);
        }
        public void setDate(String date)
        {
            TextView postdate=(TextView)view.findViewById(R.id.postdate);
            postdate.setText(date);
        }
        public void setdesc(String postdesc)
        {
            TextView postdes = (TextView)view.findViewById(R.id.postdes);
            postdes.setText(postdesc);
        }
        public void setimg(final Context ctx, final String postimag)
        {
            final ImageView postimage = (ImageView)view.findViewById(R.id.postimage);
            Picasso.with(ctx).load(postimag).networkPolicy(NetworkPolicy.OFFLINE).into(postimage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(ctx).load(postimag).into(postimage);

                }
            });


        }
    }

}





