package com.khoirullatif.suitmedia.costumView

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import com.khoirullatif.suitmedia.R

class MyButton : AppCompatButton {

    private var enabledBgButton: Drawable? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = enabledBgButton
    }

    private fun init() {
        enabledBgButton = ResourcesCompat.getDrawable(resources, R.drawable.bg_button, null)
    }
}