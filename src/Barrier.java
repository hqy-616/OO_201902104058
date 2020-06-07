
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Barrier implements Shape, CanBeAttacked, OverlapSensitive {
    {
        this.collectionsWhereIAm = new ArrayList<>();
        /*
            调用addObjectToCollectionCollection将自己分别加入canBeAttackedSet，obstacleSet，shapeSet，overlapSensitiveSet，
            以便能被 Commons.drawingPanelForSet通知画出，可以被检查重叠，可以被攻击，
            并且将自己所在的集合都加入到collectionCollection这个集合中
        */
        Helper.addObjectToCollectionCollection(this.collectionsWhereIAm,this,
                Commons.canBeAttackedSet, Commons.shapeSet, Commons.overlapSensitiveSet
                );
    }
    public Barrier() {
    }
    public Barrier(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    //返回中心点横坐标
    @Override
    public int getCx() {
        return this.x + this.w / 2;
    }

    //返回中心点纵坐标
    @Override
    public int getCy() {
        return this.y + this.h / 2;
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

    //造成伤害
    @Override
    public void attacked(CanAttack offender) {
        //减少生命值
        this.strength -= offender.getDamage();
        //当生命值小于等于0时消失
        if (this.strength <= 0) {
            this.die();
        }else{
            //否则给攻击者造成自身生命值1/2的伤害
            offender.counterAttacked(this.strength/2);
        }
    }
    private void die(){
        //从自己存在的集合中删除自己
        Helper.removeObjectFormCollectionCollection(this.collectionsWhereIAm,this);
    }
    //画出自己
    @Override
    public void drawMyself(Graphics g){
        g.drawRect(this.x,this.y,this.w,this.h);
        //画出障碍物当前的状态
        g.drawString("barrier 血量:"+this.strength,this.x,this.y);
        //获得barrier.png文件对应的Image类型对象
        Image image = ImgHelper.getImage("资源/barrier.png");
        //画出自己
        g.drawImage(image,this.x,this.y,this.w,this.h,null);
    }
    //x,y为左上角坐标
    private int x;
    private int y;
    //w，h为宽度和高度
    private int h;
    private int w;
    //生命值
    private int strength = 30;
    //自身存在与哪些集合中
    private Collection<Collection> collectionsWhereIAm;
}
