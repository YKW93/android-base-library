package com.wayne.library.ext

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addItemDecorationWithoutLastDivider(@DrawableRes dividerId: Int?) {
    if (dividerId == null) return

    val dividerDrawable = ResourcesCompat.getDrawable(resources, dividerId, context.theme)

    if (layoutManager !is LinearLayoutManager) {
        return
    }

    addItemDecoration(object : RecyclerView.ItemDecoration() {

        override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            parent.adapter?.let {
                parent.children.forEach { view ->
                    val childAdapterPosition = parent.getChildAdapterPosition(view).let {
                        if (it == RecyclerView.NO_POSITION) return else it
                    }
                    val top =
                        view.bottom + (view.layoutParams as RecyclerView.LayoutParams).bottomMargin

                    if (childAdapterPosition != state.itemCount - 1) {
                        dividerDrawable?.run {
                            bounds = Rect(left, top, right, top + intrinsicHeight)
                            draw(canvas)
                        }
                    }
                }
            }
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            parent.adapter?.let { adapter ->
                val position = parent.getChildViewHolder(view).bindingAdapterPosition
                outRect.bottom = if (adapter.itemCount - 1 == position) {
                    0
                } else {
                    dividerDrawable?.intrinsicHeight ?: 0
                }
            }
        }
    })
}