package com.example.lab_04_ltdd_04_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab_04_ltdd_04_fragment.databinding.FragmentABinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentA.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentA : Fragment() {
    private lateinit var binding: FragmentABinding
    private val navigationController by lazy{
        requireActivity() as NavigationFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddFragA.setOnClickListener {
            navigationController.navigaTo(FragmentB.newInstance(),"tagFragB","stackB")
        }
    }

    fun getTextFromFragmentA(): String {
        return binding.tvFragmentA.text.toString()
    }

    companion object {
        fun newInstance() = FragmentA()
    }
}