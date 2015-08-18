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
import com.taixinkanghu.app.model.data.DApoitNursing;
import com.taixinkanghu.app.model.data.DDepartment;
import com.taixinkanghu.app.model.data.DDepartmentList;
import com.taixinkanghu.app.model.data.DFaceImages;
import com.taixinkanghu.app.model.data.DHospital;
import com.taixinkanghu.app.model.data.DHospitalList;
import com.taixinkanghu.app.model.data.DNurseBasics;
import com.taixinkanghu.app.model.data.DNurseBasicsList;
import com.taixinkanghu.app.model.data.DNurseContainer;
import com.taixinkanghu.app.model.data.DNurseSenior;
import com.taixinkanghu.app.model.data.DNurseSeniorList;
import com.taixinkanghu.app.model.event.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.widget.circleimageview.CircleImageView;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

public class OrderConfirmActivity extends Activity
{
	//widget
	private HeaderCommon m_headerCommon = null;

	//订单内容
	private CircleImageView m_circleImageView      = null;    //头像
	private TextView        m_nameTV               = null;        //姓名
	private TextView        m_nuringLevelTV        = null;    //护理级别
	private TextView        m_jobNumTV             = null;        //工号
	private TextView        m_serviceDateTV        = null;    //服务时间
	private TextView        m_serviceAddressTV     = null;    //服务地点
	private LinearLayout    m_patientStateRegionLL = null;    //被护理人点击区域
	private TextView        m_patientNameTV        = null;    //被护理人姓名
	private LinearLayout    m_patientRegionLL      = null;    //被护理人状态点击区域
	private TextView        m_patientStateTV       = null;    //被护理人状态
	private ImageView       m_patientStateIV       = null;    //被护理人状态的image

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

