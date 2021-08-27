package com.anonymous.giphyapp.ui.base

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessGridRecyclerView(
    private val gridLayoutManager: GridLayoutManager,
    private val callBack: () -> (Unit)
) :
    RecyclerView.OnScrollListener() {
    private var previousItemCount = 0
    private var loading = false

    init {
        reset()
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        if (dy > 0) {
            val itemCount = gridLayoutManager.itemCount
            if (itemCount != previousItemCount) {
                loading = false
            }
            if (!loading && gridLayoutManager.findLastVisibleItemPosition() >= itemCount - 1) {
                previousItemCount = itemCount
                callBack.invoke()
            }
        }
    }

    private fun reset() {
        loading = false
        previousItemCount = -1
    }
}
