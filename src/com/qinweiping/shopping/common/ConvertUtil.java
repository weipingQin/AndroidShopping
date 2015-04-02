package com.qinweiping.shopping.common;

import android.content.Context;
import android.util.TypedValue;

public class ConvertUtil {
	public static int dp2px(Context context,int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
	}

	public static int sp2px(float spValue, float fontScale) {
		return (int) (spValue * fontScale + 0.5f);
	}
}
