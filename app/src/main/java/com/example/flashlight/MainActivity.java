package com.example.flashlight;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Light_Button_View_Background lbvb ;
    ToggleButton tglbtn;
    TextView tv_sos;
    Flash_Manager fm;
    ImageView iv_info;
    boolean sos_enable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lbvb = findViewById(R.id.background);
        tglbtn = findViewById(R.id.btn_light);
        tglbtn.setOnClickListener(this);
        tv_sos = findViewById(R.id.tv_sos);
        tv_sos.setOnClickListener(this);
        iv_info = findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
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
        else if(view.getId() == tv_sos.getId())
        {
            if(sos_enable == false)
            {
                fm.sos(true , lbvb);
                sos_enable = true;
            }
            else
            {
                fm.sos(false , lbvb);
                sos_enable = false;
            }
        }
        else if(view.getId() == iv_info.getId())
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("info");
            dialog.setMessage("www.sinasoheili79@gmail.com");
            dialog.show();
        }
    }
}
