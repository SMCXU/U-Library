package com.u.ulibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

import static com.u.ulibrary.log.ULogConfig.MAX_LEN;

/**
 * 给我一行代码，还你十个BUG
 *
 * @author：Mr.U 创建时间：2020/9/20
 * 更改时间：2020/9/20
 * 版本号：1
 * 文件描述：
 */
public class UConsolePrinter implements ULogPrinter {

    @Override
    public void print(@NonNull ULogConfig config, int level, String tag, @NonNull String printString) {
        int len = printString.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub > 0) {
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                Log.println(level, tag, printString.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            if (index != len) {
                Log.println(level, tag, printString.substring(index, len));
            }
        } else {
            Log.println(level, tag, printString);
        }
    }
}
