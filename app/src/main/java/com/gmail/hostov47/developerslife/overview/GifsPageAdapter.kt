/**
 * Created by Ilia Shelkovenko on 29.08.2020.
 */

package com.gmail.hostov47.developerslife.overview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GifsPageAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return GifCategoryFragment.newInstance(GifsType.values()[position].apiTitle)
    }

    override fun getCount(): Int {
        return GifsType.values().size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return GifsType.values()[position].title
    }
}
