package ganteng.hendrawd.footballclub.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import ganteng.hendrawd.footballclub.model.ClubItem
import ganteng.hendrawd.footballclub.ui.adapter.ClubAdapter
import ganteng.hendrawd.footballclub.util.KEY_CLUB_ITEM
import ganteng.hendrawd.footballclub.util.KEY_SHARED_IMAGE_TRANSITION
import ganteng.hendrawd.footballclub.util.KEY_SHARED_TEXT_TRANSITION
import ganteng.hendrawd.footballclub.viewmodel.MainViewModel
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.setContentView

typealias ViewTransitionPair = android.support.v4.util.Pair<View?, String?>

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    private val mainActivityUi by lazy { MainActivityUI() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityUi.setContentView(this)
        observeData()
    }

    private fun observeData() {
        mainViewModel.getClubList().observe(this, Observer {
            it?.let {
                showClubList(it)
            }
        })
        mainViewModel.clubListItemClickObserver.observe(this, Observer {
            it?.let {
                openDetailActivity(it)
            }
        })
    }

    private fun openDetailActivity(data: SparseArray<*>) {
        val intent = intentFor<ClubDetailActivity>(KEY_CLUB_ITEM to data[0])
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val pairImageView = ViewTransitionPair(data[1] as View, KEY_SHARED_IMAGE_TRANSITION)
            val pairTextView = ViewTransitionPair(data[2] as View, KEY_SHARED_TEXT_TRANSITION)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MainActivity, pairImageView, pairTextView
            )
            startActivity(
                    intent,
                    options.toBundle()
            )
        } else {
            startActivity(intent)
        }
    }

    private fun showClubList(clubItemList: List<ClubItem>) {
        mainActivityUi.recyclerViewClub.adapter = ClubAdapter(
                clubItemList,
                mainViewModel.clubListItemClickObserver
        )
    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        lateinit var recyclerViewClub: RecyclerView

        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            recyclerViewClub = recyclerView {
                layoutManager = LinearLayoutManager(context)
            }
            recyclerViewClub
        }
    }
}
