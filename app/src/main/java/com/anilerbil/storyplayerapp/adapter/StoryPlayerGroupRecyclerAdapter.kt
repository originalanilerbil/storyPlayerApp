package com.anilerbil.storyplayerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.anilerbil.storyplayerapp.R
import com.anilerbil.storyplayerapp.StoryGroup
import com.anilerbil.storyplayerapp.view.MainPageDirections
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.storyplayer_group_recycler_row.view.mainPagepPpText
import kotlinx.android.synthetic.main.storyplayer_group_recycler_row.view.mainPagePp

class StoryPlayerGroupRecyclerAdapter(
    val storyGroupList: ArrayList<StoryGroup>,
    val context: Context
): RecyclerView.Adapter<StoryPlayerGroupRecyclerAdapter.StoryViewHolder>() {
    class StoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun getItemCount(): Int {
        return storyGroupList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.storyplayer_group_recycler_row,parent,false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val item = storyGroupList.get(position).data.get(position).storyGroup.get(position)
        holder.itemView.mainPagepPpText.text= item.username
        Glide.with(context).load(item.userPp).into(holder.itemView.mainPagePp)

        holder.itemView.setOnClickListener {
            val action = MainPageDirections.actionMainPageToStoryDetailPage(
                item.storyId,
                item.username,
                item.userPp,
                item.storyTime,
                item.url,
                item.type)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun storyListUpdate(newStoryList: List<StoryGroup>){
        storyGroupList.clear()
        storyGroupList.addAll(newStoryList)
        notifyDataSetChanged()
    }
}