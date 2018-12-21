package com.kriatov.alex.assignmentapp.ui.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Alex Kriatov on 12/21/18
 */
class HorizontalDividerDecorator(
    context: Context,
    dividerId: Int,
    paddingStartId: Int,
    paddingEndId: Int) : RecyclerView.ItemDecoration() {

    private val divider = ContextCompat.getDrawable(context, dividerId)!!
    private val left = context.resources.getDimension(paddingStartId).toInt()
    private val right = context.resources.getDimension(paddingEndId).toInt()
    private val mBounds = Rect()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom += divider.intrinsicHeight
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        if (parent.isAnimating) return
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, this.mBounds)
            val bottom = this.mBounds.bottom + Math.round(child.translationY)
            val top = bottom - this.divider.intrinsicHeight
            this.divider.setBounds(left, top, parent.width - right, bottom)
            this.divider.draw(c)
        }
    }
}