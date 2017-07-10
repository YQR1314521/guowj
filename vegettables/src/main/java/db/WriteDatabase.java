package db;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by guoweijie on 2017/1/2.
 */
public class WriteDatabase {
    private BaseDao baseDao;
    private Connection conn;
    public WriteDatabase(String driver,String url,String username,String password) throws Exception {
        baseDao = new BaseDao(driver,url,username,password);
        baseDao.createPool();
        conn = baseDao.getConnection();
    }

    public void install(String sql, HashMap cs) throws SQLException {
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);
            for (int i = 0;i<cs.size();i++){
                pst.setObject(i+1,cs.get(i+1));
            }
            pst.addBatch();
            pst.executeBatch();
            conn.commit();
    }

    public void delete() throws SQLException, ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String nowDate = df.format(new Date());// new Date()为获取当前系统时间
        Statement sta=conn.createStatement();
        ResultSet rs=sta.executeQuery("select * from zufang");
        while(rs.next()){
            String time=rs.getString("time");
            System.out.println(time);
            System.out.println("sj:="+daysBetween(time,nowDate));
            if (daysBetween(time,nowDate)>1){
                int id = rs.getInt("id");
                Statement st=conn.createStatement();
                String sql = "DELETE FROM zufang WHERE id = "+id;
                st.executeUpdate(sql);
                st.close();
            }
        }
        rs.close();
    }


    private int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;


        cal.setTime(sdf.parse(smdate));
        time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
