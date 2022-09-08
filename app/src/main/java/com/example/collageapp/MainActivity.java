package com.example.collageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mainLayout = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        for (int i = 0; i < mainLayout.getChildCount(); i++) {
            if (mainLayout.getChildAt(i) instanceof ConstraintLayout) {
                ConstraintLayout imageTemp = (ConstraintLayout) mainLayout.getChildAt(i);

                imageTemp.setOnTouchListener(new View.OnTouchListener() {
                    float x, y;
                    float dx, dy;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            x = event.getX();
                            y = event.getY();
                        }

                        if (event.getAction() == MotionEvent.ACTION_MOVE) {
                            dx = event.getX() - x;
                            dy = event.getY() - y;
                            v.setX(v.getX() + dx);
                            v.setY(v.getY() + dy);
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            enforceBounds((ConstraintLayout) v);
                        }
                        return true;
                    }
                });
            }
        }
    }

    void enforceBounds(ConstraintLayout v) {
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        Log.i("Coords", v.getX() + ":" + width + ":" + v.getMeasuredWidth());
        if (v.getX() > width - v.getMeasuredWidth()) {
            v.setX(width - v.getMeasuredWidth());
        }
        if (v.getX() < 0) {
            v.setX(0);
        }
        if (v.getY() > height - v.getMeasuredHeight()) {
            v.setY(height - v.getMeasuredHeight());
        }
        if (v.getY() < 0) {
            v.setY(0);
        }
    }
}