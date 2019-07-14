package com.example.flashlight;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Light_Button_View_Background lbvb ;
    ToggleButton tglbtn;
    Flash_Manager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lbvb = findViewById(R.id.background);
        tglbtn = findViewById(R.id.btn_light);
        tglbtn.setOnClickListener(this);
        fm = new Flash_Manager(this);

        if(fm.has_system_flash() == false)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("WARNING : ");
            dialog.setMessage("your device hasn't torch");
            dialog.setNeutralButton("OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    finish();
                }
            });
            dialog.show();
        }
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == tglbtn.getId())
        {
            if(tglbtn.isChecked() == true)
            {
                lbvb.set_light_on();
                fm.turn_on_flash();
            }
            else
            {
                lbvb.set_light_off();
                fm.turn_off_flash();
            }
        }
    }
}
