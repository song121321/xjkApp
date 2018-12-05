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

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import song.song121321.R;
import song.song121321.adapter.ConsumeAdapter;
import song.song121321.adapter.SectionAdapter;
import song.song121321.app.MyApplication;
import song.song121321.bean.dto.ConsumeDto;
import song.song121321.util.ConsumeWebUtil;
import song.song121321.util.ListUtils;
import song.song121321.util.ListUtilsHook;
import song.song121321.util.StringUtil;
import song.song121321.xlistview.XListView;
import song.song121321.xlistview.XListView.IXListViewListener;

public class ConsumeActivity extends BaseActivity implements
        OnClickListener, OnItemClickListener, IXListViewListener {
    private XListView consunmeList;
    private ListView section_list;
    private LinearLayout ll_bar;
    private TextView tvTitle, tvBudget, tvAssert, tvConsumeType;
    private ImageView ivBudget, ivAssert, ivConsumeType;
    private List<ConsumeDto> consumeBeans = new ArrayList<ConsumeDto>();
    private ArrayList<String> consumeTypeOptions = new ArrayList<String>();
    private ArrayList<String> assertOptions = new ArrayList<String>();
    private ArrayList<String> budgetOptions = new ArrayList<String>();
    private SectionAdapter secAdapter;
    private String currentMonth, currentBudget, currentAssert, currentConsumeType;
    private PopupWindow mPopWin;
    private int sectionindex;
    private int page = 1;
    private FloatingActionButton fabAdd, fabSearch, fabPre, fabNext;

    @Override
    protected void onCreate(Bundle savedInstanceAssert) {
        super.onCreate(savedInstanceAssert);
        setContentView(R.layout.activity_consume);
        MyApplication.getInstance().addActivity(this);
        findViewById();
        initView();
        initPopupWindow();
    }

    private void findViewById() {
        consunmeList = (XListView) findViewById(R.id.xl_consume_list);
        tvTitle = (TextView) findViewById(R.id.tv_actionbar_usual_title);
        tvBudget = (TextView) findViewById(R.id.tv_consume_section_budget);
        tvAssert = (TextView) findViewById(R.id.tv_consume_section_assert);
        tvConsumeType = (TextView) findViewById(R.id.tv_consume_section_consume_type);
        ivBudget = (ImageView) findViewById(R.id.iv_consume_mark_budget);
        ivAssert = (ImageView) findViewById(R.id.iv_consume_mart_assert);
        ivConsumeType = (ImageView) findViewById(R.id.iv_consume_mark_consume_type);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_consume_add);
        fabSearch = (FloatingActionButton) findViewById(R.id.fab_consume_search);
        fabPre = (FloatingActionButton) findViewById(R.id.fab_consume_pre);
        fabNext = (FloatingActionButton) findViewById(R.id.fab_consume_next);
    }

    private void initView() {
        consunmeList.setPullLoadEnable(true);
        consunmeList.setPullRefreshEnable(true);
        consunmeList.setXListViewListener(this);
        consunmeList.setOnItemClickListener(this);
        currentBudget = "";
        currentAssert = "";
        currentConsumeType = "";
        sectionindex = 1;
        currentMonth = StringUtil.getCurrentMonthStr();
        initActionBar();
        refresh();
    }


    private void setAdapter(List<ConsumeDto> consumeList) {

        //  consunmeList.removeAllViews();
        ConsumeAdapter consumeAdapter = new ConsumeAdapter(ConsumeActivity.this, consumeList);
        consunmeList.setAdapter(consumeAdapter);
        closeLoadingDialog();
        double totalJe = 0.0;
        for (ConsumeDto consumeDto : consumeList) {
            totalJe += consumeDto.getJe();
        }
        showShortToast("共计消费：" + consumeList.size() + "笔，金额：" + String.format("%.2f", totalJe));

    }

    private void initActionBar() {
        tvTitle.setText(getResources().getString(R.string.consume_list));
        fabAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ConsumeActivity.this,
                        ConsumeAddActivity.class);
                startActivity(intent);

            }
        });
        fabSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                showShortToast("search");
            }
        });
        fabPre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                consumeBeans = new ArrayList<ConsumeDto>();
                currentMonth = StringUtil.getPreMonthStr(currentMonth);
                page = 1;
                refresh();
            }
        });
        fabNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                consumeBeans = new ArrayList<ConsumeDto>();
                currentMonth = StringUtil.getNextMonthStr(currentMonth);
                page = 1;
                refresh();
            }
        });

