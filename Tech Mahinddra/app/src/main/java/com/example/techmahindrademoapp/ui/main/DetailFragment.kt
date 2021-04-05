package com.example.techmahindrademoapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.techmahindrademoapp.R
import com.example.techmahindrademoapp.databinding.MainFragmentBinding

class DetailFragment : Fragment() {

    lateinit var binding: MainFragmentBinding
    var adapPostion = 0
    var details = ""

    private var viewModel =  DetailsViewModel("",0)
    private lateinit var factoryViewModelFactory: DetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.message.text = activity?.intent?.getStringExtra("Smartphone")
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapPostion = DetailFragmentArgs.fromBundle(requireArguments()).detailsPosition
        details = DetailFragmentArgs.fromBundle(requireArguments()).details
        factoryViewModelFactory = DetailViewModelFactory(details,adapPostion)
        viewModel = ViewModelProvider(this,factoryViewModelFactory).get(DetailsViewModel::class.java)
        binding.message.text = viewModel._mDetails.value?.name
        binding.btnUpdate.setOnClickListener {
           backToListItem()

        }

    }
    fun backToListItem(){
        var action = DetailFragmentDirections.actionDetailFragmentToSmartPhoneFragmentList()
        action.updateDetails = binding.edSmartPhone.text.toString()
        action.updatePosition = viewModel.position
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

}