package com.usyssoft.retrofit2_rxjava.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.usyssoft.retrofit2_rxjava.databinding.ActivityMainBinding
import com.usyssoft.retrofit2_rxjava.view.adapter.CarAdapter
import com.usyssoft.retrofit2_rxjava.viewModel.CarListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private lateinit var context: Context

    private lateinit var viewModel : CarListViewModel
    private var adapter: CarAdapter = CarAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        context = this@MainActivity

        b.apply {

            viewModel = ViewModelProvider(this@MainActivity)[CarListViewModel::class.java]
            viewModel.getData()


            rvMain.setHasFixedSize(true)
            val lm = LinearLayoutManager(context)
            rvMain.layoutManager = lm
            rvMain.adapter = adapter

            liveData()

        }

    }
    private val disposable = CompositeDisposable()
    private fun liveData() {
        val dispose1 = viewModel.carList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { carList ->
                carList?.let {
                    adapter.updateAdapter(carList)
                }
            }

        disposable.add(dispose1)
        val dispose2 = viewModel.carErrorMessage
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { hasError ->
                // Hata durumunda yapılacak işlemler
            }
        disposable.add(dispose2)
        val dispose3 = viewModel.carLoading
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isLoading ->
                // Yükleme durumunu gösterme/gizleme işlemleri
            }
        disposable.add(dispose3)

        /*
        viewModel.carList.observe(this@MainActivity, Observer {carlist->
            carlist?.let {
                adapter.updateAdapter(carlist)
            }
        })
        viewModel.carErrorMessage.observe(this@MainActivity, Observer {error->

        })
        viewModel.carLoading.observe(this@MainActivity, Observer {loading->

        })*/
        //disposable.clear()
    }
}