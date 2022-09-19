package com.binar.words.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binar.words.R
import com.binar.words.`interface`.OnDataPass
import com.binar.words.`interface`.OnItemClickCallback
import com.binar.words.databinding.FragmentLetterBinding
import com.binar.words.ui.adapter.LetterAdapter

class LetterFragment : Fragment() {

    private var _binding: FragmentLetterBinding? = null
    private val binding get() = _binding!!

    private lateinit var onDataPass: OnDataPass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onDataPass = context as OnDataPass
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "HURUF"

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val list = resources.getStringArray(R.array.letter).toList()

        val adapter = LetterAdapter()
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvLetter.adapter = adapter
        binding.rvLetter.layoutManager = layoutManager

        adapter.submitData(list)

        adapter.setOnItemClickCallback(object : OnItemClickCallback{
            override fun onItemClicked(data: String) {
                onDataPass.onDataPass(data)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}