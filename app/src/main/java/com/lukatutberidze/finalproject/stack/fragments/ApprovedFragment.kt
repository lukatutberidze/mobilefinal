package com.lukatutberidze.finalproject.stack.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukatutberidze.finalproject.R
class ApprovedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_approved, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ApprovedFragment()
    }
}