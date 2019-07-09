package com.tyler.app.kotlinkaiyan.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.tyler.app.kotlinkaiyan.BaseApp
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseActivity
import com.tyler.app.kotlinkaiyan.util.StatusBarUtil
import com.tyler.app.kotlinkaiyan.util.SysUtils
import com.tyler.app.kotlinkaiyan.util.UIUtils
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/4  13:58.
 * @描述  ${Splash页}.
 */
class SplashActivity : BaseActivity() {


    private var mTextTypeFaceCh: Typeface? = null
    private var mTextTypeFaceEn: Typeface? = null
    private var mAnimation: Animation? = null

    init {
        mTextTypeFaceCh = Typeface.createFromAsset(BaseApp.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        mTextTypeFaceEn = Typeface.createFromAsset(BaseApp.context.assets, "fonts/Lobster-1.4.otf")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        UIUtils.fullScreen(mActivity, true)
        //恢复默认主题
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
    }

    override fun start() {

    }

    override fun initData() {

    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        //设置状态栏透明
        StatusBarUtil.darkMode(this)
        tv_splash_slogan_ch.typeface = mTextTypeFaceCh
        tv_splash_slogan_en.typeface = mTextTypeFaceEn
        tv_splash_app_ver.text = "v " + SysUtils.getAppVerName()
        //设置缩放动画
        mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.splash_scale)
        mAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                goToNext()
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
        iv_splash_bg.startAnimation(mAnimation)
    }

    fun goToNext() {
        startActivity(Intent(mActivity, MainActivity::class.java))
    }

    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun onBackPressed() {

    }

}