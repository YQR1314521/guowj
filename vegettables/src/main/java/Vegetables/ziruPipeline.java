package Vegetables;


import db.WriteDatabase;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guoweijie on 2017/2/13.
 */
public class ziruPipeline implements Pipeline {

    private WriteDatabase writeDatabase;
    public ziruPipeline(WriteDatabase writeDatabase) throws Exception {
        super();

        this.writeDatabase = writeDatabase;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {

        String category = String.valueOf(resultItems.get("category"));
        String min = String.valueOf(resultItems.get("min"));
        String ave = String.valueOf(resultItems.get("ave"));
        String max = String.valueOf(resultItems.get("max"));
        String date = String.valueOf(resultItems.get("date"));


        String sql = "INSERT INTO vts (category, mins, ave, max, date) " +
                " VALUES (?,?,?,?,?)";
        HashMap zufang = new HashMap();

        zufang.put(1, category);
        zufang.put(2, min);
        zufang.put(3, ave);
        zufang.put(4, max);
        zufang.put(5, date);

        System.out.println(zufang);


        try {
            writeDatabase.install(sql, zufang);
        } catch (SQLException e) {
            System.out.println("posts=" + e);
        }

    }

    public String getRegion(String region){
        Pattern pattern = Pattern.compile("(.*])");
        Matcher matcher = pattern.matcher(region);
        while (matcher.find()){
            String reg = matcher.group(0).replace("[","").replace("]","");
            return reg;
        }
        return null;
    }
}

