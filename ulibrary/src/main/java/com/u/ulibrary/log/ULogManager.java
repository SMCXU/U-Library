package com.u.ulibrary.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给我一行代码，还你十个BUG
 *
 * @author：Mr.U 创建时间：2020/9/20
 * 更改时间：2020/9/20
 * 版本号：1
 * 文件描述：
 */
public class ULogManager {
    private ULogConfig config;
    private static ULogManager instance;
    private List<ULogPrinter> printers = new ArrayList<>();

    private ULogManager(ULogConfig config, ULogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static ULogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull ULogConfig config, ULogPrinter... printers) {
        instance = new ULogManager(config, printers);
    }

    public void addPrinter(ULogPrinter... printer) {
        printers.addAll(Arrays.asList(printer));
    }

    public ULogConfig getConfig() {
        return config;
    }

    public List<ULogPrinter> getPrinters() {
        return printers;
    }

}
