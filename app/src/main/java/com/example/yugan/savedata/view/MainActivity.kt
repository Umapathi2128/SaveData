package com.example.yugan.savedata.view

import android.app.AlertDialog
import android.arch.persistence.room.Room
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.View
import android.view.ViewAnimationUtils
import com.example.yugan.savedata.BR
import com.example.yugan.savedata.R
import com.example.yugan.savedata.add.AddActiviity
import com.example.yugan.savedata.databinding.ActivityMainBinding
import com.example.yugan.savedata.repository.room.SaveDataBase


class MainActivity : AppCompatActivity(), MainView, MainInterface, SwipeAdapter.RecyclerItemTouchHelperListener {


    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var list: ArrayList<MainDataModel>
    private lateinit var colors: ArrayList<Int>
    private var count = 0
    private var abc = 0
    private lateinit var mainAdapter: MainAdapter


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainViewModel = MainViewModel(this)
        activityMainBinding.setVariable(BR.add, mainViewModel)
        activityMainBinding.recyclerview.visibility = View.VISIBLE


        colors = ArrayList()
//        colors.add(R.color.lightYellow)
//        colors.add(R.color.red)
//        colors.add(R.color.hash)
//        colors.add(R.color.unknown)
//        colors.add(R.color.orange)
//        colors.add(R.color.skyBlue)
        colors.add(R.drawable.view_bg)
        colors.add(R.drawable.view3_bg)
        colors.add(R.drawable.view2_bg)
        colors.add(R.drawable.view5_bg)
        colors.add(R.drawable.view6_bg)
        colors.add(R.drawable.view7_bg)
        colors.add(R.drawable.view4_bg)

        val databse: SaveDataBase = Room.databaseBuilder(applicationContext, SaveDataBase::class.java, "saveData")
                .allowMainThreadQueries().build()
        val alist = databse.saveDataDao().getAll()
        list = ArrayList()
        for (i in 0 until alist.size) {
            if (count <= colors.size - 1) {
                list.add(MainDataModel(alist[i].name, alist[i].userId, alist[i].password, colors[count]))
                count++
            } else {
                count = 0
                list.add(MainDataModel(alist[i].name, alist[i].userId, alist[i].password, colors[count++]))
            }
        }



        mainAdapter = MainAdapter(list, this)
        val recyclerView: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        activityMainBinding.recyclerview.layoutManager = recyclerView
        activityMainBinding.recyclerview.itemAnimator = DefaultItemAnimator()
        activityMainBinding.recyclerview.adapter = mainAdapter
        activityMainBinding.recyclerview.smoothScrollToPosition(list.size)

        val itemTouchHelperListener = SwipeAdapter(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperListener).attachToRecyclerView(activityMainBinding.recyclerview)
    }

    override fun addDetails() {
        abc = 1
        activityMainBinding.recyclerview.visibility = View.INVISIBLE
        revealAnimation()
//        Toast.makeText(this, "fab", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, AddActiviity::class.java))
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
//        finish()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        super.onResume()
        if (abc == 1) {
            finishAndRemoveTask()
            abc = 0
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onPause() {
        super.onPause()
        if (abc == 1) {
        } else finishAndRemoveTask()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    /**
     *  Start revial animation ....
     */
    private fun revealAnimation() {
        val view = findViewById<ConstraintLayout>(R.id.recyclerParent)
        view.setBackgroundResource(R.color.revial)
        val cx = view.width
        val cy = view.height
        val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius)
            view.visibility = View.VISIBLE
            anim.start()
        }
    }
//    private fun hideAnimation() {
//        val view = findViewById(R.id.recyclerParent) as ConstraintLayout
//        val cx = view.getWidth() / 2
//        val cy = view.getHeight() / 2
//        val initialRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0f)
//            anim.addListener(object : AnimatorListenerAdapter() {
//                override fun onAnimationEnd(animation: Animator) {
//                    super.onAnimationEnd(animation)
//                    view.setVisibility(View.INVISIBLE)
//                }
//            })
//            anim.start()
//        }
//    }

    //from mainInterface....
    override fun swipeToRemove(positon: Int) {
//        deleteTheItem(positon)
//        mainAdapter.remove(positon)
    }

    override fun onSwipe(viewHolder: RecyclerView.ViewHolder, direction: Int, possition: Int) {
        if (viewHolder is MainAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
//            val name = list[viewHolder.adapterPosition].name

            // backup of removed item for undo purpose
            val deletedItem = list[viewHolder.adapterPosition]
            val deletedIndex = viewHolder.adapterPosition

//             remove the item from recycler view
            mainAdapter.remove(viewHolder.adapterPosition)

//deleteTheItem(deletedItem)
////            var view=LayoutInflater.from(this).inflate(R.layout.custom_alert,null)
//            val alertDialog = AlertDialog.Builder(this).create()
//
//            alertDialog.setTitle("Are you sure")
//            alertDialog.setMessage("Do you want to delete the row?")
//            alertDialog.setIcon(R.drawable.delete)
//            alertDialog.setView(view)
//            alertDialog.setButton("yes", DialogInterface.OnClickListener { dialog, _ ->
////
//            })
//            alertDialog . setButton ("NO", DialogInterface.OnClickListener{ dialog, _ ->
//               deleteTheItem(deletedIndex)
//            })
//            dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, whichButton ->
//                //pass
//            })
//            var btnNo=view.findViewById<Button>(R.id.btnNo)
//            var btnYes=view.findViewById<Button>(R.id.btnYes)
//            btnNo.setOnClickListener {
//                mainAdapter.restoreItem(deletedItem, deletedIndex)
//                mainAdapter.notifyDataSetChanged()
//            }
//            btnYes.setOnClickListener {
//                deleteTheItem(deletedIndex)
//            }
//            alertDialog.show()
            // showing snack bar with Undo option
//            val snackbar = Snackbar
//                    .make(activityMainBinding.fab, "$name removed from cart!", Snackbar.LENGTH_INDEFINITE).apply {
//                        setAction("UNDO", {
//                            // undo is selected, restore the deleted item
////                            swipeCount = true
//                            mainAdapter.restoreItem(deletedItem, deletedIndex)
//                            mainAdapter.notifyDataSetChanged()
//                        })
//                        setActionTextColor(Color.YELLOW)
//                    }
//            snackbar.setAction("NO", {
//                deleteTheItem(deletedIndex)
//            })
//            snackbar.show()

            val dialogBuilder = AlertDialog.Builder(this,R.style.AlertDialogTheme)
            dialogBuilder.setTitle("Are you sure?")
            dialogBuilder.setMessage("You want to delete the DATA")
            dialogBuilder.setIcon(R.drawable.delete)
            dialogBuilder.setPositiveButton("Cancel", { _, _ ->
                mainAdapter.restoreItem(deletedItem, deletedIndex)
                mainAdapter.notifyDataSetChanged()
            })
            dialogBuilder.setNegativeButton("Delete", { _, _ ->
                //passde
                try{
                    deleteTheItem(deletedItem)
                }
                catch (e:Exception)
                {
                    e.printStackTrace()
                }

            })
            val b = dialogBuilder.create()
//            dialogBuilder.getB
            b.show()
        }
    }

    /**
     *  This method for delete the particular row in the recyclerview....
     */
    private fun deleteTheItem(positon: MainDataModel) {
            val databse: SaveDataBase = Room.databaseBuilder(this, SaveDataBase::class.java, "saveData")
                    .allowMainThreadQueries().build()
            databse.saveDataDao().deleteByUser(positon.name, positon.userId, positon.password)
    }
}
