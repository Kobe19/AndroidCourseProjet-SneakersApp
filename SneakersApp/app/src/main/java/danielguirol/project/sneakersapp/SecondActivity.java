package danielguirol.project.sneakersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class SecondActivity extends AppCompatActivity {

    private Button btn1, btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        /* ***********************************************************************************************************************************************************************************************************
         *                       ATTACH THE ACTIVITY TO EACH FRAGMENT TO HANDLE THE CONTEXT IN SOME CASE                                                                                                             *                  *                                                                                                                                                                                                           *
         * ***********************************************************************************************************************************************************************************************************/
        LoginFragment lg = new LoginFragment();
        lg.onAttach(this);
        RegisterFragment rg = new RegisterFragment();
        rg.onAttach(this);

        /* ************************************************************************************************************************************************************************************************************
         *                       DISPLAY FRAGMENT 1 AND FRAGMENT 2                                                                                                                                                    *                  *                                                                                                                                                                                                           *
         * ************************************************************************************************************************************************************************************************************/

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment Myfraga = new LoginFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragcontainer, Myfraga)
                        .commit();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment Myfraga = new RegisterFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragcontainer, Myfraga)
                        .commit();
            }
        });
    }


    /* ************************************************************************************************************************************************************************************************************
     *                       ADDING AND PROGRAMMING MENU                                                                                                                                                          *                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Switch the preferences choices
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