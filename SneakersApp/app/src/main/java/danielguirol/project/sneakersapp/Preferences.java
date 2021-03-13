package danielguirol.project.sneakersapp;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import androidx.annotation.Nullable;

public class Preferences extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.params);
        Load_setting();
    }

    private void Load_setting() {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        Boolean mytag = sp.getBoolean("Dark", false);
        if (mytag) {
            getListView().setBackgroundColor(Color.parseColor("#222222"));
        } else {
            getListView().setBackgroundColor(Color.parseColor("#ffffff"));
        }

        SwitchPreference SP = (SwitchPreference) findPreference("Dark");

        SP.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference params, Object Obj) {
                Boolean response = (boolean) Obj;
                if (response) {
                    getListView().setBackgroundColor(Color.parseColor("#222222"));
                } else {
                    getListView().setBackgroundColor(Color.parseColor("#ffffff"));
                }
                return true;
            }
        });

        ListPreference LP = (ListPreference) findPreference("ORIENTATION");

        String list = sp.getString("ORIENTATION", "false");
        if ("1".equals(list)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
            LP.setSummary(LP.getEntry());
        } else if ("2".equals(mytag)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            LP.setSummary(LP.getEntry());
        } else if ("3".equals(mytag)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            LP.setSummary(LP.getEntry());
        }

        LP.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference params, Object Obj) {
                String items = (String) Obj;
                if (params.getKey().equals("ORIENTATION")) {
                    switch (items) {
                        case "1":
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                            break;
                        case "2":
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            break;
                        case "3":
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                            break;
                    }

                    //Set the summary when changing orientation
                    ListPreference LPP = (ListPreference) params;
                    LPP.setSummary(LPP.getEntries()[LPP.findIndexOfValue(items)]);
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        Load_setting();
        super.onResume();
    }
}

