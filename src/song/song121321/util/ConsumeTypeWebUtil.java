package song.song121321.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import song.song121321.bean.dto.ConsumeTypeDto;
import song.song121321.config.MyConfig;

public class ConsumeTypeWebUtil extends WebUtil {
    public String descp = "";

    public ConsumeTypeWebUtil() {
    }

    public ConsumeTypeWebUtil(String descp) {
        this.descp = descp;
    }

    public String getContent() {
        return descp;
    }

    public void setContent(String descp) {
        this.descp = descp;
    }

    private String getConsumeTypeStr() throws Exception {
        String urlFront = MyConfig.API_ADDR + MyConfig.CONSUME_TYPE_FOR_APP;
        String para = "/list?typeName=" + descp;
        return GetStr(urlFront + para);
    }

    /**
     * @return
     */
    public List<ConsumeTypeDto> getConsumeType() {
        List<ConsumeTypeDto> resultList = new ArrayList<>();
        try {
            JSONObject dataJson = new JSONObject(getConsumeTypeStr());
            boolean ifSuccess = dataJson.getBoolean("head");
            if (ifSuccess) {
                JSONArray jsonArray = dataJson.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    ConsumeTypeDto consumeTypeDto = new ConsumeTypeDto();
                    JSONObject singleDataJson = new JSONObject(jsonArray.getString(i));
                    consumeTypeDto.setId(singleDataJson.getInt("id"));
                    consumeTypeDto.setDescp(singleDataJson.getString("descp"));
                    consumeTypeDto.setCtime(singleDataJson.getString("ctime"));
                    consumeTypeDto.setMtime(singleDataJson.getString("mtime"));
                    consumeTypeDto.setN1(singleDataJson.getInt("n1"));
                    consumeTypeDto.setN2(singleDataJson.getInt("n2"));
                    consumeTypeDto.setC1(singleDataJson.getString("c1"));
                    consumeTypeDto.setC2(singleDataJson.getString("c2"));
                    consumeTypeDto.setD1(singleDataJson.getString("d1"));
                    consumeTypeDto.setD2(singleDataJson.getString("d2"));
                    consumeTypeDto.setDetail(singleDataJson.getString("detail"));
                    consumeTypeDto.setIcon(singleDataJson.getString("icon"));
                    resultList.add(consumeTypeDto);
                }
            }
        } catch (Exception e) {

        }
        return resultList;
    }

}