package com.example.flashlight;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

public class Flash_Manager
{
    private CameraManager cm;
    private Context context;
    private String camera_id;
    private boolean is_flash_on;

    public Flash_Manager(Context context)
    {
        this.context = context;
        cm = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try
        {
            camera_id = cm.getCameraIdList()[0];
        }
        catch (CameraAccessException e)
        {
            e.printStackTrace();
        }
    }

    public boolean has_system_flash()
    {
        boolean has;
        has = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        return has;
    }

    public void turn_on_flash()
    {
        if(is_flash_on == false)
        {
            try
            {
                cm.setTorchMode(camera_id , true);
                this.is_flash_on = true;
            }
            catch (CameraAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void turn_off_flash()
    {
        if(is_flash_on == true)
        {
            try
            {
                cm.setTorchMode(camera_id , false);
                is_flash_on = false;
            }
            catch (CameraAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

    public boolean is_flash_on()
    {
        return this.is_flash_on;
    }
}

