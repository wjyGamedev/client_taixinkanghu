package com.taixinkanghu.app.ui.nurse_order_confirm_page;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.DateConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.net.DAccount;
import com.taixinkanghu.app.model.data.net.DDepartment;
import com.taixinkanghu.app.model.data.net.DDepartmentList;
import com.taixinkanghu.app.model.data.net.DHospital;
import com.taixinkanghu.app.model.data.net.DHospitalList;
import com.taixinkanghu.app.model.data.net.DNurseBasics;
import com.taixinkanghu.app.model.data.net.DNurseBasicsList;
import com.taixinkanghu.app.model.data.net.DNurseContainer;
import com.taixinkanghu.app.model.data.net.DNurseOrder;
import com.taixinkanghu.app.model.data.net.DNurseSenior;
import com.taixinkanghu.app.model.data.net.DNurseSeniorList;
import com.taixinkanghu.app.model.data.net.DNurserOrderList;
import com.taixinkanghu.app.model.data.page.DApoitNursingPage;
import com.taixinkanghu.app.model.data.page.DFaceImages;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.data.page.DNurseOrderConfirmPage;
import com.taixinkanghu.app.model.data.page.DNursingDate;
import com.taixinkanghu.app.model.data.page.DNursingModule;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderConfirmEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderConfirmEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderConfirmForChangeNurse;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.app.ui.main_page.MainActivity;
import com.taixinkanghu.app.ui.nurse_order_pay_page.NurseOrderPayActivity;
import com.taixinkanghu.app.ui.select_date.SelectDateFragment;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.util.logcal.LogicalUtil;
import com.taixinkanghu.widget.circleimageview.CircleImageView;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.greenrobot.event.EventBus;

public class OrderConfirmActivity extends Activity
{
	//widget
	private HeaderCommon m_headerCommon = null;

	//更换时间
	private LinearLayout m_selectBeginDateRegionLL = null;
	private TextView     m_selectBeginDateTV       = null;

	//tab标签相关
	private LinearLayout m_funcTabRegionLL    = null;
	private RadioGroup   m_changeNurseRGrp    = null;
	private RadioButton  m_newNurseRBtn       = null;
	private RadioButton  m_oldBurseRBtn       = null;
	private TextView     m_TabTopDividingLine = null;

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

	private ImageView m_patientInfoArrowIV  = null;
	private ImageView m_patientStateArrowIV = null;


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
	private LinearLayout m_measuringPatientStateHeightLL = null;    //测量患者状态下拉框所需高度的LL
	private Integer      m_selectPatientStateTitleHight  = 0;    //患者状态下拉框所需高度

	//logical
	private EventBus m_eventBus = EventBus.getDefault();

	private HandlerClickEventNurseOrderConfirm m_handlerClickEventNurseOrderConfirm = null;

	private DApoitNursingPage      m_apoitNursingPage      = DNursingModule.GetInstance().getApoitNursingPage();
	private DNurseOrderConfirmPage m_nurseOrderConfirmPage = DNursingModule.GetInstance().getNurseOrderConfirmPage();

	private int    m_newNurseID = DataConfig.DEFAULT_VALUE;
	private int    m_oldNurseID = DataConfig.DEFAULT_VALUE;
	private String m_orderID    = null;

	private final String UNIT_DAY  = AppUtil.GetResources().getString(R.string.content_day);
	private final String UNIT_YUAN = AppUtil.GetResources().getString(R.string.content_yuan);

	//更换护理员
	private Date m_beginDate    = null;    //老护工的开始日期
	private Date m_newBeginDate = null;    //新护工的开始日期
	private Date m_today        = null;
	private Date m_endDate      = null;    //老护工的结束日期

	private SimpleDateFormat m_simpleDateFormat = new SimpleDateFormat(DateConfig.PATTERN_DATE_YEAR_MONTH_DAY);

