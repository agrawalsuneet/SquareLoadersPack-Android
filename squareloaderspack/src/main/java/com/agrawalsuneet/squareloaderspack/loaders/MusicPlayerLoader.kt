package com.agrawalsuneet.squareloaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.animation.*
import com.agrawalsuneet.squareloaderspack.R
import java.util.*

/**
 * Created by suneet on 1/9/18.
 */
class MusicPlayerLoader : WaveLoader {

    override var noOfRects: Int = 5

    override var rectWidth: Int = 30
    override var rectHeight: Int = 100

    override var rectDistance: Int = 5

    override var animDuration: Int = 300
    override var delayDuration: Int = 50

    override var interpolator: Interpolator = LinearInterpolator()

    constructor(context: Context, noOfRects: Int,
                rectWidth: Int, rectHeight: Int, rectDistance: Int,
                rectColor: Int) : super(context) {
        this.noOfRects = noOfRects
        this.rectWidth = rectWidth
        this.rectHeight = rectHeight
        this.rectDistance = rectDistance
        this.rectColor = rectColor
        initView()
    }

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initView()
    }

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MusicPlayerLoader, 0, 0)

        noOfRects = typedArray.getInteger(R.styleable.MusicPlayerLoader_musicplayer_noOfDots, 5)

        rectWidth = typedArray.getDimensionPixelSize(R.styleable.MusicPlayerLoader_musicplayer_rectWidth, 30)
        rectHeight = typedArray.getDimensionPixelSize(R.styleable.MusicPlayerLoader_musicplayer_rectHeight, 100)

        rectDistance = typedArray.getDimensionPixelSize(R.styleable.MusicPlayerLoader_musicplayer_rectDistance, 5)

        isSingleColor = typedArray.getBoolean(R.styleable.MusicPlayerLoader_musicplayer_isSingleColor, true)

        rectColor = typedArray.getColor(R.styleable.MusicPlayerLoader_musicplayer_rectColor, resources.getColor(R.color.grey))

        val colorsArrayId = typedArray.getResourceId(R.styleable.MusicPlayerLoader_musicplayer_rectColorsArray, 0)
        if (0 != colorsArrayId) {
            rectColorsArray = resources.getIntArray(colorsArrayId)
        }

        animDuration = typedArray.getInteger(R.styleable.MusicPlayerLoader_musicplayer_animDuration, 300)
        delayDuration = typedArray.getInteger(R.styleable.MusicPlayerLoader_musicplayer_delayDuration, 50)

        interpolator = AnimationUtils.loadInterpolator(context,
                typedArray.getResourceId(R.styleable.MusicPlayerLoader_musicplayer_interpolator,
                        android.R.anim.linear_interpolator))

        typedArray.recycle()
    }

    override fun initView() {
        super.initView()
        setVerticalGravity(Gravity.BOTTOM)
    }

    override fun getTranslateAnim(): ScaleAnimation {
        val transAnim = ScaleAnimation(1.0f, 1.0f, 0.1f, getRandom(0.5f, 2.0f),
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f)
        transAnim.duration = animDuration.toLong()
        transAnim.fillAfter = true
        transAnim.repeatCount = 1
        transAnim.repeatMode = Animation.REVERSE
        transAnim.interpolator = interpolator

        return transAnim
    }

    private val random = Random()

    private fun getRandom(from: Float, to: Float): Float {
        return (from + (random.nextFloat() * (to - from)))
    }
}