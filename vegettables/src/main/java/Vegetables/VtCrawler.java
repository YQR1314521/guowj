package Vegetables;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * Created by guoweijie on 2017/7/16.
 */
public class VtCrawler implements PageProcessor {
    @Override
    public void process(Page page) {
        Html html = page.getHtml();

        List<String> pageUrls = html.xpath("//div[@class='manu']/a/@href").all();

        page.addTargetRequests(pageUrls);

        page.putField("addreas","北京");
        List<String> tbody = html.xpath("//table[@class='hq_table']/tbody/tr").all();

        for (int x=0;x<20;x++){
            Html tr = Html.create(tbody.get(x));
            System.out.println("=============");
            String vtInfo = String.valueOf(tr.xpath("//body/text()"));
            String[] vtInfos = vtInfo.split(" ");

            page.putField("category",vtInfos[1]);
            page.putField("min",vtInfos[2]);
            page.putField("ave",vtInfos[3]);
            page.putField("max",vtInfos[4]);
            page.putField("date",vtInfos[7]);
            System.out.println("=============");

        }
    }

    @Override
    public Site getSite() {
        return Site.me()
                .setSleepTime(10)
                .setRetryTimes(10)
                .setTimeOut(30000)
                .setCycleRetryTimes(10)
                .setUseGzip(true)
                .addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36")
                .addHeader("Host","www.xinfadi.com.cn");
    }
}
