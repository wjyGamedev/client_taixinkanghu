/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.select_nurse.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/29		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.select_nurse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.DFaceImages;
import com.taixinkanghu.app.model.data.DNurseBasics;
import com.taixinkanghu.app.model.data.DNurseBasicsList;
import com.taixinkanghu.app.model.data.DNurseContainer;
import com.taixinkanghu.app.ui.adapter.IBaseAdapter;
import com.taixinkanghu.widget.circleimageview.CircleImageView;

import java.util.ArrayList;

public class SelectNurseAdapter extends IBaseAdapter
{
	private DNurseBasicsList m_nurseBasicsList = null;
	private LayoutInflater   m_layoutInflater  = null;

	@Override
	public int getCount()
	{
		ArrayList<DNurseBasics> nurseBasicses = m_nurseBasicsList.getNurseBasicses();
		if (nurseBasicses == null || nurseBasicses.isEmpty())
			return 0;

		return nurseBasicses.size();
	}

	@Override
	public Object getItem(int position)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = null;

		if (convertView == null)
		{

			viewHolder = new ViewHolder();
			convertView = m_layoutInflater.inflate(R.layout.item_worker_list, null);
			convertView.setTag(viewHolder);

			viewHolder.initListViewItem(convertView);
		}
		else
		{
			viewHolder = (ViewHolder)convertView.getTag();
		}

		viewHolder.initContent(m_nurseBasicsList, position);
		return convertView;
	}

	public SelectNurseAdapter(Context context)
	{
		super(context);
		init();
	}

	private void init()
	{
		m_nurseBasicsList = DNurseContainer.GetInstance().GetNurseBaisicsList();
		m_layoutInflater = LayoutInflater.from(m_context);
	}
}

final class ViewHolder {
	private LinearLayout    m_listViewLayout;    //listview布局
	private CircleImageView m_faceImage;        //头像
	private TextView        m_tvName;            //名字
	private RatingBar       m_starLevel;        //星级
	private TextView        m_tvAge;            //年龄
	private TextView        m_tvHomeTown;        //籍贯
	private TextView        m_tvNursingExp;    //护理经验
	private TextView        m_tvNursingLevel;    //护理级别
	private TextView        m_tvServiceChargePerDay; //价格
	private TextView        m_tvServiceStatus; //服务状态

	private ArrayList<DNurseBasics> m_nurseBasics = new ArrayList<DNurseBasics>();

	public void initListViewItem(View view)
	{
		m_listViewLayout = (LinearLayout)view.findViewById(R.id.worker_btn);
		m_faceImage = (CircleImageView)view.findViewById(R.id.pic);
		m_tvName = (TextView)view.findViewById(R.id.name);
		m_starLevel = (RatingBar)view.findViewById(R.id.star);
		m_tvAge = (TextView)view.findViewById(R.id.age);
		m_tvHomeTown = (TextView)view.findViewById(R.id.province);
		m_tvNursingExp = (TextView)view.findViewById(R.id.workYear);
		m_tvNursingLevel = (TextView)view.findViewById(R.id.level_name);
		m_tvServiceChargePerDay = (TextView)view.findViewById(R.id.price);
		m_tvServiceStatus = (TextView)view.findViewById(R.id.inService);
	}

	public void initContent(DNurseBasicsList nurseBasicsList, int position)
	{
		if (m_nurseBasics == null || m_nurseBasics.isEmpty())
		{
			m_nurseBasics = nurseBasicsList.getNurseBasicses();
		}

		if (m_nurseBasics == null)
		{
			//TODO:error
			return;
		}

		if (position >= m_nurseBasics.size())
		{
			//TODO:error
			return;
		}

		DNurseBasics tmpNurseBasics = m_nurseBasics.get(position);
		int          iID            = tmpNurseBasics.getID();
		int          iImageIndex    = (iID - 1);
		int          iImageID       = DFaceImages.getInstance().getImageIDbyIndex(iImageIndex);
		m_faceImage.setImageResource(iImageID);
		m_tvName.setText(tmpNurseBasics.getName());
		m_starLevel.setRating(tmpNurseBasics.getStarLevel());
		m_tvAge.setText(tmpNurseBasics.getAge().toString());
		m_tvHomeTown.setText(tmpNurseBasics.getHomeTown());
		m_tvNursingExp.setText(tmpNurseBasics.getNursingExp());
		m_tvNursingLevel.setText(tmpNurseBasics.getNursingLevel());
		m_tvServiceChargePerDay.setText(String.valueOf(tmpNurseBasics.getServiceChargePerAllCanntCare()));
		m_tvServiceStatus.setText(tmpNurseBasics.getServiceStatus());

	}

}
