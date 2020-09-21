package com.u.u_library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.u.ulibrary.log.*
import kotlinx.android.synthetic.main.activity_u_log_demo.*

class ULogDemoActivity : AppCompatActivity() {
    var viewProvider: UViewPrinter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u_log_demo)
        viewProvider = UViewPrinter(this)
        button.setOnClickListener {
            printLog();
        }
        viewProvider!!.viewProvider.showFloatingView()
    }

    private fun printLog() {
        ULogManager.getInstance().addPrinter(viewProvider)
        ULog.log(object : ULogConfig() {
            override fun inclideThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }

        }, ULogType.E, "Mr.U", "1231")

        ULog.a("21312");
    }
}