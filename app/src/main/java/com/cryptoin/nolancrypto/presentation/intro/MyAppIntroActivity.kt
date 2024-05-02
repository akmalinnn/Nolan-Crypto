package com.cryptoin.nolancrypto.presentation.intro

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.cryptoin.nolancrypto.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment.Companion.newInstance




class MyAppIntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        addSlide(newInstance(R.layout.intro_custom_layout1))
        addSlide(newInstance(R.layout.intro_custom_layout2))
        addSlide(newInstance(R.layout.intro_custom_layout3))
        showStatusBar(true)
        setStatusBarColorRes(R.color.md_theme_primary)
        setNavBarColorRes(R.color.md_theme_primary)
        setIndicatorColor(Color.BLACK,Color.GRAY)

        setColorDoneText(Color.BLACK)
        setColorSkipButton(Color.BLACK)
        setNextArrowColor(Color.BLACK)


    }

    public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }
}