	//logical
	private HandlerClickEventNursOrderConfirm m_handlerClickEventNursOrderConfirm = null;
	private int m_nurseID     = -1;    //当前护工的ID
	private int m_totalCharge = 0;    //总价格

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_order);

		init();
		initListener();
		initContent();
		initMoney();
	}

	private void init()
	{
		//widget
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();
		m_headerCommon.setTitle(R.string.determine_order_title);

		//订单内容
		m_circleImageView = (CircleImageView)findViewById(R.id.header_img_civ);
		m_nameTV = (TextView)findViewById(R.id.name_tv);
		m_nuringLevelTV = (TextView)findViewById(R.id.nuring_level_tv);
		m_jobNumTV = (TextView)findViewById(R.id.job_num_tv);
		m_serviceDateTV = (TextView)findViewById(R.id.service_date_tv);
		m_serviceAddressTV = (TextView)findViewById(R.id.service_address_tv);
		m_patientRegionLL = (LinearLayout)findViewById(R.id.patient_region_ll);
		m_patientNameTV = (TextView)findViewById(R.id.patient_name_tv);
		m_patientStateRegionLL = (LinearLayout)findViewById(R.id.patient_state_region_ll);
		m_patientStateTV = (TextView)findViewById(R.id.patient_state_tv);
		m_patientStateIV = (ImageView)findViewById(R.id.patient_state_img);
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

		m_handlerClickEventNursOrderConfirm = new HandlerClickEventNursOrderConfirm(this);
	}

	private void initListener()
	{
		m_patientRegionLL.setOnClickListener(m_handlerClickEventNursOrderConfirm);
		m_patientStateRegionLL.setOnClickListener(m_handlerClickEventNursOrderConfirm);

		m_userProtcolTV.setOnClickListener(m_handlerClickEventNursOrderConfirm);
		m_confirmAppointmentBtn.setOnClickListener(m_handlerClickEventNursOrderConfirm);
	}

	private void initContent()
	{
		//01. 有效性判断
		//0101. intent id 有效
		Intent intent = getIntent();
		if (intent == null)
		{
			RegisterDialog.GetInstance().setMsg("intent == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		m_nurseID = intent.getIntExtra(NurseBasicListConfig.ID, -1);
		if (m_nurseID == -1)
		{
			RegisterDialog.GetInstance().setMsg("id is invalid", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//0102. 通过ID可以找到DNurseBasics
		DNurseBasicsList nurseBasicsList = DNurseContainer.GetInstance().getNurseBasicsList();
		if (nurseBasicsList == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasicsList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		DNurseBasics nurseBasics = nurseBasicsList.getNurseBasicByID(m_nurseID);
		if (nurseBasics == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//0103. 通过ID可以找到DNurseSenior
		DNurseSeniorList nurseSeniorList = DNurseContainer.GetInstance().getNurseSeniorList();
		if (nurseSeniorList == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseSeniorList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		DNurseSenior nurseSenior = nurseSeniorList.getNurseSeniorByID(m_nurseID);
		if (nurseSenior == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseSeniorList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. set ui
		//订单内容
		int iImageID = DFaceImages.getInstance().getImageIDbyIndex(0);
		m_circleImageView.setImageResource(iImageID);
		m_nameTV.setText(nurseBasics.getName());
		m_nuringLevelTV.setText(nurseBasics.getNursingLevel());
		m_jobNumTV.setText(String.valueOf(nurseBasics.getID()));

		DApoitNursing.DNursingDate nursingDate = DApoitNursing.GetInstance().getNursingDate();
		if (nursingDate == null)
		{
			RegisterDialog.GetInstance().setMsg("nursingDate == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		String serviceDate = nursingDate.getDateDescription();
		m_serviceDateTV.setText(serviceDate);

		int       hospitalID = DApoitNursing.GetInstance().getHospitalID();
		DHospital hospital   = DHospitalList.GetInstance().getHospitalByID(hospitalID);
		if (hospital == null)
		{
			RegisterDialog.GetInstance().setMsg("hospital == null[hospitalID:=" + hospitalID + "]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		String hospitalName = hospital.getName();

		int         departmentID = DApoitNursing.GetInstance().getDepartmenetID();
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

		String patientName = DApoitNursing.GetInstance().getName();
		m_patientNameTV.setText(patientName);

		EnumConfig.PatientState patientState = DApoitNursing.GetInstance().getPatientState();
		if (patientState == null)
		{
			RegisterDialog.GetInstance().setMsg("patientState == null]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		String patientStateName = patientState.getName();
		m_patientStateTV.setText(patientStateName);

	}

	private void initMoney()
	{
		//01. PatientState
		EnumConfig.PatientState patientState = DApoitNursing.GetInstance().getPatientState();
		if (patientState == null)
		{
			RegisterDialog.GetInstance().setMsg("patientState == null]", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. DNursingDate
		DApoitNursing.DNursingDate nursingDate = DApoitNursing.GetInstance().getNursingDate();
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

		DNurseBasics nurseBasics = nurseBasicsList.getNurseBasicByID(m_nurseID);
		if (nurseBasics == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//04. 订单金额
		String unitDay  = getResources().getString(R.string.content_day);
		String unitYuan = getResources().getString(R.string.content_day);

		//全天24小时
		int allNum       = nursingDate.getAllNum();
		int chargePerAll = 0;
		if (allNum > 0)
		{
			String strAllNum = String.valueOf(allNum);
			chargePerAll = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_ALL, patientState);
			String strChargetPerAll = String.valueOf(chargePerAll);

			m_allNumTV.setText(strAllNum + unitDay);
			m_chargePerAllTV.setText(strChargetPerAll + unitYuan);
			m_allCoeffTV.setText(strChargetPerAll);
			m_allRegionLL.setVisibility(View.VISIBLE);
		}

		//白天12小时
		int dayNum       = nursingDate.getDayNum();
		int chargePerDay = 0;
		if (dayNum > 0)
		{
			String strDayNum = String.valueOf(dayNum);
			chargePerDay = nurseBasics.getServiceCharge(DataConfig.SELECT_DAY_TYEP_DAY, patientState);
			String strChargePerDay = String.valueOf(chargePerDay);

			m_dayNumTV.setText(strDayNum + unitDay);
			m_chargePerDayTV.setText(strChargePerDay + unitYuan);
			m_dayCoeffTV.setText(strChargePerDay);
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

			m_nightNumTV.setText(strNightNum + unitDay);
			m_chargePerNightTV.setText(strChargePerNight + unitYuan);
			m_NightCoeffTV.setText(strChargePerNight);
			m_nightRegionLL.setVisibility(View.VISIBLE);
		}

		//总价格
		m_totalCharge    = allNum * chargePerAll + dayNum * chargePerDay + nightNum * chargePerNight;
		String strTotalCharge = String.valueOf(m_totalCharge);
		m_TotalChargeTV.setText(strTotalCharge);
	}

	public int getNurseID()
	{
		return m_nurseID;
	}

	public int getTotalCharge()
	{
		return m_totalCharge;
	}

	public void setPatientState(EnumConfig.PatientState patientState)
	{
		//01. update ui
		m_patientStateTV.setText(patientState.getName());
		//02. upate data
		//注意02和03顺序不要变动。依赖于setPatientState，然后getPatientState
		DApoitNursing.GetInstance().setPatientState(patientState);
		//03. update charge
		initMoney();
	}
}
