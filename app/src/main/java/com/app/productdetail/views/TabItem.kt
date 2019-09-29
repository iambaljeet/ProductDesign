package com.app.productdetail.views

import android.annotation.TargetApi
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.app.productdetail.R
import com.app.productdetail.callback.OnTabItemClickListener
import com.app.productdetail.utility.LayoutParamsHelper

class TabItem : FrameLayout, View.OnClickListener {
    private var onTabItemClickListener: OnTabItemClickListener? = null
    var position: Int = 0

    private var selectedTabIcon: Drawable? = null
    private var selectedTabIconColor: Int = 0

    private var unselectedTabIcon: Drawable? = null
    private var unselectedTabIconColor: Int = 0

    private var iconImageView: ImageView? = null

    private var isActive = false
    private var bottomNavigation: BottomNavigationBar? = null

    constructor(context: Context) : super(context) {
        parseCustomAttributes(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        parseCustomAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        parseCustomAttributes(attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        parseCustomAttributes(attrs)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        checkParent()
    }

    private fun checkParent() {
        post {
            if (parent is BottomNavigationBar) {
                bottomNavigation = parent as BottomNavigationBar
                setupView()
            } else {
                throw RuntimeException("TabItem parent must be BottomNavigation!")
            }
        }
    }

    private fun setupView() {
        setOnClickListener(this)
        layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f)

        foreground = resources.getDrawable(R.drawable.tab_forground, null)

        iconImageView = ImageView(context)
        iconImageView!!.scaleType = ImageView.ScaleType.FIT_CENTER
        iconImageView!!.setImageDrawable(selectedTabIcon)
        iconImageView!!.layoutParams = LayoutParamsHelper.getTabItemIconLayoutParams()

        addView(iconImageView)
    }

    private fun parseCustomAttributes(attributeSet: AttributeSet?) {

        if (attributeSet != null) {
            //get xml attributes
            val typedArray = context.obtainStyledAttributes(attributeSet,
                R.styleable.TabItem, 0, 0)
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    selectedTabIconColor =
                        typedArray.getColor(R.styleable.TabItem_selected_tab_text_color, 0)

                    unselectedTabIconColor =
                        typedArray.getColor(R.styleable.TabItem_unselected_tab_text_color, 0)
                } else {
                    selectedTabIconColor =
                        typedArray.getColor(R.styleable.TabItem_selected_tab_text_color, 0)

                    unselectedTabIconColor =
                        typedArray.getColor(R.styleable.TabItem_unselected_tab_text_color, 0)
                }
                selectedTabIcon = typedArray.getDrawable(R.styleable.TabItem_tab_icon)
                selectedTabIcon?.setTint(selectedTabIconColor)
                unselectedTabIcon = typedArray.getDrawable(R.styleable.TabItem_unselected_tab_icon)
                unselectedTabIcon?.setTint(unselectedTabIconColor)
            } finally {
                typedArray.recycle()
            }
        }
    }

    override fun setSelected(isActive: Boolean) {
        if (this.isActive != isActive) {
            this.isActive = isActive
        }
    }

    fun setOnTabItemClickListener(onTabItemClickListener: OnTabItemClickListener) {
        this.onTabItemClickListener = onTabItemClickListener
    }

    override fun onClick(view: View) {
        if (onTabItemClickListener != null) {
            onTabItemClickListener!!.onTabItemClick(position)
        }
    }

    public fun setPositions(position: Int) {
        this.position = position
    }

    public fun getPositions(): Int {
        return position
    }
}
