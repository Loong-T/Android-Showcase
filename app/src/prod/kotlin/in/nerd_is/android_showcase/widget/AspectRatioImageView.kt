/*
 *    Copyright 2017 Xuqiang ZHENG
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package `in`.nerd_is.android_showcase.widget

import `in`.nerd_is.android_showcase.R
import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * @author Xuqiang ZHENG on 2017/7/1.
 */
class AspectRatioImageView : AppCompatImageView {

    companion object {
        val FIXED_WIDTH = 0
        val FIXED_HEIGHT = 1
    }

    var fixedSide = FIXED_WIDTH
    var ratio = 1f // = width / height

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = resources.obtainAttributes(attrs, R.styleable.AspectRatioImageView)

        fixedSide = typedArray.getInt(R.styleable.AspectRatioImageView_fixed, FIXED_WIDTH)
        ratio = typedArray.getFloat(R.styleable.AspectRatioImageView_ratio, 1f)

        typedArray.recycle()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : this(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var wSpec = widthMeasureSpec
        var hSpec = heightMeasureSpec
        if (fixedSide == FIXED_WIDTH) {
            val height = (MeasureSpec.getSize(widthMeasureSpec) / ratio).toInt()
            hSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        } else {
            val width = (MeasureSpec.getSize(heightMeasureSpec) * ratio).toInt()
            wSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        }

        super.onMeasure(wSpec, hSpec)
    }
}