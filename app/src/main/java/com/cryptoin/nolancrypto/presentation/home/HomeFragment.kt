package com.cryptoin.nolancrypto.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.cryptoin.nolancrypto.R
import com.cryptoin.nolancrypto.data.model.Coin
import com.cryptoin.nolancrypto.databinding.FragmentHomeBinding
import com.cryptoin.nolancrypto.presentation.coindetail.DetailCoinActivity
import com.cryptoin.nolancrypto.presentation.home.adapter.CoinListAdapter
import com.cryptoin.nolancrypto.presentation.home.adapter.CoinTopAdapter
import com.cryptoin.nolancrypto.utils.GridSpacingItemDecoration
import com.cryptoin.nolancrypto.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModel()

    private val productAdapter: CoinListAdapter by lazy {
        CoinListAdapter {
            DetailCoinActivity.startActivity(requireContext(), it.id)
        }
    }

    private val topAdapter: CoinTopAdapter by lazy {
        CoinTopAdapter {
            DetailCoinActivity.startActivity(requireContext(), it.id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupListProduct()
        getCoinData()
        loadProfileData()
        setupListTop()
        getCoinTopData()
    }

    private fun setupListProduct() {
        val itemDecoration = GridSpacingItemDecoration(1, 12, true)
        binding.rvItem.apply {
            adapter = productAdapter
            addItemDecoration(itemDecoration)
        }
    }

    private fun setupListTop() {
        val itemDecorationTop = GridSpacingItemDecoration(3, 12, true)
        binding.layoutFav.apply {
            adapter = topAdapter
            addItemDecoration(itemDecorationTop)
        }
    }

    private fun getCoinData() {
        homeViewModel.getCoins().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data -> bindProductList(data) }
                    binding.layoutState.pbLoading.isVisible = false
                },
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
//                    binding.layoutFav.root.isVisible = false
                },
                doOnError = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = it.exception?.message.orEmpty()
                    binding.rvItem.isVisible = false
                },
                doOnEmpty = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text =
                        getString(R.string.text_data_not_available)
                },
            )
        }
    }

    private fun getCoinTopData() {
        homeViewModel.getCoins().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data -> bindProductTop(data) }
                },
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                },
                doOnError = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = it.exception?.message.orEmpty()
                    binding.rvItem.isVisible = false
                },
                doOnEmpty = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text =
                        getString(R.string.text_data_not_available)
                },
            )
        }
    }

    private fun loadProfileData() {
        if (homeViewModel.isLoggedIn()) {
            homeViewModel.getCurrentUser()?.let { user ->
                binding.layoutHeader.layoutBanner.tvProfileName.text = getString(R.string.text_name_greetings, user.fullName)
            }
        } else {
            binding.layoutHeader.layoutBanner.tvProfileName.text = getString(R.string.text_name_banner)
        }
    }

    private fun bindProductList(data: List<Coin>) {
        productAdapter.submitData(data)
    }

    private fun bindProductTop(data: List<Coin>) {
        topAdapter.submitData(data)
    }
}
