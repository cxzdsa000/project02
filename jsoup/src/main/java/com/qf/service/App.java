package com.qf.service;

import com.qf.utils.HTTPUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       String url="https://search.jd.com/Search?keyword=%E5%8D%AB%E8%A1%A3%E7%94%B7&enc=utf-8&wq=%E5%8D%AB%E8%A1%A3%E7%94%B7&pvid=599b82e2d0fc4517bd89a5e3c9dfa148";
       String html= HTTPUtil.httpPost(url,"","utf-8");

        Document doc = Jsoup.parse(html);
        Elements lis=doc.select("li.gl-item");
        String sql="insert into goods(img,price,tal,uri)values('%s','%s','%s','%s');";
        for (Element li : lis) {
           String img=li.select(".err-product").attr("data-lazy-img");
            //System.out.println("http:"+img);
           String price=li.select(".p-price i").text();
           // System.out.println(price);
           String tal=li.select(".p-name a").attr("title");
          //  System.out.println(tal);
            String uri=li.select(".p-name a").attr("href");
            System.out.println(String.format(sql,img,price,tal,uri));
        }


    }
}
