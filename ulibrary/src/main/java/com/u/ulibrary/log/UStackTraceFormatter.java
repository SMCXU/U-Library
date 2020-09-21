package com.u.ulibrary.log;

/**
 * 给我一行代码，还你十个BUG
 *
 * @author：Mr.U 创建时间：2020/9/20
 * 更改时间：2020/9/20
 * 版本号：1
 * 文件描述：
 */
public class UStackTraceFormatter implements ULogFormatter<StackTraceElement[]> {

    @Override
    public String format(StackTraceElement[] stackTrace) {
        StringBuilder sb = new StringBuilder(128);
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        } else if (stackTrace.length == 1) {
            return "\t-" + stackTrace[0].toString();
        } else if (stackTrace.length > 1) {
            for (int i = 0, len = stackTrace.length; i < len; i++) {
                if (i == 0) {
                    sb.append("stackTrace:\n");
                }
                if (i != len - 1) {
                    sb.append("\t|-");
                    sb.append(stackTrace[i].toString());
                    sb.append("\n");
                } else {
                    sb.append("\t#");
                    sb.append(stackTrace[i].toString());
                }
            }
        }

        return sb.toString();
    }
}
