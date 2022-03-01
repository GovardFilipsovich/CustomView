package com.example.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class MovieCards(context: Context, attrs: AttributeSet): CardView(context, attrs) {
    private var urls = listOf("https://get.wallhere.com/photo/1920x1080-px-landscape-mountains-trees-1033494.jpg")
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {

        CoroutineScope(Dispatchers.IO).launch{
            var bit = BitmapFactory.decodeStream(URL(urls[0]).openConnection().getInputStream())
            if (bit != null) {
                bit = Bitmap.createScaledBitmap(bit, 312, 362, false)
                println(bit.toString())
                bit = bit.copy(Bitmap.Config.ALPHA_8, false)
                canvas.drawBitmap(bit, 0f, 0f, paint)
            }
        }

    }



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            println(event.action)
        }
        return super.onTouchEvent(event)
    }

}