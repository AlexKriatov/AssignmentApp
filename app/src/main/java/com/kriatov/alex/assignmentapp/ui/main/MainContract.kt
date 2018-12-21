package com.kriatov.alex.assignmentapp.ui.main

import com.kriatov.alex.assignmentapp.ui.model.CheckableItem

/**
 * Created by Alex Kriatov on 12/21/18
 */
interface MainContract {
    interface View{
        fun showProgress()
        fun hideProgress()
        fun setNewData(list: List<CheckableItem>)
        fun updateData(list: List<CheckableItem>)
    }

    interface Presenter<view: MainContract.View>{
        fun attachView(view: View)
        fun loadNewPage(int:Int)
        fun loadData()
        fun detachView()
    }
}