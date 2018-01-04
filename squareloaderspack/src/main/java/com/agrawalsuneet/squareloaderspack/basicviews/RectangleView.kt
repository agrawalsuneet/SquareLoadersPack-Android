package com.agrawalsuneet.squareloaderspack.basicviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.squareloaderspack.R

/**
 * Created by suneet on 1/4/18.
 */
class RectangleView : View, LoaderContract {

    private var rectWidth: Int = 50
    private var rectHeight: Int = 100

    private var rectColor: Int = resources.getColor(R.color.grey)
    private lateinit var rectPaint: Paint

    constructor(context: Context) : super(context) {}

    constructor(context: Context, rectWidth: Int, rectHeight: Int, color: Int) : super(context) {
        this.rectWidth = rectWidth
        this.rectHeight = rectHeight
        this.rectColor = color
        initValues()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttributes(attrs!!)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs!!)
    }

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectangleView, 0, 0)

        this.rectWidth = typedArray.getDimension(R.styleable.RectangleView_rectWidth,
                100f).toInt()
        this.rectHeight = typedArray.getDimension(R.styleable.RectangleView_rectHeight,
                100f).toInt()
        this.rectColor = typedArray.getColor(R.styleable.RectangleView_rectColor,
                resources.getColor(R.color.grey))
        typedArray.recycle()
        initValues()
    }

    private fun initValues() {
        rectPaint = Paint()
        rectPaint.color = rectColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(rectWidth, rectHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, rectWidth.toFloat(), rectHeight.toFloat(), rectPaint)
    }
}