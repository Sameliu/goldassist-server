package com.sutian.goldassist.core.schedule.wencai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.binding.StringFormatter;
import com.sutian.goldassist.common.http.HttpClientProxy;
import com.sutian.goldassist.common.model.Gold;
import com.sutian.goldassist.common.model.SpiderTask;
import com.sutian.goldassist.core.Spider;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author sutian
 * @email sijin.zsj@alibaba-inc.com
 * @create 2018/11/16 下午3:55
 * 接受策略问句，形成报告
 */
@Service
public class WenCaiSpiderJob implements Spider<SpiderTask,Gold>{

    private static List<String> questions = new ArrayList();

    private static String TOKEN_URL ="https://www.iwencai.com/data-robot/get-fusion-data";

    private static String GOLD_URL = "https://www.iwencai.com/stockpick/cache?token={0}&p=1&perpage=70&changeperpage=1";



    static {
        questions.add("创投概念股");
        questions.add("macd金叉");
    }

    public static void main(String[] args) {
        WenCaiSpiderJob  job = new WenCaiSpiderJob();
        job.spider();
    }
    public void spider(){
        Map<String,String> header = buildHeaderParams();
        Map<String,String> params = new HashMap<>();
        params.put("w","科创概念股有哪些");
        String rs = HttpClientProxy.doGet(TOKEN_URL,header,params,"utf-8",true);
        String token = JSON.parseObject(rs).getJSONObject("data").getJSONObject("wencai_data").getJSONObject("result").getString("token");
        String url = MessageFormat.format(GOLD_URL,token);
        Map<String,String> headerRes = buildResParams();
        String dataRs = HttpClientProxy.doGet(url,headerRes,null,"utf-8",true);
        JSONArray jsonArray = JSON.parseObject(dataRs).getJSONArray("result");
        for(int i=0;i<jsonArray.size();i++){
            JSONArray j = jsonArray.getJSONArray(i);
            for(int k =0 ;k <j.size();k++){
                System.out.println(j.getString(k));
            }


        }
        System.out.println(dataRs);


    }

    private Map<String, String> buildResParams() {
        Map<String,String> header = new HashMap<>();
        header = (Map) JSONObject.parse("{\"Accept\": \"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\",\n" +
                "\"Accept-Encoding\": \"gzip, deflate, br\",\n" +
                "\"Accept-Language\": \"zh-CN,zh;q=0.9\",\n" +
                "\"Cache-Control\": \"no-cache\",\n" +
                "\"Connection\": \"keep-alive\",\n" +
                "\"Cookie\": \"other_uid=Ths_iwencai_Xuangu_oydfix3fek1xy9ninxa77nf50e1wfvte; other_uname=latwm7te8r; cid=kjtmucutt0lde8a8teb9nhavq51508906604; ComputerID=kjtmucutt0lde8a8teb9nhavq51508906604; Hm_lvt_78c58f01938e4d85eaf619eae71b4ed1=1520582867; Hm_lvt_57a0e026e5e0e1e90bcea3f298e48e74=1520582867; PHPSESSID=4b472299ae25d8e7bb26a26c90fd2855; v=AuRV7b2ZZEH-opCNKCwe9hb0s-nVfQjnyqGcK_4FcK9yqYrfJo3YdxqxbLpN\",\n" +
                "\"Host\": \"www.iwencai.com\",\n" +
                "\"Pragma\": \"no-cache\",\n" +
                "\"Upgrade-Insecure-Requests\": \"1\",\n" +
                "\"User-Agent\": \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36\"}");
        return header;

    }

    private String getSpiderToken(String tokenUrl, Map<String, String> map) {
        return null;
    }

    private Map<String, String> buildHeaderParams() {
        Map<String,String> header = new HashMap<>();
        header = (Map) JSONObject.parse("{\"Accept\":\"application/json, text/javascript, */*; q=0.01\",\n" +
                "            \"Accept-Encoding\":\"gzip\",\n" +
                "            \"Accept-Language\":\"zh-CN,zh;q=0.9\",\n" +
                "            \"Connection\":\"keep-alive\",\n" +
                "            'User-Agent': \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36\",\n" +
                "           \"Cookie\":\"other_uid=Ths_iwencai_Xuangu_bw6sqz857g7fnn9j3acqtnj24z6ixgc0; other_uname=xeaql1ikwj; cid=ah9g6plt5rqqr7o2b43dqb02f71508420186; ComputerID=ah9g6plt5rqqr7o2b43dqb02f71508420186; OUTFOX_SEARCH_USER_ID_NCOO=196040945.3663267; PHPSESSID=341c556a23700b3791706bfa8532a1af; user=MDp6aGFuZ2hhbl8xOTg6Ok5vbmU6NTAwOjQ0MDA5OTMwMzo3LDExMTExMTExMTExLDQwOzQ0LDExLDQwOzYsMSw0MDs1LDEsNDA7MSwxLDQwOzIsMSw0MDszLDEsNDA7NSwxLDQwOzgsMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDEsNDA6Mzo6OjQzMDA5OTMwMzoxNTQxNjk3NzE2Ojo6MTUxNDI2MzUwMDo2MDQ4MDA6MDoxODQ1NTM4MGVmNWFlNGJkNjQ2MTNjOWQwNTA0NWMxZWY6ZGVmYXVsdF8yOjA%3D; userid=430099303; u_name=zhanghan_198; escapename=zhanghan_198; ticket=fafa788b37255569b25e6615b5898b9b; v=AgATDu3TTydZgjMjWjTqtBFF14XRieQ8hmw4V3qRz2Ku6q6zIpm049Z9COHJ\"}");

        return header;
    }

    @Override
    public List<SpiderTask> getSpiderTask() {

        return null;
    }

    @Override
    public String getSpiderResult(String url,Map<String,String> params) {
        //HttpClientProxy.doGet(url,params);
        return null;
    }

    @Override
    public List<Gold> parseSpiderResult() {
        return null;
    }

    @Override
    public void finishSpider() {

    }
}
