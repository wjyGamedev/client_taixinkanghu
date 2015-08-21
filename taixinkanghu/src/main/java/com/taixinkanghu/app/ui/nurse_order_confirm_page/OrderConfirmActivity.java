package com.taixinkanghu.app.ui.nurse_order_confirm_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.DApoitNursingPage;
import com.taixinkanghu.app.model.data.DDepartment;
import com.taixinkanghu.app.model.data.DDepartmentList;
import com.taixinkanghu.app.model.data.DFaceImages;
import com.taixinkanghu.app.model.data.DGlobal;
import com.taixinkanghu.app.model.data.DHospital;
import com.taixinkanghu.app.model.data.DHospitalList;
import com.taixinkanghu.app.model.data.DNurseBasics;
import com.taixinkanghu.app.model.data.DNurseBasicsList;
import com.taixinkanghu.app.model.data.DNurseContainer;
import com.taixinkanghu.app.model.data.DNurseOrderConfirmPage;
import com.taixinkanghu.app.model.data.DNurseSenior;
import com.taixinkanghu.app.model.data.DNurseSeniorList;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderListEvent;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.app.ui.nurse_order_pay_page.NurseOrderPayActivity;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.widget.circleimageview.CircleImageView;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import de.greenrobot.event.EventBus;

public class OrderConfirmActivity extends Activity
{
	//widget
	private HeaderCommon m_headerCommon = null;

	//订单内容
	private CircleImageView m_nurseHeadImgIV       = null;    //头像
	private TextView        m_nameTV               = null;        //姓名
	private TextView        m_nuringLevelTV        = null;    //护理级别
	private TextView        m_jobNumTV             = null;        //工号
	private TextView        m_serviceDateTV        = null;    //服务时间
	private TextView        m_serviceAddressTV     = null;    //服务地点
	private LinearLayout    m_patientStateRegionLL = null;    //被护理人点击区域
	private TextView        m_patientNameTV        = null;    //被护理人姓名
	private LinearLayout    m_patientRegionLL      = null;    //被护理人状态点击区域
	private TextView        m_patientStateTV       = null;    //被护理人状态

	//订单金额
	private LinearLayout m_allRegionLL      = null;    //全天24小时点击区域
	private TextView     m_allNumTV         = null;    //24小时服务天数
	private TextView     m_chargePerAllTV   = null;    //24小时服务单价
	private TextView     m_allCoeffTV       = null;    //24小时服务天数作为系数
	private LinearLayout m_dayRegionLL      = null;    //白天12小时点击区域
	private TextView     m_dayNumTV         = null;    //白天12小时服务天数
	private TextView     m_chargePerDayTV   = null;    //白天12小时服务单价
	private TextView     m_dayCoeffTV       = null;    //白天12小时服务天数作为系数
	private LinearLayout m_nightRegionLL    = null;    //黑天12小时点击区域
	private TextView     m_nightNumTV       = null;    //黑天12小时服务天数
	private TextView     m_chargePerNightTV = null;    //黑天12小时服务单价
	private TextView     m_NightCoeffTV     = null;    //黑天12小时服务天数作为系数
	private TextView     m_TotalChargeTV    = null;    //总价格

	private TextView m_userProtcolTV         = null;    //用户协议
	private Button   m_confirmAppointmentBtn = null;    //确定预约

	//测量高度用的LL
	private LinearLayout m_measuringPatientStateHeightLL       = null;    //测量患者状态下拉框所需高度的LL
	private Integer      m_selectPatientStateTitleHight        = 0;    //患者状态下拉框所需高度

	//logical
	private HandlerClickEventNurseOrderConfirm m_handlerClickEventNurseOrderConfirm = null;

	private EventBus m_eventBus = EventBus.getDefault();

