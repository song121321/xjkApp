package song.song121321.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import song.song121321.R;
import song.song121321.bean.dto.ConsumeDto;

public class ConsumeAdapter extends BaseAdapter {

    private Context context;
    private List<ConsumeDto> consumeList;

    public ConsumeAdapter(Context context, List<ConsumeDto> consumeList) {
        this.context = context;
        this.consumeList = consumeList;
    }


    @Override
    public int getCount() {
        return consumeList.size();
    }

    @Override
    public Object getItem(int position) {
        return consumeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(
                R.layout.item_consume, null);
        TextView desc = (TextView) convertView
                .findViewById(R.id.tv_item_consume_desc);
        TextView money = (TextView) convertView
                .findViewById(R.id.tv_item_consume_money);
        TextView updateTime = (TextView) convertView
                .findViewById(R.id.tv_item_consume_time);
        ImageView ctype = (ImageView) convertView
                .findViewById(R.id.iv_item_consume_type);

        ConsumeDto consume = consumeList.get(position);

        desc.setText(consume.getDescp());
        money.setText(consume.getCurrency_icontext()+consume.getJe()+"");
        updateTime.setText(consume.getCtime());
        String bese64 = consume.getConsumetype().getIcon();
        bese64 = bese64.substring(bese64.indexOf(",")+1);
        byte[] decodedString = Base64.decode(bese64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ctype.setImageBitmap(decodedByte);

        return convertView;
    }
}
