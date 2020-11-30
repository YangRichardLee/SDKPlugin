package com.lion.plugin.sdkplugin.sdk.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * 个人中心用到的点
 * Created by eleven on 16/7/26.
 */
public class DotView extends View {

    public DotView(Context context) {
        super(context);
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#eb1d44"));
        paint.setStyle(Paint.Style.FILL);
        //获取圆心的x坐标
        int center = getWidth() / 2;
        canvas.drawCircle(center, center, center, paint);
        invalidate();
    }
}
