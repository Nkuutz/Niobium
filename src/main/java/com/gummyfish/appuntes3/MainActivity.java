package com.gummyfish.appuntes3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener{

    ListView listView;
    List<RowItem> rowItems;
    private int navId;
    private static final String NAV_ITEM_ID = "navId";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (null == savedInstanceState){
            navId = R.id.nav_home;
        } else {
            navId = savedInstanceState.getInt(NAV_ITEM_ID);
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navheader = navigationView.getHeaderView(0);
        ImageView profilePic = (ImageView)navheader.findViewById(R.id.profileImageView);
        Picasso.with(this).load(GoogleSignInActivity.personPhoto).into(profilePic);

        TextView profileName = (TextView)navheader.findViewById(R.id.profileUserName);
        profileName.setText(GoogleSignInActivity.personName);

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < DownloadFromServer.titles.length; i++) {
            RowItem item = new RowItem(DownloadFromServer.images[i], DownloadFromServer.titles[i],
                    DownloadFromServer.descriptions[i], DownloadFromServer.puntos[i],
                    DownloadFromServer.puntuacion[i]);
            rowItems.add(item);
        }

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third - the Array of data

        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.principal_list_item, rowItems) {
        };


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Intent popUpIntent = new Intent(this, PopupListCard.class);
        popUpIntent.putExtra("Title", DownloadFromServer.titles[position]);
        popUpIntent.putExtra("Desc", DownloadFromServer.descriptions[position]);
        popUpIntent.putExtra("Image",DownloadFromServer.images[position].toString());
        popUpIntent.putExtra("Puntuacion", String.valueOf(DownloadFromServer.puntuacion[position]));
        popUpIntent.putExtra("Puntos", String.valueOf(DownloadFromServer.puntos[position]));

        startActivity(popUpIntent);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
            BaseActivity.hideProgressDialog();
        }
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

        } else if (navId == R.id.nav_my_profile){

            Intent profileIntent = new Intent(this, MyProfile.class);
            startActivity(profileIntent);

        } else if (navId == R.id.nav_buy_puntos) {

            Intent buyPuntosIntent = new Intent(this, BuyPuntosActivity.class);
            startActivity(buyPuntosIntent);

        } else if (navId == R.id.nav_settings){

            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);

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

            // GoogleSignInActivity googleSignInActivity = new GoogleSignInActivity();
            // googleSignInActivity.signOut();
            // GoogleSignInActivity.googleLogout();
            Intent signinIntent = new Intent(this, GoogleSignInActivity.class);
            startActivity(signinIntent);
            // if (GoogleSignInActivity.mGoogleApiClient.isConnected()) {
                // Auth.GoogleSignInApi.signOut(GoogleSignInActivity.mGoogleApiClient);
                // Toast.makeText(this,"Está conectado",Toast.LENGTH_SHORT).show();
            // } else {
                // Toast.makeText(this,"No está conectado",Toast.LENGTH_SHORT).show();
            // }

            // Intent intent = new Intent(this, GoogleSignInActivity.class);
            // startActivity(intent);

            // finish();
            // BaseActivity.hideProgressDialog();

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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.gummyfish.appuntes3/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.gummyfish.appuntes3/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    protected void onSaveInstanceState (final Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, navId);
    }
}
