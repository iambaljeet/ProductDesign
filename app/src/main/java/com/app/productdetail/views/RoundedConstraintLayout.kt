package com.app.productdetail.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class RoundedConstraintLayout : ConstraintLayout {

    lateinit var fillpaint: Paint
    lateinit var strokepaint: Paint

    constructor(context: Context) : this(context, null) {
        if (!isInEditMode) {
            setup(null)
        }
    }

    private fun setup(attrs: AttributeSet?) {
        fillpaint = Paint()
        fillpaint.color = Color.RED

        strokepaint = Paint(fillpaint)
        strokepaint.style = Paint.Style.STROKE
        strokepaint.strokeWidth = 1f
        strokepaint.color = Color.TRANSPARENT
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        if (!isInEditMode) {
            setup(attrs)
        }
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        if (!isInEditMode) {
            setup(attrs)
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        val path = Path()
        val rect = RectF(0f, 0f, this.width.toFloat(), this.height.toFloat())
        path.addRoundRect(rect, floatArrayOf(50f, 50f, 50f, 50f, 200f, 200f, 50f, 50f), Path.Direction.CW)
        canvas?.clipPath(path)
        canvas?.drawColor(Color.BLACK)
    }
}