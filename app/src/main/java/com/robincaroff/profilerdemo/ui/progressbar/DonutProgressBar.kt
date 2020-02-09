package com.robincaroff.profilerdemo.ui.progressbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.RectF
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

open class DonutProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val backgroundCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var backgroundCircleRadius = 0f

    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.SQUARE
    }
    private val arcOval = RectF()
    private var arcRadius = 0f
    private var _arcColor = 0
    var arcColor: Int
        get() = _arcColor
        set(value) {
            arcPaint.color = value
            _arcColor = value
        }
    private var _arcWidth = 20f
    var arcWidth: Float
        get() = _arcWidth
        set(value) {
            arcPaint.strokeWidth = value
            _arcWidth = value
        }

    private val textPaint = TextPaint().apply {
        textAlign = Paint.Align.LEFT
        textSize = 60f
    }
    private var _textColor = 0
    var textColor: Int
        get() = _textColor
        set(value) {
            textPaint.color = value
            _textColor = value
        }
    open var text: String = ""
        get() = progress.toString()

    private var center = Point()

    private var _progress = 0f
    var progress
        get() = _progress
        set(value) {
            _progress = value
            invalidate()
        }

    private var _maxProgress = 100f
    var maxProgress
        get() = _maxProgress
        set(value) {
            _maxProgress = value
            invalidate()
        }

    private var progressEndAngle: Float = 0.0f
        get() = progress * 360f / maxProgress

    init {
        backgroundCirclePaint.color = ContextCompat.getColor(context, android.R.color.white)

        arcColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
        arcPaint.strokeWidth = arcWidth

        textColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        center = Point(w / 2, h / 2)
        backgroundCircleRadius = w / 2f - _arcWidth / 2f
        arcRadius = w / 2f - _arcWidth / 2f
        arcOval.set(
            center.x - arcRadius,
            center.y - arcRadius,
            center.x + arcRadius,
            center.y + arcRadius
        )
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) {
            return
        }

        longProcess()

        canvas.drawCircle(
            center.x.toFloat(),
            center.y.toFloat(),
            backgroundCircleRadius,
            backgroundCirclePaint
        )

        canvas.drawArc(arcOval, -90f, progressEndAngle, false, arcPaint)

        canvas.drawText(
            text,
            center.x.toFloat() - textPaint.measureText(text) / 2f,
            center.y.toFloat() - ((textPaint.descent() + textPaint.ascent()) / 2),
            textPaint
        )
    }

    private fun longProcess() {
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}