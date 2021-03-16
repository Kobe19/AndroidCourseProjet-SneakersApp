package danielguirol.project.sneakersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class DetailsActivity extends AppCompatActivity {

    String id;
    String Brand;
    String Colorway;
    String name;
    String shoe;
    String title;
    String year;
    String url;
    String price;
    private ImageView img1;
    private TextView txt2;
    private TextView txt4;
    private TextView txt6;
    private TextView txt8;
    private TextView txt10;
    private TextView txt12;
    private TextView txt14;
    private TextView txt15;
    private Button btn1;
    private NotificationHelper notifHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Instantiate the notification helper to call the get manage method
        notifHelper = new NotificationHelper(getBaseContext());
        /* ************************************************************************************************************************************************************************************************************
         *                       GETTING THE DATA FROM PUT EXTRA AND SETTING THEM IN THE VIEW                                                                                                                         *                                                                                                                                                                                                           *
         * ************************************************************************************************************************************************************************************************************/
        img1 = findViewById(R.id.img1);
        txt2 = findViewById(R.id.text2);
        txt4 = findViewById(R.id.text4);
        txt6 = findViewById(R.id.text6);
        txt8 = findViewById(R.id.text8);
        txt10 = findViewById(R.id.text10);
        txt12 = findViewById(R.id.text12);
        txt14 = findViewById(R.id.text14);
        txt15 = findViewById(R.id.price);
        btn1 = findViewById(R.id.btn1);

        id = getIntent().getStringExtra("Id");
        Brand = getIntent().getStringExtra("Brand");
        Colorway = getIntent().getStringExtra("Colorway");
        name = getIntent().getStringExtra("name");
        shoe = getIntent().getStringExtra("shoe");
        title = getIntent().getStringExtra("title");
        year = getIntent().getStringExtra("year");
        url = getIntent().getStringExtra("url");
        price = getIntent().getStringExtra("price");

        txt2.setText(id);
        txt4.setText(Brand);
        txt6.setText(Colorway);
        txt8.setText(name);
        txt10.setText(shoe);
        txt12.setText(title);
        txt14.setText(year);
        txt15.setText(price +" €");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel3("Thanks for Trusting Us !", "You will receive all payment info by mail and the Shoe will be posted shortly depend on the localisation");
            }
        });

        /* ************************************************************************************************************************************************************************************************************
         *                       DOWNLOADING IMAGE WITH URL                                                                                                                                                           *                                                                                                                                                                                                          *
         * ************************************************************************************************************************************************************************************************************/
        // here we instantiate a queue in order to visualize the structure it also a way of organising them
        RequestQueue queue = danielguirol.project.sneakersapp.MySingleton.getInstance(this).getRequestQueue();

        // The method that will contain the response
        Response.Listener<Bitmap> rep_listener = response -> {

            img1.setImageBitmap(response);
        };

        ImageRequest imageRequest = new ImageRequest(url, rep_listener, 0, 0, ImageView.ScaleType.CENTER_CROP,
                null, new Response.ErrorListener() {

            // here we check if there is no issue with the Request
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something goes wrong details");
            }
        });

        queue.add(imageRequest);
    }

    public void sendOnChannel3(String title, String message){
        NotificationCompat.Builder nb = notifHelper.getchanelnotif3(title, message);
        notifHelper.getManage().notify(3, nb.build());
    }

    /* ***********************************************************************************************************************************************************************************************************
     *                       ADDING AND PROGRAMMING MENU                                                                                                                                                         *                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        menu.findItem(R.id.menu2).setOnMenuItemClickListener((MenuItem.OnMenuItemClickListener) v -> {
            Intent j = new Intent(this,InfosActivity.class);
            startActivity(j);
            return false;
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu1:
                Intent i = new Intent(this,Preferences.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}