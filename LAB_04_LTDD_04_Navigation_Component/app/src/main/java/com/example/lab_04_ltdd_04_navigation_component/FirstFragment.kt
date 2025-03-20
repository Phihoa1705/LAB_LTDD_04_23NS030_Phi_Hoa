package com.example.lab_04_ltdd_04_navigation_component

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.lab_04_ltdd_04_navigation_component.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater,container,false)
//        return super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var controller = findNavController()

        binding.apply {
            btnFrag1ToFrag2.setOnClickListener {
                controller.navigate(R.id.action_firstFragment_to_secondFragment)
            }
            btnFrag1ToFrag5.setOnClickListener {
                controller.navigate(R.id.action_firstFragment_to_fifthFragment)
            }
            btnSendData.setOnClickListener {
//                Cách 1: Dùng Bundle để truyền dữ liệu đơn giản giữa các Fragment
//                val bundle = bundleOf("name" to edtName.text.toString())
//                controller.navigate(R.id.action_firstFragment_to_secondFragment,bundle)

//                Cách 2: Sử dụng Safe Args
                var user = User(edtName.text.toString())
                val direction = FirstFragmentDirections.actionFirstFragmentToSecondFragment(user)
                controller.navigate(direction)
            }
        }
    }
}