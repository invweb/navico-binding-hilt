package com.navico.ui.item

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.navico.Constants
import com.navico.MapsActivity
import com.navico.R
import com.navico.data.Item
import com.navico.databinding.ItemFragmentBinding

class ItemFragment : Fragment() {

    private lateinit var viewModel: ItemViewModel
    private lateinit var itemFragmentBinding: ItemFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_map -> {
                val localItem  = requireArguments().get(Constants.ITEM) as Item
                val ctx: Context? = this.context
                val intent = Intent(ctx, MapsActivity::class.java)
                intent.putExtra(Constants.ITEM, localItem)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        itemFragmentBinding = ItemFragmentBinding.inflate(layoutInflater)
        return itemFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        val item  = requireArguments().get(Constants.ITEM) as Item

        itemFragmentBinding.name.text = item.name
        itemFragmentBinding.description.text = item.description
        itemFragmentBinding.address.text = item.address

        Glide.with(this).load(item.photo).into(itemFragmentBinding.imageView)

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)

        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle(R.string.point)
    }
}
