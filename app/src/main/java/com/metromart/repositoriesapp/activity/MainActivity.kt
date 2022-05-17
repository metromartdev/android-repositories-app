package com.metromart.repositoriesapp.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.metromart.repositoriesapp.adapter.MainAdapter
import com.metromart.repositoriesapp.databinding.ActivityMainBinding
import com.metromart.repositoriesapp.extensions.setCustomColorSchemeColors
import com.metromart.repositoriesapp.extensions.setRefreshState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        setUpView()
        loadData()
        addViewListeners()
    }

    private fun setUpObserver() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.mainState.collect { state ->
                setLoadingView(state = state)
                when (state) {
                    is MainState.LoadRepositories -> {
                        binding.animationView.visibility = View.GONE
                        mainAdapter.submitList(state.repositories)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun setUpView() {
        mainAdapter = MainAdapter()

        binding.apply {
            swipeRefresh.setCustomColorSchemeColors()
            recyclerview.apply {
                adapter = mainAdapter
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }

    private fun loadData() {
        mainViewModel.getRepositories()
    }

    private fun addViewListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun setLoadingView(state: MainState) {
        binding.apply {
            animationView.visibility = if (state == MainState.ShowLoader) View.VISIBLE else View.INVISIBLE
            recyclerview.visibility = if (state == MainState.ShowLoader) View.INVISIBLE else View.VISIBLE
            swipeRefresh.setRefreshState(isRefreshing = state == MainState.ShowLoader)
        }
    }
}