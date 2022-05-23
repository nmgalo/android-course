package dev.nmgalo.task8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.nmgalo.task8.data.model.User
import dev.nmgalo.task8.databinding.UserItemBinding

class UsersListAdapter(
    private val onItemClick: (userId: Long) -> Unit
) : ListAdapter<User, UsersListAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).bindTo(UserItemBinding.bind(holder.itemView)) {
            onItemClick(getItem(position).id)
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem == newItem
        override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
