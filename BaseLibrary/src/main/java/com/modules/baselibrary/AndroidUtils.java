package com.modules.baselibrary;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.WindowManager;

public class AndroidUtils {

    /**
     * 获取屏幕真实的物理尺寸
     * https://developer.android.com/reference/android/view/Display
     * https://developer.android.com/reference/android/view/WindowMetrics#getBounds()
     *
     * @param context application context
     * @return Point  x为宽，y为高
     */
    public static Point getScreenRealSize(Application context) {
        Point point = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Rect bounds = wm.getCurrentWindowMetrics().getBounds();
            point.x = bounds.right - bounds.left;
            point.y = bounds.bottom - bounds.top;
        } else {
            wm.getDefaultDisplay().getRealSize(point);
        }
        LogProxy.d(point);
        return point;
    }

    /**获取屏幕的实际尺寸，不包含system decor elements
     * @param context
     * @return Point  x为宽，y为高
     */
    public static Point getScreenSize(Application context) {
        Point point = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(point);
        return point;
    }
}
