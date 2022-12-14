package com.wen.richresource.spider.movie;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Component
public class MovieProcessor implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(100);
    }

    //统计
    public static int count = 0;
    //限制
    private static volatile int limit = 2000;

    @Override
    public void process(Page page) {
        boolean steal = false;
/*        if (limit < 0) {
            return;
        }*/
        //爬取资源详情
        if (page.getUrl().regex(".*/html/gndy/dyzz/[0-9]{8}/[0-9]{5}.html").match()) {
            page.putField("content", page.getHtml().xpath("//*[@id=\"Zoom\"]/span/text()").toString());
            page.putField("title", String.valueOf(page.getHtml().xpath("//*[@id=\"header\"]/div/div[3]/div[3]/div[1]/div[2]/div[1]/h1/font/text()")));
            page.putField("resourceLink", String.valueOf(page.getHtml().xpath("//*[@id=\"Zoom\"]/span/a/@href")));
            page.putField("resourceName", String.valueOf(page.getHtml().xpath("//*[@id=\"Zoom\"]/span/a/strong/font/font/font/text()")));
            page.putField("imageUrl", String.valueOf(page.getHtml().xpath("//*[@id=\"Zoom\"]/span/img/@src")));
            count++;
            limit--;
        } else {
            page.addTargetRequests(page.getHtml().xpath("//*[@id=\"header\"]/div/div[3]/div[3]/div[2]/div[2]/div[2]/ul/table/tbody/tr[2]/td[2]/b/a/@href").all());
        }

        int nextIndex = page.getHtml().xpath("//*[@id=\"header\"]/div/div[3]/div[3]/div[2]/div[2]/div[2]/div/a/text()").all().indexOf("下一页");
        // 下一页
        if (nextIndex != -1) {
            String nextUrl = page.getHtml().xpath("//*[@id=\"header\"]/div/div[3]/div[3]/div[2]/div[2]/div[2]/div/a[" + (nextIndex + 1) + "]/@href").toString();
            page.addTargetRequest(nextUrl);
        }
    }

}
