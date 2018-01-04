package com.agrawalsuneet.squareloaderspack.loaders

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.*
import android.widget.LinearLayout
import com.agrawalsuneet.squareloaderspack.R
import com.agrawalsuneet.squareloaderspack.basicviews.LoaderContract
import com.agrawalsuneet.squareloaderspack.basicviews.RectangleView
import java.util.*

/**
 * Created by suneet on 1/4/18.
 */
class WaveLoader : LinearLayout, LoaderContract {

    var noOfRects: Int = 3
        set(value) {
            field = if (value < 2) 2 else value
            invalidate()
        }

    var rectWidth: Int = 50
    var rectHeight: Int = 200

    var rectDistance: Int = 20

    var isSingleColor: Boolean = true

    var rectColor: Int = resources.getColor(R.color.grey)
    var rectColorsArray: IntArray = IntArray(noOfRects, { resources.getColor(R.color.grey) })
        set(value) {
            field = value
            initView()
        }

    var animDuration: Int = 500
    var delayDuration: Int = 100

    var interpolator: Interpolator = LinearInterpolator()

    private lateinit var rectsArrayList: ArrayList<RectangleView?>

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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveLoader, 0, 0)

        noOfRects = typedArray.getInteger(R.styleable.WaveLoader_wave_noOfDots, 3)

        rectWidth = typedArray.getDimensionPixelSize(R.styleable.WaveLoader_wave_rectWidth, 50)
        rectHeight = typedArray.getDimensionPixelSize(R.styleable.WaveLoader_wave_rectHeight, 200)

        rectDistance = typedArray.getDimensionPixelSize(R.styleable.WaveLoader_wave_rectDistance, 20)

        isSingleColor = typedArray.getBoolean(R.styleable.WaveLoader_wave_isSingleColor, true)

        rectColor = typedArray.getColor(R.styleable.WaveLoader_wave_rectColor, resources.getColor(R.color.grey))

        val colorsArrayId = typedArray.getResourceId(R.styleable.WaveLoader_wave_rectColorsArray, 0)
        if (0 != colorsArrayId) {
            rectColorsArray = resources.getIntArray(colorsArrayId)
        }

        animDuration = typedArray.getInteger(R.styleable.WaveLoader_wave_animDuration, 500)
        delayDuration = typedArray.getInteger(R.styleable.WaveLoader_wave_delayDuration, 100)

        interpolator = AnimationUtils.loadInterpolator(context,
                typedArray.getResourceId(R.styleable.WaveLoader_wave_interpolator,
                        android.R.anim.linear_interpolator))

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val calWidth = (noOfRects * rectWidth) + ((noOfRects - 1) * rectDistance)
        val calHeight = 2 * rectHeight

        setMeasuredDimension(calWidth, calHeight)
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        setVerticalGravity(Gravity.CENTER)

        rectsArrayList = ArrayList(noOfRects)

        for (count in 0 until noOfRects) {
            val color = if (!isSingleColor && count < rectColorsArray.size && null != rectColorsArray.get(count))
                rectColorsArray[count] else rectColor

            val rectangleView = RectangleView(context, rectWidth, rectHeight, color)

            val rectLayoutParam = LinearLayout.LayoutParams(rectWidth, rectHeight)
            rectLayoutParam.topMargin = (rectHeight / 2)
            rectLayoutParam.bottomMargin = (rectHeight / 2)

            if (count > 0) {
                rectLayoutParam.leftMargin = rectDistance
            }

            this.addView(rectangleView, rectLayoutParam)
            rectsArrayList.add(rectangleView)
        }

        val loaderView = this

        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                startLoading()

                val vto = loaderView.viewTreeObserver
                vto.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun startLoading() {

        for (count in 0 until noOfRects) {
            val rectScaleAnim = getTranslateAnim()

            val rectView = rectsArrayList[count]

            Handler().postDelayed({
                rectView?.startAnimation(rectScaleAnim)
            }, (count * delayDuration).toLong())

            if (count == 0) {
                rectScaleAnim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationEnd(p0: Animation?) {
                        startLoading()
                    }

                    override fun onAnimationRepeat(p0: Animation?) {
                    }

                    override fun onAnimationStart(p0: Animation?) {
                    }

                })
            }
        }
    }

    private fun getTranslateAnim(): ScaleAnimation {
        val transAnim = ScaleAnimation(1.0f, 1.0f, 0.5f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        transAnim.duration = animDuration.toLong()
        transAnim.fillAfter = true
        transAnim.repeatCount = 1
        transAnim.repeatMode = Animation.REVERSE
        transAnim.interpolator = interpolator

        return transAnim
    }
}