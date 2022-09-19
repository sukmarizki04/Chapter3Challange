package com.binar.words.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.*
import com.binar.words.R
import com.binar.words.`interface`.OnDataPass
import com.binar.words.databinding.ActivityMainBinding
import com.binar.words.ui.adapter.LetterAdapter
import com.binar.words.ui.fragment.LetterFragment
import com.binar.words.ui.fragment.WordFragment

class MainActivity : AppCompatActivity(), OnDataPass{

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val letterFragment = LetterFragment()
        val fragmentManager = supportFragmentManager

        fragmentManager.commit {
            add(R.id.nav_host_fragment_container, letterFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDataPass(letter: String) {

        val wordFragment = WordFragment()

        val bundle = Bundle()
        bundle.putString("letter", letter)

        wordFragment.arguments = bundle
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.nav_host_fragment_container, wordFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}