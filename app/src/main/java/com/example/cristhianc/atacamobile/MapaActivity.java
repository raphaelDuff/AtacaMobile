package com.example.cristhianc.atacamobile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class MapaActivity extends AppCompatActivity {


    float x,y;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        x = getIntent().getExtras().getFloat("coordx");
        y = getIntent().getExtras().getFloat("coordy");

        img = (ImageView)findViewById(R.id.imgMapa);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        int width = img.getMeasuredWidth();
        int height = img.getMeasuredHeight();

        Bitmap bmp = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Paint mpaint = new Paint();
        mpaint.setColor(Color.RED);
        Canvas mCanvas = new Canvas(bmp);

        mCanvas.drawBitmap(bmp,0,0,null);

        mCanvas.drawCircle(x,y,25,mpaint);

        img.setImageBitmap(bmp);
    }

}
