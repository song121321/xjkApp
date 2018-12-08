package song.song121321.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import song.song121321.R;
import song.song121321.adapter.BudgetAdapter;
import song.song121321.adapter.SectionAdapter;
import song.song121321.app.MyApplication;
import song.song121321.bean.dto.BudgetDto;
import song.song121321.util.BudgetWebUtil;
import song.song121321.util.ListUtils;
import song.song121321.util.ListUtilsHook;
import song.song121321.util.StringUtil;
import song.song121321.util.ToolBox;
import song.song121321.xlistview.XListView;
import song.song121321.xlistview.XListView.IXListViewListener;

public class BudgetActivity extends BaseActivity implements
        OnClickListener, OnItemClickListener, IXListViewListener {
    private XListView budgetList;
    private ListView section_list;
    private LinearLayout  ll_bar;
    ;
    private TextView tvTitle, tvMonth, tvState, tvSort;
    private ImageView ivMonth, ivState, ivSort;
    private List<BudgetDto> budgets = new ArrayList<BudgetDto>();
    private ArrayList<String> sortOptions = new ArrayList<String>();
    private ArrayList<String> stateOptions = new ArrayList<String>();
    private ArrayList<String> monthOptions = new ArrayList<String>();
    private SectionAdapter secAdapter;
    private String currentMonth, currentSort;
    private int currentState;
    private PopupWindow mPopWin;
    private int sectionindex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        MyApplication.getInstance().addActivity(this);
        findViewById();
        initView();
        initPopupWindow();
    }

    private void findViewById() {
        budgetList = (XListView) findViewById(R.id.xl_budget_list);
        tvTitle = (TextView) findViewById(R.id.tv_actionbar_usual_title);
        tvMonth = (TextView) findViewById(R.id.tv_budget_section_month);
        tvState = (TextView) findViewById(R.id.tv_budget_section_state);
        tvSort = (TextView) findViewById(R.id.tv_budget_section_sort);
        ivMonth = (ImageView) findViewById(R.id.iv_budget_mark_month);
        ivState = (ImageView) findViewById(R.id.iv_budget_mart_state);
        ivSort = (ImageView) findViewById(R.id.iv_budget_mark_sort);
    }

    private void initView() {
        budgetList.setPullLoadEnable(true);
        budgetList.setPullRefreshEnable(true);
        budgetList.setXListViewListener(this);
        budgetList.setOnItemClickListener(this);
        currentMonth = StringUtil.getCurrentMonthStr();
        tvMonth.setText(currentMonth);
        currentState = -2;
        currentSort = "";
        sectionindex = 1;
        initActionBar();
        refresh();
    }


    private void setAdapter(List<BudgetDto> wellBeanList) {

        BudgetAdapter budgetAdapter = new BudgetAdapter(BudgetActivity.this, wellBeanList);
        budgetList.setAdapter(budgetAdapter);
        closeLoadingDialog();
        showShortToast(wellBeanList.size() + " devices load successfully!");

    }

    private void initActionBar() {
        tvTitle.setText(getResources().getString(R.string.device_main_well));
    }

    private void refresh() {
        showLoadingDialog();
        new BudgetTask().execute();
    }

    private void refreshLocal() {
        showLoadingDialog();
        List<BudgetDto> budgetsCopy = new ArrayList<>(budgets);
//        if (StringUtil.isEmpty(currentMonth)) {
//            budgetsCopy = ListUtils.filter(budgetsCopy, new ListUtilsHook<BudgetDto>() {
//                @Override
//                public boolean keep(BudgetDto wb) {
//                    return wb.getLocation().equals(currentMonth.trim());
//                }
//            });
//        }

        if (currentState != -2) {
            budgetsCopy = ListUtils.filter(budgetsCopy, new ListUtilsHook<BudgetDto>() {
                @Override
                public boolean keep(BudgetDto wb) {
                    return wb.getUsedState() == currentState;
                }
            });
        }

//        if (StringUtil.isEmpty(currentSort)) {
//
//            Collections.sort(budgetsCopy, new Comparator<BudgetDto>() {
//
//                @Override
//                public int compare(BudgetDto o1, BudgetDto o2) {
//                    return o1.getState() - o2.getState();
//                }
//
//            });
//
//        }

        setAdapter(budgetsCopy);

    }


    private void initPopupWindow() {
        stateOptions = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.status_options)));
        sortOptions = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.sort_options)));
        monthOptions = ToolBox.generateMonthList("2014-09-01");
        tvMonth.setOnClickListener(this);
        tvState.setOnClickListener(this);
        tvSort.setOnClickListener(this);
    }


    private class BudgetTask extends AsyncTask<Void, Void, List<BudgetDto>> {

        @Override
        protected List<BudgetDto> doInBackground(Void... voids) {
            try {
                BudgetWebUtil cwu = new BudgetWebUtil();
                cwu.setMonth(currentMonth);
                return cwu.getBudget();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<BudgetDto> result) {
            closeLoadingDialog();
            setAdapter(result);
        }
    }


    protected void selectSecCheck(int index) {
        tvMonth.setTextColor(getResources().getColor(R.color.main_textcolor_normal));
        tvState.setTextColor(getResources().getColor(R.color.main_textcolor_normal));
        tvSort.setTextColor(getResources().getColor(R.color.main_textcolor_normal));
        ivMonth.setImageResource(R.drawable.section_bg_normal);
        ivState.setImageResource(R.drawable.section_bg_normal);
        ivSort.setImageResource(R.drawable.section_bg_normal);
        switch (index) {
            case 1:
                tvMonth.setTextColor(getResources().getColor(R.color.main_textcolor_select));
                ivMonth.setImageResource(R.drawable.section_bg_selected);
                break;
            case 2:
                tvState.setTextColor(getResources().getColor(R.color.main_textcolor_select));
                ivState.setImageResource(R.drawable.section_bg_selected);
                break;
            case 3:
                tvSort.setTextColor(getResources().getColor(R.color.main_textcolor_select));
                ivSort.setImageResource(R.drawable.section_bg_selected);
                break;
        }
    }

    private void showSectionPop(int width, int height, final int secindex) {
        selectSecCheck(sectionindex);
        ll_bar = (LinearLayout) LayoutInflater.from(BudgetActivity.this)
                .inflate(R.layout.popup_category, null);
        section_list = (ListView) ll_bar
                .findViewById(R.id.lv_popwin_section_list);
        section_list.setOnItemClickListener(this);
        switch (secindex) {
            case 1:
                secAdapter = new SectionAdapter(BudgetActivity.this,
                        monthOptions, tvMonth.getText().toString());
                break;
            case 2:
                secAdapter = new SectionAdapter(BudgetActivity.this,
                        stateOptions, tvState.getText().toString());
                break;
            case 3:
                secAdapter = new SectionAdapter(BudgetActivity.this,
                        sortOptions, tvSort.getText().toString());

                break;

        }
        section_list.setAdapter(secAdapter);
        mPopWin = new PopupWindow(ll_bar, width, LayoutParams.WRAP_CONTENT,
                true);
        mPopWin.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                // selectSecCheck(4);
            }
        });
        mPopWin.setBackgroundDrawable(new BitmapDrawable());
        mPopWin.showAsDropDown(tvMonth, 0, 0);
        mPopWin.update();
    }

    @Override
    public void onLoadMore() {
        showShortToast(getResources().getString(R.string.xlistview_footer_hint_normal));
        budgetList.stopLoadMore();
    }

    @Override
    public void onRefresh() {
        refresh();
        budgetList.stopRefresh();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_budget_section_month:
                sectionindex = 1;
                showSectionPop(budgetList.getWidth(), budgetList.getHeight(), 1);
                break;

            case R.id.tv_budget_section_state:
                //	new TypeTask(currentMonth).execute();
                sectionindex = 2;
                showSectionPop(budgetList.getWidth(), budgetList.getHeight(), 2);
                break;
            case R.id.tv_budget_section_sort:
                sectionindex = 3;
                showSectionPop(budgetList.getWidth(), budgetList.getHeight(), 3);
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View arg1, int position,
                            long arg3) {
        if (R.id.xl_budget_list == parent.getId()) {
            Intent intent = new Intent();
            intent.setClass(BudgetActivity.this,
                    DeviceWellDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("wellBean", budgets.get(position - 1));
            intent.putExtras(bundle);
            this.startActivity(intent);
            return;
        }

        switch (sectionindex) {
            case 1:
                secAdapter = new SectionAdapter(BudgetActivity.this,
                        monthOptions, monthOptions.get(position));

                tvMonth.setText(monthOptions.get(position));
                currentMonth = monthOptions.get(position);
                refresh();
                break;
            case 2:

                secAdapter = new SectionAdapter(BudgetActivity.this,
                        stateOptions, stateOptions.get(position));
                tvState.setText(stateOptions.get(position));
                currentState = ToolBox.getStateFromSelect(stateOptions.get(position));
                mPopWin.dismiss();
                refreshLocal();
                break;
            case 3:

                secAdapter = new SectionAdapter(BudgetActivity.this,
                        sortOptions, sortOptions.get(position));
                tvSort.setText(sortOptions.get(position));
                currentSort = ToolBox.getSortFromSelect(sortOptions.get(position));
                refreshLocal();
                break;
        }
        section_list.setAdapter(secAdapter);
        mPopWin.dismiss();

    }
}
