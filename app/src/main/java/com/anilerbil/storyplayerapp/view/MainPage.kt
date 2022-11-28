package com.anilerbil.storyplayerapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.anilerbil.storyplayerapp.ViewModel.MainPageViewModel
import com.anilerbil.storyplayerapp.R
import com.anilerbil.storyplayerapp.adapter.StoryPlayerGroupRecyclerAdapter
import kotlinx.android.synthetic.main.main_page.*


class MainPage : Fragment() {

    private lateinit var viewModel: MainPageViewModel
    private val recyclerStoryAdapter = StoryPlayerGroupRecyclerAdapter(arrayListOf(),requireContext())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainPageViewModel::class.java)
        //viewModel = ViewModelProvider(this).get(MainPageViewModel::class.java)
        viewModel.getDatafromInt()

        recyclerViewStoryGroupList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewStoryGroupList.adapter = recyclerStoryAdapter

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.storyGroup.observe(viewLifecycleOwner, Observer { storyGroup ->
            storyGroup?.let {
                recyclerViewStoryGroupList.visibility = View.VISIBLE
                recyclerStoryAdapter.storyListUpdate(storyGroup)
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it){
                    mainPageTextView.visibility = View.VISIBLE
                } else {
                    mainPageTextView.visibility = View.GONE
                }
            }
        })

        viewModel.isDataLoad.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                    recyclerViewStoryGroupList.visibility = View.GONE
                    mainPageTextView.visibility = View.GONE
                    mainPageProgressBar.visibility = View.VISIBLE
                }else{
                    mainPageProgressBar.visibility = View.GONE
                }
            }
        })
    }
}

