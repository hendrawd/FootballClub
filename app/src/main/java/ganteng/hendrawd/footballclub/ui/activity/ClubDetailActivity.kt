package ganteng.hendrawd.footballclub.ui.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import ganteng.hendrawd.footballclub.model.ClubItem
import ganteng.hendrawd.footballclub.util.KEY_CLUB_ITEM
import ganteng.hendrawd.footballclub.util.KEY_SHARED_IMAGE_TRANSITION
import ganteng.hendrawd.footballclub.util.KEY_SHARED_TEXT_TRANSITION
import ganteng.hendrawd.footballclub.util.loadWithGlide
import org.jetbrains.anko.*

/**
 * @author hendrawd on 16/07/18
 */
class ClubDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clubItem: ClubItem = intent.getParcelableExtra(KEY_CLUB_ITEM)
        val clubDetailActivityUi = ClubDetailActivityUI()
        clubDetailActivityUi.setContentView(this)
        clubDetailActivityUi.tvName.text = clubItem.name
        clubDetailActivityUi.tvDesc.text = clubItem.description
        clubDetailActivityUi.iv.loadWithGlide(clubItem.image)
        createBackButton()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class ClubDetailActivityUI : AnkoComponent<ClubDetailActivity> {
        lateinit var iv: ImageView
        lateinit var tvName: TextView
        lateinit var tvDesc: TextView

        override fun createView(ui: AnkoContext<ClubDetailActivity>) = with(ui) {
            verticalLayout {
                gravity = CENTER_HORIZONTAL
                padding = dip(16)

                iv = imageView {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        transitionName = KEY_SHARED_IMAGE_TRANSITION
                    }
                }.lparams(
                        width = dip(100),
                        height = dip(100)
                )

                tvName = textView {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        transitionName = KEY_SHARED_TEXT_TRANSITION
                    }
                }.lparams(
                        width = wrapContent,
                        height = wrapContent
                ) {
                    topMargin = dip(8)
                }

                tvDesc = textView {}.lparams(
                        width = wrapContent,
                        height = wrapContent
                ) {
                    topMargin = dip(24)
                }
            }
        }
    }
}