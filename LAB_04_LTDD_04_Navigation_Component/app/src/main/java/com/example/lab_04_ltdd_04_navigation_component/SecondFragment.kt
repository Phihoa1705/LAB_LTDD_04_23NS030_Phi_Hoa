package com.example.lab_04_ltdd_04_navigation_component

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lab_04_ltdd_04_navigation_component.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater,container,false)
//        return super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var controller = findNavController()

        binding.apply {
            btnFrag2ToFrag3.setOnClickListener {
                controller.navigate(R.id.action_secondFragment_to_thirdFragment)
            }
//                Cách 1: Dùng Bundle để truyền dữ liệu đơn giản giữa các Fragment
//            val name = arguments?.getString("name")
//            tvName.text = name

//                Cách 2: Sử dụng Safe Args
            val args:SecondFragmentArgs by navArgs()
            val user = args.user
            tvName.text = user.name
        }
    }
}