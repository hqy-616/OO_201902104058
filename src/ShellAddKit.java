import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
/**
 * 功能 ：炮弹包为接收炮弹者提供炮弹
 *
 * @author 胡庆阳 谷娟娟
 * @version 0.1
 */
public class ShellAddKit implements Shape,OverlapSensitive,Runnable,CanProvideAmmunition {
    {
        this.collectionsWhereIAm = new ArrayList<>();
        /*
            调用addObjectToCollectionCollection将自己分别加入canTransferStrengthSet，shapeSet，overlapSensitiveSet，
            以便能被 Commons.drawingPanelForSet通知画出，可以被检查重叠，可以加炮弹，
            并且将自己所在的集合都加入到collectionCollection这个集合中
        */
        Helper.addObjectToCollectionCollection(
                this.collectionsWhereIAm,
                this,
                Commons.canProvideAmmunitions,
                Commons.shapeSet,
                Commons.overlapSensitiveSet
        );
    }
    public ShellAddKit() {
    }

    public ShellAddKit(int x, int y, int w, int h){
        this.x = x;
        this.y= y;
        this.w= w;
        this.h = h;
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


    //返回增加的炮弹数量
    @Override
    public int provideShell() {
        //消失
        Helper.removeObjectFormCollectionCollection(this.collectionsWhereIAm,this);
        return 5;
    }

    //画出自己
    @Override
    public void drawMyself(Graphics g) {
        //画出炸弹包当前的状态
        g.drawString("shellBag",this.x,this.y);
        //获得shellBag文件对应的Image类型对象
        Image image = ImgHelper.getImage("imgs/shellBag.png");
        //画出自己
        g.drawImage(image,this.x,this.y,this.w,this.h,null);
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
