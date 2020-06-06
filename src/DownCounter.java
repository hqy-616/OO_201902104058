import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class DownCounter implements Shape,Runnable{
    {
         /*
            调用addObjectToCollectionCollection将自己分别加入shapeSet，
            以便能被 Commons.drawingPanelForSet通知画出，
            并且将自己所在的集合都加入到collectionCollection这个集合中
        */
        this.collectionCollection = new ArrayList<>();
        Helper.addObjectToCollectionCollection(this.collectionCollection, this, Commons.shapeSet);
    }
    public DownCounter() {

    }
    public DownCounter(int x, int y, int h, int w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    //倒计时
    public void outPut(){
        DownCounter.time--;
    }
    //画出自己
    @Override
    public void drawMyself(Graphics g) {
        //画出加油包当前的状态
        g.drawString(""+DownCounter.time,this.x+8,this.y+17);
        //画出自己
       g.drawRect(this.x,this.y,this.w,this.h);
    }
    //执行时间减一
    @Override
    public void run(){
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 60; i >=0 ; i--) {
            outPut();
            //刷新面板
            Commons.drawingPanel.repaint();
            try {
                //睡眠20ms
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static int time = 60;
    //x,y为左上角坐标
    private int x;
    private int y;
    //w，h为宽度和高度
    private int h;
    private int w;
    //自身存在与哪些集合中
    private Collection<Collection> collectionCollection;
}
