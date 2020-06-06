
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class FirstAid implements Shape, CanTransferStrength, OverlapSensitive,Runnable{
    {
        this.collectionsWhereIAm = new ArrayList<>();
        /*
            调用addObjectToCollectionCollection将自己分别加入canTransferStrengthSet，shapeSet，overlapSensitiveSet，
            以便能被 Commons.drawingPanelForSet通知画出，可以被检查重叠，可以加血，
            并且将自己所在的集合都加入到collectionCollection这个集合中
        */
        Helper.addObjectToCollectionCollection(
                this.collectionsWhereIAm,
                this,
                Commons.canTransferStrengthSet,
                Commons.shapeSet,
                Commons.overlapSensitiveSet
        );
    }
    public FirstAid() {
    }

    public FirstAid(int x, int y, int w, int h){
        this.x = x;
        this.y= y;
        this.w= w;
        this.h = h;
    }
    //返回中心点横坐标
    @Override
    public int getCx() {
        return this.x+this.w/2;
    }
    //返回中心点纵坐标
    @Override
    public int getCy() {
        return this.y+this.h/2;
    }
    //返回宽度
    @Override
    public int getW() {
        return this.w;
    }
    //返回高度
    @Override
    public int getH() {
        return this.h;
    }
    //返回加血量
    @Override
    public double transferStrength() {
        //加血后消失
        Helper.removeObjectFormCollectionCollection(this.collectionsWhereIAm,this);
        return 0.01*this.w*this.h;
    }


    //画出自己
    @Override
    public void drawMyself(Graphics g) {
        //画出加油包当前的状态
        g.drawString("FistAid",this.x,this.y);
        //获得Bag.png文件对应的Image类型对象
        Image image = ImgHelper.getImage("资源/Bag.png");
        //画出自己
        g.drawImage(image,this.x,this.y,this.w,this.h,null);
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            //下降
            this.y += 5;
            //刷新面板
            Commons.drawingPanel.repaint();
            try {
                //睡眠200ms
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //从自己存在的集合中删除自己
        Helper.removeObjectFormCollectionCollection(this.collectionsWhereIAm,this);
    }
    //x,y为左上角坐标
    private int x;
    private int y;
    //w，h为宽度和高度
    private int h;
    private int w;
    //自身存在与哪些集合中
    private Collection<Collection> collectionsWhereIAm;
}
