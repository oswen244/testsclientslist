package com.test.testclientslist.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.testclientslist.R
import com.test.testclientslist.data.models.Client
import com.test.testclientslist.databinding.FragmentProspectsListBinding
import com.test.testclientslist.ui.EditActivity
import com.test.testclientslist.ui.adapters.ProspectsAdapter
import com.test.testclientslist.viewModel.ProspectViewModel
import kotlinx.android.synthetic.main.fragment_prospects_list.view.*


class ProspectsListFragment : Fragment(), ProspectsAdapter.OnItemClickListener {

    private lateinit var binding: FragmentProspectsListBinding
    private val prospectsRecyclerAdapter = ProspectsAdapter(arrayListOf(), this)
    private lateinit var viewModel: ProspectViewModel

    override fun onItemClick(position: Int) {
        val i = Intent(activity, EditActivity::class.java)
        i.putExtra("prospect", viewModel.getOneProspect((position+1).toString()))
        startActivity(i)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_prospects_list, container, false)
        val view: View = binding.root
        viewModel =  ViewModelProviders.of(this).get(ProspectViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.loadProspects()

        binding.root.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.root.recyclerView.adapter = prospectsRecyclerAdapter

        viewModel.prospects.observe(this, Observer<ArrayList<Client>> {
            it?.let {
                prospectsRecyclerAdapter.replaceData(it)
            }
        })


        // Inflate the layout for this fragment
        return view
    }

}
