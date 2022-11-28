package com.anilerbil.storyplayerapp.view

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import com.anilerbil.storyplayerapp.R
import com.anilerbil.storyplayerapp.Story
import com.anilerbil.storyplayerapp.ViewModel.StoryDetailPageViewModel
import com.anilerbil.storyplayerapp.adapter.DetailPageViewPagerAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.story_detail_page.*
import kotlinx.coroutines.flow.callbackFlow

class StoryDetailPage(
    private val stories: List<Story>
) : Fragment() {

    private lateinit var viewModel: StoryDetailPageViewModel

    private lateinit var touch: ConstraintLayout
    private lateinit var progressBarList: ArrayList<ProgressBar>
    private lateinit var profilePic: ImageView
    private lateinit var username: TextView
    private lateinit var time: TextView
    private lateinit var image: ImageView
    private lateinit var video: VideoView
    private lateinit var topTab: LinearLayout

    private val viewPagerAdapter = DetailPageViewPagerAdapter(childFragmentManager)

    private var currentStory = 0
    private var isStoryHold = false
    private val handler = Handler(Looper.getMainLooper())
    private var thread: Thread? = null
    private lateinit var layout: ConstraintLayout

    private var tempRunnable = Runnable {
        isStoryHold = true
        holdStory()
        touch.parent.requestDisallowInterceptTouchEvent(true)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.story_detail_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(StoryDetailPageViewModel::class.java)

        initView(view)
        listener()
        progressBar()

    }

    private fun initView(view: View){
        touch = view.findViewById(R.id.touchScreen)
        image = view.findViewById(R.id.storyDetailImageView)
        video = view.findViewById(R.id.storyDetailVideoView)
        topTab = view.findViewById(R.id.tabLayout)
        username = view.findViewById(R.id.nameId)
        time = view.findViewById(R.id.timeId)
    }

    override fun onResume(){
        super.onResume()
        thread?.interrupt()
        thread = null
        layout.visibility = View.VISIBLE
    }

    override fun onPause(){
        super.onPause()
        reset()
    }

    private fun collectStories(){
        Glide.with(requireContext()).load(stories.elementAt(currentStory).userPp).into(ppImageView)
        username.text = stories.elementAt(currentStory).username
        time.text = stories.elementAt(currentStory).storyTime

        if(stories.elementAt(currentStory).type == 0){
            showImage()
        }else{
            showVideo()
        }

    }

    private fun showImage(){
        video.visibility = View.GONE
        image.visibility = View.VISIBLE
        Glide.with(requireContext()).load(stories.elementAt(currentStory).url)
    }

    private fun showVideo(){
        image.visibility = View.GONE
        video.visibility = View.VISIBLE
        video.setVideoURI(Uri.parse(stories.elementAt(currentStory).url))
        video.start()
    }

    private fun nextStory(){
        if (currentStory < stories.size) {
            currentStory = currentStory + 1
            collectStories()
        }else {
            StoryDetailPageViewModel.changeStoryGroup.nextStoryGroup()
        }
    }

    private fun previousStory(){
        if(currentStory == 0){
            StoryDetailPageViewModel.changeStoryGroup.previousStoryGroup()
        }else{
            currentStory = currentStory - 1
            collectStories()
        }
    }

    private fun holdStory(){
        thread?.interrupt()
        thread = null
        isStoryHold = true
        if(stories.elementAt(currentStory).type == 0){
            layout.visibility = View.INVISIBLE
        }else{
            video.pause()
        }
    }

    private fun resumeStory(){
        if (stories.elementAt(currentStory).type == 0){
            layout.visibility = View.VISIBLE
        }else{
            video.start()
        }
    }

    private fun changeStory(l: Float){
        val width = progressBarList.size
        if(l > width/2){
            nextStory()
        }else{
            previousStory()
        }
    }

    private fun reset(){
        thread?.interrupt()
        thread = null
        video.visibility = View.GONE
        image.visibility = View.GONE
        isStoryHold = false
    }

    private fun progressBar(){
        val width = tabLayout.width
        val singleStorySize = FrameLayout.LayoutParams(
            width.toInt() / stories.size,
             WindowManager.LayoutParams.MATCH_PARENT)

        for (i in 1 .. stories.size){
            val progressBar = ProgressBar(requireContext(),null,R.id.tabLayout)
            progressBar.layoutParams = singleStorySize
            progressBar.isIndeterminate = false
            progressBar.progress = 0
            progressBar.max = 100
            progressBarList.add(progressBar)
        }
    }


    // Listener'da hata alıp duruyorum çizdiremedim bir türlü.
    private fun listener() {
        touch.setOnTouchListener { view, touchAction ->
            when (touchAction.action) {
                MotionEvent.ACTION_DOWN -> {
                    handler.postDelayed(tempRunnable, 100)
                }
                MotionEvent.ACTION_UP -> {
                    if (isStoryHold){
                        resumeStory()
                    }else {
                        changeStory()
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        resumeStory()
                        view.parent.requestDisallowInterceptTouchEvent(false)
                        handler.removeCallbacks(tempRunnable)
                        isStoryHold = false
                    }
                }
            }
        }
    }

}
