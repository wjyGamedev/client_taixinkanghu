package com.taixinkanghu.app.ui.main_page;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2015/7/16.
 */
public class PersonalTabFragmentWrapper extends Fragment
{
    public void init(Fragment baseFragment)
    {
        m_BasePersonalTabFragment = baseFragment;
    }
    private Fragment m_BasePersonalTabFragment = null;
}
