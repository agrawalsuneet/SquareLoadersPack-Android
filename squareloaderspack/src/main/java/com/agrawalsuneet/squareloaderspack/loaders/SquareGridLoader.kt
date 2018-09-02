package com.agrawalsuneet.squareloaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.ViewTreeObserver
import android.view.animation.*
import android.widget.LinearLayout
import com.agrawalsuneet.squareloaderspack.R
import com.agrawalsuneet.squareloaderspack.basicviews.LoaderContract
import com.agrawalsuneet.squareloaderspack.basicviews.SquareView

/**
 * Created by agrawalsuneet on 9/2/18.
 */

class SquareGridLoader : LinearLayout, LoaderContract {

    var squareCount: Int = 5
        set(value) {
            field = if (value < 2) 2 else value
        }

    var squareLength: Int = 100
    var squareColor: Int = resources.getColor(R.color.green)

    var animDuration: Int = 500
    var animDelay: Int = 100

    var interpolator: Interpolator = LinearInterpolator()

    private var isForward = true

    private var squaresList: ArrayList<ArrayList<SquareView>> = ArrayList()

    constructor(context: Context?, squareCount: Int, squareLength: Int, squareColor: Int) : super(context) {
        this.squareCount = squareCount
        this.squareLength = squareLength
        this.squareColor = squareColor
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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SquareGridLoader, 0, 0)

        this.squareColor = typedArray.getInteger(R.styleable.SquareGridLoader_squaregrid_squareCount, 3)

        this.squareLength = typedArray.getDimensionPixelSize(R.styleable.SquareGridLoader_squaregrid_squareLength, 200)


        this.squareColor = typedArray
                .getColor(R.styleable.SquareGridLoader_squaregrid_sqaureColor, resources.getColor(R.color.grey))

        this.animDuration = typedArray
                .getInteger(R.styleable.SquareGridLoader_squaregrid_animDuration, 500)

        this.animDelay = typedArray
                .getInteger(R.styleable.SquareGridLoader_squaregrid_animDelay, 100)

        interpolator = AnimationUtils.loadInterpolator(context,
                typedArray.getResourceId(R.styleable.SquareGridLoader_squaregrid_interpolator,
                        android.R.anim.linear_interpolator))

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val calWidthHeight = (squareCount * squareLength)
        setMeasuredDimension(calWidthHeight, calWidthHeight)
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.orientation = LinearLayout.VERTICAL

        val params = LinearLayout.LayoutParams((squareCount * squareLength), squareLength)

        for (j in 0 until squareCount) {
            val linearLayout = LinearLayout(context)

            linearLayout.layoutParams = params
            linearLayout.orientation = LinearLayout.HORIZONTAL

            val squares = ArrayList<SquareView>()

            for (i in 0 until squareCount) {
                val squareView = SquareView(context, squareColor, squareLength)
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

                val scaleAnim = getScaleAnimation((squareCount - 1) - i + j)

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
                (squareLength / 2).toFloat(), (squareLength / 2).toFloat())
        scaleAnim.duration = animDuration.toLong()
        scaleAnim.fillAfter = true
        scaleAnim.repeatCount = 0
        scaleAnim.interpolator = interpolator
        scaleAnim.startOffset = (animDelay * count).toLong()

        return scaleAnim
    }
}