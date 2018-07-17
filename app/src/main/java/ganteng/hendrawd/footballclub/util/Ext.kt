package ganteng.hendrawd.footballclub.util

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author hendrawd on 16/07/18
 */
fun ImageView.loadWithGlide(@DrawableRes resourceImage: Int){
    Glide.with(context).load(resourceImage).into(this)
}