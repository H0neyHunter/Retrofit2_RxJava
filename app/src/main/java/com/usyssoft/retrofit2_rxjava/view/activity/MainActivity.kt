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

    private fun liveData() {
        viewModel.carList.observe(this@MainActivity, Observer {carlist->
            carlist?.let {
                adapter.updateAdapter(carlist)
            }
        })
        viewModel.carErrorMessage.observe(this@MainActivity, Observer {error->

        })
        viewModel.carLoading.observe(this@MainActivity, Observer {loading->

        })
    }
}