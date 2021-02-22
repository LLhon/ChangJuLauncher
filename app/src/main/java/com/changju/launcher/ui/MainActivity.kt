package com.changju.launcher.ui

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.changju.launcher.R
import com.changju.launcher.listener.OnEditDialogResultListener
import com.changju.launcher.ui.dialog.InputPwdDialog
import com.opensource.svgaplayer.SVGAImageView

class MainActivity : AppCompatActivity() {

    lateinit var mSvgaImageView: SVGAImageView
    lateinit var mTvSetting: TextView
    lateinit var mTvChangJu: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        mSvgaImageView = findViewById(R.id.svga_view)
        mTvSetting = findViewById(R.id.tv_setting)
        mTvChangJu = findViewById(R.id.tv_changju)

        mTvSetting.setOnClickListener {
            InputPwdDialog
                .newInstance()
                .setListener(object : OnEditDialogResultListener {
                    override fun onResult(result: String) {
                        val intent = Intent(Settings.ACTION_SETTINGS)
                        startActivity(intent)
                    }
                })
                .show(supportFragmentManager, "DEMO_DIALOG")
        }
        mTvChangJu.setOnClickListener {
            val intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val comp = ComponentName("com.newproject.hardqing",
                "com.newproject.hardqing.ui.ScanCodeActivity")
            intent.component = comp
            intent.action = "android.intent.action.VIEW"
            startActivity(intent)
        }
    }
}