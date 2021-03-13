package danielguirol.project.sneakersapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        db = new DatabaseHelper(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        txt1 = view.findViewById(R.id.txt1);
        txt2 = view.findViewById(R.id.txt2);
        txt3 = view.findViewById(R.id.txt3);
        txt4 = view.findViewById(R.id.txt4);
        txt5 = view.findViewById(R.id.txt5);
        txt6 = view.findViewById(R.id.txt6);
        reg = view.findViewById(R.id.reg);

        notifHelper = new NotificationHelper(getActivity());

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

                            String user = t2;
                            sendOnChannel("Connected Successfully", "Welcome "+ user +", Enjoy and share around you ");

                        }
                    }else if(chk == false){
                        Toast.makeText(getActivity(), "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    public void sendOnChannel(String title, String message){
        NotificationCompat.Builder nb = notifHelper.getchanelnotif(title, message);
        notifHelper.getManage().notify(2, nb.build());
    }

}