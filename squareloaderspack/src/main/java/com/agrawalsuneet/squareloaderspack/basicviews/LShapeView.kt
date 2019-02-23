package com.agrawalsuneet.squareloaderspack.basicviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.squareloaderspack.R

class LShapeView : View, LoaderContract {


    var baseRectWidth: Int = 400
    var baseRectHeight: Int = 100

    var verticalRectWidth: Int = 100
    var verticalRectHeight: Int = 400


    private var rectColor: Int = resources.getColor(android.R.color.darker_gray)
    private lateinit var rectPaint: Paint

    constructor(context: Context) : super(context)

    constructor(context: Context, baseRectWidth: Int, baseRectHeight: Int,
                verticalRectWidth: Int, verticalRectHeight: Int, color: Int) : super(context) {
        this.baseRectWidth = baseRectWidth
        this.baseRectHeight = baseRectHeight
        this.verticalRectWidth = verticalRectWidth
        this.verticalRectHeight = verticalRectHeight
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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LShapeView, 0, 0)

        this.baseRectWidth = typedArray.getDimensionPixelSize(R.styleable.LShapeView_baseRectWidth,
                400)
        this.baseRectHeight = typedArray.getDimensionPixelSize(R.styleable.LShapeView_baseRectHeight,
                100)

        this.verticalRectWidth = typedArray.getDimensionPixelSize(R.styleable.LShapeView_verticalRectWidth,
                100)
        this.verticalRectHeight = typedArray.getDimensionPixelSize(R.styleable.LShapeView_verticalRectHeight,
                400)

        this.rectColor = typedArray.getColor(R.styleable.LShapeView_lShapeColor,
                resources.getColor(android.R.color.darker_gray))
        typedArray.recycle()
        initValues()
    }

    private fun initValues() {
        rectPaint = Paint()
        rectPaint.isAntiAlias = true
        rectPaint.style = Paint.Style.FILL
        rectPaint.color = rectColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(baseRectWidth, verticalRectHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //draw base
        canvas.drawRect(0f, (verticalRectHeight - baseRectHeight).toFloat()
                , baseRectWidth.toFloat(), verticalRectHeight.toFloat(), rectPaint)

        //draw vertical rectangle
        canvas.drawRect(0f, 0f, verticalRectWidth.toFloat(), verticalRectHeight.toFloat(), rectPaint)
    }
}