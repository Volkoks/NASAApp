package com.example.nasaapp.ui.photo_of_the_past

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nasaapp.ui.before_yesterday_photo.BeforeYesterdayPhotoFragment
import com.example.nasaapp.ui.yesterday_photo.YesterdayPhotoFragment

class PhotoOfThePastVPAdapter(private val fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> YesterdayPhotoFragment()
            1-> BeforeYesterdayPhotoFragment()
            else-> YesterdayPhotoFragment()
        }
    }
}