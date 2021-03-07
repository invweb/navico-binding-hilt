package com.navico.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.navico.BR
import com.navico.Constants
import com.navico.R
import com.navico.data.Item
import com.navico.databinding.RecyclerviewItemBinding
import java.util.*

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {
    private lateinit var activityLocal: Activity
    private var items: LinkedList<Item?>  = LinkedList<Item?>()

    class ViewHolder(itemBinding: RecyclerviewItemBinding)
        : RecyclerView.ViewHolder(itemBinding.root)  {
        private val binding: ViewDataBinding = itemBinding

        fun bind(item: Item?) {
            binding.setVariable(BR.item, item)
        }
    }

    fun addItems(newItems: List<Item?>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerviewItemBinding =
            inflate(inflater, R.layout.recyclerview_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        val navController = Navigation.findNavController(activityLocal, R.id.nav_host_fragment)

        holder.itemView.setOnClickListener {
            val itemBundle = bundleOf(Constants.ITEM to item)
            navController.navigate(R.id.itemFragment, itemBundle)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setActivity(activity: FragmentActivity) {
        this.activityLocal = activity
    }
}