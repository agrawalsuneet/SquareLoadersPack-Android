package com.agrawalsuneet.squareloaderspack.basicviews

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.view.animation.Interpolator
import android.widget.LinearLayout
import com.agrawalsuneet.squareloaderspack.R

/**
 * Created by suneet on 1/4/18.
 */
abstract class FourSquaresBaseLayout : LinearLayout {

    var squareLenght = 100

    var firstSquareColor = resources.getColor(R.color.red)
    var secondSquareColor = resources.getColor(R.color.green)
    var thirdSquareColor = resources.getColor(R.color.blue)
    var forthSquareColor = resources.getColor(R.color.grey)

    var startLoadingDefault = false

    var animationDuration = 500

    var interpolator: Interpolator = AccelerateInterpolator()

    //private variables

    var isLoading: Boolean = false
        protected set

    protected var firstSquare: SquareView? = null
    protected lateinit var secondSquare: SquareView
    protected lateinit var thirdSquare: SquareView
    protected lateinit var forthSquare: SquareView

    protected lateinit var topLinearLayout: LinearLayout
    protected lateinit var bottomLinearLayout: LinearLayout


    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, squareLenght: Int, firstSquareColor: Int,
                secondSquareColor: Int, thirdSquareColor: Int,
                forthSquareColor: Int, startLoadingDefault: Boolean) : super(context) {
        this.squareLenght = squareLenght
        this.firstSquareColor = firstSquareColor
        this.secondSquareColor = secondSquareColor
        this.thirdSquareColor = thirdSquareColor
        this.forthSquareColor = forthSquareColor
        this.startLoadingDefault = startLoadingDefault
        initView()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(2 * squareLenght, 2 * squareLenght)
    }


    open protected fun initView() {
        if (isLoading) {
            return
        }

        removeAllViews()
        removeAllViewsInLayout()

        isLoading = false

        this.orientation = LinearLayout.VERTICAL

        firstSquare = SquareView(context, firstSquareColor, squareLenght)
        secondSquare = SquareView(context, secondSquareColor, squareLenght)
        thirdSquare = SquareView(context, thirdSquareColor, squareLenght)
        forthSquare = SquareView(context, forthSquareColor, squareLenght)

        topLinearLayout = LinearLayout(context)
        topLinearLayout.gravity = Gravity.END
        topLinearLayout.orientation = LinearLayout.HORIZONTAL
        topLinearLayout.addView(firstSquare)
        topLinearLayout.addView(secondSquare)

        bottomLinearLayout = LinearLayout(context)
        bottomLinearLayout.gravity = Gravity.END
        bottomLinearLayout.orientation = LinearLayout.HORIZONTAL
        bottomLinearLayout.addView(forthSquare)
        bottomLinearLayout.addView(thirdSquare)

        val llParams = LinearLayout.LayoutParams(2 * squareLenght, squareLenght)
        llParams.gravity = Gravity.END

        addView(topLinearLayout, llParams)
        addView(bottomLinearLayout, llParams)

        requestLayout()

        if (startLoadingDefault) {
            val viewTreeObserver = this.viewTreeObserver
            val loaderView = this

            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    startLoading()

                    val vto = loaderView.viewTreeObserver
                    vto.removeOnGlobalLayoutListener(this)
                }
            })
            startLoadingDefault = false
        }
    }

    abstract fun startLoading()

    abstract fun stopLoading()
}