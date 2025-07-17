package com.lukatutberidze.finalproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukatutberidze.finalproject.data.Leave
import com.lukatutberidze.finalproject.databinding.LeaveItemBinding

class LeaveAdapter(private val leaves: List<Leave>) : RecyclerView.Adapter<LeaveAdapter.LeaveViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaveViewHolder {
        val binding = LeaveItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeaveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeaveViewHolder, position: Int) {
        holder.bind(leaves[position])
    }

    override fun getItemCount(): Int = leaves.size

    class LeaveViewHolder(private val binding: LeaveItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(leave: Leave) {
            binding.tvLeaveReason.text = leave.reason
            binding.tvLeaveDate.text = leave.date
        }
    }
}