package song.song121321.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SightWebInfo {
    public String pid = "0";
    public String cid = "0";
    public String page = "1";

    public SightWebInfo(String pid, String cid, String page) {
        super();
        this.pid = pid;
        this.cid = cid;
        this.page = page;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSightInfo() throws Exception {
        String urlfront = "http://155.94.128.192:8080/xjk/mgr/0/consume-for-app/list?month=2018-11&content=&budgetId=0&consumeTypeId=0&bankId=0&page=1";
        String beforeencode = "";
//				"scenery?pid=" + pid + "&cid=" + cid + "&page="
//				+ page + "&key=" + Config.appkey;
        URL url;
        System.out.println("sos--- " + urlfront);
        url = new URL(urlfront + beforeencode);
        URLConnection connection = url.openConnection();
        InputStream urlStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(urlStream, "utf-8"));
        String ss = null;
        String loginStr = "";
        while ((ss = bufferedReader.readLine()) != null) {
            loginStr += ss;
            loginStr += "\n";
        }
        System.out.println("sos--- " + loginStr);
        return null;

    }

    /**
     * 将网络得到的sight列表信息转化为SightJasonBean对象
     *
     * @param Str
     * @return
     */
//	private SightJasonBean str2sjb(String Str) {
//		ArrayList<SightBean> list = new ArrayList<SightBean>();
//		JSONObject dataJson = JSONObject.fromObject(Str);
//		String error_code = dataJson.getString("error_code");
//		String reason = dataJson.getString("reason");
//		if (error_code.equals("0")) {
//			JSONArray jsonresultArray = dataJson.getJSONArray("result");
//
//			for (int i = 0; i < jsonresultArray.size(); i++) {
//
//				System.out.println("item" + i + ":"
//						+ jsonresultArray.getString(i));
//				JSONObject singledataJson = JSONObject
//						.fromObject(jsonresultArray.getString(i));
//
//				String title = singledataJson.getString("title");
//				String grade = singledataJson.getString("grade");
//				String price_min = singledataJson.getString("price_min");
//				String comm_cnt = singledataJson.getString("comm_cnt");
//				String cityid = singledataJson.getString("cityId");
//				String address = singledataJson.getString("address");
//				String sid = singledataJson.getString("sid");
//				String url = singledataJson.getString("url");
//				String imgurl = singledataJson.getString("imgurl");
//				SightBean s = new SightBean(title, grade, price_min, comm_cnt,
//						cityid, address, url, imgurl, sid);
//				list.add(s);
//			}
//		}
//		SightJasonBean sjb = new SightJasonBean(error_code, reason, list);
//		return sjb;
//	}

}