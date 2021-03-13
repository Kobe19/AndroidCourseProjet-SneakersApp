package danielguirol.project.sneakersapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;
import java.util.Vector;

public class MyAdapter extends BaseAdapter implements Filterable{

    //creating a vector that will contains list of link coming from the AsyncFlickrJSONDataForList
    //java.util.Vector<String> Vector = new Vector<String>();
    java.util.Vector<JSONData> Vector = new Vector<JSONData>();
    public java.util.Vector<JSONData> MyinitialData ;
    FilterHelper filterHelper;

    //a variable context to access some application-specific ressources and classes
    Context context_;

    //A constructor to call in the main of the activity
    public MyAdapter(Context context) {
        this.context_ = context;
        this.MyinitialData = Vector;
    }

    // Add method to add take the data into the vector
    public void Add(JSONData data){
        Vector.add(data);
    }


    // retourne le nombre d'objet présent dans notre liste
    @Override
    public int getCount() {
        return Vector.size();
    }

    // retourne un élément de notre liste en fonction de sa position
    @Override
    public Object getItem(int position) {
        return Vector.get(position);
    }

    // retourne l'id d'un élément de notre liste en fonction de sa position
    @Override
    public long getItemId(int position) {
        return position;
    }

    //This getView will handle the display of the information from the vector in a selected view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bitmap resp ;

        try{
            final JSONData str = (JSONData) this.getItem(position);

            //Here we inflate a new view in which we will display the elements
            if (convertView == null) {
                convertView = LayoutInflater.from(context_).inflate(R.layout.imagelayout, parent, false);
            }
            TextView txt1 = (TextView) convertView.findViewById(R.id.texte);
            txt1.setText(str.getBrand());

            // Linking the element with the variable
            ImageView imageView2 = convertView.findViewById(R.id.imageView2);


            // here we instantiate a queue in order to visualize the structure it also a way of organising them
            RequestQueue queue = danielguirol.project.sneakersapp.MySingleton.getInstance(context_).getRequestQueue();

            // The method that will contain the response
            Response.Listener<Bitmap> rep_listener = response -> {

                imageView2.setImageBitmap(response);
            };

            ImageRequest imageRequest = new ImageRequest(str.getUrl(), rep_listener, 0, 0, ImageView.ScaleType.CENTER_CROP,
                    null, new Response.ErrorListener() {

                // here we check if there is no issue with the Request
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Something goes wrong");
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context_, DetailsActivity.class);
                    intent.putExtra("Id", str.getId());
                    intent.putExtra("Brand", str.getBrand());
                    intent.putExtra("Colorway", str.getColorway());
                    intent.putExtra("name", str.getName());
                    intent.putExtra("shoe", str.getShoe());
                    intent.putExtra("title", str.getTitle());
                    intent.putExtra("year", str.getYear());
                    intent.putExtra("url", str.getUrl());
                    intent.putExtra("price", str.getPrice());
                    context_.startActivity(intent);
                }
            });

            queue.add(imageRequest);
        }catch (Exception e){

        }

        return convertView;
    }

    public void setinfo(Vector<JSONData> filtervector){
        this.Vector=filtervector;
    }

    public void refresh(){
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        if(filterHelper == null){
            filterHelper = new FilterHelper(MyinitialData, this,context_);
        }
        return filterHelper;
    }
}

