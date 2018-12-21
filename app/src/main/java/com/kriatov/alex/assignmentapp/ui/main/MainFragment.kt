package com.kriatov.alex.assignmentapp.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kriatov.alex.assignmentapp.R
import com.kriatov.alex.assignmentapp.core.ApiProvider
import com.kriatov.alex.assignmentapp.ui.model.CheckableItem
import com.kriatov.alex.assignmentapp.ui.recycler.CheckableItemAdapter
import com.kriatov.alex.assignmentapp.ui.recycler.HorizontalDividerDecorator
import com.kriatov.alex.assignmentapp.ui.recycler.PagingListener
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by Alex Kriatov on 12/21/18
 */
class MainFragment : Fragment(), MainContract.View, CheckableItemAdapter.OnCheckedItemCountChangedListener {

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: CheckableItemAdapter
    private var pagingListener = ScrollListener()

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setNewData(list: List<CheckableItem>) {
        adapter.setData(list)
    }

    override fun updateData(list: List<CheckableItem>) {
        adapter.addData(list)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CheckableItemAdapter()
        adapter.addOnCheckedItemCountChangedListener(this)
        with(recycler) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(HorizontalDividerDecorator(context!! ,R.drawable.shape_divider, R.dimen.results_decorator_padding,
                R.dimen.results_decorator_padding))
            adapter = this@MainFragment.adapter
            pagingListener.attach(this)
        }
        presenter = MainPresenter(context?.applicationContext as ApiProvider)
        presenter.attachView(this)
        presenter.loadData()
    }

    override fun onCheckedItemCountChanged(count: Int) {
        tvCount.text = count.toString()
    }
    private inner class ScrollListener : PagingListener() {
        var page: Int = 1

        override fun lastItemVisible() {
            page++
            pagingListener.setPaused(true)
            presenter.loadNewPage(page)
        }
    }

}