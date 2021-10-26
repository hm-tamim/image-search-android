package com.hmtamim.imagesearch.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class PaginationScrollListener : RecyclerView.OnScrollListener {
    private var staggeredGridLayoutManager: StaggeredGridLayoutManager? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var linearLayoutManager: LinearLayoutManager? = null

    constructor() {}
    constructor(gridLayoutManager: GridLayoutManager?) {
        this.gridLayoutManager = gridLayoutManager
    }

    constructor(linearLayoutManager: LinearLayoutManager?) {
        this.linearLayoutManager = linearLayoutManager
    }

    constructor(staggeredGridLayoutManager: StaggeredGridLayoutManager?) {
        this.staggeredGridLayoutManager = staggeredGridLayoutManager
    }

    fun setLinearLayoutManager(linearLayoutManager: LinearLayoutManager?) {
        this.linearLayoutManager = linearLayoutManager
        gridLayoutManager = null
        staggeredGridLayoutManager = null
    }

    fun setGridLayoutManager(gridLayoutManager: GridLayoutManager?) {
        this.gridLayoutManager = gridLayoutManager
        linearLayoutManager = null
        staggeredGridLayoutManager = null
    }

    fun setStaggeredGridLayoutManager(staggeredGridLayoutManager: StaggeredGridLayoutManager?) {
        gridLayoutManager = null
        linearLayoutManager = null
        this.staggeredGridLayoutManager = staggeredGridLayoutManager
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            var visibleItemCount = 0
            var totalItemCount = 0
            var firstVisibleItemPosition = 0
            if (gridLayoutManager != null) {
                visibleItemCount = gridLayoutManager!!.childCount
                totalItemCount = gridLayoutManager!!.itemCount
                firstVisibleItemPosition = gridLayoutManager!!.findFirstVisibleItemPosition()
            }
            if (linearLayoutManager != null) {
                visibleItemCount = linearLayoutManager!!.childCount
                totalItemCount = linearLayoutManager!!.itemCount
                firstVisibleItemPosition = linearLayoutManager!!.findFirstVisibleItemPosition()
            }
            if (staggeredGridLayoutManager != null) {
                visibleItemCount = staggeredGridLayoutManager!!.childCount
                totalItemCount = staggeredGridLayoutManager!!.itemCount
                val firstVisibleItems: IntArray? = staggeredGridLayoutManager!!.findFirstVisibleItemPositions(null)
                if (firstVisibleItems != null && firstVisibleItems.isNotEmpty()) firstVisibleItemPosition =
                    firstVisibleItems[0]
            }
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) loadMoreItem()
        }
    }

    abstract fun loadMoreItem()
}
