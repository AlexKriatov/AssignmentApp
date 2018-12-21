package com.kriatov.alex.assignmentapp.ui.recycler

/**
 * Created by Alex Kriatov on 12/21/18
 */
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

abstract class PagingListener : RecyclerView.OnScrollListener() {

    private var adapter: RecyclerView.Adapter<*>? = null
    private var enabled = true
    private var size: Int = 0
    private var paused: Boolean = false


    fun attach(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(this)
        adapter = recyclerView.adapter
    }

    fun detach(recyclerView: RecyclerView) {
        recyclerView.removeOnScrollListener(this)
        adapter = null
    }

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    fun isPaused(): Boolean {
        return paused
    }

    fun setPaused(paused: Boolean) {
        this.paused = paused
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (enabled and !paused) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
            size = recyclerView.adapter!!.itemCount
            val last = layoutManager!!.findLastVisibleItemPosition()
            if (last == size - 1) {
                lastItemVisible()
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        Log.d("PaginationListener", "dy:$dy")
    }

    protected abstract fun lastItemVisible()
}
