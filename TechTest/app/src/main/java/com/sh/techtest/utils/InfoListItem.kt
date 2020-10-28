package com.sh.techtest.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sh.techtest.R

/**
 * - Basic layout containing a view for a field and a value
 * - Supports data binding
 * */
class InfoListItem(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {


    init {
        orientation = VERTICAL
        inflate(context, R.layout.layout_info_list_item, this)

        context.obtainStyledAttributes(attrs, R.styleable.InfoListItem).let {
            val field = it.getString(R.styleable.InfoListItem_fieldStr).orEmpty()
            val value = it.getString(R.styleable.InfoListItem_valueStr).orEmpty()

            findViewById<TextView>(R.id.field_str).text = field
            findViewById<TextView>(R.id.value_str).text = value

            it.recycle()
        }
    }
}
@BindingAdapter("app:fieldStr")
fun fieldStr(view: View, text: String){
    view.findViewById<TextView>(R.id.field_str).text = text
}

@BindingAdapter("app:valueStr")
fun valueStr(view: View, text: String){
    view.findViewById<TextView>(R.id.value_str).text = text
}