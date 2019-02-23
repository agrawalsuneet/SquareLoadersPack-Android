# SquareLoadersPack-Android
> Android SquareLoadersPack

> A replacement of default android material progressbar with various Squares and Rectangles Loaders

latest version : [ ![Download](https://api.bintray.com/packages/agrawalsuneet/androidlibs/squareloaderspack/images/download.svg) ](https://bintray.com/agrawalsuneet/androidlibs/squareloaderspack/_latestVersion)    [![CircleCI](https://circleci.com/gh/agrawalsuneet/SquareLoadersPack-Android.svg?style=svg)](https://circleci.com/gh/agrawalsuneet/SquareLoadersPack-Android)

[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-SquareLoadersPack-green.svg?style=flat )]( https://android-arsenal.com/details/1/7246 )


### SquareGridLoader
![squaregridloader](https://user-images.githubusercontent.com/12999622/44960467-15765880-aef8-11e8-80fb-5a834ca5c628.gif)


### ZipZapLoader
![zipzaploader](https://user-images.githubusercontent.com/12999622/36222631-d8228584-11b9-11e8-8478-d1442891419c.gif)


### WaveLoader
![waveloader](https://user-images.githubusercontent.com/12999622/34330606-af613dc8-e916-11e7-9965-a686071002b9.gif)


### MusicPlayerLoader
![musicplayerloader](https://user-images.githubusercontent.com/12999622/35103527-00136c14-fc5e-11e7-84a0-9c2bc47236be.gif)


### RotatingSquareLoader
![RotatingSquareLoader](https://user-images.githubusercontent.com/12999622/35877618-c2f139c8-0b6d-11e8-8c66-b830c27ac430.gif)

Check all other loaders [here](https://agrawalsuneet.github.io/agrawalsuneet/opensourcecontribution/)


## How To use
include below dependency in build.gradle of application and compile it
```
implementation 'com.agrawalsuneet.androidlibs:squareloaderspack:0.4'
```

### SquareGridLoader
##### Through XML
```
<com.agrawalsuneet.squareloaderspack.loaders.SquareGridLoader
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:squaregrid_animDelay="80"
        app:squaregrid_animDuration="400"
        app:squaregrid_interpolator="@android:interpolator/linear"
        app:squaregrid_sqaureColor="@color/blue"
        app:squaregrid_squareCount="5"
        app:squaregrid_squareLength="24dp" />
```
##### Through Code
* Kotlin
```
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
```

* Java
```
SquareGridLoader squareGridLoader = new SquareGridLoader(
                this,
                3,
                100,
                ContextCompat.getColor(this, R.color.red));

        squareGridLoader.setAnimDuration(500);
        squareGridLoader.setAnimDelay(100);
        squareGridLoader.setInterpolator(new LinearInterpolator());


        container.addView(squareGridLoader);
```


### ZipZapLoader
##### Through XML
```
<com.agrawalsuneet.squareloaderspack.loaders.ZipZapLoader
        android:id="@+id/zipzap"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:zipzap_animDuration="200"
        app:zipzap_firstSquareColor="@color/blue"
        app:zipzap_forthSquareColor="@color/blue"
        app:zipzap_fromScale="0.9"
        app:zipzap_secondSquareColor="@color/blue"
        app:zipzap_squareLength="40dp"
        app:zipzap_startLoadingDefault="true"
        app:zipzap_thirdSquareColor="@color/blue"
        app:zipzap_toScale="0.7" />
```
##### Through Code
* Kotlin
```
        zipzap = ZipZapLoader(this, 120,
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.red),
                false).apply {
            fromScale = 1.0f
            toScale = 0.8f
            animationDuration = 1000
        }

        container.addView(zipzap)
```

* Java
```ZipZapLoader zipZapLoader = new ZipZapLoader(this, 40,
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.green),
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.colorAccent),
                true);
        
        zipZapLoader.setAnimationDuration(500);
        zipZapLoader.setFromScale(1.0f);
        zipZapLoader.setToScale(0.8f);

        container.addView(zipZapLoader);
```


### WaveLoader
##### Through XML
```
<com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:wave_animDuration="500"
        app:wave_delayDuration="100"
        app:wave_interpolator="@android:anim/linear_interpolator"
        app:wave_isSingleColor="true"
        app:wave_noOfDots="10"
        app:wave_rectColor="@color/blue"
        app:wave_rectDistance="8dp"
        app:wave_rectHeight="80dp"
        app:wave_rectWidth="16dp" />


    <com.agrawalsuneet.squareloaderspack.loaders.WaveLoader
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:wave_animDuration="500"
            app:wave_delayDuration="100"
            app:wave_interpolator="@android:anim/linear_interpolator"
            app:wave_isSingleColor="false"
            app:wave_noOfDots="4"
            app:wave_rectColorsArray="@array/waveloader_colorsarray"
            app:wave_rectDistance="8dp"
            app:wave_rectHeight="80dp"
            app:wave_rectWidth="16dp" />
```
##### Through Code
* Kotlin
```
         val waveLoader = WaveLoader(this, 8, 40,
                        200, 20, ContextCompat.getColor(baseContext, R.color.blue))
                        .apply {
                            //isSingleColor = false
                            //rectColorsArray = resources.getIntArray(R.array.waveloader_colorsarray)
                            interpolator = LinearInterpolator()
                            animDuration = 1000
                            delayDuration = 100
                        }
        
                container.addView(waveLoader)
```

* Java
```
WaveLoader waveLoader = new WaveLoader(this, 8, 40,
                   200, 20, ContextCompat.getColor(getBaseContext(), R.color.blue));
   
           //waveLoader.setSingleColor(false);
           //waveLoader.setRectColorsArray(getResources().getIntArray(R.array.waveloader_colorsarray));
           waveLoader.setInterpolator(new LinearInterpolator());
           waveLoader.setAnimDuration(1000);
           waveLoader.setDelayDuration(100);
   
   
           container.addView(waveLoader);
```


### MusicPlayerLoader
##### Through XML
```
<com.agrawalsuneet.squareloaderspack.loaders.MusicPlayerLoader
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:musicplayer_animDuration="500"
            app:musicplayer_delayDuration="200"
            app:musicplayer_interpolator="@android:anim/linear_interpolator"
            app:musicplayer_isSingleColor="true"
            app:musicplayer_noOfDots="5"
            app:musicplayer_rectColor="@color/green"
            app:musicplayer_rectDistance="2dp"
            app:musicplayer_rectHeight="30dp"
            app:musicplayer_rectWidth="8dp" />


    <com.agrawalsuneet.squareloaderspack.loaders.MusicPlayerLoader
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginLeft="16dp"
            app:musicplayer_animDuration="500"
            app:musicplayer_delayDuration="200"
            app:musicplayer_interpolator="@android:anim/linear_interpolator"
            app:musicplayer_isSingleColor="false"
            app:musicplayer_noOfDots="4"
            app:musicplayer_rectColor="@color/blue"
            app:musicplayer_rectColorsArray="@array/waveloader_colorsarray"
            app:musicplayer_rectDistance="2dp"
            app:musicplayer_rectHeight="60dp"
            app:musicplayer_rectWidth="12dp"/>
```
##### Through Code
* Kotlin
```
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
```

* Java
```
MusicPlayerLoader loader = new MusicPlayerLoader(this, 4, 40,
                100, 4, ContextCompat.getColor(this, R.color.blue));
                    /*isSingleColor = false
                    rectColorsArray = resources.getIntArray(R.array.waveloader_colorsarray)*/
        loader.setInterpolator(new LinearInterpolator());
        loader.setAnimDuration(500);
        loader.setDelayDuration(100);

        container.addView(loader);
```
  
  
### RotatingSquareLoader
##### Through XML
```
    <com.agrawalsuneet.squareloaderspack.loaders.RotatingSquareLoader
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:rotatingsquare_animDuration="2000"
        app:rotatingsquare_sqaureColor="@color/amber"
        app:rotatingsquare_squareSideLength="40dp"
        app:rotatingsquare_strokeWidth="15dp" />
```
##### Through Code
* Kotlin
```
         val rotatingSquareLoader = RotatingSquareLoader(this,
                 200.0f, 60.0f, ContextCompat.getColor(this, R.color.red))
                 .apply {
                     animDuration = 5000
                 }
 
         container.addView(rotatingSquareLoader)
```

* Java
```
RotatingSquareLoader rotatingSquareLoader = new RotatingSquareLoader(this,
                200.0f, 60.0f, ContextCompat.getColor(this, R.color.red));
        rotatingSquareLoader.setAnimDuration(5000);

        container.addView(rotatingSquareLoader);
```  
  

Please take a 2 mins survey to make this library better [here](https://goo.gl/forms/2Iluao9HV9CAMLx63).
It won't take more than 2 mins I promise :) or feel free to drop an email at agrawalsuneet@gmail.com if face any issue or require any additional functionality in it.
```
Copyright 2017 Suneet Agrawal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
