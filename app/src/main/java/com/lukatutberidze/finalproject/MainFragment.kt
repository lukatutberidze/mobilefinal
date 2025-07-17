package com.lukatutberidze.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukatutberidze.finalproject.calendar.CalendarFragment
import com.lukatutberidze.finalproject.databinding.FragmentMainBinding
import com.lukatutberidze.finalproject.home.HomeFragment
import com.lukatutberidze.finalproject.list.ListFragment
import com.lukatutberidze.finalproject.stack.StackFragment
import com.lukatutberidze.finalproject.ticket.TicketFragment

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun loadFragment(f:Fragment) {
        parentFragmentManager.beginTransaction().replace(R.id.placeholder, f).commit()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        loadFragment(HomeFragment.newInstance())
        super.onViewCreated(view, savedInstanceState)
        bottomNavMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment.newInstance())
                    true
                }
                R.id.calendar -> {
                    loadFragment(CalendarFragment.newInstance())
                    true
                }
                R.id.list -> {
                    loadFragment(ListFragment.newInstance())
                    true
                }
                R.id.ticket -> {
                    loadFragment(TicketFragment.newInstance())
                    true
                }
                R.id.Stack -> {
                    loadFragment(StackFragment.newInstance())
                    true
                }
                else -> {
                    loadFragment(HomeFragment.newInstance())
                    true
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =  MainFragment()
    }

}