package ganteng.hendrawd.footballclub.ui.ankolayout

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import ganteng.hendrawd.footballclub.R
import org.jetbrains.anko.*

class ClubItemUI : AnkoComponent<ViewGroup> {
    lateinit var tvName: TextView
    lateinit var ivLogo: ImageView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            padding = dip(16)

            ivLogo = imageView {
                imageResource = R.drawable.ic_logo_barca
            }.lparams(width = dip(50), height = dip(50))

            tvName = textView {
            }.lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.CENTER_VERTICAL
                margin = dip(10)
            }
        }
    }
}