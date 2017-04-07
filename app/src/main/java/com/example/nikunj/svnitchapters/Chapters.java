package com.example.nikunj.svnitchapters;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Chapters extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public FirebaseAuth firebaseAuth2;

    public DatabaseReference databaseReference2;
    public ViewPager viewPager;
    public String inputname2;
    public ImageView image2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        firebaseAuth2=FirebaseAuth.getInstance();

        if(firebaseAuth2.getCurrentUser()==null||!firebaseAuth2.getCurrentUser().isEmailVerified())
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

else {
            Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
            setSupportActionBar(toolbar2);
            databaseReference2 = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth2.getCurrentUser().getUid());
            databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    inputname2 = dataSnapshot.child("Name").getValue().toString();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "for more Queries , contact to khokharnikunj8@gmail.com", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar2, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            image2 = (ImageView) findViewById(R.id.image2);
            viewPager = (ViewPager) findViewById(R.id.tab_viewpager);


            setupViewPager(viewPager);


            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }


    public void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Announcements(), "Announcements");
        adapter.addFrag(new GDG(),"GDG");
        adapter.addFrag(new ACM(),"ACM");
        adapter.addFrag(new Drishti(),"Drishti");
        adapter.addFrag(new LAC(),"LAC");
        adapter.addFrag(new SAE(),"SAE");
        adapter.addFrag(new CHRD(),"CHRD");
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);


    }
    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position){
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chapters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            viewPager.setCurrentItem(0);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this,AboutUs.class));
        }  else if (id == R.id.signout3) {
            firebaseAuth2.signOut();
            finish();
            startActivity(new Intent(this,MainActivity.class));
      
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
