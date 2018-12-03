package song.song121321.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import song.song121321.bean.dto.BudgetDto;
import song.song121321.config.MyConfig;

public class BudgetWebUtil extends WebUtil {
    public String month = "";
    public String descp = "";

    public BudgetWebUtil() {
    }

    public BudgetWebUtil(String month, String descp) {
        this.month = month;
        this.descp = descp;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


    public String getContent() {
        return descp;
    }

    public void setContent(String descp) {
        this.descp = descp;
    }

    private String getBudgetStr() throws Exception {
        String urlFront = MyConfig.API_ADDR + MyConfig.BUDGET_FOR_APP;
        String para = "/list?month=" + month + "&descp=" + descp;
        return GetStr(urlFront + para);
    }

    /**
     * @return
     */
    public List<BudgetDto> getBudget() {
        List<BudgetDto> resultList = new ArrayList<>();
        try {
            JSONObject dataJson = new JSONObject(getBudgetStr());
            boolean ifSuccess = dataJson.getBoolean("head");
            if (ifSuccess) {
                JSONArray jsonArray = dataJson.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    BudgetDto budgetDto = new BudgetDto();
                    JSONObject singleDataJson = new JSONObject(jsonArray.getString(i));
                    budgetDto.setId(singleDataJson.getInt("id"));
                    budgetDto.setDescp(singleDataJson.getString("descp"));
                    budgetDto.setCtime(singleDataJson.getString("ctime"));
                    budgetDto.setMtime(singleDataJson.getString("mtime"));
                    budgetDto.setN1(singleDataJson.getInt("n1"));
                    budgetDto.setN2(singleDataJson.getInt("n2"));
                    budgetDto.setC1(singleDataJson.getString("c1"));
                    budgetDto.setC2(singleDataJson.getString("c2"));
                    budgetDto.setD1(singleDataJson.getString("d1"));
                    budgetDto.setD2(singleDataJson.getString("d2"));
                    budgetDto.setDetail(singleDataJson.getString("detail"));
                    budgetDto.setLeftje(singleDataJson.getDouble("leftje"));
                    budgetDto.setJe(singleDataJson.getDouble("je"));
                    budgetDto.setUseJe(singleDataJson.getDouble("useJe"));
                    budgetDto.setYear(singleDataJson.getInt("year"));
                    budgetDto.setMonth(singleDataJson.getInt("month"));
                    budgetDto.setStatus(singleDataJson.getInt("status"));
                    budgetDto.setConsumeNumber(singleDataJson.getInt("consumeNumber"));
                    resultList.add(budgetDto);
                }
            }
        } catch (Exception e) {

        }
        return resultList;
    }

}