//        llSearch.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
////                Intent intent = new Intent(DeviceWellActivity.this,
////                        BaijiaConsumeSearchActivity.class);
////                startActivity(intent);
//            }
//        });
    }

    private void refresh() {
        showLoadingDialog();
        new loadingConsumeTask().execute();

    }

    private void refreshLocal() {
        showLoadingDialog();
        List<ConsumeDto> consumeDtoCopy = new ArrayList<>(consumeBeans);
        if (!StringUtil.isEmpty(currentBudget)) {
            consumeDtoCopy = ListUtils.filter(consumeDtoCopy, new ListUtilsHook<ConsumeDto>() {
                @Override
                public boolean keep(ConsumeDto wb) {
                    return wb.getBudget_descp().equals(currentBudget.trim());
                }
            });
        }
        if (!StringUtil.isEmpty(currentAssert)) {
            consumeDtoCopy = ListUtils.filter(consumeDtoCopy, new ListUtilsHook<ConsumeDto>() {
                @Override
                public boolean keep(ConsumeDto wb) {
                    return wb.getBank_descp().equals(currentAssert.trim());
                }
            });
        }
        if (!StringUtil.isEmpty(currentConsumeType)) {
            consumeDtoCopy = ListUtils.filter(consumeDtoCopy, new ListUtilsHook<ConsumeDto>() {
                @Override
                public boolean keep(ConsumeDto wb) {
                    return wb.getConsumetype().getDescp().trim().equals(currentConsumeType.trim());
                }
            });
        }
        setAdapter(consumeDtoCopy);

    }


    private void initPopupWindow() {
        tvBudget.setOnClickListener(this);
        tvAssert.setOnClickListener(this);
        tvConsumeType.setOnClickListener(this);
    }

    private void initOptions() {
        Set<String> budgetSet = new HashSet<>();
        Set<String> assertSet = new HashSet<>();
        Set<String> cTypeSet = new HashSet<>();
        budgetSet.add(getResources().getString(R.string.budget));
        assertSet.add(getResources().getString(R.string.cassert));
        cTypeSet.add(getResources().getString(R.string.ctype));
        for (ConsumeDto consumeDto : consumeBeans) {
            budgetSet.add(consumeDto.getBudget_descp());
            assertSet.add(consumeDto.getBank_descp());
            cTypeSet.add(consumeDto.getConsumetype().getDescp());
        }
        budgetOptions = new ArrayList<>(budgetSet);
        assertOptions = new ArrayList<>(assertSet);
        consumeTypeOptions = new ArrayList<>(cTypeSet);
    }


    class loadingConsumeTask extends AsyncTask<Object, Object, List<ConsumeDto>> {

        protected List<ConsumeDto> doInBackground(Object... params) {
            try {
                ConsumeWebUtil cwu = new ConsumeWebUtil();
                cwu.setMonth(currentMonth);
                cwu.setPage("" + page);
                return cwu.getConsume();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(List<ConsumeDto> result) {
            consumeBeans.addAll(result);
            refreshLocal();
            initOptions();
        }

    }


    protected void selectSecCheck(int index) {
        tvBudget.setTextColor(getResources().getColor(R.color.main_textcolor_normal));
        tvAssert.setTextColor(getResources().getColor(R.color.main_textcolor_normal));
        tvConsumeType.setTextColor(getResources().getColor(R.color.main_textcolor_normal));
        ivBudget.setImageResource(R.drawable.section_bg_normal);
        ivAssert.setImageResource(R.drawable.section_bg_normal);
        ivConsumeType.setImageResource(R.drawable.section_bg_normal);
        switch (index) {
            case 1:
                tvBudget.setTextColor(getResources().getColor(R.color.main_textcolor_select));
                ivBudget.setImageResource(R.drawable.section_bg_selected);
                break;
            case 2:
                tvAssert.setTextColor(getResources().getColor(R.color.main_textcolor_select));
                ivAssert.setImageResource(R.drawable.section_bg_selected);
                break;
            case 3:
                tvConsumeType.setTextColor(getResources().getColor(R.color.main_textcolor_select));
                ivConsumeType.setImageResource(R.drawable.section_bg_selected);
                break;
        }
    }

    private void showSectionPop(int width, int height, final int secindex) {
        selectSecCheck(sectionindex);
        ll_bar = (LinearLayout) LayoutInflater.from(ConsumeActivity.this)
                .inflate(R.layout.popup_category, null);
        section_list = (ListView) ll_bar
                .findViewById(R.id.lv_popwin_section_list);
        section_list.setOnItemClickListener(this);
        switch (secindex) {
            case 1:
                secAdapter = new SectionAdapter(ConsumeActivity.this,
                        budgetOptions, tvBudget.getText().toString());
                break;
            case 2:
                secAdapter = new SectionAdapter(ConsumeActivity.this,
                        assertOptions, tvAssert.getText().toString());
                break;
            case 3:
                secAdapter = new SectionAdapter(ConsumeActivity.this,
                        consumeTypeOptions, tvConsumeType.getText().toString());

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
        mPopWin.showAsDropDown(tvBudget, 0, 0);
        mPopWin.update();
    }

    @Override
    public void onLoadMore() {
        page++;
        refresh();
        consunmeList.stopLoadMore();
    }

    @Override
    public void onRefresh() {
        page = 1;
        consumeBeans = new ArrayList<>();
        refresh();
        consunmeList.stopRefresh();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_consume_section_budget:
                sectionindex = 1;
                showSectionPop(consunmeList.getWidth(), consunmeList.getHeight(), 1);
                break;

            case R.id.tv_consume_section_assert:
                //	new TypeTask(currentBudget).execute();
                sectionindex = 2;
                showSectionPop(consunmeList.getWidth(), consunmeList.getHeight(), 2);
                break;
            case R.id.tv_consume_section_consume_type:
                sectionindex = 3;
                showSectionPop(consunmeList.getWidth(), consunmeList.getHeight(), 3);
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View arg1, int position,
                            long arg3) {
        if (R.id.xl_consume_list == parent.getId()) {
            Intent intent = new Intent();
            intent.setClass(ConsumeActivity.this,
                    ConsumeEditActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("singleConsume", consumeBeans.get(position - 1));
            intent.putExtras(bundle);
            this.startActivity(intent);
            return;
        }

        switch (sectionindex) {
            case 1:
                secAdapter = new SectionAdapter(ConsumeActivity.this,
                        budgetOptions, budgetOptions.get(position));

                tvBudget.setText(budgetOptions.get(position));
                currentBudget = getResources().getString(R.string.budget).trim().equals(budgetOptions.get(position)) ? "" : budgetOptions.get(position);
                refreshLocal();
                break;
            case 2:

                secAdapter = new SectionAdapter(ConsumeActivity.this,
                        assertOptions, assertOptions.get(position));
                tvAssert.setText(assertOptions.get(position));
                currentAssert = getResources().getString(R.string.cassert).trim().equals(assertOptions.get(position)) ? "" : assertOptions.get(position);
                mPopWin.dismiss();
                refreshLocal();
                break;
            case 3:

                secAdapter = new SectionAdapter(ConsumeActivity.this,
                        consumeTypeOptions, consumeTypeOptions.get(position));
                tvConsumeType.setText(consumeTypeOptions.get(position));
                currentConsumeType = getResources().getString(R.string.ctype).trim().equals(consumeTypeOptions.get(position)) ? "" : consumeTypeOptions.get(position);
                refreshLocal();
                break;
        }
        section_list.setAdapter(secAdapter);
        mPopWin.dismiss();

    }
}
