package com.anonymous.giphyapp.ui.gifSearch.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.anonymous.giphyapp.databinding.FragmentSearchBinding
import com.anonymous.giphyapp.domain.search.model.AnimatedImagesInMultipleSize
import com.anonymous.giphyapp.domain.search.model.Resource
import com.anonymous.giphyapp.ui.base.BaseFragment
import com.anonymous.giphyapp.ui.base.EndlessGridRecyclerView
import com.anonymous.giphyapp.ui.base.hideKeyboard
import com.anonymous.giphyapp.ui.gifSearch.SearchViewModel
import com.anonymous.giphyapp.ui.gifSearch.adapter.GifImageAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {
    private lateinit var binding: FragmentSearchBinding
    private var adapter = GifImageAdapter()
    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageList.adapter = adapter
        setScrollingListenerToRecyclerView()
        setViewModelObserver()
        setSearchListener()
    }

    private fun setSearchListener() {
        binding.searchRepo.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                startSearch(v)
                true
            } else {
                false
            }
        }
    }

    private fun startSearch(view: TextView) {
        view.hideKeyboard()
        adapter.submitList(null)
        viewModel.searchImages(view.text.toString())
    }

    private fun setScrollingListenerToRecyclerView() {
        binding.imageList.layoutManager?.let {
            binding.imageList.addOnScrollListener(
                EndlessGridRecyclerView(it as GridLayoutManager) {
                    viewModel.getMoreImages()
                }
            )
        }
    }

    private fun setViewModelObserver() {
        viewModel.imageList.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> updateDataToAdapter(it.data)
                is Resource.DataError -> it.errorCode?.let { it1 ->
                    showSnackBar(viewModel.getErrorMessage(it1).description)
                }
            }
        })
    }

    private fun updateDataToAdapter(data: List<AnimatedImagesInMultipleSize>?) {
        data?.let {
            adapter.submitList(data)
        }
    }
}
