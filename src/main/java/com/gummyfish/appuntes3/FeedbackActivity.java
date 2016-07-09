package com.gummyfish.appuntes3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.squareup.picasso.Picasso;

/**
 * Created by enekourunuela on 13/02/16.
 */
public class FeedbackActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private EditText message;

    private int navId;
    private static final String NAV_ITEM_ID = "navId";

    // Defined Array description values to show in ListView
    public static final String[] recipient = new String[]{"apuntseuetib@gmail.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (null == savedInstanceState){
            navId = R.id.nav_feedback;
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

        message = (EditText) findViewById(R.id.feedbackEditText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.sendFeedbackButton){

            sendEmail();
            message.setText("");
        }

        return super.onOptionsItemSelected(item);
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

            Intent buyPuntosIntent = new Intent(this, BuyPuntosActivity.class);
            startActivity(buyPuntosIntent);

        } else if (navId == R.id.nav_settings) {

        } else if (navId == R.id.nav_share) {

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Descargate esta app! Es una pasada!");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (navId == R.id.nav_feedback) {

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

    // private void clearChecked() {
    //    int size = navigationView.getMenu().size();
    //    for (int i = 0; i < size; i++) {
    //        navigationView.getMenu().getItem(i).setChecked(false);
    //    }
    // }

    protected void sendEmail(){

        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        // promps email clients only
        email.setType("message/rfc822");

        email.putExtra(Intent.EXTRA_EMAIL, recipient);
        email.putExtra(Intent.EXTRA_SUBJECT, "Feedback Appuntes");
        email.putExtra(Intent.EXTRA_TEXT,message.getText().toString());

        try {
            // The user can choose the email client
            startActivity(Intent.createChooser(email,getText(R.string.chooser_message)));

        } catch (android.content.ActivityNotFoundException ex){

            Toast.makeText(FeedbackActivity.this,getText(R.string.no_email_client),
                    Toast.LENGTH_LONG).show();
        }
    }

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
