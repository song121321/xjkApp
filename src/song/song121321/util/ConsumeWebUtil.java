package song.song121321.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import song.song121321.bean.dto.ConsumeDto;
import song.song121321.bean.dto.ConsumeTypeDto;
import song.song121321.config.MyConfig;

public class ConsumeWebUtil extends WebUtil {
    public String month = "";
    public String budgetId = "0";
    public String content = "";
    public String consumeTypeId = "0";
    public String bankId = "0";
    public String page = "1";

    public ConsumeWebUtil(){}
    public ConsumeWebUtil(String month, String budgetId, String content, String consumeTypeId, String bankId, String page) {
        this.month = month;
        this.budgetId = budgetId;
        this.content = content;
        this.consumeTypeId = consumeTypeId;
        this.bankId = bankId;
        this.page = page;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConsumeTypeId() {
        return consumeTypeId;
    }

    public void setConsumeTypeId(String consumeTypeId) {
        this.consumeTypeId = consumeTypeId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    private String getConsumeStr() throws Exception {
        String urlFront = MyConfig.API_ADDR + MyConfig.CONSUME_FOR_APP;
        String para = "/list?month=" + month + "&content=" + content + "&budgetId=" + budgetId + "&consumeTypeId=" + consumeTypeId + "&bankId=" + bankId + "&page=" + page;
        return GetStr(urlFront + para);
    }

    /**
     *
     *
     * @return
     */
    public List<ConsumeDto> getConsume() {
        List<ConsumeDto> resultList = new ArrayList<>();
        try {
            JSONObject dataJson = new JSONObject(getConsumeStr());
            boolean ifSuccess = dataJson.getBoolean("head");
            if (ifSuccess) {
                JSONArray jsonArray = dataJson.getJSONArray("body");

                for (int i = 0; i < jsonArray.length(); i++) {
                    ConsumeDto consumeDto = new ConsumeDto();
                    JSONObject singleDataJson = new JSONObject(jsonArray.getString(i));
                    consumeDto.setId(singleDataJson.getInt("id"));
                    consumeDto.setDescp(singleDataJson.getString("descp"));
                    consumeDto.setCtime(singleDataJson.getString("ctime"));
                    consumeDto.setMtime(singleDataJson.getString("mtime"));
                    consumeDto.setN1(singleDataJson.getInt("n1"));
                    consumeDto.setN2(singleDataJson.getInt("n2"));
                    consumeDto.setC1(singleDataJson.getString("c1"));
                    consumeDto.setC2(singleDataJson.getString("c2"));
                    consumeDto.setD1(singleDataJson.getString("d1"));
                    consumeDto.setD2(singleDataJson.getString("d2"));
                    consumeDto.setDetail(singleDataJson.getString("detail"));
                    consumeDto.setBank_id(singleDataJson.getInt("bank_id"));
                    consumeDto.setBank_descp(singleDataJson.getString("bank_descp"));
                    consumeDto.setBudget_id(singleDataJson.getInt("budget_id"));
                    consumeDto.setBank_descp(singleDataJson.getString("budget_descp"));
                    consumeDto.setAccount_id(singleDataJson.getInt("account_id"));
                    consumeDto.setAccount_nickname(singleDataJson.getString("account_nickname"));
                    consumeDto.setCurrency_id(singleDataJson.getInt("currency_id"));
                    consumeDto.setCurrency_icontext(singleDataJson.getString("currency_icontext"));
                    consumeDto.setCurrency_icon(singleDataJson.getString("currency_icon"));
                    JSONObject consumeTypeJson = new JSONObject(singleDataJson.getString("consumetype"));
                    ConsumeTypeDto consumeTypeDto = new ConsumeTypeDto();
                    consumeTypeDto.setId(consumeTypeJson.getLong("id"));
                    consumeTypeDto.setDescp(consumeTypeJson.getString("descp"));
                    consumeTypeDto.setCtime(consumeTypeJson.getString("ctime"));
                    consumeTypeDto.setMtime(consumeTypeJson.getString("mtime"));
                    consumeTypeDto.setN1(consumeTypeJson.getInt("n1"));
                    consumeTypeDto.setN2(consumeTypeJson.getInt("n2"));
                    consumeTypeDto.setC1(consumeTypeJson.getString("c1"));
                    consumeTypeDto.setC2(consumeTypeJson.getString("c2"));
                    consumeTypeDto.setD1(consumeTypeJson.getString("d1"));
                    consumeTypeDto.setD2(consumeTypeJson.getString("d2"));
                    consumeTypeDto.setDetail(consumeTypeJson.getString("detail"));
                    consumeTypeDto.setIcon(consumeTypeJson.getString("icon"));
                    consumeDto.setConsumetype(consumeTypeDto);
                    consumeDto.setJe(singleDataJson.getDouble("je"));
                    consumeDto.setStatus(singleDataJson.getInt("status"));
                    resultList.add(consumeDto);
                }
            }
        } catch (Exception e) {

        }
        return resultList;
    }

}