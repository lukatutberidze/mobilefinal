package com.lukatutberidze.finalproject.stack.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukatutberidze.finalproject.adapters.LeaveAdapter
import com.lukatutberidze.finalproject.data.LeaveDbHelper
import com.lukatutberidze.finalproject.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {

    private lateinit var binding: FragmentReviewBinding
    private lateinit var dbHelper: LeaveDbHelper
    private lateinit var leaveAdapter: LeaveAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        dbHelper = LeaveDbHelper(requireContext())

        binding.rvLeaves.layoutManager = LinearLayoutManager(context)
        leaveAdapter = LeaveAdapter(dbHelper.getAllLeaves())
        binding.rvLeaves.adapter = leaveAdapter

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        leaveAdapter = LeaveAdapter(dbHelper.getAllLeaves())
        binding.rvLeaves.adapter = leaveAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = ReviewFragment()
    }
}