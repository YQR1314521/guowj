package wuba;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoweijie on 2017/2/12.
 */
public class wbCrawler implements PageProcessor {
    private List<String> urls = new ArrayList<String>();
    @Override
    public void process(Page page) {
        Html html = page.getHtml();

        urls.add("http://bj.58.com/chuzu/0/pn2/");
        urls.add("http://bj.58.com/chuzu/0/pn3/");
        urls.add("http://bj.58.com/chuzu/0/pn4/");
        urls.add("http://bj.58.com/chuzu/0/pn5/");

        page.addTargetRequests(urls);

        List<String> pageUrls = html.xpath("//div[@class='listBox']/ul[@class='listUl']/li/div[@class='img_list']/a/@href").all();

        page.addTargetRequests(pageUrls);

        page.putField("url",page.getUrl());
        page.putField("title",html.xpath("//div[@class='house-title']/h1/text()"));
        page.putField("money",html.xpath("//div[@class='house-pay-way f16']/span/b/text()"));
        page.putField("address",html.xpath("//div[@class='house-desc-item fl c_333']/ul/li[5]/span[2]/a/text()").all());
        page.putField("img",html.xpath("//div[@class='house-basic-pic fl']/div[@id='bigImg']/img/@src"));
        page.putField("leasetype",html.xpath("//div[@class='house-desc-item fl c_333']/ul/li[2]/span[2]/text()"));
        page.putField("addressInfo",html.xpath("//div[@class='house-desc-item fl c_333']/ul/li[6]/span[2]/text()"));
        page.putField("roomtype",html.xpath("//div[@class='house-desc-item fl c_333']/ul/li[1]/span[2]/text()"));
        page.putField("pay",html.xpath("//div[@class='house-pay-way f16']/span[2]/text()"));

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
                .addHeader("Host","bj.58.com");
    }
}
