package com.example.nikunj.svnitchapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static com.example.nikunj.svnitchapters.R.attr.colorPrimary;
import static com.example.nikunj.svnitchapters.R.attr.colorPrimaryDark;

public class activepost extends AppCompatActivity {
    public TextView title4;
    public publish pb;
    public ImageView postimage4;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    public TextView desc4,date4,description4;
    public DatabaseReference databaseReference4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_activepost);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        pb = i.getParcelableExtra("publish");
        postimage4=(ImageView)findViewById(R.id.postimage4);
        desc4=(TextView)findViewById(R.id.desc4);
        date4=(TextView)findViewById(R.id.date4);
        description4=(TextView)findViewById(R.id.description4);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(pb.getTitle().toString());
        picassoLoader(this, postimage4, pb.getImage());
        desc4.setText(pb.getDesc());
        description4.setText(pb.getDescription());
        date4.setText(pb.getDate());



        dynamicToolbarColor();

        toolbarTextAppernce();
    }
    public void picassoLoader(Context context, ImageView imageView, String url){

        Picasso.with(context)
                .load(url)
                //.resize(30,30)

                .into(imageView);
    }

    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.as1);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

            }
        });
    }


    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }
}



