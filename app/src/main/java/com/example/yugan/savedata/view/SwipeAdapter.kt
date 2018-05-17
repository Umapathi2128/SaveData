package com.example.yugan.savedata.view

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View



class SwipeAdapter(var dragDir:Int,var swipeDir:Int,var listener:RecyclerItemTouchHelperListener): ItemTouchHelper.SimpleCallback(dragDir,swipeDir) {

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?
                        , target: RecyclerView.ViewHolder?): Boolean {

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        if (viewHolder != null) {
            listener.onSwipe(viewHolder,direction,viewHolder.adapterPosition)
        }
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?
                             , dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val foregroundView:View= (viewHolder as MainAdapter.MyViewHolder).itemView
        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive)
    }

    override fun onChildDrawOver(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
            super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if(viewHolder!=null)
        {
            val foregroundView:View= (viewHolder as MainAdapter.MyViewHolder).itemView
            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override
    fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    public interface RecyclerItemTouchHelperListener{
        fun onSwipe(viewHolder: RecyclerView.ViewHolder,direction:Int,possition:Int)
    }

}