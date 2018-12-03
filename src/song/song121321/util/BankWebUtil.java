package song.song121321.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import song.song121321.bean.dto.AccountListDto;
import song.song121321.bean.dto.BankDto;
import song.song121321.config.MyConfig;

public class BankWebUtil extends WebUtil {
    public String descp = "";

    public BankWebUtil() {
    }

    public BankWebUtil(String descp) {
        this.descp = descp;
    }

    public String getContent() {
        return descp;
    }

    public void setContent(String descp) {
        this.descp = descp;
    }

    private String getBankStr() throws Exception {
        String urlFront = MyConfig.API_ADDR + MyConfig.BANK_FOR_APP;
        String para = "/list?zhName=" + descp;
        return GetStr(urlFront + para);
    }

    /**
     * @return
     */
    public List<BankDto> getBank() {
        List<BankDto> resultList = new ArrayList<>();
        try {
            JSONObject dataJson = new JSONObject(getBankStr());
            boolean ifSuccess = dataJson.getBoolean("head");
            if (ifSuccess) {
                JSONArray jsonArray = dataJson.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    BankDto bankDto = new BankDto();
                    JSONObject singleDataJson = new JSONObject(jsonArray.getString(i));
                    bankDto.setId(singleDataJson.getInt("id"));
                    bankDto.setDescp(singleDataJson.getString("descp"));
                    bankDto.setCtime(singleDataJson.getString("ctime"));
                    bankDto.setMtime(singleDataJson.getString("mtime"));
                    bankDto.setN1(singleDataJson.getInt("n1"));
                    bankDto.setN2(singleDataJson.getInt("n2"));
                    bankDto.setC1(singleDataJson.getString("c1"));
                    bankDto.setC2(singleDataJson.getString("c2"));
                    bankDto.setD1(singleDataJson.getString("d1"));
                    bankDto.setD2(singleDataJson.getString("d2"));
                    bankDto.setDetail(singleDataJson.getString("detail"));
                    bankDto.setAccounttype(singleDataJson.getInt("accounttype"));
                    bankDto.setJe(singleDataJson.getDouble("je"));
                    bankDto.setFromBankId(singleDataJson.getInt("fromBankId"));
                    bankDto.setFromBankName(singleDataJson.getString("fromBankName"));
                    bankDto.setStatus(singleDataJson.getInt("status"));

                    JSONObject accountListDtoJson = new JSONObject(singleDataJson.getString("account"));
                    AccountListDto accountListDto = new AccountListDto();
                    accountListDto.setId(accountListDtoJson.getLong("id"));
                    accountListDto.setName(accountListDtoJson.getString("name"));
                    accountListDto.setNickname(accountListDtoJson.getString("nickname"));
                    accountListDto.setCreator(accountListDtoJson.getString("creator"));
                    accountListDto.setInitAccount(accountListDtoJson.getBoolean("initAccount"));
                    bankDto.setAccount(accountListDto);
                    resultList.add(bankDto);
                }
            }
        } catch (Exception e) {

        }
        return resultList;
    }

}