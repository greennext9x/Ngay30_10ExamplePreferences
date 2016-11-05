package app.hoangcuong.com.ngay30_10examplepreferences;

import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //addPreferencesFromResource(R.xml.preferences);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Myfragment())
                .commit();
    }

    public static class Myfragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            PreferenceManager.setDefaultValues(getActivity(),
                    R.xml.preferences,false);
        }

        @Override
        public void onResume() {
            super.onResume();
            PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(this);
            if (PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean("checkbox_tip_percent", true))
            {
                findPreference("default_tip").setEnabled(false);
            } else findPreference("default_tip").setEnabled(true);
            //registerOnSharedPreferenceChangeListener
        }

        @Override
        public void onPause() {
            super.onPause();
            PreferenceManager.getDefaultSharedPreferences(getActivity()).unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("checkbox_tip_percent")) {
                if (sharedPreferences.getBoolean("checkbox_tip_percent", true)) {
                    findPreference("default_tip").setEnabled(false);
                } else findPreference("default_tip").setEnabled(true);
            }
        }
    }
}


