package com.sutian.goldassist.core.schedule.wencai;

import com.sutian.goldassist.common.http.HttpClientProxy;
import com.sutian.goldassist.common.model.Gold;
import com.sutian.goldassist.common.model.SpiderTask;
import com.sutian.goldassist.core.Spider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private static String GOLD_URLs = "https://www.iwencai.com/stockpick/cache?token={0}&p=1&perpage=70&changeperpage=1";



    static {
        questions.add("创投概念股");
        questions.add("macd金叉");
    }

    public void spider(){
        Map<String,String> map = buildParams();
       getSpiderResult(TOKEN_URL,map);
        List<SpiderTask> tasks= getSpiderTask();

    }

    private Map<String, String> buildParams() {

        return null;
    }

    @Override
    public List<SpiderTask> getSpiderTask() {

        return null;
    }

    @Override
    public String getSpiderResult(String url,Map<String,String> params) {
        HttpClientProxy.doGet(url,params);
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
