package song.song121321.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import song.song121321.R;
import song.song121321.bean.dto.BudgetDto;

public class BudgetAdapter extends BaseAdapter {

    private Context context;
    private List<BudgetDto> budgetDtoList;

    public BudgetAdapter(Context context, List<BudgetDto> budgetDtoList) {
        this.context = context;
        this.budgetDtoList = budgetDtoList;
    }


    @Override
    public int getCount() {
        return budgetDtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return budgetDtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(
                R.layout.item_budget, null);
        TextView name = (TextView) convertView
                .findViewById(R.id.tv_item_budget_desc);
        TextView updateTime = (TextView) convertView
                .findViewById(R.id.tv_item_budget_mtime);
        ImageView state = (ImageView) convertView
                .findViewById(R.id.iv_item_budget_state);

        BudgetDto budgetDto = budgetDtoList.get(position);

        name.setText(budgetDto.getDescp());
        updateTime.setText(budgetDto.getMtime());

        if (budgetDto.getUsedState() == 1) {
            state.setImageResource(R.drawable.green_dot);
        } else if (budgetDto.getUsedState() == 0) {
            state.setImageResource(R.drawable.blue_dot);
        } else {
            state.setImageResource(R.drawable.red_dot);
        }
        return convertView;
    }
}
