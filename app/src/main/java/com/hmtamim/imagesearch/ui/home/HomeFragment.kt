package com.hmtamim.imagesearch.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.FragmentHomeBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import com.hmtamim.imagesearch.utils.ToastUtils
import com.hmtamim.imagesearch.utils.openKeyboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    HomeViewModel::class.java,
    R.layout.fragment_home
) {
    override fun initViews() {
        initSearch()
    }

    private fun initSearch() {

        // open keyboard automatically for better user experience
        binding.etSearch.openKeyboard()

        // listen to search click and open perform search on gallery fragment
        binding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                val query = binding.etSearch.text.toString()
                if (query.isEmpty()) {
                    ToastUtils.show("Please type something to search", context)
                    return@OnEditorActionListener false
                }
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGalleryFragment(query))
                return@OnEditorActionListener true
            }
            false
        })

        // show/hide search clear button
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnClearSearch.visibility =
                    if (binding.etSearch.text.toString().isEmpty()) View.GONE else View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    override fun liveEventsObservers() {
        viewModel.networkConnectionObserver.observe(viewLifecycleOwner, Observer {
            Log.d("is_offline", it.toString())
        })
    }

    override fun clickListeners() {
        binding.btnClearSearch.setOnClickListener {
            binding.etSearch.setText("")
            binding.etSearch.openKeyboard()
        }
    }

}