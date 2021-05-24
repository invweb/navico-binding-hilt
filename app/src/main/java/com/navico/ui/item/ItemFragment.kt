package com.navico.ui.item

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
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
                val localItem = requireArguments().get(Constants.ITEM) as Item
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
        activity?.setActionBar(itemFragmentBinding.toolbar)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?.setDisplayShowHomeEnabled(true)

        itemFragmentBinding.toolbar.setNavigationOnClickListener {
            val navController = activity?.let {
                it1 ->
                    Navigation.findNavController(it1, R.id.nav_host_fragment)
            }
            navController?.navigateUp()
        }

        return itemFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        val item  = requireArguments().get(Constants.ITEM) as Item
        itemFragmentBinding.item = item
    }
}
