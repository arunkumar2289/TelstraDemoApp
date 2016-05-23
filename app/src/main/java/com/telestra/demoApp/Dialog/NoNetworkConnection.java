package com.telestra.demoApp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.telestra.demoApp.R;

/**
 * Created for Alert dialogs to users
 */
public class NoNetworkConnection  extends Dialog implements View.OnClickListener{

    Context context; //Context for the dialog

    String header; //Header  for the alert dialog

    String message;  //Message  for the alert dialog

   //Constructor for this class
    public NoNetworkConnection(Context context,String header,String message) {
        super(context);
        this.context = context;
        this.header = header;
        this.message = message;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_no_network);
        ((TextView)findViewById(R.id.alert_title)).setText(header); //Setting header
        ((TextView)findViewById(R.id.alert_message)).setText(message);//Setting up message
        Button dialogButtonOk = (Button) findViewById(R.id.alert_ok);//Ok button
        dialogButtonOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss(); //Dismiss the dialog
    }
}
