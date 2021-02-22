package com.changju.launcher.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.changju.launcher.R
import com.changju.launcher.ui.adapter.NumberKeyboardAdapter
import com.changju.launcher.common.Constant

class NumberKeyboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var keyValueList: MutableList<Map<String, String>>? = null

    private lateinit var rvKeyboard: RecyclerView

    /**
     * get key board adapter
     *
     * @return NumberKeyboardAdapter
     */
    var adapter: NumberKeyboardAdapter? = null
        private set

    init {
        init(context)
    }

    /**
     * initial
     */
    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(
            R.layout.layout_number_keyboard,
            this,
            false
        )
        rvKeyboard = view.findViewById(R.id.rvKeyboard)
        initKeyValueList()
        initRv(context)
        addView(view)
    }

    /**
     * initial key value list
     */
    private fun initKeyValueList() {
        if (null == keyValueList) {
            keyValueList = ArrayList()
            for (i in 1..12) {
                val map = HashMap<String, String>()
                when {
                    i < Constant.KEYBOARD_ZERO -> map[Constant.MAP_KEY_NAME] = i.toString()
                    i == Constant.KEYBOARD_ZERO -> map[Constant.MAP_KEY_NAME] = "."
                    i == Constant.KEYBOARD_DEL -> map[Constant.MAP_KEY_NAME] = 0.toString()
                    else -> map[Constant.MAP_KEY_NAME] = ""
                }
                keyValueList!!.add(map)
            }
        }
    }

    /**
     * initial recycler view
     */
    private fun initRv(context: Context) {
        adapter = NumberKeyboardAdapter(keyValueList)
        rvKeyboard.layoutManager = GridLayoutManager(context, 3)
        val dividerItemDecoration = GridDividerItemDecoration.Builder(context)
            .setShowLastLine(false)
            .setHorizontalSpan(R.dimen.dp_5)
            .setVerticalSpan(R.dimen.dp_5)
            .setColor(Color.parseColor("#ebebeb"))
            .build()
        rvKeyboard.addItemDecoration(dividerItemDecoration)
        rvKeyboard.adapter = adapter
    }

    /**
     * get key value list
     *
     * @return key value list
     */
    fun getKeyValueList(): List<Map<String, String>>? {
        return keyValueList
    }

}