package com.example.lab_04_ltdd_04_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lab_04_ltdd_04_fragment.databinding.FragmentBBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentB.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentB : Fragment() {
    private lateinit var binding: FragmentBBinding
    private val navigationController by lazy {
        requireActivity() as NavigationFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddFragB.setOnClickListener {
            navigationController.navigaTo(FragmentC.newInstance(),"tagFragC","StackC")
        }

        binding.btnBackB.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.btnRemoveFragB.setOnClickListener {
            val fragmentB: FragmentB = requireActivity().supportFragmentManager.
            findFragmentByTag("tagFragB") as FragmentB

            if (fragmentB != null) {
                requireActivity().supportFragmentManager.beginTransaction().remove(fragmentB).commit()
            } else {
                Toast.makeText(requireActivity(),"Fragment A không tồn tại!",Toast.LENGTH_SHORT).show()
            }
        }
    }
    companion object {
        fun newInstance () = FragmentB()
    }
}