package com.agrawalsuneet.squareloaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import com.agrawalsuneet.squareloaderspack.R
import com.agrawalsuneet.squareloaderspack.basicviews.LoaderContract
import com.agrawalsuneet.squareloaderspack.basicviews.SquareView

/**
 * Created by suneet on 1/18/18.
 */
class RotatingSquareLoader : LinearLayout, LoaderContract {

    var squareSideLenght: Float = 200.0f
    var strokeWidth: Float = 50.0f

    var squareColor: Int = resources.getColor(R.color.green)

    var animDuration: Int = 2000

    private lateinit var squareView: SquareView


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

    constructor(context: Context, noOfSticks: Int, outerCircleRadius: Float, innerCircleRadius: Float, sticksColor: Int, viewBackgroundColor: Int) : super(context) {
        /*this.noOfSticks = noOfSticks
        this.outerCircleRadius = outerCircleRadius
        this.innerCircleRadius = innerCircleRadius
        this.sticksColor = sticksColor*/
        initView()
    }

    override fun initAttributes(attrs: AttributeSet) {
        /*val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotatingCircularSticksLoader, 0, 0)

        this.noOfSticks = typedArray
                .getInteger(R.styleable.RotatingCircularSticksLoader_rotatingsticks_noOfSticks, 50)

        this.outerCircleRadius = typedArray
                .getDimension(R.styleable.RotatingCircularSticksLoader_rotatingsticks_outerCircleRadius, 200.0f)
        this.innerCircleRadius = typedArray
                .getDimension(R.styleable.RotatingCircularSticksLoader_rotatingsticks_innerCircleRadius, 100.0f)


        this.sticksColor = typedArray
                .getColor(R.styleable.RotatingCircularSticksLoader_rotatingsticks_stickColor, resources.getColor(R.color.grey))
        this.viewBackgroundColor = typedArray
                .getColor(R.styleable.RotatingCircularSticksLoader_rotatingsticks_viewBackgroundColor, resources.getColor(android.R.color.white))

        this.animDuration = typedArray
                .getInteger(R.styleable.RotatingCircularSticksLoader_rotatingsticks_animDuration, 5000)

        typedArray.recycle()*/
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(2 * squareSideLenght.toInt(), 2 * squareSideLenght.toInt())
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        gravity = Gravity.CENTER

        squareView = SquareView(context, squareColor, squareSideLenght.toInt(), true, strokeWidth.toInt())


        addView(squareView)

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

        val rotationAnim = getRotateAnimation()
        squareView.startAnimation(rotationAnim)
    }

    private fun getRotateAnimation(): RotateAnimation {

        val transAnim = RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        transAnim.duration = animDuration.toLong()
        transAnim.fillAfter = true
        transAnim.repeatCount = Animation.INFINITE
        transAnim.repeatMode = Animation.RESTART
        transAnim.interpolator = LinearInterpolator()

        return transAnim
    }
}