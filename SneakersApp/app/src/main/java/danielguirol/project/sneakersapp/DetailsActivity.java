package danielguirol.project.sneakersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class DetailsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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

        String id = getIntent().getStringExtra("Id");
        String Brand = getIntent().getStringExtra("Brand");
        String Colorway = getIntent().getStringExtra("Colorway");
        String name = getIntent().getStringExtra("name");
        String shoe = getIntent().getStringExtra("shoe");
        String title = getIntent().getStringExtra("title");
        String year = getIntent().getStringExtra("year");
        String url = getIntent().getStringExtra("url");
        String price = getIntent().getStringExtra("price");

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

            }
        });

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
                System.out.println("Something goes wrong");
            }
        });

        queue.add(imageRequest);

    }
}