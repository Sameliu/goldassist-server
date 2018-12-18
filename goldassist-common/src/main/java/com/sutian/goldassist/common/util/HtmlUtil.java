package com.sutian.goldassist.common.util;

import org.apache.http.client.utils.DateUtils;

import java.io.*;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List; /**
 * @author sutian
 * @email sijin.zsj@alibaba-inc.com
 * @create 2018/12/18 上午11:30
 */
public class HtmlUtil {

    public static String ROOT= "/Users/zhangsijin/xiaosu/goldassist-server/goldassist-web/src/main/resources/report/";

    public static String html(List<String> codesList) {
        String url = "<td><a href =''http://quote.eastmoney.com/{0}.html''><img src= ''http://image.sinajs.cn/newchart/daily/n/{1}.gif''/></a></td>";
        StringBuilder builder = new StringBuilder("<table><tr>");
        for(int i =0 ;i<codesList.size();i++ ){
            String td = MessageFormat.format(url,codesList.get(i),codesList.get(i));
            builder.append(td);
            if(i >=3 && (i%3 == 0)){
                builder.append("</tr>");
                builder.append("<tr>");
            }
        }
        builder.append("</tr></table>");
        return builder.toString();
    }

    public static void htmlToFile(List<String> codesList,String name) {
        String dateStr = DateUtils.formatDate(new Date(),"YYYY-MM-dd");
        File file = new File(ROOT + dateStr + "/" + name +".html");
        try {
            if (!file.exists()){
                File p = file.getParentFile();
                p.mkdirs();
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(file.getPath(),false),"UTF-8"));
            writer.write(html(codesList));
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "<td><a href =''http://quote.eastmoney.com/{0}.html''><img src= ''http://image.sinajs.cn/newchart/daily/n/{1}.gif''/></a></td>";
        System.out.println(MessageFormat.format(url,"AA","BB"));
    }

}
