package song.song121321.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.List;

import song.song121321.R;
import song.song121321.app.MyApplication;
import song.song121321.bean.dto.BankDto;
import song.song121321.bean.dto.BudgetDto;
import song.song121321.bean.dto.ConsumeTypeDto;
import song.song121321.util.BankWebUtil;
import song.song121321.util.BudgetWebUtil;
import song.song121321.util.ConsumeTypeWebUtil;
import song.song121321.util.ConsumeWebUtil;
import song.song121321.util.StringUtil;

public class ConsumeAddActivity extends BaseActivity implements
        OnClickListener {
    EditText etDesc, etJe, etDetail;
    RelativeLayout rlAssert, rlBudget, rlCtype;
    TextView tvAssertTemp, tvBudgetTemp, tvCtypeTemp, tvActionbarCancel,
            tvActionbarSure, tvActionbarTitle;
    String desc, cassert, budget, money, ctype, detail;
    private MaterialDialog.Builder mBuilder;
    private MaterialDialog mMaterialDialog;
    private BudgetDto selectedBudget;
    private BankDto selectedAssert;
    private ConsumeTypeDto selectedConsumeType;


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
                showLoadingDialog();
                new AssertTask().execute();
                break;
            case R.id.rl_consume_add_budget:
                showLoadingDialog();
                new BudgetTask().execute();
                break;
            case R.id.rl_consume_add_ctype:
                showLoadingDialog();
                new ConsumeTypeTask().execute();
                break;

            default:
                break;
        }
    }

    private void showBudgetDialog(final List<BudgetDto> result) {
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
                selectedBudget = result.get(position);
                tvBudgetTemp.setText(selectedBudget.getDescp());
            }
        });
        mMaterialDialog = mBuilder.build();
        mMaterialDialog.show();
    }


    private void showAssertDialog(final List<BankDto> result) {
        String[] bankArray = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            BankDto bankDto = result.get(i);
            bankArray[i] = bankDto.getDescp() + " ￥" + bankDto.getJe();
        }
        mBuilder = new MaterialDialog.Builder(ConsumeAddActivity.this);
        mBuilder.title("选择付款账户");
        mBuilder.titleGravity(GravityEnum.CENTER);
        mBuilder.items(bankArray);
        mBuilder.theme(Theme.LIGHT);
        mBuilder.autoDismiss(true);
        mBuilder.itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                selectedAssert = result.get(position);
                tvAssertTemp.setText(selectedAssert.getDescp());
            }
        });
        mMaterialDialog = mBuilder.build();
        mMaterialDialog.show();
    }

    private void showConsumeTypeDialog(final List<ConsumeTypeDto> result) {
        String[] cTypeArray = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ConsumeTypeDto cTypeDto = result.get(i);
            cTypeArray[i] = cTypeDto.getDescp();
        }
        mBuilder = new MaterialDialog.Builder(ConsumeAddActivity.this);
        mBuilder.title("选择消费类型");
        mBuilder.titleGravity(GravityEnum.CENTER);
        mBuilder.items(cTypeArray);
        mBuilder.theme(Theme.LIGHT);
        mBuilder.autoDismiss(true);
        mBuilder.itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                selectedConsumeType = result.get(position);
                tvCtypeTemp.setText(selectedConsumeType.getDescp());
            }
        });
        mMaterialDialog = mBuilder.build();
        mMaterialDialog.show();
    }


    private void addConsume() {

        if (null != selectedAssert && null != selectedBudget && null != selectedConsumeType && StringUtil.notEmpty(etDesc.getText().toString()) && StringUtil.notEmpty(etJe.getText().toString()) && StringUtil.notEmpty(etDetail.getText().toString())) {
            showLoadingDialog();
            new AddConsumeTask().execute();
        }else{
            showLongToast("必填项请填写完整。");
        }

    }

    private JSONObject createConsumeBean() {
        JSONObject consumeBean = new JSONObject();
        try {
            consumeBean.put("descp", etDesc.getText().toString());
            consumeBean.put("bankid", selectedAssert.getId());
            consumeBean.put("accountid", 4);
            consumeBean.put("currencyid", 1);
            consumeBean.put("budgetid", selectedBudget.getId());
            consumeBean.put("je", etJe.getText().toString());
            consumeBean.put("status", 0);
            consumeBean.put("consumetypeid", selectedConsumeType.getId());
            consumeBean.put("detail", etDetail.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return consumeBean;

    }


    private class BudgetTask extends AsyncTask<Void, Void, List<BudgetDto>> {

        @Override
        protected List<BudgetDto> doInBackground(Void... voids) {
            try {
                BudgetWebUtil cwu = new BudgetWebUtil();
                cwu.setMonth(StringUtil.getCurrentMonthStr());
                return cwu.getBudget();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<BudgetDto> result) {
            closeLoadingDialog();
            showBudgetDialog(result);
        }
    }

    private class AssertTask extends AsyncTask<Void, Void, List<BankDto>> {

        @Override
        protected List<BankDto> doInBackground(Void... voids) {
            try {
                BankWebUtil cwu = new BankWebUtil();
                return cwu.getBank();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<BankDto> result) {
            closeLoadingDialog();
            showAssertDialog(result);
        }
    }

    private class ConsumeTypeTask extends AsyncTask<Void, Void, List<ConsumeTypeDto>> {

        @Override
        protected List<ConsumeTypeDto> doInBackground(Void... voids) {
            try {
                List<ConsumeTypeDto> resultList = LitePal.findAll(ConsumeTypeDto.class);
                if(null!=resultList&&resultList.size()>0){
                    return resultList;
                }
                ConsumeTypeWebUtil cwu = new ConsumeTypeWebUtil();
                return cwu.getConsumeType();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<ConsumeTypeDto> result) {
            for(ConsumeTypeDto consumeTypeDto:result){
                consumeTypeDto.save();
            }
            closeLoadingDialog();
            showConsumeTypeDialog(result);
        }
    }

    private class AddConsumeTask extends
            AsyncTask<Void, Void, String> {

        public AddConsumeTask() {
            super();
        }

        @Override
        protected String doInBackground(Void... arg0) {
            try {
                return ConsumeWebUtil.addConsume(createConsumeBean().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            boolean ifSuccess = false;
            try {
                JSONObject dataJson = new JSONObject(result);
                ifSuccess = dataJson.getBoolean("head");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            closeLoadingDialog();
            if (ifSuccess) {
                showLongToast("添加成功！");
                MyApplication.getInstance().removeActivity(
                        ConsumeAddActivity.this);
                finish();
            }
        }
    }


}
