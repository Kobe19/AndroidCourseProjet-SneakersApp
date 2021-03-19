package danielguirol.project.sneakersapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.core.content.ContextCompat.getSystemService;

public class RegisterFragment extends Fragment {
    DatabaseHelper db;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;
    private TextView txt6;
    private Button reg;
    private NotificationHelper notifHelper;
    private LinearLayout Ly;

    //Get the context of the activity to store the data information in the database because we're not in an activity but a fragment
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        db = new DatabaseHelper(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Call The Inflate layout element for this fragment and affecting them to local variables
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        txt1 = view.findViewById(R.id.txt1);
        txt2 = view.findViewById(R.id.txt2);
        txt3 = view.findViewById(R.id.txt3);
        txt4 = view.findViewById(R.id.txt4);
        txt5 = view.findViewById(R.id.txt5);
        txt6 = view.findViewById(R.id.txt6);
        reg = view.findViewById(R.id.reg);
        Ly = view.findViewById(R.id.frag1);
        Load_setting();

        //Instantiate the notification helper to call the get manage method
        notifHelper = new NotificationHelper(getActivity());

        /* ************************************************************************************************************************************************************************************************************
         *          GET USER'S DATA, VERIFY IF IT'S EMPTY AND INSERT IT IN THE DATA BASE WITH DIFFERENT NOTIFICATION                                                                                                  *                                                                                                                                                                                                           *
         * ************************************************************************************************************************************************************************************************************/
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = txt1.getText().toString();
                String t2 = txt2.getText().toString();
                String t3 = txt3.getText().toString();
                String t4 = txt4.getText().toString();
                String t5 = txt5.getText().toString();
                String t6 = txt6.getText().toString();
                if (t1.isEmpty() || t2.isEmpty() || t3.isEmpty() || t4.isEmpty() || t5.isEmpty() || t6.isEmpty()) {
                    Toast.makeText(getActivity(), "please fill the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean chk = db.checkuser(t2);
                    if (chk == true) {
                        boolean insert = db.insert(t1, t2, t3, t4, t5, t6);
                        if (insert == true) {
                            Toast.makeText(getActivity(), "Registered Sucessful", Toast.LENGTH_SHORT).show();

                            //Removing text in the boxes
                            txt1.setText(" ");
                            txt2.setText(" ");
                            txt3.setText(" ");
                            txt4.setText(" ");
                            txt5.setText(" ");
                            txt6.setText(" ");

                            //Stating the Notification
                            String user = t2;
                            sendOnChannel("Register Successful", "Welcome to the Community "+ user +" Now get connect to see more content");

                        }
                    }else if(chk == false){
                        Toast.makeText(getActivity(), "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    /* ************************************************************************************************************************************************************************************************************
     *                       METHOD TO CALL THE NOTIFICATION HELPER AND SET THE TITLE AND MESSAGE OF THE NOTIFICATION                                                                                             *                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/

    public void sendOnChannel(String title, String message){
        NotificationCompat.Builder nb = notifHelper.getchanelnotif(title, message);
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