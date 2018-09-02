package com.agrawalsuneet.squareloaders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.agrawalsuneet.squareloaderspack.loaders.MusicPlayerLoader;
import com.agrawalsuneet.squareloaderspack.loaders.RotatingSquareLoader;
import com.agrawalsuneet.squareloaderspack.loaders.SquareGridLoader;
import com.agrawalsuneet.squareloaderspack.loaders.WaveLoader;
import com.agrawalsuneet.squareloaderspack.loaders.ZipZapLoader;

/**
 * Created by suneet on 10/28/17.
 */

public class MainActivityJava extends AppCompatActivity {

    private LinearLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_zipzap);

        ZipZapLoader zipZapLoader = new ZipZapLoader(this, 40,
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.green),
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.colorAccent),
                true);

        zipZapLoader.setAnimationDuration(500);
        zipZapLoader.setFromScale(1.0f);
        zipZapLoader.setToScale(0.8f);


        WaveLoader waveLoader = new WaveLoader(this, 8, 40,
                200, 20, ContextCompat.getColor(getBaseContext(), R.color.blue));

        waveLoader.setSingleColor(false);
        waveLoader.setRectColorsArray(getResources().getIntArray(R.array.waveloader_colorsarray));
        waveLoader.setInterpolator(new LinearInterpolator());
        waveLoader.setAnimDuration(1000);
        waveLoader.setDelayDuration(100);


        //container.addView(waveLoader);

        MusicPlayerLoader loader = new MusicPlayerLoader(this, 4, 40,
                100, 4, ContextCompat.getColor(this, R.color.blue));
                    /*isSingleColor = false
                    rectColorsArray = resources.getIntArray(R.array.waveloader_colorsarray)*/
        loader.setInterpolator(new LinearInterpolator());
        loader.setAnimDuration(500);
        loader.setDelayDuration(100);

        container.addView(loader);


        RotatingSquareLoader rotatingSquareLoader = new RotatingSquareLoader(this,
                200.0f, 60.0f, ContextCompat.getColor(this, R.color.red));
        rotatingSquareLoader.setAnimDuration(5000);

        container.addView(rotatingSquareLoader);

        SquareGridLoader squareGridLoader = new SquareGridLoader(
                this,
                3,
                100,
                ContextCompat.getColor(this, R.color.red));

        squareGridLoader.setAnimDuration(500);
        squareGridLoader.setAnimDelay(100);
        squareGridLoader.setInterpolator(new LinearInterpolator());


        container.addView(squareGridLoader);
    }
}
