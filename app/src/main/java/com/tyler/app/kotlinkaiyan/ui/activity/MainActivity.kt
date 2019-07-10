package com.tyler.app.kotlinkaiyan.ui.activity

import android.support.v4.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseActivity
import com.tyler.app.kotlinkaiyan.showToast
import com.tyler.app.kotlinkaiyan.ui.MainTabEntity
import com.tyler.app.kotlinkaiyan.ui.fragment.MainDiscoveryFragment
import com.tyler.app.kotlinkaiyan.ui.fragment.MainHomeFragment
import com.tyler.app.kotlinkaiyan.ui.fragment.MainHotFragment
import com.tyler.app.kotlinkaiyan.ui.fragment.MainMineFragment
import com.tyler.app.kotlinkaiyan.util.Constant
import com.tyler.app.kotlinkaiyan.util.StatusBarUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        /**
         * 跳转其他页面 包
         */
        val BUNDLE_GO_TO = "BUNDLE_GO_TO"

        val MAIN_TAB_HOME: Int = 0
        val MAIN_TAB_DISCOVERY: Int = 1
        val MAIN_TAB_HOT: Int = 2
        val MAIN_TAB_MINE: Int = 3

        val DOUBLE_CLICK_EXIT_INTERVAL: Long = 2 * 1000L
    }

    private val mTabUnselectedIcons =
            intArrayOf(
                    R.drawable.ic_main_tab_home_unselected,
                    R.drawable.ic_main_tab_discovery_unselected,
                    R.drawable.ic_main_tab_hot_unselected,
                    R.drawable.ic_main_tab_mine_unselected
            )
    private val mTabSelectedIcons = intArrayOf(
            R.drawable.ic_main_tab_home_selected,
            R.drawable.ic_main_tab_discovery_selected,
            R.drawable.ic_main_tab_hot_selected,
            R.drawable.ic_main_tab_mine_selected
    )
    private val mTabTitles = arrayOf("精选", "发现", "热门", "我的")
    private var mHomeFragment: MainHomeFragment? = null
    private var mDiscoveryFragment: MainDiscoveryFragment? = null
    private var mHotFragment: MainHotFragment? = null
    private var mMineFragment: MainMineFragment? = null

    private var mLastPressBackTimeMillis: Long = 0L
    /**
     * 跳转其他页面处理(推送等点击跳转...),默认0 不跳转
     */
    private var mGoToOtherPageTag: Int = Constant.Main.TO_OTHER_NULL

    //当前选中
    private var mCurrentPos: Int = MAIN_TAB_HOME


    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        mGoToOtherPageTag = intent.getIntExtra(BUNDLE_GO_TO, 0)
    }

    override fun initView() {
        //设置状态栏透明
        StatusBarUtils.darkMode(this)
        StatusBarUtils.setPaddingSmart(this, fl_main)

        //设置底部tab菜单
        val tabEntityList = ArrayList<CustomTabEntity>()
        (0 until mTabTitles.size).mapTo(tabEntityList) {
            MainTabEntity(mTabUnselectedIcons[it], mTabSelectedIcons[it], mTabTitles[it])
        }
        ctl_main.setTabData(tabEntityList)
        ctl_main.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {
            }

        })
        switchFragment(MAIN_TAB_HOME)
    }

    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        //隐藏所有
        hideAllFragment(transaction)
        when (position) {

            MAIN_TAB_HOME -> mHomeFragment?.let { transaction.show(it) }
                    ?: MainHomeFragment.getInstance().let {
                        mHomeFragment = it
                        transaction.add(R.id.fl_main, it)
                    }

            MAIN_TAB_DISCOVERY -> mDiscoveryFragment?.let { transaction.show(it) }
                    ?: MainDiscoveryFragment.getInstance().let {
                        mDiscoveryFragment = it
                        transaction.add(R.id.fl_main, it)
                    }

            MAIN_TAB_HOT -> mHotFragment?.let { transaction.show(it) }
                    ?: MainHotFragment.getInstance().let {
                        mHotFragment = it
                        transaction.add(R.id.fl_main, it)
                    }

            MAIN_TAB_MINE -> mMineFragment?.let { transaction.show(it) }
                    ?: MainMineFragment.getInstance().let {
                        mMineFragment = it
                        transaction.add(R.id.fl_main, it)
                    }
        }

        mCurrentPos = position
        ctl_main.currentTab = position
        transaction.commitAllowingStateLoss()
    }

    fun hideAllFragment(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mDiscoveryFragment?.let { transaction.hide(it) }
        mHotFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    override fun start() {
        //主页数据请求等等...

        //跳转其他页面
        goToOtherPage()
    }

    private fun goToOtherPage() {
        when (mGoToOtherPageTag) {
            Constant.Main.TO_OTHER_NULL -> return
            //...
        }
    }

    override fun onBackPressed() {
        exitApp()
    }

    private fun exitApp() {
        if (System.currentTimeMillis() - mLastPressBackTimeMillis > DOUBLE_CLICK_EXIT_INTERVAL) {
            showToast("再按一次退出程序")
            mLastPressBackTimeMillis = System.currentTimeMillis()
        } else {
            mBaseApp?.clearAllActivities()
        }
    }

}
