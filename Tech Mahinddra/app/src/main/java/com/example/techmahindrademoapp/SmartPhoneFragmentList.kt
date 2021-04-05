package com.example.techmahindrademoapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techmahindrademoapp.databinding.SmartPhoneFragmentListBinding
import com.example.techmahindrademoapp.databinding.SmartphoneItemsBinding
import com.example.techmahindrademoapp.dummy.SmartPhone
import com.example.techmahindrademoapp.ui.main.DetailViewModelFactory
import com.example.techmahindrademoapp.ui.main.DetailsViewModel

class SmartPhoneFragmentList : Fragment(), AdapterItemClickListener<SmartPhone> {

    private lateinit var sharedViewModel: DetailViewModelFactory
    private var viewModel= SmartPhoneViewModel("",0)
    private lateinit var binding: SmartPhoneFragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.smart_phone_fragment_list,container,false)
        var position = SmartPhoneFragmentListArgs.fromBundle(requireArguments()).updatePosition
        var details = SmartPhoneFragmentListArgs.fromBundle(requireArguments()).updateDetails
        sharedViewModel = DetailViewModelFactory(details, position)
        viewModel = ViewModelProvider(this,sharedViewModel).get(SmartPhoneViewModel::class.java)
        binding.recycler.layoutManager = LinearLayoutManager(activity)

        if (!SmartPhoneFragmentListArgs.fromBundle(requireArguments()).updateDetails.isNullOrEmpty()){

            binding.recycler.adapter = SmartPhoneRecyclerAdapter(viewModel, viewLifecycleOwner, this,viewModel.updateList(details,position))
        }
        viewModel.lst.observe(viewLifecycleOwner, Observer {
            binding.recycler.adapter = SmartPhoneRecyclerAdapter(viewModel, viewLifecycleOwner, this,viewModel.lst.value!!)
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.recycler.adapter?.notifyDataSetChanged()
    }

    override fun onItemClicked(item: SmartPhone, position: Int) {
        var action = SmartPhoneFragmentListDirections.actionSmartPhoneFragmentListToDetailFragment()
        action.details = item.name
        action.detailsPosition = position
        NavHostFragment.findNavController(this).navigate(action)
    }
}

class SmartPhoneRecyclerAdapter(val viewModel: SmartPhoneViewModel,val lifcylewoner: LifecycleOwner,
    listener: AdapterItemClickListener<SmartPhone>,var items: ArrayList<SmartPhone>)
    : RecyclerView.Adapter<SmartPhoneRecyclerAdapter.SmartPhoneViewHolder>() {
    var mLitener = listener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartPhoneRecyclerAdapter.SmartPhoneViewHolder {

        return SmartPhoneViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SmartPhoneRecyclerAdapter.SmartPhoneViewHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item?.name
        holder.bind(item!!)

    }

    override fun getItemCount(): Int = viewModel.lst.value?.size!!


    inner  class SmartPhoneViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.smartphone_items, parent,false )) {

        var binding: SmartphoneItemsBinding = DataBindingUtil.bind(itemView)!!
        private var mSmartphone : SmartPhone? = null

        fun bind(smartphone: SmartPhone){
            highlight(smartphone)
            var update = viewModel.update
            itemView.setOnClickListener {
                mSmartphone = smartphone
                mSmartphone?.let {
                    mLitener.onItemClicked(it,adapterPosition)
                    viewModel._details.postValue(it.name)
                }

                mSmartphone?.let { viewModel.setHighLightedItem(it) }
                highlight(smartphone)
            }


        }
        fun highlight(smartphone: SmartPhone){
            viewModel.getHighLightedItem(smartphone)?.observe(lifcylewoner, Observer {
                when(it){
                    smartphone -> binding.title.setBackgroundColor(Color.DKGRAY)
                    else -> binding.title.setBackgroundColor(Color.TRANSPARENT)
                }
            })

        }
    }

}


interface AdapterItemClickListener<T> {
    fun onItemClicked(item: T,position: Int)
}