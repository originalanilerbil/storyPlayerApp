package com.anilerbil.storyplayerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.anilerbil.storyplayerapp.*
import com.anilerbil.storyplayerapp.ui.MainPageDirections
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.storyplayer_group_recycler_row.view.*

class StoryPlayerGroupRecyclerAdapter(
    val storygrouplist: ArrayList<StoryGroup>,
    val context: Context
): RecyclerView.Adapter<StoryPlayerGroupRecyclerAdapter.StoryViewHolder>() {
    class StoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun getItemCount(): Int {
        return storygrouplist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.storyplayer_group_recycler_row,parent,false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val item = storygrouplist.get(position).data.get(position).storyGroup.get(position)
        holder.itemView.mainPagepPpText.text= item.username
        Glide.with(context).load(item.userPp).into(holder.itemView.mainPagePp)

        holder.itemView.setOnClickListener {
            val action = MainPageDirections.actionMainpageToStoryyDetailPage(item.storyId,item.username,item.userPp)
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun storylistupdate(newStoryList: List<StoryGroup>){
        storygrouplist.clear()
        storygrouplist.addAll(newStoryList)
        notifyDataSetChanged()
    }
}