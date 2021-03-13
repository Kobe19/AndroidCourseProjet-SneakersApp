package danielguirol.project.sneakersapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.MenuItemCompat;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;


public class ThirdActivity extends AppCompatActivity {

    private ListView list;
    private SearchView app_bar_search;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        list = (ListView) findViewById(R.id.list);

        //Here it's a way of retrieving data in the myadapter from the getView and set in the list of our layout
        adapter = new MyAdapter(this);
        list.setAdapter(adapter);

        //Of course we need to execute the AsyncTask to get the data to treat and from the JSON URL
        new AsyncJSONData(adapter).execute();
        Log.i("JFL", "Run successfully");
    }


    /* ************************************************************************************************************************************************************************************************************
     *                       PROGRAMMING THE SEARCH FONCTION IN THE MENU                                                                                                                                                          *                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        //finding the item
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        //set the search view
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //Filter the list depending on what was entered in the search bar
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //implementing the choices of preferences menu
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
}