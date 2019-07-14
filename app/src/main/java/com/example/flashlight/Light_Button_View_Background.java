package com.example.flashlight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Light_Button_View_Background extends View
{
    private Paint p_background;
    private Paint p_circle;

    private int radius_center = 200;
    private int radius_small_circle = 5;
    private int count_ring = 3;

    public Light_Button_View_Background(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        init_p_background();
        init_p_circle();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //draw background
        canvas.drawRect(0 , 0 , getWidth() , getHeight() , p_background);

        //draw circle
        for(int l=0 ; l<count_ring ; l++)
        {
            for(int x = -radius_center ; x<= radius_center; x++)
            {
                for(int y = -radius_center ; y<= radius_center; y++)
                {
                    if((Math.pow(x,2) + Math.pow(y,2)) == Math.pow(radius_center,2))
                    {
                        canvas.drawOval(((getWidth()/2)+x)-radius_small_circle , ((getHeight()/2)-y)-radius_small_circle ,((getWidth()/2)+x)+radius_small_circle , ((getHeight()/2)-y)+radius_small_circle, p_circle);
                    }
                }
            }

            radius_center+=100;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width  = getWidth()  - getPaddingRight() - getPaddingLeft();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        width  = resolveSizeAndState(width , widthMeasureSpec , 0);
        height = resolveSizeAndState(height , heightMeasureSpec , 0);

        setMeasuredDimension(width , height);
    }

    private void init_p_background()
    {
        p_background = new Paint();
        p_background.setColor(getResources().getColor(R.color.background));
    }

    private void init_p_circle()
    {
        p_circle = new Paint();
        p_circle.setColor(getResources().getColor(R.color.circle_off));
    }

    public void set_light_on()
    {
        p_circle.setColor(getResources().getColor(R.color.circle_on));
        invalidate();
    }

    public void set_light_off()
    {
        p_circle.setColor(getResources().getColor(R.color.circle_off));
        invalidate();
    }
}
