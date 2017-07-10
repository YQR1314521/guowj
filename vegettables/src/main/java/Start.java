import db.WriteDatabase;
import com.virjar.dungproxy.client.webmagic.DungProxyDownloader;
import us.codecraft.webmagic.Spider;
import wuba.wbCrawler;
import wuba.wbDatabasePipeline;


/**
 * Created by guoweijie on 2017/4/12.
 */
public class Start implements Runnable{
    public static void main(String[]args) throws Exception {
        DBBase dbBase = new DBBase();
        WriteDatabase writeDatabase =dbBase.getWrite();
        Start start1 = new Start("58",writeDatabase);
        Start start2 = new Start("ganji",writeDatabase);
        Start start3 = new Start("huoju",writeDatabase);
        Start start4 = new Start("fangtianxia",writeDatabase);
        Start start5 = new Start("58gy",writeDatabase);
        Start start7 = new Start("ziru",writeDatabase);

        new Thread(start1).start();
        new Thread(start2).start();
        new Thread(start3).start();
        new Thread(start4).start();
        new Thread(start5).start();
        new Thread(start7).start();


    }

    private String name;
    private WriteDatabase writeDatabase;
    public Start(String name, WriteDatabase writeDatabase) throws Exception {
        this.name = name;
        this.writeDatabase = writeDatabase;
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        if (name.equals("58")) {
            try {
                wuba(writeDatabase);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void wuba(WriteDatabase writeDatabase) throws Exception {
        System.out.println("wuba");
        Spider.create(new wbCrawler())
                .addUrl("http://bj.58.com/chuzu/0/pn1/")
                .addPipeline(new wbDatabasePipeline(writeDatabase))
                .setDownloader(new DungProxyDownloader())
                .thread(2)
                .run();
    }
}