	private int m_deltaPrice = DataConfig.DEFAULT_VALUE;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_order);

		init();
		initListener();
		getHight();
		initUI();
		initContent();

	}

	@Override
	protected void onStart()
	{
		updateUI();
		updateContent();
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

	private void initUI()
	{
		m_funcTabRegionLL = (LinearLayout)findViewById(R.id.func_tab_region_ll);
		m_changeNurseRGrp = (RadioGroup)findViewById(R.id.change_nurse_rgrp);
		m_newNurseRBtn = (RadioButton)findViewById(R.id.new_nurse_rbtn);
		m_oldBurseRBtn = (RadioButton)findViewById(R.id.old_nurse_rbtn);
		m_TabTopDividingLine = (TextView)findViewById(R.id.tab_top_dividing_line);

	}

	private void updateUI()
	{
		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.CHANGE_NURSE)
		{
			//select begin time
			m_selectBeginDateRegionLL.setVisibility(View.VISIBLE);
			m_selectBeginDateRegionLL.setOnClickListener(m_handlerClickEventNurseOrderConfirm);
			m_selectBeginDateTV.setText(getString(R.string.contetn_click_select));
			//tab
			m_funcTabRegionLL.setVisibility(View.VISIBLE);
			m_TabTopDividingLine.setVisibility(View.GONE);
			m_newNurseRBtn.setOnClickListener(m_handlerClickEventNurseOrderConfirm);
			m_oldBurseRBtn.setOnClickListener(m_handlerClickEventNurseOrderConfirm);
			m_newNurseRBtn.setChecked(true);

			//btn
			m_patientInfoArrowIV.setVisibility(View.INVISIBLE);
			m_patientStateArrowIV.setVisibility(View.INVISIBLE);

			m_patientRegionLL.setOnClickListener(null);
			m_patientStateRegionLL.setOnClickListener(null);
		}
		else
		{
			//select begin time
			m_selectBeginDateRegionLL.setVisibility(View.GONE);
			m_selectBeginDateRegionLL.setOnClickListener(null);
			m_selectBeginDateTV.setText("");

			m_funcTabRegionLL.setVisibility(View.GONE);
			m_TabTopDividingLine.setVisibility(View.VISIBLE);
			m_patientInfoArrowIV.setVisibility(View.VISIBLE);
			m_patientStateArrowIV.setVisibility(View.VISIBLE);

			m_patientRegionLL.setOnClickListener(m_handlerClickEventNurseOrderConfirm);
			m_patientStateRegionLL.setOnClickListener(m_handlerClickEventNurseOrderConfirm);
		}
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

		//更换护工，时间
		m_selectBeginDateRegionLL = (LinearLayout)findViewById(R.id.select_begin_date_region_ll);
		m_selectBeginDateTV = (TextView)findViewById(R.id.select_begin_date_tv);
		//tab标签相关
		m_funcTabRegionLL = (LinearLayout)findViewById(R.id.func_tab_region_ll);
		m_changeNurseRGrp = (RadioGroup)findViewById(R.id.change_nurse_rgrp);
		m_newNurseRBtn = (RadioButton)findViewById(R.id.new_nurse_rbtn);
		m_oldBurseRBtn = (RadioButton)findViewById(R.id.old_nurse_rbtn);
		m_TabTopDividingLine = (TextView)findViewById(R.id.tab_top_dividing_line);

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
		m_patientInfoArrowIV = (ImageView)findViewById(R.id.patient_info_arrow_iv);
		m_patientStateArrowIV = (ImageView)findViewById(R.id.patient_state_arrow_iv);


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

	public void switchNewNurse()
	{
		m_newNurseRBtn.setChecked(true);
		updateContent();
	}

	public void switchOldNurse()
	{
		m_oldBurseRBtn.setChecked(true);
		updateContent();
	}

	private void initContent()
	{
		Intent intent = getIntent();
		if (intent == null)
		{
			RegisterDialog.GetInstance().setMsg("intent == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		m_newNurseID = intent.getIntExtra(NurseBasicListConfig.NEW_ID, DataConfig.DEFAULT_VALUE);
		m_oldNurseID = intent.getIntExtra(NurseBasicListConfig.OLD_ID, DataConfig.DEFAULT_VALUE);
		if (m_newNurseID == DataConfig.DEFAULT_VALUE)
		{
			RegisterDialog.GetInstance().setMsg("id is invalid", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		if (DGlobal.GetInstance().getNursingModuleStatus() == EnumConfig.NursingModuleStatus.CHANGE_NURSE)
		{
			DNurseOrder nurseOrder = DNurserOrderList.GetInstance().getNurseOrderByNurseID(m_oldNurseID);
			if (nurseOrder == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseOrder == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}

			int orderID = nurseOrder.getOrderID();
			m_orderID = String.valueOf(orderID);
		}

		saveToDOrderConfirm(m_newNurseID);

	}

	public String getOrderID()
	{
		return m_orderID;
	}

	private void updateContentByDefault()
	{
		if (m_apoitNursingPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_apoitNursingPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		if (m_nurseOrderConfirmPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderConfirmPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		int headerImgResID = m_nurseOrderConfirmPage.getNurseHeaderImgResID();
		m_nurseHeadImgIV.setImageResource(headerImgResID);

		String name = m_nurseOrderConfirmPage.getNurseName();
		m_nameTV.setText(name);

		String jobNum = m_nurseOrderConfirmPage.getNurseJobNum();
		m_jobNumTV.setText(jobNum);

		String nursingLevel = m_nurseOrderConfirmPage.getNursingLevel();
		m_nuringLevelTV.setText(nursingLevel);

		String serviceDate = m_nurseOrderConfirmPage.getServiceDate();
		m_serviceDateTV.setText(serviceDate);

		String serviceAddress = m_nurseOrderConfirmPage.getServiceAddress();
		m_serviceAddressTV.setText(serviceAddress);

		//被护理人
		String patientName = m_apoitNursingPage.getName();
		m_patientNameTV.setText(patientName);
		//被护理人状态
		EnumConfig.PatientState patientState = m_apoitNursingPage.getPatientState();
		if (patientState == null)
		{
			RegisterDialog.GetInstance().setMsg("patientState == null");
			RegisterDialog.GetInstance().show();
			return;
		}
		m_patientStateTV.setText(patientState.getName());

		//金额
		int    allNum       = m_nurseOrderConfirmPage.getAllNum();
		int    chargePerAll = m_nurseOrderConfirmPage.getChargePerAll();
		String strAllNum    = String.valueOf(allNum);

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

		int    dayNum       = m_nurseOrderConfirmPage.getDayNum();
		int    chargePerDay = m_nurseOrderConfirmPage.getChargePerDay();
		String strDayNum    = String.valueOf(dayNum);

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


		int    nightNum       = m_nurseOrderConfirmPage.getNightNum();
		int    chargePerNight = m_nurseOrderConfirmPage.getChargePerNight();
		String strNightNum    = String.valueOf(nightNum);

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

		//新价格
		int newTotalCharge = allNum * chargePerAll + dayNum * chargePerDay + nightNum * chargePerNight;//m_nurseOrderConfirmPage

		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.CHANGE_NURSE)
		{
			//老价格
			DNurseOrder nurseOrder = DNurserOrderList.GetInstance().getNurseOrderByNurseID(m_oldNurseID);
			if (nurseOrder == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseOrder == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}

			DNurseBasics nurseBasics = nurseOrder.getNurseBasics();
			if (nurseBasics == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}

			//天数：全天24小时，白天12小时，黑天12小时
			allNum = m_nurseOrderConfirmPage.getAllNum();//nursingDate.getAllNum();
			dayNum = m_nurseOrderConfirmPage.getDayNum();//nursingDate.getDayNum();
			nightNum = m_nurseOrderConfirmPage.getNightNum();//nursingDate.getNightNum();

			//单价：全天24小时，白天12小时，黑天12小时
			chargePerAll = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_ALL, patientState);
			chargePerDay = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_DAY, patientState);
			chargePerNight = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_NIGHT, patientState);
			int oldtotalCharge = allNum * chargePerAll + dayNum * chargePerDay + nightNum * chargePerNight;

			int    deltaPrice = newTotalCharge - oldtotalCharge;
			String priceDesc  = null;
			m_deltaPrice = deltaPrice;
			if (deltaPrice >= 0)
			{
				priceDesc = String.valueOf(newTotalCharge) + "(+" + deltaPrice + ")";
			}
			else
			{
				deltaPrice = Math.abs(deltaPrice);
				priceDesc = String.valueOf(newTotalCharge) + "(-" + deltaPrice + ")";
			}

			m_TotalChargeTV.setText(String.valueOf(priceDesc));
		}
		else
		{
			m_TotalChargeTV.setText(String.valueOf(newTotalCharge));
		}


	}

	private void updateContentByOldNurse()
	{
		int headerImgResID = DFaceImages.getInstance().getImgResIDbyID(m_oldNurseID);
		if (headerImgResID == DataConfig.DEFAULT_VALUE)
		{
			headerImgResID = DFaceImages.DEFAULT_IMAGE_RES_ID;
		}
		m_nurseHeadImgIV.setImageResource(headerImgResID);


		DNurseOrder nurseOrder = DNurserOrderList.GetInstance().getNurseOrderByNurseID(m_oldNurseID);
		if (nurseOrder == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrder == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		DNurseBasics nurseBasics = nurseOrder.getNurseBasics();
		if (nurseBasics == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		m_nameTV.setText(nurseBasics.getName());
		m_nuringLevelTV.setText(nurseBasics.getNursingLevel());

		DNurseSenior nurseSenior = nurseOrder.getNurseSenior();
		if (nurseSenior == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseSenior == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_jobNumTV.setText(nurseSenior.getJobNum());

		//服务时间
		DNursingDate nursingDate = m_apoitNursingPage.getNursingDate();
		if (nursingDate == null)
		{
			RegisterDialog.GetInstance().setMsg("nursingDate == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//		String serviceDate = nursingDate.getDateDescription();
		String serviceDate = m_nurseOrderConfirmPage.getServiceDate();
		m_serviceDateTV.setText(serviceDate);

		//服务地点
		int       hospitalID = m_apoitNursingPage.getHospitalID();
		DHospital hospital   = DHospitalList.GetInstance().getHospitalByID(hospitalID);
		if (hospital == null)
		{
			RegisterDialog.GetInstance().setMsg("hospital == null[hospitalID:=" + hospitalID + "]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		String hospitalName = hospital.getName();

		int         departmentID = m_apoitNursingPage.getDepartmenetID();
		DDepartment department   = DDepartmentList.GetInstance().getDepartmentByID(departmentID);
		if (department == null)
		{
			RegisterDialog.GetInstance().setMsg("department == null[departmentID:=" + departmentID + "]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		String departmentName = department.getName();
		String serviceAddress = hospitalName + departmentName;
		m_serviceAddressTV.setText(serviceAddress);

		//被护理人
		m_patientNameTV.setText(m_apoitNursingPage.getName());

		//被护理人状态
		EnumConfig.PatientState patientState = m_apoitNursingPage.getPatientState();
		if (patientState == null)
		{
			RegisterDialog.GetInstance().setMsg("patientState == null");
			RegisterDialog.GetInstance().show();
			return;
		}
		m_patientStateTV.setText(patientState.getName());

		//天数：全天24小时，白天12小时，黑天12小时
		int allNum   = m_nurseOrderConfirmPage.getAllNum();//nursingDate.getAllNum();
		int dayNum   = m_nurseOrderConfirmPage.getDayNum();//nursingDate.getDayNum();
		int nightNum = m_nurseOrderConfirmPage.getNightNum();//nursingDate.getNightNum();

		//单价：全天24小时，白天12小时，黑天12小时
		int chargePerAll   = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_ALL, patientState);
		int chargePerDay   = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_DAY, patientState);
		int chargePerNight = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_NIGHT, patientState);
		int totalCharge    = allNum * chargePerAll + dayNum * chargePerDay + nightNum * chargePerNight;

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

		m_TotalChargeTV.setText(String.valueOf(totalCharge));

	}

	private void updateContent()
	{
		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.CHANGE_NURSE)
		{
			int id = m_changeNurseRGrp.getCheckedRadioButtonId();
			if (id == R.id.new_nurse_rbtn)
			{
				updateContentByDefault();
			}
			else
			{
				updateContentByOldNurse();
			}
		}
		else
		{
			clearupDate();
			updateContentByDefault();
		}
	}

	private void updateCharge()
	{
		DNursingDate nursingDate = m_apoitNursingPage.getNursingDate();
		if (nursingDate == null)
		{
			RegisterDialog.GetInstance().setMsg("nursingDate == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		EnumConfig.PatientState patientState = m_apoitNursingPage.getPatientState();
		if (patientState == null)
		{
			RegisterDialog.GetInstance().setMsg("patientState == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		int          nurseID     = m_nurseOrderConfirmPage.getNurseID();
		DNurseBasics nurseBasics = null;


		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();

		//01. 来自于续订流程
		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.REPEAT_ORDER)
		{
			DNurseOrder nurseOrder = DNurserOrderList.GetInstance().getNurseOrderByOrderID(nurseID);
			if (nurseOrder == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseOrder == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}

			nurseBasics = nurseOrder.getNurseBasics();
			if (nurseBasics == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}
		}
		//03. 来自于预约陪护流程
		else
		{
			DNurseBasicsList nurseBasicsList = DNurseContainer.GetInstance().getNurseBasicsList();
			if (nurseBasicsList == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseBasicsList == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}

			nurseBasics = nurseBasicsList.getNurseBasicByID(nurseID);
			if (nurseBasics == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}
		}

		//天数：全天24小时，白天12小时，黑天12小时
		int allNum   = nursingDate.getAllNum();
		int dayNum   = nursingDate.getDayNum();
		int nightNum = nursingDate.getNightNum();

		//单价：全天24小时，白天12小时，黑天12小时
		int chargePerAll   = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_ALL, patientState);
		int chargePerDay   = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_DAY, patientState);
		int chargePerNight = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_NIGHT, patientState);

		//04. 订单金额
		//全天24小时
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
			m_nurseOrderConfirmPage.setAllNum(allNum);
			m_nurseOrderConfirmPage.setChargePerAll(chargePerAll);
		}
		else
		{
			m_allRegionLL.setVisibility(View.GONE);
		}

		//白天12小时
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
			m_nurseOrderConfirmPage.setDayNum(dayNum);
			m_nurseOrderConfirmPage.setChargePerDay(chargePerDay);
		}
		else
		{
			m_dayRegionLL.setVisibility(View.GONE);
		}

		//夜间12小时
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
			m_nurseOrderConfirmPage.setNightNum(nightNum);
			m_nurseOrderConfirmPage.setChargePerNight(chargePerNight);
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
		m_nurseOrderConfirmPage.setTotalCharge(totalCharge);

	}

	public Integer getSelectPatientStateTitleHight()
	{
		return m_selectPatientStateTitleHight;
	}

	public void setPatientState(EnumConfig.PatientState patientState)
	{
		if (m_apoitNursingPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_apoitNursingPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//01. update ui
		m_patientStateTV.setText(patientState.getName());
		//02. upate data
		//注意02和03顺序不要变动。依赖于setPatientState，然后getPatientState
		m_apoitNursingPage.setPatientState(patientState);
		//03. update charge
		updateCharge();
	}

	private void saveToDOrderConfirm(int nurseID)
	{
		//01. 有效性判断
		if (m_nurseOrderConfirmPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderConfirmPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		m_nurseOrderConfirmPage.setNurseID(nurseID);    //nurse ID

		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();

		int headerImgResID = DFaceImages.getInstance().getImgResIDbyID(nurseID);
		if (headerImgResID == DataConfig.DEFAULT_VALUE)
		{
			headerImgResID = DFaceImages.DEFAULT_IMAGE_RES_ID;
		}
		String nurseName    = null;
		String nursingLevel = null;
		String jobNum       = null;

		//服务时间
		String       serviceDate = null;
		DNursingDate nursingDate = m_apoitNursingPage.getNursingDate();
		if (nursingDate == null)
		{
			RegisterDialog.GetInstance().setMsg("nursingDate == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		serviceDate = nursingDate.getDateDescription();

		//服务地点
		String    serviceAddress = null;
		int       hospitalID     = m_apoitNursingPage.getHospitalID();
		DHospital hospital       = DHospitalList.GetInstance().getHospitalByID(hospitalID);
		if (hospital == null)
		{
			RegisterDialog.GetInstance().setMsg("hospital == null[hospitalID:=" + hospitalID + "]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		String hospitalName = hospital.getName();

		int         departmentID = m_apoitNursingPage.getDepartmenetID();
		DDepartment department   = DDepartmentList.GetInstance().getDepartmentByID(departmentID);
		if (department == null)
		{
			RegisterDialog.GetInstance().setMsg("department == null[departmentID:=" + departmentID + "]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		String departmentName = department.getName();
		serviceAddress = hospitalName + departmentName;

		//被护理人
		String patientName = m_apoitNursingPage.getName();

		//被护理人状态
		EnumConfig.PatientState patientState = m_apoitNursingPage.getPatientState();
		if (patientState == null)
		{
			RegisterDialog.GetInstance().setMsg("patientState == null");
			RegisterDialog.GetInstance().show();
			return;
		}


		//天数：全天24小时，白天12小时，黑天12小时
		int allNum   = nursingDate.getAllNum();
		int dayNum   = nursingDate.getDayNum();
		int nightNum = nursingDate.getNightNum();

		//单价：全天24小时，白天12小时，黑天12小时
		int chargePerAll   = 0;
		int chargePerDay   = 0;
		int chargePerNight = 0;

		//02. 来自于续订流程
		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.REPEAT_ORDER)
		{
			DNurseOrder nurseOrder = DNurserOrderList.GetInstance().getNurseOrderByNurseID(nurseID);
			if (nurseOrder == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseOrder == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}

			DNurseBasics nurseBasics = nurseOrder.getNurseBasics();
			if (nurseBasics == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}

			nurseName = nurseBasics.getName();
			nursingLevel = nurseBasics.getNursingLevel();


			DNurseSenior nurseSenior = nurseOrder.getNurseSenior();
			if (nurseSenior == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseSenior == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}
			jobNum = nurseSenior.getJobNum();

			chargePerAll = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_ALL, patientState);
			chargePerDay = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_DAY, patientState);
			chargePerNight = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_NIGHT, patientState);
		}
		//03. 来自于预约陪护流程,换护理员流程
		else
		{
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

			nurseName = nurseBasics.getName();
			nursingLevel = nurseBasics.getNursingLevel();
			jobNum = nurseSenior.getJobNum();

			chargePerAll = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_ALL, patientState);
			chargePerDay = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_DAY, patientState);
			chargePerNight = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_NIGHT, patientState);

		}

		m_nurseOrderConfirmPage.setNurseHeaderImgResID(headerImgResID);
		m_nurseOrderConfirmPage.setNurseName(nurseName);
		m_nurseOrderConfirmPage.setNursingLevel(nursingLevel);
		m_nurseOrderConfirmPage.setNurseJobNum(jobNum);
		m_nurseOrderConfirmPage.setServiceDate(serviceDate);
		m_nurseOrderConfirmPage.setServiceAddress(serviceAddress);
		m_patientNameTV.setText(patientName);
		m_patientStateTV.setText(patientState.getName());

		m_nurseOrderConfirmPage.setAllNum(allNum);
		m_nurseOrderConfirmPage.setDayNum(dayNum);
		m_nurseOrderConfirmPage.setNightNum(nightNum);
		m_nurseOrderConfirmPage.setChargePerAll(chargePerAll);
		m_nurseOrderConfirmPage.setChargePerDay(chargePerAll);
		m_nurseOrderConfirmPage.setChargePerNight(chargePerAll);

		//总价格
		int totalCharge = allNum * chargePerAll + dayNum * chargePerDay + nightNum * chargePerNight;
		m_nurseOrderConfirmPage.setTotalCharge(totalCharge);

	}


	private void setNurseName(String name)
	{
		//ui
		m_nameTV.setText(name);
		//data
		if (m_nurseOrderConfirmPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderConfirmPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_nurseOrderConfirmPage.setNurseName(name);
	}

	private void setNurseHeaderImgResID(int resID)
	{
		//ui
		m_nurseHeadImgIV.setImageResource(resID);
		//data
		if (m_nurseOrderConfirmPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderConfirmPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_nurseOrderConfirmPage.setNurseHeaderImgResID(resID);
	}

	private void setNurseJobNum(String jobNum)
	{
		//ui
		m_jobNumTV.setText(jobNum);
		//data
		if (m_nurseOrderConfirmPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderConfirmPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_nurseOrderConfirmPage.setNurseJobNum(jobNum);
	}

	private void setNursingLevel(String nursingLevel)
	{
		//ui
		m_nuringLevelTV.setText(nursingLevel);
		//data
		if (m_nurseOrderConfirmPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderConfirmPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_nurseOrderConfirmPage.setNursingLevel(nursingLevel);
	}

	private void setServiceDate(String serviceDate)
	{
		//ui
		m_serviceDateTV.setText(serviceDate);
		//data
		if (m_nurseOrderConfirmPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderConfirmPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_nurseOrderConfirmPage.setServiceDate(serviceDate);
	}

	private void setServiceAddress(String address)
	{
		//ui
		m_serviceAddressTV.setText(address);
		//data
		if (m_nurseOrderConfirmPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderConfirmPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_nurseOrderConfirmPage.setServiceAddress(address);
	}

	/**
	 * event bus handle
	 */
	//下订单成功，跳转到支付页面。
	public void onEventMainThread(FinishedNurseOrderConfirmEvent event)
	{
		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
		if (nursingModuleStatus == null)
		{
			Intent intent = new Intent(this, NurseOrderPayActivity.class);
			int nurserID = event.getNurseID();
			int orderID = event.getOrderID();
			String orderSerialNum = event.getOrderSerialNum();
			int totalPrice = event.getTotalPrice();
			intent.putExtra(NurseOrderConfig.NURSE_ID, nurserID);
			intent.putExtra(NurseOrderConfig.ORDER_ID, orderID);
			intent.putExtra(NurseOrderConfig.ORDER_SERIAL_NUM, orderSerialNum);
			intent.putExtra(NurseOrderConfig.ORDER_USER_PAY, totalPrice);

			startActivity(intent);
			return;
		}

		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.CHANGE_NURSE)
		{
			if (m_deltaPrice == 0)
			{
				RegisterDialog.GetInstance().setMsg("多余款项为0，您不需要支付。请保持手机畅通，服务人员会在1小时内和您联系。", this, new DialogInterface.OnClickListener()
													{
														@Override
														public void onClick(DialogInterface dialog, int which)
														{
															OrderConfirmActivity.this.finish();
															OrderConfirmActivity.this.startActivity(new Intent(OrderConfirmActivity.this,
																											   MainActivity.class));
														}
													}
												   );
				RegisterDialog.GetInstance().show();

			}
			else if (m_deltaPrice < 0)
			{
				RegisterDialog.GetInstance().setMsg("您的订单已经生成。多余款项，服务人员会在1小时内转到您的支付宝中，请注意查收。另外，请保持手机畅通。",
													this,
													new DialogInterface.OnClickListener()
													{
														@Override
														public void onClick(DialogInterface dialog, int which)
														{
															OrderConfirmActivity.this.finish();
															OrderConfirmActivity.this.startActivity(new Intent(OrderConfirmActivity.this,
																											   MainActivity.class
																									)
																								   );
														}
													}
												   );
				RegisterDialog.GetInstance().show();

				return;
			}
			else
			{


				Intent intent = new Intent(this, NurseOrderPayActivity.class);
				int nurserID = event.getNurseID();
				int orderID = event.getOrderID();
				String orderSerialNum = event.getOrderSerialNum();
				int totalPrice = event.getTotalPrice();
				intent.putExtra(NurseOrderConfig.NURSE_ID, nurserID);
				intent.putExtra(NurseOrderConfig.ORDER_ID, orderID);
				intent.putExtra(NurseOrderConfig.ORDER_SERIAL_NUM, orderSerialNum);
				intent.putExtra(NurseOrderConfig.ORDER_USER_PAY, totalPrice);

				startActivity(intent);
			}
			return;
		}

		Intent intent = new Intent(this, NurseOrderPayActivity.class);
		int nurserID = event.getNurseID();
		int orderID = event.getOrderID();
		String orderSerialNum = event.getOrderSerialNum();
		int totalPrice = event.getTotalPrice();
		intent.putExtra(NurseOrderConfig.NURSE_ID, nurserID);
		intent.putExtra(NurseOrderConfig.ORDER_ID, orderID);
		intent.putExtra(NurseOrderConfig.ORDER_SERIAL_NUM, orderSerialNum);
		intent.putExtra(NurseOrderConfig.ORDER_USER_PAY, totalPrice);

		startActivity(intent);

		return;
	}

	public void clearupDate()
	{
		m_beginDate = null;
		m_today = null;
		m_endDate = null;
	}

	public void selectBeginDateAction()
	{
		DNurseOrder nurseOrder = DNurserOrderList.GetInstance().getNurseOrderByNurseID(m_oldNurseID);
		if (nurseOrder == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrder == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		DNurseBasics nurseBasics = nurseOrder.getNurseBasics();
		if (nurseBasics == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		Calendar calendar = Calendar.getInstance();
		m_today = calendar.getTime();
		m_beginDate = nurseOrder.getBeginDate();
		m_endDate = nurseOrder.getEndDate();

		//打开选择日期框。
		SelectDateFragment  selectDateFragment = new SelectDateFragment();
		FragmentTransaction transaction        = getFragmentManager().beginTransaction();
		transaction.replace(R.id.order_confirm_page, selectDateFragment, selectDateFragment.getClass().getName());
		transaction.addToBackStack(null);
		transaction.commit();

		//		Intent intent = new Intent(this, SelectDateActivity.class);
		//		intent.putExtra(NurseOrderConfig.CHANGE_NURSE_BEGIN_DATE, beginDate);
		//		intent.putExtra(NurseOrderConfig.CHANGE_NURSE_TODAY, today);
		//		intent.putExtra(NurseOrderConfig.CHANGE_NURSE_END_DATE, endDate);
		//		intent.putExtra(NurseOrderConfig.CHANGE_NURSE_DATE_DESCRIPTION, endDate);
		//
		//		startActivity(intent);
	}

	public Date getEndDate()
	{
		return m_endDate;
	}

	public void setNewBeginDate(Date date)
	{
		m_newBeginDate = date;
	}

	public Date getToday()
	{
		return m_today;
	}

	public Date getBeginDate()
	{
		return m_beginDate;
	}

	public void confirmDateAction()
	{
		//01. 显示变更日期
		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
		if (nursingModuleStatus == null)
			return;

		if (nursingModuleStatus != EnumConfig.NursingModuleStatus.CHANGE_NURSE)
			return;

		if (m_newBeginDate == null)
			return;

		//01. 设置本地日期显示。
		String beginContent = m_simpleDateFormat.format(m_newBeginDate);
		String endContent   = m_simpleDateFormat.format(m_endDate);
		int    days         = LogicalUtil.GetDayNums(m_newBeginDate, m_endDate);
		if (days == 0)
		{
			days = 1;
		}
		String total   = getResources().getString(R.string.char_total);
		String day     = getResources().getString(R.string.char_day);
		String display = beginContent + " - " + endContent + total + days + day;

		m_selectBeginDateTV.setText(display);
		m_nurseOrderConfirmPage.setServiceDate(display);


		//02. 显示金额
		DNurseBasicsList nurseBasicsList = DNurseContainer.GetInstance().getNurseBasicsList();
		if (nurseBasicsList == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasicsList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		DNurseBasics nurseBasics = nurseBasicsList.getNurseBasicByID(m_newNurseID);
		if (nurseBasics == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		EnumConfig.PatientState patientState = m_apoitNursingPage.getPatientState();
		if (patientState == null)
		{
			RegisterDialog.GetInstance().setMsg("patientState == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		int chargeNewPerAll   = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_ALL, patientState);
		int chargeNewPerDay   = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_DAY, patientState);
		int chargeNewPerNight = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_NIGHT, patientState);


		DNurseOrder nurseOrder = DNurserOrderList.GetInstance().getNurseOrderByNurseID(m_oldNurseID);
		if (nurseOrder == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrder == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		nurseBasics = nurseOrder.getNurseBasics();
		if (nurseBasics == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		int chargeOldPerAll   = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_ALL, patientState);
		int chargeOldPerDay   = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_DAY, patientState);
		int chargeOldPerNight = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_NIGHT, patientState);


		DNursingDate nursingDate = m_apoitNursingPage.getNursingDate();
		if (nursingDate == null)
		{
			RegisterDialog.GetInstance().setMsg("nursingDate == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		ArrayList<Calendar> allCalendarList   = nursingDate.getAllCalendarList();
		ArrayList<Calendar> dayCalendarList   = nursingDate.getDayCalendarList();
		ArrayList<Calendar> nightCalendarList = nursingDate.getNightCalendarList();

		Calendar tmpCalendar = Calendar.getInstance();
		tmpCalendar.setTime(m_newBeginDate);
		int currMonth = tmpCalendar.get(Calendar.MONTH);
		int currDay   = tmpCalendar.get(Calendar.DAY_OF_MONTH);

		int iMonth      = 0;
		int iDay        = 0;
		int allNewNum   = 0;
		int dayNewNum   = 0;
		int nightNewNum = 0;

		int allOldNum   = 0;
		int dayOldNum   = 0;
		int nightOldNum = 0;

		for (Calendar allCalendar : allCalendarList)
		{
			iMonth = allCalendar.get(Calendar.MONTH);
			if (iMonth < currMonth)
			{
				allOldNum++;
				continue;
			}

			if (iMonth > currMonth)
			{
				allNewNum++;
				continue;
			}

			iDay = allCalendar.get(Calendar.DAY_OF_MONTH);

			if (iDay < currDay)
			{
				allOldNum++;
				continue;
			}

			allNewNum++;
			continue;
		}

		m_nurseOrderConfirmPage.setAllNum(allNewNum);

		for (Calendar dayCalendar : dayCalendarList)
		{
			iMonth = dayCalendar.get(Calendar.MONTH);
			if (iMonth < currMonth)
			{
				dayOldNum++;
				continue;
			}

			if (iMonth > currMonth)
			{
				dayNewNum++;
				continue;
			}

			iDay = dayCalendar.get(Calendar.DAY_OF_MONTH);

			if (iDay < currDay)
			{
				dayOldNum++;
				continue;
			}

			dayNewNum++;
			continue;
		}

		m_nurseOrderConfirmPage.setDayNum(dayNewNum);

		for (Calendar nightCalendar : nightCalendarList)
		{
			iMonth = nightCalendar.get(Calendar.MONTH);
			if (iMonth < currMonth)
			{
				nightOldNum++;
				continue;
			}

			if (iMonth > currMonth)
			{
				nightNewNum++;
				continue;
			}

			iDay = nightCalendar.get(Calendar.DAY_OF_MONTH);

			if (iDay < currDay)
			{
				nightOldNum++;
				continue;
			}

			nightNewNum++;
			continue;
		}

		m_nurseOrderConfirmPage.setNightNum(nightNewNum);

		updateContent();


	}


	public void confirmAction()
	{
		DApoitNursingPage apoitNursingPage = DNursingModule.GetInstance().getApoitNursingPage();
		if (apoitNursingPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_apoitNursingPage == null");
			RegisterDialog.GetInstance().show();
			return;
		}
		DNurseOrderConfirmPage nurseOrderConfirmPage = DNursingModule.GetInstance().getNurseOrderConfirmPage();
		if (nurseOrderConfirmPage == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrderConfirmPage == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.CHANGE_NURSE)
		{

			ReqNurseOrderConfirmForChangeNurse event = new ReqNurseOrderConfirmForChangeNurse();
			int nurseID = nurseOrderConfirmPage.getNurseID();
			event.setNurseID(String.valueOf(nurseID));
			String orderID = getOrderID();
			event.setOrderID(orderID);

			//当时日期
			String dateDescrption = m_simpleDateFormat.format(m_newBeginDate);
			event.setBeginDate(dateDescrption);
			m_eventBus.post(event);

		}
		else
		{

			//01. 发送nurse order confirm event
			ReqNurseOrderConfirmEvent event = new ReqNurseOrderConfirmEvent();
			int hospitalID = apoitNursingPage.getHospitalID();
			event.setHospitalID(hospitalID);

			String userID = DAccount.GetInstance().getId();
			event.setUserID(userID);

			int nurseID = nurseOrderConfirmPage.getNurseID();
			event.setNurseID(nurseID);

			int departmentID = apoitNursingPage.getDepartmenetID();
			event.setNurseDepartmentID(departmentID);

			String phoneNum = apoitNursingPage.getPhone();
			event.setPhoneNum(phoneNum);

			String name = apoitNursingPage.getName();
			event.setPatientName(name);

			EnumConfig.GenderStatus genderStatus = apoitNursingPage.getGenderStatus();
			if (genderStatus != null)
			{
				event.setGenderID(genderStatus.getId());
			}

			EnumConfig.AgeRage age = apoitNursingPage.getAgeRage();
			if (age != null)
			{
				event.setPatientAge(age.getName());
			}

			EnumConfig.WeightRage weight = apoitNursingPage.getWeightRage();
			if (weight != null)
			{
				event.setPatientWeight(weight.getName());
			}

			EnumConfig.PatientState patientState = apoitNursingPage.getPatientState();
			if (patientState != null)
			{
				event.setPatientStatusID(patientState.getId());
			}

			event.setPatientRemark("");

			int totalCharge = nurseOrderConfirmPage.getTotalCharge();
			event.setTotalCharge(totalCharge);

			DNursingDate nursingDate = apoitNursingPage.getNursingDate();
			if (nursingDate == null)
			{
				RegisterDialog.GetInstance().setMsg("nursingDate == null", this);
				RegisterDialog.GetInstance().show();
				return;
			}

			String allDescription = nursingDate.getSchedualAllDescription();
			event.setAllDescription(allDescription);

			String dayDescription = nursingDate.getSchedualDayDescription();
			event.setDayDescription(dayDescription);

			String nightDescription = nursingDate.getSchedualNightDescription();
			event.setNightDescription(nightDescription);

			m_eventBus.post(event);
		}
	}
}
