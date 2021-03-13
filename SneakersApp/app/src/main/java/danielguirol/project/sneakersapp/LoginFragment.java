package danielguirol.project.sneakersapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {
    DatabaseHelper db;
    private Button suscribe, login;
    private TextView log, pwd;
    String value;
    private NotificationHelper notifHelper;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        db = new DatabaseHelper(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        suscribe = view.findViewById(R.id.suscribe);
        login = view.findViewById(R.id.login);
        log = view.findViewById(R.id.log);
        pwd = view.findViewById(R.id.pwd);

        notifHelper = new NotificationHelper(getActivity());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = log.getText().toString();
                String t2 = pwd.getText().toString();
                boolean chkin = db.checkuandpass(t1,t2);
                if (chkin == true) {
                    value = t1;
                    Toast.makeText(getActivity(), "Connexion Sucessful", Toast.LENGTH_SHORT).show();

                    sendOnChannel2("Register Successful", "Welcome to the Community "+ value +" Now get connect to see more content");

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("key", value);
                    startActivity(intent);


                }else {
                    Toast.makeText(getActivity(), "Please check your login and password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void sendOnChannel2(String title, String message){
        NotificationCompat.Builder nb = notifHelper.getchanelnotif2(title, message);
        notifHelper.getManage().notify(2, nb.build());
    }
}