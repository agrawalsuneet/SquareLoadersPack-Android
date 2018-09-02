package com.agrawalsuneet.squareloaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import android.widget.LinearLayout
import com.agrawalsuneet.squareloaderspack.R
import com.agrawalsuneet.squareloaderspack.basicviews.LoaderContract
import com.agrawalsuneet.squareloaderspack.basicviews.SquareView

/**
 * Created by agrawalsuneet on 9/2/18.
 */

class NineSquareLoader : LinearLayout, LoaderContract {

    var squareSideLength: Int = 200
    var squareColor: Int = resources.getColor(R.color.green)

    var animDuration: Int = 500
    var animDelay: Int = 100

    var interpolator: Interpolator = LinearInterpolator()

    private var isForward = true

    private var squaresList: ArrayList<ArrayList<SquareView>> = ArrayList()


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
        /*val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotatingSquareLoader, 0, 0)

        this.squareSideLength = typedArray
                .getDimension(R.styleable.RotatingSquareLoader_rotatingsquare_squareSideLength, 200.0f)
        this.strokeWidth = typedArray
                .getDimension(R.styleable.RotatingSquareLoader_rotatingsquare_strokeWidth, 50.0f)


        this.squareColor = typedArray
                .getColor(R.styleable.RotatingSquareLoader_rotatingsquare_sqaureColor, resources.getColor(R.color.green))

        this.animDuration = typedArray
                .getInteger(R.styleable.RotatingSquareLoader_rotatingsquare_animDuration, 2000)

        typedArray.recycle()*/
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val calWidthHeight = (3 * squareSideLength)
        setMeasuredDimension(calWidthHeight, calWidthHeight)
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.orientation = LinearLayout.VERTICAL

        val params = LinearLayout.LayoutParams(3 * squareSideLength, squareSideLength)

        for (j in 0..2) {
            val linearLayout = LinearLayout(context)

            linearLayout.layoutParams = params
            linearLayout.orientation = LinearLayout.HORIZONTAL

            var squares = ArrayList<SquareView>()

            for (i in 0..2) {
                val squareView = SquareView(context, squareColor, squareSideLength)
                linearLayout.addView(squareView)

                squares.add(squareView)
            }

            this.addView(linearLayout)
            squaresList.add(squares)
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
        for (i in squaresList.size - 1 downTo 0) {
            for (j in 0 until squaresList.size) {

                val scaleAnim = getScaleAnimation(2 - i + j)

                if (i == 0 && j == squaresList.size - 1) {
                    scaleAnim.setAnimationListener(object : Animation.AnimationListener {

                        override fun onAnimationEnd(animation: Animation?) {
                            isForward = !isForward
                            startLoading()
                        }

                        override fun onAnimationStart(animation: Animation?) {
                        }

                        override fun onAnimationRepeat(animation: Animation?) {
                        }

                    })
                }

                squaresList.get(i).get(j).startAnimation(scaleAnim)
            }
        }
    }

    private fun getScaleAnimation(count: Int): ScaleAnimation {

        val fromX = if (isForward) 1.0f else 0.0f
        val toX = if (isForward) 0.0f else 1.0f

        val scaleAnim = ScaleAnimation(fromX, toX,
                fromX, toX,
                (squareSideLength / 2).toFloat(), (squareSideLength / 2).toFloat())
        scaleAnim.duration = animDuration.toLong()
        scaleAnim.fillAfter = true
        scaleAnim.repeatCount = 0
        scaleAnim.interpolator = interpolator
        scaleAnim.startOffset = (animDelay * count).toLong()

        return scaleAnim
    }
}