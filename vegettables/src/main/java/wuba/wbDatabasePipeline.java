package wuba;

import Util.timUtil;
import db.WriteDatabase;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by guoweijie on 2017/2/13.
 */
public class wbDatabasePipeline  implements Pipeline {

    private WriteDatabase writeDatabase;
    public wbDatabasePipeline(WriteDatabase writeDatabase) throws Exception {
        super();
        this.writeDatabase = writeDatabase;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String nowDate = timUtil.getNowTime();// new Date()为获取当前系统时间


        String url = String.valueOf(resultItems.get("url"));
        int type =1;
        String title = String.valueOf(resultItems.get("title"));
        String money = String.valueOf(resultItems.get("money"));
        String img = String.valueOf(resultItems.get("img"));
        String roomtype = String.valueOf(resultItems.get("roomtype"));
        if (roomtype.contains("合租")){
            roomtype = "合租";
        }else if (roomtype.contains("整租")){
            roomtype = "整租";
        }

        String lease = String.valueOf(resultItems.get("leasetype"));
        String leaseType = lease.substring(0,6);
        String area = lease.substring(8,11)+"㎡";

        String address = String.valueOf(resultItems.get("address")).replace("[","").replace("]","");
        String region = address.split(",")[0];
        String region2 = address.split(",")[1].replace(" ","");
        String addressInfo = String.valueOf(resultItems.get("addressInfo"));
        String pay = String.valueOf(resultItems.get("pay"));

        if (address.contains("床 -")){
            region = "暂无信息";
            region2="暂无信息";
        }
        if (addressInfo.contains("床 -")){
            addressInfo = "暂无信息";
        }



        String sql = "INSERT INTO house (title, city, create_time, money, url, region, sources, room_type, type, address_info,lease_type,area,img_url,region2,pay_type) " +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        HashMap zufang = new HashMap();

        zufang.put(1, title);
        zufang.put(2, "北京");
        zufang.put(3, nowDate);
        zufang.put(4, money);
        zufang.put(5, url);
        zufang.put(6, region);
        zufang.put(7, "58同城");
        zufang.put(8, roomtype);
        zufang.put(9, type);
        zufang.put(10, addressInfo);
        zufang.put(11, leaseType);
        zufang.put(12, area);
        zufang.put(13, img);
        zufang.put(14, region2);
        zufang.put(15, pay);




        try {
            writeDatabase.install(sql, zufang);
        } catch (SQLException e) {
        System.out.println("posts=" + e);
    }

    }



}
