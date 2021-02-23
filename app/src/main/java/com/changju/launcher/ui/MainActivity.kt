package com.changju.launcher.ui

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.changju.launcher.R
import com.changju.launcher.databinding.ActivityMainBinding
import com.changju.launcher.listener.OnEditDialogResultListener
import com.changju.launcher.ui.dialog.InputPwdDialog
import com.opensource.svgaplayer.SVGAImageView

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
    }

    private fun initView() {

        mBinding.tvSetting.setOnClickListener {
            InputPwdDialog
                .newInstance()
                .setListener(object : OnEditDialogResultListener {
                    override fun onResult(result: String) {
                        startActivity(Intent(Settings.ACTION_SETTINGS))
                    }
                })
                .show(supportFragmentManager, "DEMO_DIALOG")
        }
        mBinding.tvChangju.setOnClickListener {
            val intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.component = ComponentName("com.newproject.hardqing",
                "com.newproject.hardqing.ui.ScanCodeActivity")
            intent.action = "android.intent.action.VIEW"
            startActivity(intent)
        }
    }
}