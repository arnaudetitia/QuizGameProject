package com.example.arnaudetitia.quizgameproject.ui.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.R;
import com.example.arnaudetitia.quizgameproject.ui.dialog.ColorSettingDialog;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends Activity {

    List<String> mSettingList;
    ArrayAdapter<String> mSettingListAdapter;
    ListView mSettingListView;

    final public Context mContext = this;

    FragmentManager mFragManager = getFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mSettingList = new ArrayList<>();
        mSettingList.add("Couleur");
        mSettingList.add("Musique");
        mSettingList.add("Son");
        mSettingList.add("Aide");
        mSettingListAdapter = new SettingsAdapter(this,android.R.layout.simple_list_item_1);
        mSettingListView = (ListView) findViewById(R.id.setting_list);
        mSettingListView.setAdapter(mSettingListAdapter);

        mSettingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        ColorSettingDialog colorDialog = new ColorSettingDialog();
                        colorDialog.show(mFragManager,"Choisir couleur");
                        break;
                    default:
                        break;
                }
            }
        });

    }

    static class Holder{
        TextView text;
    }

    private class SettingsAdapter extends ArrayAdapter<String>
    {
        Holder mHolder;

        public SettingsAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return (mSettingList==null) ? 0 : mSettingList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(SettingsActivity.this).inflate(android.R.layout.simple_list_item_1,null);
                mHolder = new Holder();
                mHolder.text = (TextView) convertView.findViewById(android.R.id.text1);
            }
            else{
                mHolder = (Holder) convertView.getTag();
            }

            mHolder.text.setText(mSettingList.get(position));
            mHolder.text.setTextColor(Color.WHITE);
            return convertView;
        }
    }
}
