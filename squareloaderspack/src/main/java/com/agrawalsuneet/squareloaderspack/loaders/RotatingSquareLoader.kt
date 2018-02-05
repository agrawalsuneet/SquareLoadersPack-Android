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

    var squareSideLength: Float = 200.0f
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

    constructor(context: Context, squareSideLenght: Float, strokeWidth: Float, squareColor: Int) : super(context) {
        this.squareSideLength = squareSideLenght
        this.strokeWidth = strokeWidth
        this.squareColor = squareColor
        initView()
    }

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotatingSquareLoader, 0, 0)

        this.squareSideLength = typedArray
                .getDimension(R.styleable.RotatingSquareLoader_rotatingsquare_squareSideLength, 200.0f)
        this.strokeWidth = typedArray
                .getDimension(R.styleable.RotatingSquareLoader_rotatingsquare_strokeWidth, 50.0f)


        this.squareColor = typedArray
                .getColor(R.styleable.RotatingSquareLoader_rotatingsquare_sqaureColor, resources.getColor(R.color.green))

        this.animDuration = typedArray
                .getInteger(R.styleable.RotatingSquareLoader_rotatingsquare_animDuration, 2000)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        //sin45 = 0.707
        //width = 2 * sin45 * (sideLength)
        val width = (1.5 * (squareSideLength))
        setMeasuredDimension(width.toInt(), width.toInt())
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        gravity = Gravity.CENTER

        squareView = SquareView(context, squareSideLength.toInt(), squareColor, true, strokeWidth.toInt())


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