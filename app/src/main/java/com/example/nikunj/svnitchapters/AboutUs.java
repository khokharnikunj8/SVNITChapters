package com.example.nikunj.svnitchapters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AboutUs extends AppCompatActivity {
    public AdView adView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        adView5=(AdView)findViewById(R.id.adView5);
        AdRequest adRequest5=new AdRequest.Builder().build();
        adView5.loadAd(adRequest5);
        
    }
}
