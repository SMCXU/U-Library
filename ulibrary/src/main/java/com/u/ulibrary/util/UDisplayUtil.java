package com.u.ulibrary.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * 给我一行代码，还你十个BUG
 *
 * @author：Mr.U 创建时间：2020/9/21
 * 更改时间：2020/9/21
 * 版本号：1
 * 文件描述：
 */
public class UDisplayUtil {

    public static int dp2px(float dp, Resources resources){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,resources.getDisplayMetrics());
    }
}
