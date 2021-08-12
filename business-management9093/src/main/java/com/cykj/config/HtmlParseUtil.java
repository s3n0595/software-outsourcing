package com.cykj.config;

import com.cykj.bean.RealtimeInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/11 15:58
 */
public class HtmlParseUtil {
    public List<RealtimeInfo> parseInfo(){
        HttpClientDownPage httpClientDownPage=new HttpClientDownPage();
        Document document = Jsoup.parse(httpClientDownPage.sendGet("https://it.ithome.com/"));
        Elements elements=document.getElementsByClass("bl").select("li");
        ArrayList<RealtimeInfo> InfoList=new ArrayList<>();
        for (Element e1 : elements) {
            String img=e1.getElementsByTag("img").eq(0).attr("src");
            String title=e1.getElementsByClass("title").eq(0).text();
            String content=e1.getElementsByClass("m").eq(0).text();
            String tags1=e1.getElementsByClass("tags").select("a").eq(0).text();
            String tags2=e1.getElementsByClass("tags").select("a").eq(1).text();
            String tags3=e1.getElementsByClass("tags").select("a").eq(2).text();
            String time=e1.getElementsByClass("d").eq(0).text();
            RealtimeInfo realtimeInfo=new RealtimeInfo();
            realtimeInfo.setImg(img);
            realtimeInfo.setTitle(title);
            realtimeInfo.setContent(content);
            realtimeInfo.setTags1(tags1);
            realtimeInfo.setTags2(tags2);
            realtimeInfo.setTags3(tags3);
            realtimeInfo.setTime(time);
            InfoList.add(realtimeInfo);
        }
        return InfoList;
    }
}
