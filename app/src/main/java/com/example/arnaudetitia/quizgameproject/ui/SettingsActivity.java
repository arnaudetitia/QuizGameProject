package com.example.arnaudetitia.quizgameproject.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends Activity {

    List<String> mSettingList;
    ArrayAdapter<String> mSettingListAdapter;
    ListView mSettingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mSettingList = new ArrayList<>();
        mSettingList.add("Couleur");
        mSettingList.add("Option 2");
        mSettingList.add("Option 3");
        mSettingListAdapter = new SettingsAdapter(this,android.R.layout.simple_list_item_multiple_choice);
        mSettingListView = (ListView) findViewById(R.id.setting_list);
        mSettingListView.setAdapter(mSettingListAdapter);
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
                convertView = LayoutInflater.from(SettingsActivity.this).inflate(android.R.layout.simple_list_item_multiple_choice,null);
                mHolder = new Holder();
                mHolder.text = (TextView) convertView.findViewById(android.R.id.text1);
            }
            else{
                mHolder = (Holder) convertView.getTag();
            }

            mHolder.text.setText(mSettingList.get(position));
            return convertView;
        }
    }
}
