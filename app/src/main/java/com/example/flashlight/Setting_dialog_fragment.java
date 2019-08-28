package com.example.flashlight;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Setting_dialog_fragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener
{
    private SeekBar sb ;
    private Context context;
    public static final String tag_seekbar = "seekbar_value";

    public Setting_dialog_fragment(Context context)
    {
        this.context = context;
    }

    @Nullable
    @Override
    public View getView()
    {
        View v = View.inflate(context , R.layout.setting_dialog_fragment , null);

        init_seekbar(v);

        return v;
    }

    public void init_seekbar(View v)
    {
        sb = v.findViewById(R.id.sb_speed_sos);
        sb.setOnSeekBarChangeListener(this);
        sb.setMax(800);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            sb.setMin(200);
        }

        SharedPreferences pref = context.getSharedPreferences(Flash_Manager.tag_pref , Context.MODE_PRIVATE);
        int progress = pref.getInt(tag_seekbar , 500);
        sb.setProgress(progress);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar , int i , boolean b)
    {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        SharedPreferences pref = context.getSharedPreferences(Flash_Manager.tag_pref , Context.MODE_PRIVATE);
        pref.edit().putInt(tag_seekbar , seekBar.getProgress()).commit();
    }
}
