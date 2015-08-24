package com.taixinkanghu.app.ui.appointment_nursing;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.page.DApoitNursingPage;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.data.page.DNursingModule;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.model.net.event.send.ReqApoitNursingEvent;
import com.taixinkanghu.app.ui.activity.AgreementActivity;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.nurse_order_confirm_page.OrderConfirmActivity;
import com.taixinkanghu.app.ui.select_date.SelectDateActivity;
import com.taixinkanghu.app.ui.select_nurse.SelectNurseActivity;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/7/28.
 */
public class HandlerClickEventAppinmentNursing extends BaseHandleOnClickEvent
{
	private EventBus m_eventBus = EventBus.getDefault();

	public HandlerClickEventAppinmentNursing(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		ApoitNursingActivity apoitNursingActivity = (ApoitNursingActivity)m_context;
		if (apoitNursingActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("apoitNursingActivity == null");
			RegisterDialog.GetInstance().show();
			return;
		}


		FragmentTransaction transaction = apoitNursingActivity.getFragmentManager().beginTransaction();

		switch (v.getId())
		{
			//01. 点击区域
			case R.id.name_region_ll:
			{
				apoitNursingActivity.setNameFocus();
			}
			return;
			case R.id.phone_num_region_ll:
			{
				apoitNursingActivity.setPhoneNumFocus();
			}
			return;
			case R.id.gender_region_ll:
			{
				Integer genderTitleHight = apoitNursingActivity.getSelectGenderTitleHight();
				SelectSexFragment selectSexFragment = new SelectSexFragment();
				selectSexFragment.setGenderTitleHight(genderTitleHight);
				transaction.replace(R.id.appointment_nursing_page, selectSexFragment, selectSexFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.age_region_ll:
			{
				Integer ageTitleHight = apoitNursingActivity.getSelectAgeTitleHight();
				SelectAgeFragment selectAgeFragment = new SelectAgeFragment();
				selectAgeFragment.setAgeTitleHight(ageTitleHight);
				transaction.replace(R.id.appointment_nursing_page, selectAgeFragment, selectAgeFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.weight_region_ll:
			{
				Integer weightTitleHight = apoitNursingActivity.getSelectWeightTitleHight();
				SelectWeightFragment selectWeightFragment = new SelectWeightFragment();
				selectWeightFragment.setWeightTitleHight(weightTitleHight);
				transaction.replace(R.id.appointment_nursing_page, selectWeightFragment, selectWeightFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.hospital_region_ll:
			{
				SelectHospitalFragment selectHospitalFragment = new SelectHospitalFragment();
				transaction.replace(R.id.appointment_nursing_page, selectHospitalFragment, selectHospitalFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.department_region_ll:
			{
				SelectDepartmentFragment selectDepartmentFragment = new SelectDepartmentFragment();
				transaction.replace(R.id.appointment_nursing_page, selectDepartmentFragment, selectDepartmentFragment.getClass().getName
											());
				transaction.commit();
			}
			return;
			case R.id.patient_state_region_ll:
			{
				Integer patientStateTitleHight = apoitNursingActivity.getSelectPatientStateTitleHight();
				SelectPatientStateFragment selectPatientStateFragment = new SelectPatientStateFragment();
				selectPatientStateFragment.setPatientStateTitleHight(patientStateTitleHight);
				transaction.replace(R.id.appointment_nursing_page,
									selectPatientStateFragment,
									selectPatientStateFragment.getClass().getName()
								   );
				transaction.commit();
			}
			return;
			case R.id.service_date_region_ll:
			{
				apoitNursingActivity.startActivity(new Intent(apoitNursingActivity, SelectDateActivity.class));
			}
			return;
			//02. 控件点击
			case R.id.protocol_tv:
			{
				apoitNursingActivity.startActivity(new Intent(apoitNursingActivity, AgreementActivity.class));
			}
			return;
			case R.id.btn_bottom:
			{
				//01. 从预约陪护发起的业务流程
				EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
				if (nursingModuleStatus == null ||
						nursingModuleStatus == EnumConfig.NursingModuleStatus.APIOT_NURSING)
				{
					//0101. 保存本地信息
					apoitNursingActivity.confirmAction();

					//0102. 必填选项是否已经填写
					String hospitalName = apoitNursingActivity.getHospitalName();
					String departmentName = apoitNursingActivity.getDepartmentName();
					String patientState = apoitNursingActivity.getPatientState();
					String dateDescription = apoitNursingActivity.getDateDescription();
					if (TextUtils.isEmpty(hospitalName) || TextUtils.isEmpty(departmentName) || TextUtils.isEmpty(patientState) || TextUtils.isEmpty(
							dateDescription
																																					))
					{
						RegisterDialog.GetInstance().setMsg(apoitNursingActivity.getResources().getString(R.string
																												  .err_info_fill_required_options),
															apoitNursingActivity
														   );
						RegisterDialog.GetInstance().show();
						return;
					}

					//0103. 发送消息到服务器
					ReqApoitNursingEvent reqApoitNursingEvent = new ReqApoitNursingEvent();
					m_eventBus.post(reqApoitNursingEvent);
					//备注：nurse senior list 在接收完成nurse basic list成功后，发送。

					//0104. 跳转到护理员列表界面
					apoitNursingActivity.startActivity(new Intent(apoitNursingActivity, SelectNurseActivity.class));

					//0105. set global data
					DGlobal.GetInstance().trySetNursingModuleStatus(EnumConfig.NursingModuleStatus.APIOT_NURSING);

					return;
				}
				//02. 从续订发起的业务流程
				else
				{
					//0201. 跳转到订单确认页面。
					DApoitNursingPage apoitNursingPage = DNursingModule.GetInstance().getApoitNursingPage();
					if (apoitNursingPage == null)
					{
						RegisterDialog.GetInstance().setMsg("apoitNursingPage == null");
						RegisterDialog.GetInstance().show();
						return;
					}
					int nurseID = apoitNursingActivity.getNurseID();
					Intent intent = new Intent(apoitNursingActivity, OrderConfirmActivity.class);
					intent.putExtra(NurseBasicListConfig.NEW_ID, nurseID);
					m_context.startActivity(intent);

				}

				break;
			}
		}    //end_switch (v.getId())

		return;

	}    //end_onClick


}
