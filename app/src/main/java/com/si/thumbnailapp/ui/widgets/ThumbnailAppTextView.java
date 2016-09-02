package com.si.thumbnailapp.ui.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * A simple custom TextView to use a special font type.
 */
public class ThumbnailAppTextView extends TextView {
    public ThumbnailAppTextView(Context context) {
        super(context);
        init();
    }

    public ThumbnailAppTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThumbnailAppTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ThumbnailAppTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface type = Typeface.createFromAsset(getContext().getAssets(),"fonts/Oswald-Regular.ttf");
            setTypeface(type);
        }
    }


}
