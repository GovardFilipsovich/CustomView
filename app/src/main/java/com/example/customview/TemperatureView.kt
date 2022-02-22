package com.example.customview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.View
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class TemperatureView(context: Context, attrs: AttributeSet): View(context, attrs) {
    private val back_color = Color.BLACK
    private val border_color = Color.parseColor("#3C3C3C")
    private val orange = Color.argb(255,255, 117, 24)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val size = 400f
    private val border_size = 30f

    override fun onDraw(canvas: Canvas){
        drawBackGround(canvas)
        drawBorder(canvas)
        drawLine(canvas, 120f)
        drawText(canvas, 25f)
        drawGrads(canvas, 26.4)
    }

    private fun drawGrads(canvas: Canvas, num: Double) {
        paint.color = Color.WHITE
        paint.textSize = 55f
        canvas.drawText(num.toString(), size / 2 - 75, size / 2, paint)
        paint.textSize = 30f
        canvas.drawText("°C", size / 2 + 35, size / 2 - 20, paint)
    }

    private fun drawText(canvas: Canvas, wid: Float) {
        paint.color = Color.WHITE
        paint.textSize = wid
        canvas.drawText("Temperature", size / 2 - wid * 3, size / 2 + wid * 1.5f, paint)
    }

    private fun drawLine(canvas: Canvas, angle: Float) {
        paint.color = orange
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = border_size * 2 / 3

        val arc_coord = border_size / 2 - paint.strokeWidth / 2


        val oval = RectF(arc_coord, arc_coord, size - arc_coord, size - arc_coord)

        canvas.drawArc(oval, 270f, angle, false, paint)

        val start_x = 0
        val start_y = size / 2 - arc_coord

        var end_x = -start_x * cos(angle * PI / 180) + start_y * sin(angle* PI / 180)
        var end_y = start_x * sin(angle* PI / 180) + start_y * cos(angle* PI / 180)
        end_x += size / 2
        end_y = -end_y + size / 2

        paint.color = Color.argb(255, 24, 23, 28)
        paint.style = Paint.Style.FILL

        canvas.drawCircle(end_x.toFloat(), end_y.toFloat(), border_size / 2 + 5, paint)

    }

    private fun drawBorder(canvas: Canvas) {
        paint.color = border_color
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = border_size

        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint)
    }

    private fun drawBackGround(canvas: Canvas) {

        paint.shader = LinearGradient(0f, 0f, size, size, back_color, Color.parseColor("#606060"), Shader.TileMode.MIRROR )

        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint)
        paint.shader = null
    }


}