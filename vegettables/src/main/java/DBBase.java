import db.WriteDatabase;

/**
 * Created by guoweijie on 2017/4/12.
 */
public class DBBase {
    protected final String DRIVER = "com.mysql.jdbc.Driver";
    // 连接路径
    protected final String URL = "jdbc:mysql://127.0.0.1:3306/vt?useUnicode=true&characterEncoding=UTF8&useSSL=false&rewriteBatchedStatements=true";
    //    // 用户名
    protected final String USERNAME = "root";
    //    // 密码
    protected final String PASSWORD = "VN^GKMMpr*vA2T@%5Cx5";

//
//    protected final String DRIVER = "com.mysql.jdbc.Driver";
//    // 连接路径
//    protected final String URL = "jdbc:mysql://localhost:3306/zufang?useUnicode=true&characterEncoding=UTF8&useSSL=false&rewriteBatchedStatements=true";
//    //    // 用户名
//    protected final String USERNAME = "root";
//    //    // 密码
//    protected final String PASSWORD = "VN^GKMMpr*vA2T@%5Cx5";


    protected WriteDatabase writeDatabase;

    public DBBase() throws Exception {
        writeDatabase = new WriteDatabase(DRIVER,URL,USERNAME,PASSWORD);
    }

    public WriteDatabase getWrite(){
        return writeDatabase;
    }
}
