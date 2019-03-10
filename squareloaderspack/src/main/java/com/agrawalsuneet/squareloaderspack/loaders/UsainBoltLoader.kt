package com.agrawalsuneet.squareloaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.agrawalsuneet.squareloaderspack.R
import com.agrawalsuneet.squareloaderspack.basicviews.LShapeView
import com.agrawalsuneet.squareloaderspack.basicviews.LoaderContract
import com.agrawalsuneet.squareloaderspack.basicviews.RectangleView

class UsainBoltLoader : LinearLayout, LoaderContract {

    var rectangleColor = resources.getColor(android.R.color.darker_gray)
    var rectangleWidth: Int = 60

    private var shouldAnimate: Boolean = true

    private var calWidth: Int = 0
    private var calHeight: Int = 0

    private var relativeLayout: RelativeLayout? = null


    private var leftLegRectangleView: RectangleView? = null
    private var rightLegLShapeView: LShapeView? = null
    private var leftHandLShapeView: LShapeView? = null
    private var rightHandLShapeView: LShapeView? = null

    private var leftHandContainerLL: LinearLayout? = null
    private var rightHandContainerLL: LinearLayout? = null


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

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)

        /*if (visibility != VISIBLE) {
            if (!shouldAnimate) {
                shouldAnimate = true
                initView()
            }
        } else {
            shouldAnimate = false
            initView()
        }*/
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.gravity = Gravity.CENTER_HORIZONTAL

        relativeLayout = RelativeLayout(context)
        relativeLayout?.clipChildren = false
        relativeLayout?.clipToPadding = false

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

        leftLegRectangleView?.pivotX = (0.5 * rectangleWidth).toFloat()
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

        //left hand
        leftHandLShapeView = LShapeView(context = context,
                baseRectWidth = (3.5 * rectangleWidth).toInt(),
                baseRectHeight = rectangleWidth,
                verticalRectWidth = rectangleWidth,
                verticalRectHeight = 2 * rectangleWidth,
                color = rectangleColor)

        leftHandLShapeView?.rotation = 135f

        //left hand container
        leftHandContainerLL = LinearLayout(context)
        leftHandContainerLL?.clipChildren = false
        leftHandContainerLL?.clipToPadding = false
        leftHandContainerLL?.setPadding((0.5 * rectangleWidth).toInt(), rectangleWidth,
                (1.5 * rectangleWidth).toInt(), (1 * rectangleWidth).toInt())

        val leftHandContainerParam = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

        leftHandContainerParam.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
        leftHandContainerParam.topMargin = (rectangleWidth).toInt()

        relativeLayout?.addView(leftHandContainerLL, leftHandContainerParam)
        leftHandContainerLL?.addView(leftHandLShapeView)


        //right hand
        rightHandLShapeView = LShapeView(context = context,
                baseRectWidth = (3.5 * rectangleWidth).toInt(),
                baseRectHeight = rectangleWidth,
                verticalRectWidth = rectangleWidth,
                verticalRectHeight = 2 * rectangleWidth,
                color = resources.getColor(android.R.color.holo_red_light))

        rightHandLShapeView?.rotation = -45f

        //right hand container
        rightHandContainerLL = LinearLayout(context)
        rightHandContainerLL?.clipChildren = false
        rightHandContainerLL?.clipToPadding = false
        rightHandContainerLL?.setPadding(4 * rectangleWidth, rectangleWidth, 0, rectangleWidth)

        val rightHandContainerParam = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

        rightHandContainerParam.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)

        relativeLayout?.addView(rightHandContainerLL, rightHandContainerParam)
        rightHandContainerLL?.addView(rightHandLShapeView)

        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                if (shouldAnimate) {
                    startLoading()
                }

                this@UsainBoltLoader.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun startLoading() {
        //left leg animation
        val leftLegAnim = getLeftLegAnimation()
        leftLegRectangleView?.startAnimation(leftLegAnim)

        //right leg animation
        val rightLegAnim = getRightLegAnimation()
        rightLegLShapeView?.startAnimation(rightLegAnim)

        //left hand animation
        val leftHandAnim = getLeftHandAnimation()
        leftHandLShapeView?.startAnimation(leftHandAnim)

        //right hand animation
        val rightHandAnim = getRightHandAnimation()
        rightHandLShapeView?.startAnimation(rightHandAnim)

    }

    private fun getLeftLegAnimation(): RotateAnimation {
        val pivotX = (0.5 * rectangleWidth).toFloat()
        val pivotY = (-rectangleWidth).toFloat()

        val rotateAnimation = RotateAnimation(0f, -90f, pivotX, pivotY)

        rotateAnimation.duration = 10000
        rotateAnimation.repeatCount = Animation.INFINITE
        rotateAnimation.repeatMode = Animation.REVERSE
        rotateAnimation.interpolator = LinearInterpolator()

        return rotateAnimation
    }

    private fun getRightLegAnimation(): RotateAnimation {
        val pivotX = (1.5 * rectangleWidth).toFloat()
        val pivotY = 0f

        val rotateAnimation = RotateAnimation(0f, 90f, pivotX, pivotY)

        rotateAnimation.duration = 10000
        rotateAnimation.repeatCount = Animation.INFINITE
        rotateAnimation.repeatMode = Animation.REVERSE
        rotateAnimation.interpolator = LinearInterpolator()

        return rotateAnimation
    }

    private fun getLeftHandAnimation(): RotateAnimation {
        val pivotX = (3 * rectangleWidth).toFloat()
        val pivotY = (0.5 * rectangleWidth).toFloat()

        val rotateAnimation = RotateAnimation(0f, -160f, pivotX, pivotY)

        rotateAnimation.duration = 10000
        rotateAnimation.repeatCount = Animation.INFINITE
        rotateAnimation.repeatMode = Animation.REVERSE
        rotateAnimation.interpolator = LinearInterpolator()

        return rotateAnimation
    }

    private fun getRightHandAnimation(): RotateAnimation {
        val pivotX = (0.5 * rectangleWidth).toFloat()
        val pivotY = (1.5 * rectangleWidth).toFloat()

        val rotateAnimation = RotateAnimation(0f, 170f, pivotX, pivotY)

        rotateAnimation.duration = 10000
        rotateAnimation.repeatCount = Animation.INFINITE
        rotateAnimation.repeatMode = Animation.REVERSE
        rotateAnimation.interpolator = LinearInterpolator()

        return rotateAnimation
    }

}