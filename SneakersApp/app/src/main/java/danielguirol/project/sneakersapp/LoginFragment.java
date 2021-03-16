package danielguirol.project.sneakersapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {
    DatabaseHelper db;
    private Button suscribe, login;
    private TextView log, pwd;
    String value;
    private LinearLayout Ly;
    private NotificationHelper notifHelper;


    //Get the context of the activity to store the data information in the database because we're not in an activity but a fragment
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        db = new DatabaseHelper(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Call The Inflate layout element for this fragment and affecting them to local variables
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        login = view.findViewById(R.id.login);
        log = view.findViewById(R.id.log);
        pwd = view.findViewById(R.id.pwd);
        Ly = view.findViewById(R.id.frag2);
        Load_setting();

        //Instantiate the notification helper to call the get manage method
        notifHelper = new NotificationHelper(getActivity());

        /* ************************************************************************************************************************************************************************************************************
         *          GET LOGIN USER'S DATA, VERIFY IF THEY'RE CORRECT AND LOG IN                                                                                                                                       *                                                                                                                                                                                                           *
         * ************************************************************************************************************************************************************************************************************/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = log.getText().toString();
                String t2 = pwd.getText().toString();
                boolean chkin = db.checkuandpass(t1,t2);
                if (chkin == true) {
                    value = t1;
                    Toast.makeText(getActivity(), "Connexion Sucessful", Toast.LENGTH_SHORT).show();

                    //Send a notification of connexion
                    sendOnChannel2("Connected Successfully", "Welcome "+ value +", Enjoy and share around you ");

                    //Drive to another activity
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("key", value);
                    startActivity(intent);


                }else {
                    //In case the verification fail
                    Toast.makeText(getActivity(), "Please check your login and password or Create an account", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void sendOnChannel2(String title, String message){
        NotificationCompat.Builder nb = notifHelper.getchanelnotif2(title, message);
        notifHelper.getManage().notify(2, nb.build());
    }

    /* ***********************************************************************************************************************************************************************************************************
     *                       ADDING AND PROGRAMMING MENU                                                                                                                                                          *                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    private void Load_setting() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        Boolean mytag = sp.getBoolean("Dark", false);
        if (mytag) {
            Ly.setBackgroundColor(Color.parseColor("#222222"));
        } else {
            // set the colors back
            Ly.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public void onResume() {
        Load_setting();
        super.onResume();
    }
}