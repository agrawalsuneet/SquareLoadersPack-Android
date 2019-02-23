package com.agrawalsuneet.squareloaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.agrawalsuneet.squareloaderspack.R
import com.agrawalsuneet.squareloaderspack.basicviews.LShapeView
import com.agrawalsuneet.squareloaderspack.basicviews.LoaderContract
import com.agrawalsuneet.squareloaderspack.basicviews.RectangleView

class UsainBoltLoader : LinearLayout, LoaderContract {

    var rectangleColor = resources.getColor(android.R.color.darker_gray)
    var rectangleWidth: Int = 50

    private var calWidthHeight: Int = 0

    private var relativeLayout: RelativeLayout? = null


    var leftLegRectangleView: RectangleView? = null
    var rightLegLShapeView: LShapeView? = null

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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotatingSquareLoader, 0, 0)

        /*this.squareSideLength = typedArray
                .getDimension(R.styleable.RotatingSquareLoader_rotatingsquare_squareSideLength, 200.0f)
        this.strokeWidth = typedArray
                .getDimension(R.styleable.RotatingSquareLoader_rotatingsquare_strokeWidth, 50.0f)


        this.squareColor = typedArray
                .getColor(R.styleable.RotatingSquareLoader_rotatingsquare_sqaureColor, resources.getColor(R.color.green))

        this.animDuration = typedArray
                .getInteger(R.styleable.RotatingSquareLoader_rotatingsquare_animDuration, 2000)*/

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (calWidthHeight == 0) {
            calWidthHeight = 10 * rectangleWidth
        }

        setMeasuredDimension(calWidthHeight, calWidthHeight)
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.gravity = Gravity.CENTER_HORIZONTAL

        relativeLayout = RelativeLayout(context)

        if (calWidthHeight == 0) {
            calWidthHeight = 10 * rectangleWidth
        }

        val relParam = RelativeLayout.LayoutParams(calWidthHeight, calWidthHeight)

        addView(relativeLayout, relParam)

        val legLength = 4 * rectangleWidth

        //left leg
        leftLegRectangleView = RectangleView(context, rectangleWidth, legLength, rectangleColor)

        val leftLegParam = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        leftLegParam.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
        leftLegParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

        leftLegParam.bottomMargin = (0.5 * rectangleWidth).toInt()

        leftLegRectangleView?.pivotX = (rectangleWidth / 2).toFloat()
        leftLegRectangleView?.pivotY = (-rectangleWidth).toFloat()

        leftLegRectangleView?.rotation = 45f

        relativeLayout?.addView(leftLegRectangleView, leftLegParam)


        //right leg
        rightLegLShapeView = LShapeView(context,
                baseRectWidth = (3.5 * rectangleWidth).toInt(),
                baseRectHeight = rectangleWidth,
                verticalRectWidth = rectangleWidth,
                verticalRectHeight = (3.5 * rectangleWidth).toInt() ,
                color = rectangleColor)

        val rightLegParam = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

        rightLegParam.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
        rightLegParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        rightLegParam.bottomMargin = (2 * rectangleWidth).toInt()

        rightLegLShapeView?.rotation = -135f

        relativeLayout?.addView(rightLegLShapeView, rightLegParam)

        /*viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                this@UsainBoltLoader.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })*/
    }

}