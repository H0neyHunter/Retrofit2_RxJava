package com.usyssoft.retrofit2_rxjava.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usyssoft.retrofit2_rxjava.model.CarList
import com.usyssoft.retrofit2_rxjava.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CarListViewModel : ViewModel() {
    val carList = MutableLiveData<List<CarList>>()
    val carErrorMessage = MutableLiveData<Boolean>()
    val carLoading = MutableLiveData<Boolean>()

    private var apiService: ApiService  = ApiService()
    private val disposable = CompositeDisposable()

    fun getData() {
        disposable.add(
            apiService.getCarListData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CarList>>() {
                    override fun onSuccess(t: List<CarList>) {
                        carList.value = t
                        carErrorMessage.value =  false
                        carLoading.value =  false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        carErrorMessage.value =  true
                        carLoading.value =  false
                    }

                })
        )
    }
}