package com.royalhouse.coffiegarden.Constant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.royalhouse.coffiegarden.R;

import java.util.List;

public class SuperDialog {
    Context context;
    private static  Dialog progressDialog;

    public SuperDialog() {
    }

    public SuperDialog(Context context) {
        this.context = context;
    }


    public void createProgressDialog(LayoutInflater inflater, String title, Context context1){
        AlertDialog.Builder view = new AlertDialog.Builder(context1);
        View v = inflater.inflate(R.layout.progress_dialog,null);
        final TextView textView = v.findViewById(R.id.txtProgressLoading);
        textView.setText(title);
        view.setView(v);
        view.setCancelable(false);
        progressDialog = view.create();
    }

    public void show(){
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.getWindow().setDimAmount(0);
        progressDialog.show();
    }

    public void hide(){
        progressDialog.dismiss();
    }

    public void showDialogWithEditText(String title, boolean numberInputType, int length, String hint, final InputSenderDialogListener listener){
        final String input_text;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        LinearLayout parentLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(45, 45, 45, 10);



        final EditText input = new EditText(context);
        input.setMaxWidth(length);
        input.setHint(hint);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setLayoutParams(layoutParams);
        if(numberInputType)input.setInputType(InputType.TYPE_CLASS_NUMBER);
        parentLayout.addView(input);
        builder.setView(parentLayout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(listener!=null){
                    listener.onOK(input.getText().toString());
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void showDialogWithRadio(String title, List<String> option, int deffault, final InputSenderDialogListener listener){
        final String input_text;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        LinearLayout parentLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(45, 45, 45, 10);

        final RadioGroup ll = new RadioGroup(context);
        ll.setLayoutParams(layoutParams);
        ll.setOrientation(LinearLayout.VERTICAL);
        for (int row = 0; row < 1; row++) {


            for (int i = 0; i < option.size(); i++) {
                RadioButton rdbtn = new RadioButton(context);
                if(i==deffault){
                    rdbtn.setChecked(true);
                }
                rdbtn.setId(i);
                rdbtn.setText(option.get(i));
                ll.addView(rdbtn);

            }

        }

        parentLayout.addView(ll);
        builder.setView(parentLayout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(listener!=null){
                    String val = String.valueOf(ll.getCheckedRadioButtonId());
                    listener.onOK(val);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public static void showDialogMessage(Context context, String title, String message){
       AlertDialog.Builder builder = new AlertDialog.Builder(context);
       builder.setTitle(title);
       builder.setMessage(message);
       builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               return;
           }
       });
       builder.create().show();
    }

    public static void showListenerDialogMessage(Context context, String title, String message, final InputSenderDialogListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onOK("");
            }
        });
        builder.create().show();
    }


    public static void showListenerOkCancelDialogMessage(Context context, String title, String message, final InputSenderDialogListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onOK("");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onCancel("");
            }
        });
        builder.create().show();
    }

    public interface InputSenderDialogListener{
        public abstract void onOK(String value);
        public abstract void onCancel(String value);
    }





}
