package danielguirol.project.sneakersapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.accounts.Account;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private LinearLayout Ly;
    private ImageView phone, msg, join;
    private ImageButton imgB;
    public TextView tex2;
    DatabaseHelper db;
    private static final int REQUEST_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        phone = findViewById(R.id.phone);
        msg = findViewById(R.id.msg);
        join = findViewById(R.id.join);
        imgB = findViewById(R.id.imgB);
        tex2 = findViewById(R.id.texte2);

        //Retrieving datas(The name of the user) sent from another activity
        String username = getIntent().getStringExtra("key");
        System.out.println("from the main activity "+username);
        tex2.setText(username);

        //On click, join the the list of shoes if you are already a user
        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tex2.getText().equals("")){
                    openDialog();
                }else{
                    Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                    startActivity(intent);
                }
            }
        });

        //On click, make the call to a predifined number
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        //On click, send a message
        msg.setOnClickListener(new View.OnClickListener() {
            final int penumbra = 0144225510;
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + penumbra));
                intent.putExtra("sms_body", "Hello need to contact you !");
                startActivity(intent);
            }
        });

        //On click, access the login and the register activity
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        /* ***********************************************************************************************************************************************************************************************************
         *                       SLIDER                                                                                                                                                           *                  *                                                                                                                                                                                                           *
         * ************************************************************************************************************************************************************************************************************/

        ImageSlider im = findViewById(R.id.slide);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://static.lexpress.fr/medias_9305/w_736,h_320,c_crop,x_0,y_90/w_1000,h_563,c_fill,g_north/v1392655689/nike-logo_4764201.png","Welcome to a new Experience, Discorer our Undeniable Sneakers", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://thesneakersbible.fr/wp-content/uploads/2021/02/air-jordan-1-high-og-university-blue-555088-134-release-date.jpg","Wonderful shoes !",ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://i.pinimg.com/originals/20/34/51/20345145a2eb3edc193b57919a344ca1.jpg","Found your attractive price !",ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://www.sportbusinessmag.com/wp-content/uploads/2020/12/jordan-11-adapt.jpg","Come over and see More !!",ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fwp-content%2Fblogs.dir%2F11%2Ffiles%2F2019%2F01%2Fyeezy-boost-700-inertia-photo-nouveau-sortie-01.jpg","Welcome to sneaker world",ScaleTypes.CENTER_CROP));
        im.setImageList(slideModels);

        Ly = (LinearLayout) findViewById(R.id.principal);
        Load_setting();
    }

    /* ***********************************************************************************************************************************************************************************************************
     *                       ADDING AND PROGRAMMING MENU                                                                                                                                                          *                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    private void Load_setting() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        Boolean mytag = sp.getBoolean("Dark", false);
        if (mytag) {
            Ly.setBackgroundColor(Color.parseColor("#222222"));
        } else {
            // set the colors back
            Ly.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        String list = sp.getString("ORIENTATION", "false");
        if ("1".equals(list)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
        } else if ("2".equals(mytag)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if ("3".equals(mytag)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    public void openDialog(){
        MyDialog md = new MyDialog();
        md.show(getSupportFragmentManager(),"information");
    }

    /* ***********************************************************************************************************************************************************************************************************
     *                       ADDING AND PROGRAMMING MENU                                                                                                                                                         *                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu1:
                Intent i = new Intent(this,Preferences.class);
                startActivity(i);
            case R.id.menu2:
                Intent j = new Intent(this,Preferences.class);
                startActivity(j);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Load_setting();
        super.onResume();
    }



    /* ***********************************************************************************************************************************************************************************************************
    *                       PERFORMING THE CALL                                                                                                                                                                  *                  *                                                                                                                                                                                                           *
    * ************************************************************************************************************************************************************************************************************/


    //In order to avoid Access denial permission we must declare in the manifest but also explicitly this such as dangerous permission
    private void makePhoneCall(){
        String phone = "0166776688";

        //Parsing the permission we want to check for
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            // check first if we have the permission to make the phone call
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

        } else {
            //start the call if we already have the permission otherwise we go back in the if to get the permission
            String dial = "tel:" + phone;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            // now here we check if the one permissions we are calling is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}