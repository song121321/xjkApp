package song.song121321.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.List;

import song.song121321.R;
import song.song121321.app.MyApplication;
import song.song121321.bean.dto.BudgetDto;
import song.song121321.util.BudgetWebUtil;

public class ConsumeAddActivity extends BaseActivity implements
        OnClickListener {
    EditText etDesc, etJe, etDetail;
    RelativeLayout rlAssert, rlBudget, rlCtype;
    TextView tvAssertTemp, tvBudgetTemp, tvCtypeTemp, tvActionbarCancel,
            tvActionbarSure, tvActionbarTitle;
    String desc, cassert, budget, money, ctype, detail;
    private MaterialDialog.Builder mBuilder;
    private MaterialDialog mMaterialDialog;

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
                showBudgetDialog();
                break;
            case R.id.rl_consume_add_ctype:
                //showPersonDialog();
                break;

            default:
                break;
        }
    }

    private void showBudgetDialog() {
        new BudgetTask().execute();
    }


    private void addConsume() {

        new AddConsumeTask().execute();

    }

    public static String httpPostWithJSON(String para)
            throws Exception {
        String url = "http://155.94.128.192:8080/xjk/mgr/0/consume-for-app/save?data=";
        para =
                "{\"descp\":\"试试\",\"bankid\":\"11\",\"budgetid\":\"1386\",\"consumetypeid\":\"1\",\"accountid\":\"1\",\"currencyid\":1,\"je\":\"1\",\"status\":0,\"n1\":2,\"n2\":3,\"c1\":\"ss\",\"c2\":\"dd\",\"detail\":\"2333\"}";
        para = URLEncoder.encode(para, "gbk");
        url += para;
        HttpPost httpPost = new HttpPost(url);
        System.out.println(url);
        DefaultHttpClient client = new DefaultHttpClient();
        String respContent = null;
        StringEntity s = new StringEntity(para, "utf-8");
        //  StringEntity s = new StringEntity(para, Charset.forName("UTF-8"));
        httpPost.setEntity(s);
        httpPost.setHeader("Content-Type",
                "application/application/json; charset=UTF-8");
        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        }
        return respContent;
    }

    private class BudgetTask extends AsyncTask<Void, Void, List<BudgetDto>> {

        @Override
        protected List<BudgetDto> doInBackground(Void... voids) {
            try {
                BudgetWebUtil cwu = new BudgetWebUtil();
                cwu.setMonth("2018-12");
                return cwu.getBudget();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<BudgetDto> result) {
            String[] budgetArray = new String[result.size()];
            for (int i = 0; i < result.size(); i++) {
                BudgetDto budgetDto = result.get(i);
                budgetArray[i] = budgetDto.getDescp() + " ￥" + budgetDto.getLeftje();
            }
            mBuilder = new MaterialDialog.Builder(ConsumeAddActivity.this);
            mBuilder.title("选择预算");
            mBuilder.titleGravity(GravityEnum.CENTER);
            mBuilder.items(budgetArray);
            mBuilder.theme(Theme.LIGHT);
            mBuilder.autoDismiss(true);
            mBuilder.itemsCallback(new MaterialDialog.ListCallback() {
                @Override
                public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                    Toast.makeText(ConsumeAddActivity.this, result.get(position).getDescp() + result.get(position).getId(), Toast.LENGTH_SHORT).show();
                    tvBudgetTemp.setText(text);
                }
            });
            mMaterialDialog = mBuilder.build();
            mMaterialDialog.show();
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
    private class AddConsumeTask extends
            AsyncTask<Void, Void, String> {

        public AddConsumeTask() {
            super();
        }

        @Override
        protected String doInBackground(Void... arg0) {

            try {
                httpPostWithJSON("");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {


        }
    }


}
