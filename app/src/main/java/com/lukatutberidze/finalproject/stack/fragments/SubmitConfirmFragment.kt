package com.lukatutberidze.finalproject.stack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lukatutberidze.finalproject.R
import com.lukatutberidze.finalproject.data.LeaveDbHelper
import com.lukatutberidze.finalproject.databinding.FragmentSubmitConfirmBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SubmitConfirmFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSubmitConfirmBinding
    private lateinit var dbHelper: LeaveDbHelper

    override fun getTheme(): Int = R.style.CustomBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubmitConfirmBinding.inflate(inflater, container, false)
        dbHelper = LeaveDbHelper(requireContext())

        binding.confirmButton.setOnClickListener {
            val reason = binding.etReason.text.toString()
            if (reason.isNotEmpty()) {
                dbHelper.addLeave(reason)
                Toast.makeText(requireContext(), "Leave submitted", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Please enter a reason", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancelLeave.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SubmitConfirmFragment()
    }
}