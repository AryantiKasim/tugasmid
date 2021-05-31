package com.example.mid.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mid.main.MainActivity
import com.example.mid.R
import com.example.mid.databinding.FragmentMainBinding
import com.example.mid.main.MainActionListener
import com.example.mid.util.obtainViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment() {

    private lateinit var viewBinding: FragmentMainBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false)

        viewBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            vm = (activity as MainActivity).obtainViewModel()
            action = object :  MainActionListener {
                override fun onClickRepos() {
                    vm?.openRepo()
                }
            }
        }

        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vm?.start()

    }

    companion object {
        fun newInstance() = MainFragment().apply {

        }
    }


}