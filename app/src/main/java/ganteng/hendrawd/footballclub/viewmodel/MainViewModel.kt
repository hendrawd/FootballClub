package ganteng.hendrawd.footballclub.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.SparseArray
import ganteng.hendrawd.footballclub.MainApplication
import ganteng.hendrawd.footballclub.R.array.*
import ganteng.hendrawd.footballclub.model.ClubItem
import ganteng.hendrawd.footballclub.util.SingleLiveEvent

class MainViewModel : ViewModel() {

    private val clubList: MutableLiveData<List<ClubItem>> by lazy {
        val mutableLiveData = MutableLiveData<List<ClubItem>>()
        mutableLiveData.value = getClubItemListFromSomewhere()
        mutableLiveData
    }

    val clubListItemClickObserver by lazy { SingleLiveEvent<SparseArray<*>>() }

    fun getClubList(): LiveData<List<ClubItem>> {
        return clubList
    }

    fun setClubList(cardList: List<ClubItem>) {
        clubList.value = cardList
    }

    /**
     * The real implementation should be like from network call or from database
     */
    private fun getClubItemListFromSomewhere(): List<ClubItem> {
        val clubList: MutableList<ClubItem> = mutableListOf()

        val res = MainApplication.applicationContext().resources
        val clubNames = res.getStringArray(club_name)
        val clubDescriptions = res.getStringArray(club_desc)
        val clubLogoImages = res.obtainTypedArray(club_image)

        clubNames.forEachIndexed { index, clubName ->
            clubList.add(
                    ClubItem(
                            clubName,
                            clubDescriptions[index],
                            clubLogoImages.getResourceId(
                                    index,
                                    0
                            )
                    )
            )
        }
        clubLogoImages.recycle()
        return clubList
    }
}