package com.example.arnaudetitia.quizgameproject.ui.dialog;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import android.widget.Toast;

import com.example.arnaudetitia.quizgameproject.QuizApp;
import com.example.arnaudetitia.quizgameproject.R;

/**
 * Created by Arnaud ETITIA on 13/12/2016.
 */
public class ColorSettingDialog extends DialogFragment {

    int mColorChosenIndex;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.colorDialogTitle)
                .setSingleChoiceItems(R.array.colors_array, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mColorChosenIndex = which;
                    }
                })
                .setPositiveButton(R.string.valid_setting_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QuizApp app = (QuizApp) getActivity().getApplication();
                        app.changeColor(mColorChosenIndex);
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
