package com.iboomboom.grace.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iboomboom.grace.R;


/**
 * Created by DEVIN on 2017/11/23.
 */

public class KVTextView extends LinearLayout {

    TextView tvKey;
    TextView tvValue;
    EditText etValue;
    LinearLayout ll;
    View dividerLine1;
    View dividerLine2;
    private boolean canEdit;

    public KVTextView(Context context) {
        this(context, null);
    }

    public KVTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KVTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final View view = View.inflate(context, R.layout.abc_kv_textview, null);
        addView(view);

        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.KVTextView, defStyleAttr, 0);
        String content = ta.getString(R.styleable.KVTextView_contentText);
        String title = ta.getString(R.styleable.KVTextView_titleText);
        String contentHint = ta.getString(R.styleable.KVTextView_contentHint);
        int titleColor = ta.getColor(R.styleable.KVTextView_titleColor, 0xFF999999);
        int contentColor = ta.getColor(R.styleable.KVTextView_contentColor, 0xFF323232);
        float titleSize = ta.getDimension(R.styleable.KVTextView_titleSize, 12);
        float contentSize = ta.getDimension(R.styleable.KVTextView_contentSize, 14);
//        float contentInputType = ta.getInt(R.styleable.KVTextView_contentInputType,3);
        boolean isVertical = ta.getBoolean(R.styleable.KVTextView_isVertical, false);
        boolean isShowDividerLine = ta.getBoolean(R.styleable.KVTextView_isShowDividerLine, false);
        boolean isTitleWrapContent = ta.getBoolean(R.styleable.KVTextView_titleWrapContent, false);
        boolean isTitleGravityRight = ta.getBoolean(R.styleable.KVTextView_titleGravityRight, false);
        boolean isContentGravityRight = ta.getBoolean(R.styleable.KVTextView_contentGravityRight, false);
        Drawable leftIcon = ta.getDrawable(R.styleable.KVTextView_leftIcon);
        Drawable rightIcon = ta.getDrawable(R.styleable.KVTextView_rightIcon);

        canEdit = ta.getBoolean(R.styleable.KVTextView_canEdit, false);
        ta.recycle();

        tvValue = findViewById(R.id.tv_value);
        etValue = findViewById(R.id.et_value);
        tvKey = findViewById(R.id.tv_key);

        tvValue.setVisibility(canEdit ? GONE : VISIBLE);
        etValue.setVisibility(canEdit ? VISIBLE : GONE);

        tvValue.setHint(contentHint);
        etValue.setHint(contentHint);

        ll = findViewById(R.id.ll);
        dividerLine1 = findViewById(R.id.view_divider_01);
        dividerLine2 = findViewById(R.id.view_divider_02);

        tvKey.setTextColor(titleColor);
        tvValue.setTextColor(contentColor);
        etValue.setTextColor(contentColor);

        tvKey.setTextSize(TypedValue.COMPLEX_UNIT_PX,titleSize);
        tvValue.setTextSize(TypedValue.COMPLEX_UNIT_PX,contentSize);
        etValue.setTextSize(TypedValue.COMPLEX_UNIT_PX,contentSize);

        if (isVertical) {
            tvValue.setPadding(tvKey.getPaddingLeft(), 0, tvKey.getPaddingRight(), tvKey.getPaddingBottom());
            etValue.setPadding(tvKey.getPaddingLeft(), 0, tvKey.getPaddingRight(), tvKey.getPaddingBottom());
            //如果选择纵向排列，将标题的宽度设置为全屏显示
            LayoutParams titleParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvKey.setLayoutParams(titleParams);
        } else {
            if (isTitleWrapContent) {
                //如果选择title包裹内容，只支持横向排列
                LayoutParams titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvKey.setLayoutParams(titleParams);
            }
            tvValue.setPadding(tvKey.getPaddingLeft(), tvKey.getPaddingTop(), tvKey.getPaddingRight(), tvKey.getPaddingBottom());
            etValue.setPadding(tvKey.getPaddingLeft(), tvKey.getPaddingTop(), tvKey.getPaddingRight(), tvKey.getPaddingBottom());
        }

        //设置左侧和右侧图标
        if (leftIcon != null) {
            leftIcon.setBounds(0, 0, leftIcon.getMinimumWidth(), leftIcon.getMinimumHeight());
            tvKey.setCompoundDrawables(leftIcon, null, null, null);
        }

        if (rightIcon != null) {
            rightIcon.setBounds(0, 0, rightIcon.getMinimumWidth(), rightIcon.getMinimumHeight());
            tvValue.setCompoundDrawables(null, null, rightIcon, null);
            etValue.setCompoundDrawables(null, null, rightIcon, null);
        }

        //设置文本在文本框的位置，默认左对齐
        tvValue.setGravity(!isContentGravityRight ? Gravity.START : Gravity.END);
        etValue.setGravity(!isContentGravityRight ? Gravity.START : Gravity.END);
        tvKey.setGravity(!isTitleGravityRight ? Gravity.START : Gravity.END);

        ll.setOrientation(isVertical ? VERTICAL : HORIZONTAL);
        dividerLine1.setVisibility(isShowDividerLine ? VISIBLE : GONE);
        dividerLine2.setVisibility(isShowDividerLine ? VISIBLE : GONE);

        setContent(TextUtils.isEmpty(content) ? "" : content);
        setTitle(TextUtils.isEmpty(title) ? "" : title);

        handler.sendEmptyMessageDelayed(0, 100);

    }


    public void setContent(String content) {
        tvValue.setText(content);
        etValue.setText(content);
    }

    public void setTitle(String title) {
        tvKey.setText(title);
    }

    public String getContent() {
        return canEdit ? etValue.getText().toString() : tvValue.getText().toString();
    }

    public TextView getTitleView() {
        return tvKey;
    }

    public TextView getContentView() {
        return tvValue;
    }

    public EditText getEditView() {
        return etValue;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(getContent());
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //如果现实分界线的话或者将值右对齐时，将value控件的宽度设置为全屏
            if (getLayoutParams() != null && getLayoutParams().width == LayoutParams.MATCH_PARENT) {
                RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvValue.setLayoutParams(contentParams);
                etValue.setLayoutParams(contentParams);
            }
            return true;
        }
    });
}
