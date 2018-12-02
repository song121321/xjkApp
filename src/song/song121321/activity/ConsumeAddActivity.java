package song.song121321.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import song.song121321.R;
import song.song121321.app.MyApplication;

public class ConsumeAddActivity extends BaseActivity implements
		OnClickListener {
	EditText etDesc, etJe, etDetail;
	RelativeLayout rlAssert, rlBudget, rlCtype;
	TextView tvAssertTemp, tvBudgetTemp, tvCtypeTemp, tvActionbarCancel,
			tvActionbarSure, tvActionbarTitle;
	String desc, cassert, budget, money, ctype, detail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consume_add);
		MyApplication.getInstance().addActivity(ConsumeAddActivity.this);
		findView();
		initView();
		setListener();
	}

	private void findView() {
		etDesc = (EditText) findViewById(R.id.et_consume_add_desc);
		etJe = (EditText) findViewById(R.id.et_consume_add_je);
		etDetail = (EditText) findViewById(R.id.et_consume_add_detail);
		rlAssert = (RelativeLayout) findViewById(R.id.rl_consume_add_assert);
		rlBudget = (RelativeLayout) findViewById(R.id.rl_consume_add_budget);
		rlCtype = (RelativeLayout) findViewById(R.id.rl_consume_add_ctype);
		tvAssertTemp = (TextView) findViewById(R.id.tv_consume_add_assert_temp);
		tvBudgetTemp = (TextView) findViewById(R.id.tv_consume_add_budget_temp);
		tvCtypeTemp = (TextView) findViewById(R.id.tv_consume_add_ctype_temp);

		tvActionbarCancel = (TextView) findViewById(R.id.tv_actionbar_usual_cancel);
		tvActionbarSure = (TextView) findViewById(R.id.tv_actionbar_usual_sure);
		tvActionbarTitle = (TextView) findViewById(R.id.tv_actionbar_usual_title);

	}

	private void initView() {
		desc = "";
		money = "0.00";
		ctype = "";
		budget = "";
		cassert = "";
		detail = "";
		tvActionbarTitle.setText(getString(R.string.consume_add_add_consume));
	}

	private void setListener() {
		tvActionbarCancel.setOnClickListener(this);
		tvActionbarSure.setOnClickListener(this);
		rlAssert.setOnClickListener(this);
		rlCtype.setOnClickListener(this);
		rlBudget.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.tv_actionbar_usual_cancel:
			MyApplication.getInstance().removeActivity(
					ConsumeAddActivity.this);
			finish();
			break;
		case R.id.tv_actionbar_usual_sure:
			addConsume();
			break;
		case R.id.rl_consume_add_assert:
			//showTimeDialog();
			break;
		case R.id.rl_consume_add_budget:
			//showBudgetDialog();
			break;
		case R.id.rl_consume_add_ctype:
			//showPersonDialog();
			break;

		default:
			break;
		}
	}




	private void addConsume() {

		if (etDesc.getText().toString().equals("")
				|| etJe.getText().toString().equals("")) {
			// 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
			AlertDialog.Builder builder = new AlertDialog.Builder(
					ConsumeAddActivity.this);
			// 设置Title的图标
			builder.setIcon(android.R.drawable.ic_dialog_info);
			// 设置Title的内容
			builder.setTitle("提示");
			// 设置Content来显示一个信息
			builder.setMessage("内容和金额不能为空");
			// 设置一个PositiveButton
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			// 显示出该对话框
			builder.show();
		} else {


		}

	}


//
//	private void showBudgetDialog() {
//		new TypeTask().execute();
//	}
//
//	private class TypeTask extends AsyncTask<Void, Void, ArrayList<String>> {
//
//		@Override
//		protected ArrayList<String> doInBackground(Void... arg0) {
//			HashMap<String, String> params = new HashMap<String, String>();
//			params.put("ctype", ToolBox.getcurrentmonth());
//			String nameSpace = MyConfig.nameSpace;
//			String methodName = MyConfig.methodName_GetBudget;
//			String endPoint = MyConfig.endPoint;
//			return WebServiceUtil.getArrayOfAnyType(nameSpace, methodName,
//					endPoint, params);
//		}
//
//		@Override
//		protected void onPostExecute(ArrayList<String> result) {
//			super.onPostExecute(result);
//			result.remove(0);
//			budgetlist = new String[result.size()];
//			result.toArray(budgetlist);
//			new AlertDialog.Builder(BaijiaConsumeAddActivity.this)
//					.setTitle("选择预算")
//					.setIcon(android.R.drawable.ic_dialog_info)
//					.setSingleChoiceItems(budgetlist, 0,
//							new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog,
//										int which) {
//									budget = budgetlist[which];
//									tvBudgetTemp.setText(budget);
//									dialog.dismiss();
//								}
//							})
//					.setNegativeButton(getString(R.string.system_cancel), null)
//					.show();
//			closeLoadingDialog();
//		}
//
//	}
//
//	private class AddConsumeTask extends
//			AsyncTask<Void, Void, ArrayList<String>> {
//		String desc, cassert, budget, money, ctype, detail;
//
//		public AddConsumeTask(String desc, String cassert, String budget,
//				String money, String ctype, String detail) {
//			super();
//			this.desc = desc;
//			this.cassert = cassert;
//			this.budget = budget;
//			this.money = money;
//			this.ctype = ctype;
//			this.detail = detail;
//		}
//
//		@Override
//		protected ArrayList<String> doInBackground(Void... arg0) {
//
//			HashMap<String, String> params = new HashMap<String, String>();
//			params.put("desc", desc);
//			params.put("cassert", cassert);
//			params.put("Budget", budget);
//			params.put("omoney", money);
//			params.put("ctype", ctype);
//			params.put("detail", detail);
//			String nameSpace = MyConfig.nameSpace;
//			String methodName = MyConfig.methodName_AddConsume;
//			String endPoint = MyConfig.endPoint;
//			return WebServiceUtil.getArrayOfAnyType(nameSpace, methodName,
//					endPoint, params);
//		}
//
//		@Override
//		protected void onPostExecute(ArrayList<String> result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			Boolean res = false;
//			if (result.size() == 1) {
//				if (result.get(0).trim().equals("success")) {
//					res = true;
//				}
//			}
//			closeLoadingDialog();
//			if (res) {
//				Toast.makeText(BaijiaConsumeAddActivity.this, "添加成功",
//						Toast.LENGTH_LONG).show();
//				MyApplication.getInstance().removeActivity(
//						BaijiaConsumeAddActivity.this);
//				finish();
//			} else {
//				Toast.makeText(BaijiaConsumeAddActivity.this, "哎呀，失败了，好好查查",
//						Toast.LENGTH_LONG).show();
//
//			}
//
//		}
//	}


}
