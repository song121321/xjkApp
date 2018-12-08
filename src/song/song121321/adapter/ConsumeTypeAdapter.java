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
import song.song121321.bean.dto.ConsumeTypeDto;
import song.song121321.bean.dto.ConsumeTypeDto;

public class ConsumeTypeAdapter extends BaseAdapter {

    private Context context;
    private List<ConsumeTypeDto> consumeTypeList;

    public ConsumeTypeAdapter(Context context, List<ConsumeTypeDto> consumeTypeList) {
        this.context = context;
        this.consumeTypeList = consumeTypeList;
    }


    @Override
    public int getCount() {
        return consumeTypeList.size();
    }

    @Override
    public Object getItem(int position) {
        return consumeTypeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(
                R.layout.item_consume_type, null);
        TextView desc = (TextView) convertView
                .findViewById(R.id.tv_item_consume_type_desc);
        ImageView ctype = (ImageView) convertView
                .findViewById(R.id.iv_item_consume_type_ico);

        ConsumeTypeDto consumeTypeDto = consumeTypeList.get(position);

        desc.setText(consumeTypeDto.getDescp());
        String bese64 = consumeTypeDto.getIcon();
        bese64 = bese64.substring(bese64.indexOf(",") + 1);
        byte[] decodedString = Base64.decode(bese64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ctype.setImageBitmap(decodedByte);
        return convertView;
    }
}
