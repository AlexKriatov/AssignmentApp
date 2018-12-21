package com.kriatov.alex.assignmentapp.ui.main

import com.kriatov.alex.assignmentapp.core.ApiProvider
import com.kriatov.alex.assignmentapp.network.Api
import com.kriatov.alex.assignmentapp.network.model.ModelConfig
import com.kriatov.alex.assignmentapp.utils.Mappers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alex Kriatov on 12/21/18
 */
class MainPresenter(provider: ApiProvider): MainContract.Presenter<MainContract.View> {

    private lateinit var disposable: CompositeDisposable
    private var view:MainContract.View? = null
    private var api: Api = provider.getApi()

    override fun attachView(view: MainContract.View) {
        disposable = CompositeDisposable()
        this.view = view
    }

    override fun detachView() {
        disposable.dispose()
        view = null
    }

    override fun loadNewPage(int: Int) {
        safeSubscribe {
            api.loadPage(page = int)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.showProgress() }
                .subscribe({
                    mapDataAndUpdateList(it)
                }, {
                    onLoadingError(it)
                })
        }
    }

    override fun loadData() {
        safeSubscribe {
            api.loadPage(page = 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.showProgress() }
                .subscribe({
                    mapDataAndSetList(it)
                }, {
                    onLoadingError(it)
                })
        }
    }

    private fun mapDataAndSetList(config: ModelConfig){
        view?.hideProgress()
        view?.setNewData(Mappers.mapModelConfigToCheckableItems(config))

    }
    private fun mapDataAndUpdateList(config: ModelConfig){
        view?.hideProgress()
        view?.updateData(Mappers.mapModelConfigToCheckableItems(config))

    }
    private fun onLoadingError(error: Throwable){
        error.printStackTrace()
        view?.hideProgress()
    }

    private fun safeSubscribe(block: () -> Disposable) {
        disposable.add(block())
    }
}