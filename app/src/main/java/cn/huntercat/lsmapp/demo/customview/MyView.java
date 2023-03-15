package cn.huntercat.lsmapp.demo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import cn.huntercat.lsmapp.R;

public class MyView extends androidx.appcompat.widget.AppCompatTextView {

    private static final String TAG = MyView.class.getSimpleName();

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        String test = typedArray.getString(R.styleable.MyView_test);
        Log.i(TAG, test);
        typedArray.recycle();
    }
}
