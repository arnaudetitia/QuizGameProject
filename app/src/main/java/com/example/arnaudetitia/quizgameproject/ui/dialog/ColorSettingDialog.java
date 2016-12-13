package com.example.arnaudetitia.quizgameproject.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arnaudetitia.quizgameproject.R;
import com.example.arnaudetitia.quizgameproject.ui.activity.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arnaud ETITIA on 13/12/2016.
 */
public class ColorSettingDialog extends DialogFragment {

    public ColorSettingDialog() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.colorDialogTitle)
                .setSingleChoiceItems(R.array.colors_array, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String [] colors = getResources().getStringArray(R.array.colors_array);
                        Toast.makeText(getContext(),colors[which],Toast.LENGTH_LONG).show();
                    }
                })
                .setPositiveButton(R.string.valid_setting_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel_seting_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }


}
