package com.unas.filmku.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import com.unas.filmku.R

class CustomEditText(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {
    private var titleText: TextView? = null
    private var textInputLayout: TextInputLayout? = null
    private var mEditText: EditText? = null

    val editText : EditText? get() = mEditText

    init {
        val view = inflate(context, R.layout.custom_edit_text, this)
        titleText = view.findViewById(R.id.tv_title)
        textInputLayout = view.findViewById(R.id.text_input_layout)
        mEditText = view.findViewById(R.id.edit_text)

        context.obtainStyledAttributes(
            attributeSet, R.styleable.TextInputCustom, 0, 0
        ).apply {

            val title = getString(R.styleable.TextInputCustom_title).orEmpty()
            val hintText = getString(R.styleable.TextInputCustom_hint_text).orEmpty()
            val inputTypeRes = getInt(R.styleable.TextInputCustom_input_type, 0)
            titleText?.text = title
            mEditText?.hint = hintText
            mEditText?.inputType = when(inputTypeRes) {
                0 -> android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                1 -> { setTogglePassword()
                    android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                else -> android.text.InputType.TYPE_CLASS_TEXT
            }


            mEditText?.addTextChangedListener {
                textInputLayout?.error = null
                textInputLayout?.isErrorEnabled = false
                textInputLayout?.setPadding(0, 0, 0, 0)
            }
        }.recycle()
    }
    private fun setTogglePassword() {
        textInputLayout?.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
    }
    fun setError(message : String) {
        textInputLayout?.error = message
    }


}