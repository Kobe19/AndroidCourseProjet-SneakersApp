package danielguirol.project.sneakersapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncJSONData extends AsyncTask<String, Void, JSONObject> {
    URL url;
    JSONObject jsonb;
    String img;
    String brand;
    String id;
    String colorway;
    String shoe;
    String title;
    String year;
    String name;
    String price;
    MyAdapter adapter;


    public AsyncJSONData(MyAdapter adapter) {
        this.adapter = adapter;

    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            // Open connexion to this url

            url = new URL("https://api.thesneakerdatabase.com/v1/sneakers?limit=100");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                //Get an inputstream to get the bytes and get a reader to to turn it into string
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String s = readStream(in);

                //just to test that all is working properly
                Log.i("JFList", "second http working");

                // Getting here all the object from the link in a JSON Format
                jsonb = new JSONObject(s);


            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jsonb;
    }

    @Override
    protected void onPostExecute(JSONObject jsonb) {


        try {

            //extracting data array from json string
            JSONArray ja_data = jsonb.getJSONArray("results");
            int length = ja_data.length();

            for (int i = 0; i < length; i++) {
                JSONObject jObj = ja_data.getJSONObject(i);
                JSONObject jObj2 = jObj.getJSONObject("media");

                img = jObj2.getString("imageUrl");
                brand = jObj.getString("brand");
                id = jObj.getString("id");
                colorway = jObj.getString("colorway");
                name = jObj.getString("name");
                year = jObj.getString("year");
                title = jObj.getString("title");
                shoe = jObj.getString("shoe");
                price = jObj.getString("retailPrice");

                if (img.equals("null")){
                    img = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";
                }else{
                JSONData data = new JSONData();
                data.setUrl(img);
                data.setBrand(brand);
                data.setId(id);
                data.setColorway(colorway);
                data.setYear(year);
                data.setTitle(title);
                data.setShoe(shoe);
                data.setName(name);
                data.setPrice(price);

                adapter.Add(data);

                adapter.notifyDataSetChanged();
                }
                //some tests
                Log.i("JFL3", "Adding to adapter url : " + img);
                Log.i("JFL3", "Adding to adapter brand : " + brand);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Reading the stream and convert bytes into text
    private String readStream(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        in.close();
        return sb.toString();
    }
}
