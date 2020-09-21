package com.u.u_library

import android.app.Application
import com.google.gson.Gson
import com.u.ulibrary.log.UConsolePrinter
import com.u.ulibrary.log.ULogConfig
import com.u.ulibrary.log.ULogManager
import com.u.ulibrary.log.ULogPrinter

/**
 * 给我一行代码，还你十个BUG
 * @author：Mr.U
 *
 * 创建时间：2020/9/20
 * 更改时间：2020/9/20
 * 版本号：1
 * 文件描述：
 *
 */
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ULogManager.init(object : ULogConfig() {

            override fun injectJsonParser(): JsonParser {
                return JsonParser { src ->
                    Gson().toJson(src)
                }
            }

            override fun getGlobalTag(): String {
                return "Mr.U"
            }

            override fun enable(): Boolean {
                return true
            }
        }, UConsolePrinter())
    }
}