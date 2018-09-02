package com.agrawalsuneet.squareloaders

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.LinearLayout
import com.agrawalsuneet.squareloaderspack.loaders.*

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    private lateinit var zipzapXML: ZipZapLoader
    private lateinit var zipzap: ZipZapLoader

    private lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_squaregrid)

        supportActionBar?.title = "SquareGridLoader"

        container = findViewById(R.id.container)

        //initRotatingSquareLoader()

        //initMusicPlayerLoader()

        //initWaveLoader()

        //initZipZapLoader()
        //initControls();

        initSquareGridLoader()
    }

    private fun initSquareGridLoader(){
        val squareGridLoader = SquareGridLoader(
                this,
                3,
                100,
                ContextCompat.getColor(this, R.color.red))
                .apply {
                    animDuration = 500
                    animDelay = 100
                    interpolator = LinearInterpolator()
                }

        container.addView(squareGridLoader)
    }

    private fun initRotatingSquareLoader() {
        val rotatingSquareLoader = RotatingSquareLoader(this,
                200.0f, 60.0f, ContextCompat.getColor(this, R.color.red))
                .apply {
                    animDuration = 5000
                }

        container.addView(rotatingSquareLoader)
    }

    private fun initMusicPlayerLoader() {
        val loader = MusicPlayerLoader(this, 4, 40,
                100, 4, ContextCompat.getColor(baseContext, R.color.blue))
                .apply {
                    /*isSingleColor = false
                    rectColorsArray = resources.getIntArray(R.array.waveloader_colorsarray)*/
                    interpolator = LinearInterpolator()
                    animDuration = 500
                    delayDuration = 200
                }

        container.addView(loader)
    }

    private fun initWaveLoader() {
        val waveLoader = WaveLoader(this, 8, 40,
                200, 20, ContextCompat.getColor(baseContext, R.color.blue))
                .apply {
                    /*isSingleColor = false
                    rectColorsArray = resources.getIntArray(R.array.waveloader_colorsarray)*/
                    interpolator = LinearInterpolator()
                    animDuration = 1000
                    delayDuration = 100
                }

        container.addView(waveLoader)
    }

    private fun initControls() {

        button = findViewById(R.id.button)
        zipzapXML = findViewById(R.id.zipzap)

        button.setOnClickListener {
            if (zipzap.isLoading) {
                zipzap.stopLoading()
            } else {
                zipzap.startLoading()
            }

            if (zipzapXML.isLoading) {
                zipzapXML.stopLoading()
            } else {
                zipzapXML.startLoading()
            }
        }
    }

    private fun initZipZapLoader() {
        //zipzapXML = findViewById(R.id.zipzap) as ZipZapLoader

        zipzap = ZipZapLoader(this, 120,
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.red),
                false).apply {
            fromScale = 1.0f
            toScale = 0.8f
            animationDuration = 100
        }

        container.addView(zipzap)
    }

}

