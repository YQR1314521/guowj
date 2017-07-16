import Vegetables.VtCrawler;
import Vegetables.ziruPipeline;
import db.WriteDatabase;
import com.virjar.dungproxy.client.webmagic.DungProxyDownloader;
import us.codecraft.webmagic.Spider;


/**
 * Created by guoweijie on 2017/4/12.
 */
public class Start implements Runnable{
    public static void main(String[]args) throws Exception {
        DBBase dbBase = new DBBase();
        WriteDatabase writeDatabase =dbBase.getWrite();
        Start start1 = new Start("vt",writeDatabase);


        new Thread(start1).start();



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
        if (name.equals("vt")) {
            try {
                vt(writeDatabase);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void vt(WriteDatabase writeDatabase) throws Exception {
        System.out.println("wuba");
        Spider.create(new VtCrawler())
                .addUrl("http://www.xinfadi.com.cn/marketanalysis/1/list/1.shtml")
                .addPipeline(new ziruPipeline(writeDatabase))
                .setDownloader(new DungProxyDownloader())

                .thread(2)
                .run();
    }
}

