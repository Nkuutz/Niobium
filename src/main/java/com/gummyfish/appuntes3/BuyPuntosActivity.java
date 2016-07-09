package com.gummyfish.appuntes3;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.squareup.picasso.Picasso;

public class BuyPuntosActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private int navId;
    private static final String NAV_ITEM_ID = "navId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_puntos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (null == savedInstanceState){
            navId = R.id.nav_buy_puntos;
        } else {
            navId = savedInstanceState.getInt(NAV_ITEM_ID);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(navId).setChecked(true);

        View navheader = navigationView.getHeaderView(0);
        ImageView profilePic = (ImageView)navheader.findViewById(R.id.profileImageView);
        Picasso.with(this).load(GoogleSignInActivity.personPhoto).into(profilePic);

        TextView profileName = (TextView)navheader.findViewById(R.id.profileUserName);
        profileName.setText(GoogleSignInActivity.personName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        navId = item.getItemId();
        // clearChecked();
        // navigationView.getMenu().findItem(navId).setChecked(true);

        if (navId == R.id.nav_home) {
            // Handle the action
            super.onBackPressed();

        } else if (navId == R.id.nav_my_profile) {

            Intent profileIntent = new Intent(this, MyProfile.class);
            startActivity(profileIntent);

        } else if (navId == R.id.nav_buy_puntos) {

        } else if (navId == R.id.nav_settings) {

        } else if (navId == R.id.nav_share) {

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Descargate esta app! Es una pasada!");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (navId == R.id.nav_feedback) {

            Intent feedbackIntent = new Intent(this, FeedbackActivity.class);
            startActivity(feedbackIntent);

        } else if (navId == R.id.nav_help) {

        } else if (navId == R.id.nav_log_out){

            // if (GoogleSignInActivity.mGoogleApiClient.isConnected()) {
                // Auth.GoogleSignInApi.signOut(GoogleSignInActivity.mGoogleApiClient);
            // }
            finish();
            BaseActivity.hideProgressDialog();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void buyPuntosCheap (View view){

        Toast.makeText(this,"No crees que es poco?",Toast.LENGTH_SHORT).show();

    }

    public void buyPuntosMid (View view){

        Toast.makeText(this,"Gasta gasta gasta gasta",Toast.LENGTH_SHORT).show();

    }

    public void buyPuntosTop (View view){

        Toast.makeText(this,"Cuanto más caro mejor!",Toast.LENGTH_SHORT).show();

    }

    // private void clearChecked() {
    //    int size = navigationView.getMenu().size();
    //    for (int i = 0; i < size; i++) {
    //        navigationView.getMenu().getItem(i).setChecked(false);
    //    }
    // }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    protected void onSaveInstanceState (final Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, navId);
    }
}
