package com.u.ulibrary.log;

import androidx.annotation.NonNull;

/**
 * 给我一行代码，还你十个BUG
 *
 * @author：Mr.U 创建时间：2020/9/20
 * 更改时间：2020/9/20
 * 版本号：1
 * 文件描述：
 */
public interface ULogPrinter {

    void print(@NonNull ULogConfig config,int level,String tag,@NonNull String printString);

}
