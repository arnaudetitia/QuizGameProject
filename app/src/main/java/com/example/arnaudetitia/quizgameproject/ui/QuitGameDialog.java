package com.example.arnaudetitia.quizgameproject.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.arnaudetitia.quizgameproject.R;

/**
 * Created by Arnaud ETITIA on 19/09/2016.
 */
public class QuitGameDialog extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_question_string)
        .setPositiveButton(R.string.dialog_quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameActivity gameActivity = (GameActivity) getActivity();
                gameActivity.quitGame();
                dialog.dismiss();
            }
        })
        .setNegativeButton(R.string.dialog_stay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameActivity gameActivity = (GameActivity) getActivity();
                gameActivity.restartGame();
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}
