package com.lukatutberidze.finalproject.stack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukatutberidze.finalproject.adapters.ViewPagerAdapter
import com.lukatutberidze.finalproject.data.LeaveDbHelper
import com.lukatutberidze.finalproject.databinding.FragmentStackBinding
import com.lukatutberidze.finalproject.stack.fragments.ApprovedFragment
import com.lukatutberidze.finalproject.stack.fragments.RejectedFragment
import com.lukatutberidze.finalproject.stack.fragments.ReviewFragment
import com.lukatutberidze.finalproject.stack.fragments.SubmitConfirmFragment
import com.google.android.material.tabs.TabLayoutMediator

class StackFragment : Fragment() {

    private lateinit var binding: FragmentStackBinding
    private lateinit var dbHelper: LeaveDbHelper

    private val fList = listOf(
        ReviewFragment.newInstance(),
        ApprovedFragment.newInstance(),
        RejectedFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStackBinding.inflate(inflater, container, false)
        dbHelper = LeaveDbHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVP()
        updateLeaveBalance()

        binding.btnStackSubmitLeave.setOnClickListener{
            SubmitConfirmFragment.newInstance().show(parentFragmentManager, "SubmitConfirmFragment")
        }
    }

    private fun initVP() = with(binding) {
        if (isAdded) {
            val adapter = ViewPagerAdapter(requireActivity(), fList)
            vp.adapter = adapter

            val titles = listOf("Review", "Approved", "Rejected")

            TabLayoutMediator(tlStackTabLayout, vp) { tab, position ->
                tab.text = titles[position]
            }.attach()
        }
    }

    private fun updateLeaveBalance() {
        val usedLeaves = dbHelper.getLeavesCount()
        val availableLeaves = 20 - usedLeaves
        binding.stackLeaveAvailableAmount.text = availableLeaves.toString()
        binding.stackLeaveUsedAmount.text = usedLeaves.toString()
    }

    override fun onResume() {
        super.onResume()
        updateLeaveBalance()
    }

    companion object {
        @JvmStatic
        fun newInstance() = StackFragment()
    }
}