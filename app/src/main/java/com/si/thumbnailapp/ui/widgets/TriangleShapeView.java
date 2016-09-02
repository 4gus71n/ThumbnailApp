package com.si.thumbnailapp.ui.widgets;

/**
 * Created by Agustin Tomas Larghi on 02/09/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * A simple custom view that draws a right triangle using some color. The color is set using
 * {@link TriangleShapeView#setColorCode(int)}.
 */
public class TriangleShapeView extends View {

    public int colorCode = Color.GREEN;

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    public TriangleShapeView(Context context) {
        super(context);
    }

    public TriangleShapeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TriangleShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth() / 2;
        int h = getHeight() / 2;

        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.lineTo(0, 0);

        path.close();

        Paint p = new Paint();
        p.setColor(colorCode);
        p.setAntiAlias(true);

        canvas.drawPath(path, p);
    }

    public void setSeparatorColor(int highlightColor) {
        colorCode = highlightColor;
        invalidate();
    }
}