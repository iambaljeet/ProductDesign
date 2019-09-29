package com.app.productdetail.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import com.app.productdetail.R
import com.app.productdetail.callback.OnSelectedItemChangeListener
import com.app.productdetail.callback.OnTabItemClickListener

class BottomNavigationBar: LinearLayout,
    OnTabItemClickListener {

    private var defaultItem = 0

    private var selectedItemPosition = defaultItem

    var tabItems: MutableList<TabItem> = ArrayList()

    private var onSelectedItemChangeListener: OnSelectedItemChangeListener? = null


    constructor(context: Context) : this(context, null) {
        if (!isInEditMode) {
            setup(null)
        }
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        if (!isInEditMode) {
            setup(attrs)
        }
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        if (!isInEditMode) {
            setup(attrs)
        }
    }

    /**
     * this method setup necessary attributes and behavior of bottom navigation
     *
     * @param attributeSet used for setup xml custom attributes
     */
    private fun setup(attributeSet: AttributeSet?) {
        parseAttributes(attributeSet)
        orientation = HORIZONTAL
        gravity = Gravity.START
        minimumHeight = context.resources.getDimensionPixelSize(R.dimen.bottom_navigation_min_width)
    }

    /**
     * we call [.setupChildren] in this method, because bottom navigation children are drew in this
     * state and aren't null
     */
    override fun onFinishInflate() {
        super.onFinishInflate()
        setupChildren()
    }

    /**
     * this function setup [TabItem]s, also specify [BottomNavigation.type]
     */
    private fun setupChildren() {
        if (childCount > 0) {
            if (childCount >= 3 && childCount <= 5) {
                for (i in 0 until childCount) {
                    if (getChildAt(i) !is TabItem) {
                        throw RuntimeException("Bottom navigation only accept tab item as child.")
                    } else {
                        val tabItem = getChildAt(i) as TabItem
                        tabItem.setPositions(i)
                        tabItems.add(tabItem)
                        tabItem.setOnTabItemClickListener(this)
                    }
                }
            } else {
                throw RuntimeException("Bottom navigation child count must between 3 to 5 only.")
            }
        } else {
            throw RuntimeException("Bottom navigation can't be empty!")
        }
    }

    /**
     * this function used to manage which tab item must selected or which item must deselect
     */
    private fun onSelectedItemChanged() {
        for (i in tabItems.indices) {
            tabItems[i].isSelected = tabItems[i].getPositions() == selectedItemPosition
        }
    }

    /**
     * @see OnTabItemClickListener
     */
    override fun onTabItemClick(position: Int) {
        if (position != selectedItemPosition) {
            selectedItemPosition = position
            onSelectedItemChanged()
            if (onSelectedItemChangeListener != null) {
                post { onSelectedItemChangeListener!!.onSelectedItemChanged(tabItems[position].id) }
            }
        }
    }

    /**
     * this function get xml custom attributes and parse it to instance variables
     *
     * @param attributeSet used for retrieve custom values
     */
    private fun parseAttributes(attributeSet: AttributeSet?) {
        if (attributeSet != null) {
            val typedArray =
                context.obtainStyledAttributes(attributeSet,
                    R.styleable.BottomNavigation
                )

            typedArray.recycle()
        }
    }

    fun setDefaultItem(position: Int) {
        this.defaultItem = position
        this.selectedItemPosition = position
    }

    fun getDefaultItem(): Int {
        return defaultItem
    }

    fun getTabByPosition(position: Int): TabItem {
        return tabItems[position]
    }

    fun setOnSelectedItemChangeListener(onSelectedItemChangeListener: OnSelectedItemChangeListener) {
        this.onSelectedItemChangeListener = onSelectedItemChangeListener
        onSelectedItemChangeListener.onSelectedItemChanged(tabItems[defaultItem].id)
    }

    fun getSelectedItem(): Int {
        return selectedItemPosition
    }

    fun setSelectedItem(position: Int) {
        onTabItemClick(position)
    }
}

