package com.u.ulibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * 给我一行代码，还你十个BUG
 *
 * @author：Mr.U 创建时间：2020/9/19
 * 更改时间：2020/9/19
 * 版本号：1
 * 文件描述：
 * 1.打印堆栈信息
 * 2.File输出
 * 3.模拟控制台
 */
public class ULog {
    private static final String U_LOG_PACKAGE;

    static {
        String className = ULog.class.getName();
        U_LOG_PACKAGE = className.substring(0, className.lastIndexOf(".") + 1);
    }

    public static void v(Object... contents) {
        log(ULogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(ULogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(ULogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(ULogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(ULogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(ULogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(ULogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(ULogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(ULogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(ULogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(ULogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(ULogType.A, tag, contents);
    }

    public static void log(@ULogType.TYPE int type, Object... contents) {
        log(type, ULogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@ULogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(ULogManager.getInstance().getConfig(), type, tag, contents);
    }

    public static void log(@NonNull ULogConfig config, @ULogType.TYPE int type, @NonNull String tag, Object... contents) {

        if (!config.enable()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        //threadName
        if (config.inclideThread()) {
            String threadInfo = ULogConfig.U_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }
        //堆栈信息
        if (config.stackTraceDepth() > 0) {
            String stackTrace = ULogConfig.U_STACK_FORMATTER.format(UStackTraceUtil.getCroppedRealStackTrace(new Throwable().getStackTrace(),
                    U_LOG_PACKAGE, config.stackTraceDepth()
            ));
            sb.append(stackTrace).append("\n");
        }
        String body = parseBody(contents, config);
        sb.append(body);
        List<ULogPrinter> printers = config.printers() != null ? Arrays.asList(config.printers()) : ULogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }

        //打印log
        for (ULogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
        Log.println(type, tag, body);
    }

    private static String parseBody(@NonNull Object[] contents, @NonNull ULogConfig config) {
        if (config.injectJsonParser() != null) {
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


}
