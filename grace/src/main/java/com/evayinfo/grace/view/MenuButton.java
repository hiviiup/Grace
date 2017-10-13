package com.evayinfo.grace.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evayinfo.grace.R;

/**
 * Created by hiviiup on 2017/3/1.
 */

public class MenuButton extends LinearLayout {

    //按钮文字
    private String name;
    //按钮图标
    private Drawable icon;
    //按钮文字大小
    private float size;
    //文字颜色
    private ColorStateList color;

    private TextView tvName;
    private ImageView ivIcon;

    public MenuButton(Context context) {
        this(context, null);
    }

    public MenuButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Resources.Theme theme = context.getTheme();
        TypedArray ta = theme.obtainStyledAttributes(attrs, R.styleable.MenuButton, defStyleAttr, 0);
        icon = ta.getDrawable(R.styleable.MenuButton_menuIcon);
        name = ta.getString(R.styleable.MenuButton_menuName);
        size = ta.getDimension(R.styleable.MenuButton_menuNameSize, 12f);
        color = ta.getColorStateList(R.styleable.MenuButton_menuNameColor);
        ta.recycle();

        init();
    }

    private void init() {
        //设置方向
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        //创建IconView;
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        ivIcon = new ImageView(getContext());
        ivIcon.setImageDrawable(icon);
        addView(ivIcon, params);
        //创建TextView
        tvName = new TextView(getContext());
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        tvName.setTextColor(color);
        tvName.setText(name);
        addView(tvName, params);
    }


    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        tvName.setSelected(selected);
        ivIcon.setSelected(selected);
    }
}
