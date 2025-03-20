package com.example.lab_04_ltdd_04_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lab_04_ltdd_04_fragment.databinding.FragmentCBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentC.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentC : Fragment() {
    private lateinit var binding:FragmentCBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnBackC.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            btnPopBackC.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack("stackA",0)
            }
        }
        // Retrieve value from FragmentA safely
        val fragmentA = requireActivity()
            .supportFragmentManager
            .findFragmentByTag("tagFragA") as? FragmentA

        if (fragmentA != null) {
            Toast.makeText(requireActivity(), fragmentA.getTextFromFragmentA(), Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance() = FragmentC()
    }
}