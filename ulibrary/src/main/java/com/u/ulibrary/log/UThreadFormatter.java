package com.u.ulibrary.log;

/**
 * 给我一行代码，还你十个BUG
 *
 * @author：Mr.U 创建时间：2020/9/20
 * 更改时间：2020/9/20
 * 版本号：1
 * 文件描述：
 */
public class UThreadFormatter implements ULogFormatter<Thread>{

    @Override
    public String format(Thread data) {
        return "Thread:"+data.getName();
    }
}
