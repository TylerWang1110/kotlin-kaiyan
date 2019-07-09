package com.tyler.app.kotlinkaiyan.ui

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  10:05.
 * @描述  ${主界面Tab实体类}.
 */
class MainTabEntity(val unSelectedIcon: Int, val selectedIcon: Int, val title: String) : CustomTabEntity {

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }

}