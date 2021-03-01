package com.barreservation.extension

import android.animation.Animator
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.addAnimationEndListener(callback: (Animator?) -> Unit) {
    addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationEnd(animation: Animator?) {
            callback(animation)
        }

        override fun onAnimationRepeat(animation: Animator?) {}

        override fun onAnimationCancel(animation: Animator?) {}

        override fun onAnimationStart(animation: Animator?) {}
    })
}