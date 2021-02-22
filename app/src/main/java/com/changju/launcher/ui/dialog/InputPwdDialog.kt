package com.changju.launcher.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.DialogCompat
import androidx.fragment.app.DialogFragment
import com.changju.launcher.R
import com.changju.launcher.listener.OnEditDialogResultListener
import com.changju.launcher.listener.OnKeyboardChangeListener
import com.changju.launcher.widget.KeyboardHelper
import com.changju.launcher.widget.NumberKeyboardView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * 输入密码数字键盘dialog
 * Created by LLhon
 */
class InputPwdDialog : DialogFragment() {

    private lateinit var helper: KeyboardHelper
    private var inputContent: String? = null
    private var listener: OnEditDialogResultListener? = null
    private lateinit var editText: EditText
    private lateinit var numberKeyboard: NumberKeyboardView
    private lateinit var cancel: TextView
    private lateinit var ok: TextView

    companion object {

        fun newInstance(): InputPwdDialog {
            return InputPwdDialog()
        }

    }

    fun setListener(listener: OnEditDialogResultListener): InputPwdDialog {
        this.listener = listener
        return this
    }

    override fun onStart() {
        super.onStart()

        if (null == view) {
            return
        }

        val parent = view!!.parent as View
        val params = parent.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.CENTER
        parent.layoutParams = params

        val window = dialog?.window
        if (window != null) {
            val decorView = window.decorView
            decorView.setPadding(0, 0, 0, 0)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setWindowAnimations(R.style.DialogAnimation)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_input_password, container, false)
        view.isSaveEnabled = false
        editText = view.findViewById(R.id.edit)
        numberKeyboard = view.findViewById(R.id.numberKeyboard)
        cancel = view.findViewById(R.id.cancel)
        ok = view.findViewById(R.id.ok)
        helper = KeyboardHelper.get()
        initView()
        return view
    }

    /**
     * initial view
     */
    private fun initView() {
        cancel.setOnClickListener {
            cancel()
        }
        ok.setOnClickListener {
            ok()
        }
        activity?.let { helper.banSystemKeyboard(it, editText) }
        helper.bind(editText, numberKeyboard, object : OnKeyboardChangeListener {
            override fun onTextChange(text: String) {
                inputContent = text
            }
        })
    }

    override fun onResume() {
        super.onResume()
        editText.requestFocus()
    }

    private fun cancel() {
        dismiss()
    }

    private fun ok() {
        Log.d("InputPwdDialog", "inputContent: $inputContent")
        if (TextUtils.isEmpty(inputContent)) {
            Toast.makeText(context, "输入内容不能为空!", Toast.LENGTH_SHORT).show()
            return
        }
        if ("688688" != inputContent) {
            Toast.makeText(context, "密码错误!!", Toast.LENGTH_SHORT).show()
            return
        }
        listener?.onResult(inputContent ?: "")
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        helper.unBind()
    }
}