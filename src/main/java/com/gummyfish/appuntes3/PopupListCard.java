package com.gummyfish.appuntes3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PopupListCard extends AppCompatActivity {

    String title;
    String desc;
    String puntos;
    String puntuacion;
    String imageReceiver;
    int profilePoints = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_popup_list_card);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //Your toolbar is now an action bar and you can use it like you always do, for example:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get all info of the apuntes to show
        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        desc = intent.getStringExtra("Desc");
        puntos = intent.getStringExtra("Puntos");
        puntuacion = intent.getStringExtra("Puntuacion");
        imageReceiver = intent.getStringExtra("Image");

        // Show the info received in layout
        TextView titleTextView = (TextView) findViewById(R.id.popup_title);
        titleTextView.setText(title);
        TextView descTextView = (TextView) findViewById(R.id.popup_desc);
        descTextView.setText(desc);
        Button button = (Button) findViewById(R.id.buy_apuntes_button);
        button.setText(puntos + " points");
        ImageView imageView = (ImageView) findViewById(R.id.popup_image);
        imageView.setImageResource(Integer.valueOf(imageReceiver));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buyApuntes(View view){

        if ( profilePoints >= Integer.parseInt(puntos)){

            Toast.makeText(this,"Puedes comprar estos apuntes!",Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this,"No tienes puntos suficientes para comprar esto, ahorra!!!",Toast.LENGTH_SHORT).show();
        }
    }

    public void imagePreview(View view){

        Toast.makeText(this,"Qué pasaaaaaaaa!",Toast.LENGTH_SHORT).show();
    }



}
