package com.agrawalsuneet.squareloaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.agrawalsuneet.squareloaderspack.R
import com.agrawalsuneet.squareloaderspack.basicviews.LShapeView
import com.agrawalsuneet.squareloaderspack.basicviews.LoaderContract
import com.agrawalsuneet.squareloaderspack.basicviews.RectangleView

class UsainBoltLoader : LinearLayout, LoaderContract {

    var rectangleColor = resources.getColor(android.R.color.darker_gray)
    var rectangleWidth: Int = 50

    private var calWidth: Int = 0
    private var calHeight: Int = 0

    private var relativeLayout: RelativeLayout? = null


    private var leftLegRectangleView: RectangleView? = null
    private var rightLegLShapeView: LShapeView? = null
    private var leftArmLShapeView: LShapeView? = null
    private var rightArmLShapeView: LShapeView? = null

    private var leftArmContainerLL: LinearLayout? = null


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

        if (calWidth == 0 || calHeight == 0) {
            calWidth = 10 * rectangleWidth
            calHeight = 10 * rectangleWidth
        }

        setMeasuredDimension(calWidth, calHeight)
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.gravity = Gravity.CENTER_HORIZONTAL

        relativeLayout = RelativeLayout(context)

        if (calWidth == 0 || calHeight == 0) {
            calWidth = 10 * rectangleWidth
            calHeight = 10 * rectangleWidth
        }

        val relParam = RelativeLayout.LayoutParams(calWidth, calHeight)

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
        rightLegLShapeView = LShapeView(context = context,
                baseRectWidth = (3.5 * rectangleWidth).toInt(),
                baseRectHeight = rectangleWidth,
                verticalRectWidth = rectangleWidth,
                verticalRectHeight = (3.5 * rectangleWidth).toInt(),
                color = rectangleColor)

        val rightLegParam = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

        rightLegParam.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
        rightLegParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        rightLegParam.bottomMargin = (2 * rectangleWidth).toInt()

        rightLegLShapeView?.rotation = -135f

        relativeLayout?.addView(rightLegLShapeView, rightLegParam)

        //left arm
        leftArmLShapeView = LShapeView(context = context,
                baseRectWidth = (3.5 * rectangleWidth).toInt(),
                baseRectHeight = rectangleWidth,
                verticalRectWidth = rectangleWidth,
                verticalRectHeight = 2 * rectangleWidth,
                color = rectangleColor)

        leftArmLShapeView?.rotation = 135f

        //left arm container
        leftArmContainerLL = LinearLayout(context)
        leftArmContainerLL?.clipChildren = false
        leftArmContainerLL?.clipToPadding = false
        leftArmContainerLL?.setPadding(0, rectangleWidth, rectangleWidth, 0)

        val leftArmContainerParam = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

        leftArmContainerParam.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
        leftArmContainerParam.topMargin = (rectangleWidth).toInt()

        relativeLayout?.addView(leftArmContainerLL, leftArmContainerParam)
        leftArmContainerLL?.addView(leftArmLShapeView)

        /*viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                this@UsainBoltLoader.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })*/
    }

}