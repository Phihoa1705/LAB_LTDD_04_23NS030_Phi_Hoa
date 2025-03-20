package com.example.lab_04_ltdd_04_navigation_component

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab_04_ltdd_04_navigation_component.databinding.FragmentFifthBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FifthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FifthFragment : Fragment() {
    private lateinit var binding: FragmentFifthBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFifthBinding.inflate(layoutInflater,container,false)
//        return super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var controller = findNavController()

        binding.btnFrag5ToFrag1.setOnClickListener {
            controller.navigate(R.id.action_fifthFragment_to_firstFragment)
        }
    }
}