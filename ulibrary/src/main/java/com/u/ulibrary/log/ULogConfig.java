package com.u.ulibrary.log;

/**
 * 给我一行代码，还你十个BUG
 *
 * @author：Mr.U 创建时间：2020/9/20
 * 更改时间：2020/9/20
 * 版本号：1
 * 文件描述：
 */
public abstract class ULogConfig {
    static int MAX_LEN = 512;
    static UStackTraceFormatter U_STACK_FORMATTER = new UStackTraceFormatter();
    static UThreadFormatter U_THREAD_FORMATTER = new UThreadFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "ULog";
    }

    public boolean enable() {
        return true;
    }

    public boolean inclideThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public ULogPrinter[] printers() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object src);
    }
}
