package `in`.angler.myangler

import `in`.angler.myangler.databinding.MyEmployeeAdapterBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


class MyEmployeeAdapter : RecyclerView.Adapter<MyEmployeeAdapter.EmpViewHolder>() {
    class EmpViewHolder(private val binding: MyEmployeeAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employeeData: EmployeeData) {
            binding.apply {
                company.text = employeeData.company
                id.text = employeeData.emp_id
                name.text = employeeData.name
                age.text = employeeData.age
                phone.text = employeeData.mobile
                phone.text = employeeData.mobile

            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpViewHolder {
        return EmpViewHolder(
            MyEmployeeAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EmpViewHolder, position: Int) {
        val view = differ.currentList[position]
        holder.bind(view)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val callback = object : DiffUtil.ItemCallback<EmployeeData>() {

        override fun areItemsTheSame(
            oldItem: EmployeeData,
            newItem: EmployeeData
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: EmployeeData,
            newItem: EmployeeData
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

}