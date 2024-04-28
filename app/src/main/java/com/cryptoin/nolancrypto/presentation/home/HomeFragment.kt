package com.cryptoin.nolancrypto.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.cryptoin.nolancrypto.data.model.Coin
import com.cryptoin.nolancrypto.databinding.FragmentHomeBinding
import com.cryptoin.nolancrypto.presentation.coindetail.DetailCoinActivity
import com.cryptoin.nolancrypto.presentation.home.adapter.CoinListAdapter
import com.cryptoin.nolancrypto.utils.GridSpacingItemDecoration
import com.cryptoin.nolancrypto.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModel()
    

    private val productAdapter: CoinListAdapter by lazy {
        CoinListAdapter {
            DetailCoinActivity.startActivity(requireContext(), it )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListProduct()
        getCoinData()
    }



    private fun setupListProduct() {
        val itemDecoration = GridSpacingItemDecoration(1, 12, true)
        binding.rvItem.apply {
            adapter = productAdapter
            addItemDecoration(itemDecoration)
        }
    }

    private fun getCoinData() {
        homeViewModel.getCoins().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data -> bindProductList(data) }
                }
            )
        }
    }



    private fun bindProductList(data: List<Coin>) {
        productAdapter.submitData(data)
    }

//    private fun navigateToProfile() {
//        if (requireActivity() !is MainActivity) return
//        (requireActivity() as MainActivity).navigateToTabProfile()
//    }
}