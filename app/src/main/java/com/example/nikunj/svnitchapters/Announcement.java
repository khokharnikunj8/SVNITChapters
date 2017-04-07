package com.example.nikunj.svnitchapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class Announcement extends AppCompatActivity {
    public DatabaseReference databaseReference42,databaseReference41;
    public String suffix;
    public RecyclerView recycle4;
    public FirebaseRecyclerAdapter<publish,Announcements.BlogViewHolder> firebaseRecyclerAdapter4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_announcements);

        databaseReference41=FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Admission");
        databaseReference41.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                suffix=dataSnapshot.getValue().toString().substring(1,3);
                databaseReference42 = FirebaseDatabase.getInstance().getReference().child(suffix);
                recycle4 = (RecyclerView) findViewById(R.id.recycle);
                recycle4.setLayoutManager(new LinearLayoutManager(Announcement.this));
                get();
                recycle4.setAdapter(firebaseRecyclerAdapter4);
                firebaseRecyclerAdapter4.notifyDataSetChanged();
                databaseReference42.keepSynced(true);
                recycle4.invalidate();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    public void get()
    {
        databaseReference42.keepSynced(true);
        firebaseRecyclerAdapter4 = new FirebaseRecyclerAdapter<publish, Announcements.BlogViewHolder>(
                publish.class,
                R.layout.framework,
                Announcements.BlogViewHolder.class,
                databaseReference42
        ) {


            @Override
            protected void populateViewHolder(Announcements.BlogViewHolder viewHolder, final publish model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setdesc(model.getDesc());
                viewHolder.setimg(getApplicationContext(), model.getImage());
                viewHolder.setDate(model.getDate());

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(Announcement.this, activepost.class).putExtra("publish", model));

                    }
                });


            }
        };
    }
    public void picassoLoader(Context context, ImageView imageView, String url){

        Picasso.with(context)
                .load(url)
                //.resize(30,30)

                .into(imageView);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {

        View view;
        public BlogViewHolder(View itemView) {
            super(itemView);
            view=itemView;
        }
        public void setDate(String date)
        {
            TextView postdate=(TextView)view.findViewById(R.id.postdate);
            postdate.setText(date);
        }
        public void setTitle(String Title)
        {
            TextView posttitle = (TextView)view.findViewById(R.id.posttitle);
            posttitle.setText(Title);
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