	private final String UNIT_DAY  = AppUtil.GetResources().getString(R.string.content_day);
	private final String UNIT_YUAN = AppUtil.GetResources().getString(R.string.content_yuan);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_order);

		init();
		initListener();
		getHight();
		updateContent();
		updateCharge();

	}

	@Override
	protected void onStart()
	{
		setContent();
		setCharge();
		initGlobalData();
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		clearupGlobalData();
		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		m_eventBus.unregister(this);
		super.onDestroy();
	}

	private void initGlobalData()
	{
		DGlobal.GetInstance().setContext(this);
	}

	private void clearupGlobalData()
	{
		DGlobal.GetInstance().clearupContext(this);
	}

	private void init()
	{
		//widget
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();
		m_headerCommon.setTitle(R.string.determine_order_title);

		//订单内容
		m_nurseHeadImgIV = (CircleImageView)findViewById(R.id.header_img_civ);
		m_nameTV = (TextView)findViewById(R.id.name_tv);
		m_nuringLevelTV = (TextView)findViewById(R.id.nuring_level_tv);
		m_jobNumTV = (TextView)findViewById(R.id.job_num_tv);
		m_serviceDateTV = (TextView)findViewById(R.id.service_date_tv);
		m_serviceAddressTV = (TextView)findViewById(R.id.service_address_tv);
		m_patientRegionLL = (LinearLayout)findViewById(R.id.patient_region_ll);
		m_patientNameTV = (TextView)findViewById(R.id.patient_name_tv);
		m_patientStateRegionLL = (LinearLayout)findViewById(R.id.patient_state_region_ll);
		m_patientStateTV = (TextView)findViewById(R.id.patient_state_tv);
		//订单金额
		m_allRegionLL = (LinearLayout)findViewById(R.id.all_region_ll);
		m_allRegionLL.setVisibility(View.GONE);
		m_allNumTV = (TextView)findViewById(R.id.all_num_tv);
		m_chargePerAllTV = (TextView)findViewById(R.id.charge_per_all_tv);
		m_allCoeffTV = (TextView)findViewById(R.id.all_coeff_tv);

		m_dayRegionLL = (LinearLayout)findViewById(R.id.day_region_ll);
		m_dayRegionLL.setVisibility(View.GONE);
		m_dayNumTV = (TextView)findViewById(R.id.day_num_tv);
		m_chargePerDayTV = (TextView)findViewById(R.id.charge_per_day_tv);
		m_dayCoeffTV = (TextView)findViewById(R.id.day_coeff_tv);

		m_nightRegionLL = (LinearLayout)findViewById(R.id.night_region_ll);
		m_nightRegionLL.setVisibility(View.GONE);
		m_nightNumTV = (TextView)findViewById(R.id.night_num_tv);
		m_chargePerNightTV = (TextView)findViewById(R.id.charge_per_night_tv);
		m_NightCoeffTV = (TextView)findViewById(R.id.night_coeff_tv);
		m_TotalChargeTV = (TextView)findViewById(R.id.total_charge_tv);

		m_userProtcolTV = (TextView)findViewById(R.id.user_protocol_tv);

		m_confirmAppointmentBtn = (Button)findViewById(R.id.confirm_appointment_btn);
		m_userProtcolTV.append(Html.fromHtml("<a href=>" + "《用户协议》" + "</a> "));

		m_handlerClickEventNurseOrderConfirm = new HandlerClickEventNurseOrderConfirm(this);

		m_eventBus.register(this);

		//测量高度用的LL
		m_measuringPatientStateHeightLL = (LinearLayout)findViewById(R.id.measuring_height_patient_state);
	}

	private void initListener()
	{
		m_patientRegionLL.setOnClickListener(m_handlerClickEventNurseOrderConfirm);
		m_patientStateRegionLL.setOnClickListener(m_handlerClickEventNurseOrderConfirm);

		m_userProtcolTV.setOnClickListener(m_handlerClickEventNurseOrderConfirm);
		m_confirmAppointmentBtn.setOnClickListener(m_handlerClickEventNurseOrderConfirm);
	}

	private void getHight()
	{
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

		m_measuringPatientStateHeightLL.measure(w, h);
		m_selectPatientStateTitleHight = m_measuringPatientStateHeightLL.getMeasuredHeight();
	}

	private void updateContent()
	{
		Intent intent = getIntent();
		if (intent == null)
		{
			RegisterDialog.GetInstance().setMsg("intent == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		int nurseID = intent.getIntExtra(NurseBasicListConfig.ID, -1);
		if (nurseID == -1)
		{
			RegisterDialog.GetInstance().setMsg("id is invalid", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		//data
		DNurseOrderConfirmPage.GetInstance().setNurseID(nurseID);

		//0101. m_nurseID有效性判断
		//通过ID可以找到DNurseBasics
		DNurseBasicsList nurseBasicsList = DNurseContainer.GetInstance().getNurseBasicsList();
		if (nurseBasicsList == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasicsList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		DNurseBasics nurseBasics = nurseBasicsList.getNurseBasicByID(nurseID);
		if (nurseBasics == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//通过ID可以找到DNurseSenior
		DNurseSeniorList nurseSeniorList = DNurseContainer.GetInstance().getNurseSeniorList();
		if (nurseSeniorList == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseSeniorList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		DNurseSenior nurseSenior = nurseSeniorList.getNurseSeniorByID(nurseID);
		if (nurseSenior == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseSeniorList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//0102. update ui
		int imgResID = DFaceImages.getInstance().getImgResIDbyIndex(0);
		setNurseHeaderImgResID(imgResID);
		setNurseName(nurseBasics.getName());
		setNursingLevel(nurseBasics.getNursingLevel());
		setNurseJobNum(nurseSenior.getJobNum());

		//服务时间
		DApoitNursingPage.DNursingDate nursingDate = DApoitNursingPage.GetInstance().getNursingDate();
		if (nursingDate == null)
		{
			RegisterDialog.GetInstance().setMsg("nursingDate == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		String serviceDate = nursingDate.getDateDescription();
		setServiceDate(serviceDate);

		//服务地点
		int       hospitalID = DApoitNursingPage.GetInstance().getHospitalID();
		DHospital hospital   = DHospitalList.GetInstance().getHospitalByID(hospitalID);
		if (hospital == null)
		{
			RegisterDialog.GetInstance().setMsg("hospital == null[hospitalID:=" + hospitalID + "]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		String hospitalName = hospital.getName();

		int         departmentID = DApoitNursingPage.GetInstance().getDepartmenetID();
		DDepartment department   = DDepartmentList.GetInstance().getDepartmentByID(departmentID);
		if (department == null)
		{
			RegisterDialog.GetInstance().setMsg("department == null[departmentID:=" + departmentID + "]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		String departmentName = department.getName();
		String serviceAddress = hospitalName + departmentName;
		setServiceAddress(serviceAddress);

		//被护理人
		String patientName = DApoitNursingPage.GetInstance().getName();
		m_patientNameTV.setText(patientName);
		//被护理人状态
		EnumConfig.PatientState patientState = DApoitNursingPage.GetInstance().getPatientState();
		if (patientState == null)
		{
			RegisterDialog.GetInstance().setMsg("patientState == null");
			RegisterDialog.GetInstance().show();
			return;
		}
		m_patientStateTV.setText(patientState.getName());

	}

	private void setContent()
	{
		int headerImgResID = DNurseOrderConfirmPage.GetInstance().getNurseHeaderImgResID();
		m_nurseHeadImgIV.setImageResource(headerImgResID);

		String name = DNurseOrderConfirmPage.GetInstance().getNurseName();
		m_nameTV.setText(name);

		String jobNum = DNurseOrderConfirmPage.GetInstance().getNurseJobNum();
		m_jobNumTV.setText(jobNum);

		String nursingLevel = DNurseOrderConfirmPage.GetInstance().getNursingLevel();
		m_nuringLevelTV.setText(nursingLevel);

		String serviceDate = DNurseOrderConfirmPage.GetInstance().getServiceDate();
		m_serviceDateTV.setText(serviceDate);

		String serviceAddress = DNurseOrderConfirmPage.GetInstance().getServiceAddress();
		m_serviceAddressTV.setText(serviceAddress);

		//被护理人
		String patientName = DApoitNursingPage.GetInstance().getName();
		m_patientNameTV.setText(patientName);
		//被护理人状态
		EnumConfig.PatientState patientState = DApoitNursingPage.GetInstance().getPatientState();
		if (patientState == null)
		{
			RegisterDialog.GetInstance().setMsg("patientState == null");
			RegisterDialog.GetInstance().show();
			return;
		}
		m_patientStateTV.setText(patientState.getName());
	}

	private void updateCharge()
	{
		//01. PatientState
		EnumConfig.PatientState patientState = DApoitNursingPage.GetInstance().getPatientState();
		if (patientState == null)
		{
			RegisterDialog.GetInstance().setMsg("patientState == null]", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. DNursingDate
		DApoitNursingPage.DNursingDate nursingDate = DApoitNursingPage.GetInstance().getNursingDate();
		if (nursingDate == null)
		{
			RegisterDialog.GetInstance().setMsg("nursingDate == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//03. DNurseBasics
		DNurseBasicsList nurseBasicsList = DNurseContainer.GetInstance().getNurseBasicsList();
		if (nurseBasicsList == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasicsList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		int nurseID = DNurseOrderConfirmPage.GetInstance().getNurseID();
		DNurseBasics nurseBasics = nurseBasicsList.getNurseBasicByID(nurseID);
		if (nurseBasics == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//04. 订单金额
		//全天24小时
		int allNum       = nursingDate.getAllNum();
		int chargePerAll = 0;
		if (allNum > 0)
		{
			String strAllNum = String.valueOf(allNum);
			chargePerAll = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_ALL, patientState);
			String strChargetPerAll = String.valueOf(chargePerAll);

			//ui
			m_allNumTV.setText(strAllNum + UNIT_DAY);
			m_chargePerAllTV.setText(strChargetPerAll + UNIT_YUAN);
			m_allCoeffTV.setText(strAllNum);
			m_allRegionLL.setVisibility(View.VISIBLE);
			//data
			DNurseOrderConfirmPage.GetInstance().setAllNum(allNum);
			DNurseOrderConfirmPage.GetInstance().setChargePerAll(chargePerAll);
		}
		else
		{
			m_allRegionLL.setVisibility(View.GONE);
		}

		//白天12小时
		int dayNum       = nursingDate.getDayNum();
		int chargePerDay = 0;
		if (dayNum > 0)
		{
			String strDayNum = String.valueOf(dayNum);
			chargePerDay = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_DAY, patientState);
			String strChargePerDay = String.valueOf(chargePerDay);

			//ui
			m_dayNumTV.setText(strDayNum + UNIT_DAY);
			m_chargePerDayTV.setText(strChargePerDay + UNIT_YUAN);
			m_dayCoeffTV.setText(strDayNum);
			m_dayRegionLL.setVisibility(View.VISIBLE);
			//data
			DNurseOrderConfirmPage.GetInstance().setDayNum(dayNum);
			DNurseOrderConfirmPage.GetInstance().setChargePerDay(chargePerDay);
		}
		else
		{
			m_dayRegionLL.setVisibility(View.GONE);
		}

		//夜间12小时
		int nightNum       = nursingDate.getNightNum();
		int chargePerNight = 0;
		if (nightNum > 0)
		{
			String strNightNum = String.valueOf(nightNum);
			chargePerNight = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_NIGHT, patientState);
			String strChargePerNight = String.valueOf(chargePerNight);

			//ui
			m_nightNumTV.setText(strNightNum + UNIT_DAY);
			m_chargePerNightTV.setText(strChargePerNight + UNIT_YUAN);
			m_NightCoeffTV.setText(strNightNum);
			m_nightRegionLL.setVisibility(View.VISIBLE);
			//data
			DNurseOrderConfirmPage.GetInstance().setNightNum(nightNum);
			DNurseOrderConfirmPage.GetInstance().setChargePerNight(chargePerNight);
		}
		else
		{
			m_nightRegionLL.setVisibility(View.GONE);
		}

		//总价格
		int    totalCharge    = allNum * chargePerAll + dayNum * chargePerDay + nightNum * chargePerNight;
		String strTotalCharge = String.valueOf(totalCharge) + getResources().getString(R.string.content_yuan);
		//ui
		m_TotalChargeTV.setText(strTotalCharge);
		//data
		DNurseOrderConfirmPage.GetInstance().setTotalCharge(totalCharge);

	}

	private void setCharge()
	{
		int allNum       = DNurseOrderConfirmPage.GetInstance().getAllNum();
		int chargePerAll = DNurseOrderConfirmPage.GetInstance().getChargePerAll();
		String strAllNum = String.valueOf(allNum);

		if (allNum != 0)
		{
			m_allNumTV.setText(strAllNum + UNIT_DAY);
			m_chargePerAllTV.setText(String.valueOf(chargePerAll) + UNIT_YUAN);
			m_allCoeffTV.setText(strAllNum);
			m_allRegionLL.setVisibility(View.VISIBLE);
		}
		else
		{
			m_allRegionLL.setVisibility(View.GONE);
		}

		int dayNum       = DNurseOrderConfirmPage.GetInstance().getDayNum();
		int chargePerDay = DNurseOrderConfirmPage.GetInstance().getChargePerDay();
		String strDayNum = String.valueOf(dayNum);

		if (dayNum != 0)
		{
			m_dayNumTV.setText(strDayNum + UNIT_DAY);
			m_chargePerDayTV.setText(String.valueOf(chargePerDay) + UNIT_YUAN);
			m_dayCoeffTV.setText(strDayNum);
			m_dayRegionLL.setVisibility(View.VISIBLE);
		}
		else
		{
			m_dayRegionLL.setVisibility(View.GONE);
		}


		int nightNum       = DNurseOrderConfirmPage.GetInstance().getNightNum();
		int chargePerNight = DNurseOrderConfirmPage.GetInstance().getChargePerNight();
		String strNightNum = String.valueOf(nightNum);

		if (nightNum != 0)
		{
			m_nightNumTV.setText(strNightNum + UNIT_DAY);
			m_chargePerNightTV.setText(String.valueOf(chargePerNight) + UNIT_YUAN);
			m_NightCoeffTV.setText(strNightNum);
			m_nightRegionLL.setVisibility(View.VISIBLE);
		}
		else
		{
			m_nightRegionLL.setVisibility(View.GONE);
		}

	}

	public Integer getSelectPatientStateTitleHight()
	{
		return m_selectPatientStateTitleHight;
	}

	public void setPatientState(EnumConfig.PatientState patientState)
	{
		//01. update ui
		m_patientStateTV.setText(patientState.getName());
		//02. upate data
		//注意02和03顺序不要变动。依赖于setPatientState，然后getPatientState
		DApoitNursingPage.GetInstance().setPatientState(patientState);
		//03. update charge
		updateCharge();
	}

	private void setNurseName(String name)
	{
		//ui
		m_nameTV.setText(name);
		//data
		DNurseOrderConfirmPage.GetInstance().setNurseName(name);
	}

	private void setNurseHeaderImgResID(int resID)
	{
		//ui
		m_nurseHeadImgIV.setImageResource(resID);
		//data
		DNurseOrderConfirmPage.GetInstance().setNurseHeaderImgResID(resID);
	}

	private void setNurseJobNum(String jobNum)
	{
		//ui
		m_jobNumTV.setText(jobNum);
		//data
		DNurseOrderConfirmPage.GetInstance().setNurseJobNum(jobNum);
	}

	private void setNursingLevel(String nursingLevel)
	{
		//ui
		m_nuringLevelTV.setText(nursingLevel);
		//data
		DNurseOrderConfirmPage.GetInstance().setNursingLevel(nursingLevel);
	}

	private void setServiceDate(String serviceDate)
	{
		//ui
		m_serviceDateTV.setText(serviceDate);
		//data
		DNurseOrderConfirmPage.GetInstance().setServiceDate(serviceDate);
	}

	private void setServiceAddress(String address)
	{
		//ui
		m_serviceAddressTV.setText(address);
		//data
		DNurseOrderConfirmPage.GetInstance().setServiceAddress(address);
	}

	/**
	 * event bus handle
	 */
	//下订单成功，跳转到支付页面。
	public void onEventMainThread(FinishedNurseOrderListEvent event)
	{
		Intent intent   = new Intent(this, NurseOrderPayActivity.class);
		int    nurserID = event.getNurseID();
		int    orderID  = event.getOrderID();
		intent.putExtra(NurseOrderConfig.NURSE_ID, nurserID);
		intent.putExtra(NurseOrderConfig.ORDER_ID, orderID);
		startActivity(intent);
		return;
	}

}
