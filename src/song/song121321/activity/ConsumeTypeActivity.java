package song.song121321.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import song.song121321.R;
import song.song121321.adapter.ConsumeTypeAdapter;
import song.song121321.app.MyApplication;
import song.song121321.bean.dto.ConsumeTypeDto;
import song.song121321.ui.DragLayout;
import song.song121321.util.ConsumeTypeWebUtil;
import song.song121321.xlistview.XListView;
import song.song121321.xlistview.XListView.IXListViewListener;

public class ConsumeTypeActivity extends BaseActivity implements
        OnClickListener, OnItemClickListener, IXListViewListener {
    /** 左边侧滑菜单 */
    private DragLayout mDragLayout;
    private ListView menuListView;// 菜单列表
    private XListView consunmeList;
    private TextView tvTitle;
    private List<ConsumeTypeDto> consumeTypeBeans = new ArrayList<ConsumeTypeDto>();

    @Override
    protected void onCreate(Bundle savedInstanceAssert) {
        super.onCreate(savedInstanceAssert);
        setContentView(R.layout.activity_consume_type);
        MyApplication.getInstance().addActivity(this);
        findViewById();
        initView();
    }

    private void findViewById() {
        mDragLayout = (DragLayout) findViewById(R.id.dl_consume);
        menuListView = (ListView) findViewById(R.id.ll_left_menu);
        consunmeList = (XListView) findViewById(R.id.xl_consume_type_list);
        tvTitle = (TextView) findViewById(R.id.tv_actionbar_usual_title);
    }

    private void initView() {
        consunmeList.setPullLoadEnable(true);
        consunmeList.setPullRefreshEnable(true);
        consunmeList.setXListViewListener(this);
        consunmeList.setOnItemClickListener(this);
        initActionBar();
        refresh();
    }


    private void setAdapter(List<ConsumeTypeDto> consumeList) {
        ConsumeTypeAdapter consumeTypeAdapter = new ConsumeTypeAdapter(ConsumeTypeActivity.this, consumeList);
        consunmeList.setAdapter(consumeTypeAdapter);
        closeLoadingDialog();
    }

    private void initActionBar() {
        tvTitle.setText(getResources().getString(R.string.consume_list));
        mDragLayout.setDragListener(new DragLayout.DragListener() {// 动作监听
            @Override
            public void onOpen() {
            }

            @Override
            public void onClose() {
            }

            @Override
            public void onDrag(float percent) {

            }
        });
        List<Map<String, Object>> leftMenuData = new ArrayList<>();
        Map<String, Object> item = new HashMap<>();
        item.put("item", "消费");
        leftMenuData.add(item);
        Map<String, Object> item1 = new HashMap<>();
        item1.put("item", "预算");
        leftMenuData.add(item1);
        Map<String, Object> item2 = new HashMap<>();
        item2.put("item", "类型");
        leftMenuData.add(item2);
        menuListView.setAdapter(new SimpleAdapter(this, leftMenuData,
                R.layout.item_left_menu, new String[] { "item" },
                new int[] { R.id.menu_text }));
        menuListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == 0) {
                    Intent intent = new Intent(ConsumeTypeActivity.this,
                            ConsumeActivity.class);
                    startActivity(intent);
                    ConsumeTypeActivity.this.finish();
                } else {
                    mDragLayout.close();
                }
            }
        });
    }

    private void refresh() {
        showLoadingDialog();
        new ConsumeTypeTask().execute();

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
            setAdapter(result);
        }
    }

    @Override
    public void onLoadMore() {
        consunmeList.stopLoadMore();
    }

    @Override
    public void onRefresh() {
        refresh();
        consunmeList.stopRefresh();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View arg1, int position,
                            long arg3) {
        if (R.id.xl_consume_type_list == parent.getId()) {
            Intent intent = new Intent();
            intent.setClass(ConsumeTypeActivity.this,
                    ConsumeEditActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("singleConsume", consumeTypeBeans.get(position - 1));
            intent.putExtras(bundle);
            this.startActivity(intent);
            return;
        }

    }
}
