package com.example.mid.repo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mid.R
import com.example.mid.databinding.FragmentRepoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RepoFragment : Fragment() {

    private lateinit var viewBinding: FragmentRepoBinding
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentRepoBinding.inflate(inflater, container, false).apply {
            vm = (activity as RepoActivity).obtainViewModel()
        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRepo()
    }

    private fun setupRepo() {
        val viewModel = viewBinding.vm
        if(viewModel != null){
            repoAdapter = RepoAdapter(viewModel.repoList, viewModel)
            viewBinding.rvRepo.adapter = repoAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vm?.start()

    }

    companion object {
        fun newInstance() = RepoFragment().apply {

        }
    }
}