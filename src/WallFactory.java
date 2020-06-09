import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
随机产生两种类型的墙（各五块）。黑墙：阻挡任何东西的攻击，永不消失。绿墙：有生命值，被攻击后会消失。
*/
//墙工厂
public class WallFactory implements Runnable{
    //随机生成五个绿墙和五个黑墙
    @Override
    public void run() {
        for (int i = 0 ; i <= 4 ; i++){
            new BlackWall((int) (1 + Math.random() * 500), (int) (1 + Math.random() * 500), 100, 20);
            new GreenWall((int) (1 + Math.random() * 500), (int) (1 + Math.random() * 500), 20, 100, 50);
        }
    }
    //绿墙
    public class GreenWall implements Shape,CanBeAttacked,OverlapSensitive{
        /*
            调用addObjectToCollectionCollection将自己分别加入canBeAttackedSet，shapeSet，overlapSensitiveSet，
            以便能被 Commons.drawingPanelForSet通知画出，可以被检查重叠，可以被攻击，
            并且将自己所在的集合都加入到collectionCollection这个集合中
        */
        {
            this.collectionWhereIAm = new ArrayList<>();
            Helper.addObjectToCollectionCollection(this.collectionWhereIAm,this,Commons.shapeSet,Commons.overlapSensitiveSet,Commons.canBeAttackedSet);
        }
        public GreenWall(){

        }
        public GreenWall(int x,int y,int w,int h,int strength){
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.strength = strength;
            //检测对象重叠
            this.checkAOverlap();
        }

        //被攻击
        @Override
        public void attacked(CanAttack offender) {
            //根据伤害值扣血
            this.strength-=offender.getDamage();
            //做出反击，反击伤害为0
            offender.counterActed(0);
            //生命值小于零则死亡
            if (this.strength<=0){
                this.die();
            }
        }

        //获得中心点x
        @Override
        public int getCx() {
            return this.x + this.w/2;
        }

        //获得中心点y
        @Override
        public int getCy() {
            return this.y + this.h/2;
        }

        //获得宽度w
        @Override
        public int getW() {
            return this.w;
        }

        //获得高度h
        @Override
        public int getH() {
            return this.h;
        }

        //画出自己
        @Override
        public void drawMyself(Graphics g) {
            g.drawImage(ImgHelper.getImage("imgs/GreenWall.png"),this.x,this.y,this.w,this.h,null);
            g.drawString("绿墙：生命值 " + this.strength,this.x,this.y);
        }
        //死亡
        public void die(){
            Helper.removeObjectFormCollectionCollection(this.collectionWhereIAm,this);
            Commons.drawingPanel.repaint();
        }
        //如果重叠物体，则删除自己再新建一个新的绿墙对象
        private void checkAOverlap(){
            for (OverlapSensitive overlapSensitive : Commons.overlapSensitiveSet){
                if (Helper.checkOverlap(this,overlapSensitive) && overlapSensitive != this){
                    Helper.removeObjectFormCollectionCollection(this.collectionWhereIAm,this);
                    new GreenWall((int)(1+Math.random()*500),(int)(1+Math.random()*500),50,50,50);
                }
            }
        }
        //左上角坐标
        private int x,y;
        //宽度高度
        private int w,h;
        //生命值
        private int strength;
        //自身存在哪些集合中
        private Collection<Collection> collectionWhereIAm;
    }
    //黑墙
    public class BlackWall implements Shape,CanBeAttacked,OverlapSensitive{
        {
            /*
            调用addObjectToCollectionCollection将自己分别加入canBeAttackedSet，shapeSet，overlapSensitiveSet，
            以便能被 Commons.drawingPanelForSet通知画出，可以被检查重叠，可以被攻击，
            并且将自己所在的集合都加入到collectionCollection这个集合中
            */
            this.collectionWhereIAm = new ArrayList<>();
            Helper.addObjectToCollectionCollection(this.collectionWhereIAm,this,Commons.shapeSet,Commons.canBeAttackedSet,Commons.overlapSensitiveSet);
        }
        public BlackWall(){

        }
        public BlackWall(int x,int y,int w,int h){
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            //检测重叠
            this.checkAOverlap();
        }
        //被攻击
        @Override
        public void attacked(CanAttack offender) {
            //做出反击，反击伤害0
            offender.counterActed(0);
        }

        //获得中心点x
        @Override
        public int getCx() {
            return this.x + this.w/2;
        }

        //获得中心点y
        @Override
        public int getCy() {
            return this.y + this.h/2;
        }

        //获得宽度w
        @Override
        public int getW() {
            return this.w;
        }

        //获得高度h
        @Override
        public int getH() {
            return this.h;
        }

        //画出自己
        @Override
        public void drawMyself(Graphics g) {
            g.drawImage(ImgHelper.getImage("imgs/BlackWall.png"),this.x,this.y,this.w,this.h,null);
        }
        //如果重叠物体，则删除自己再新建一个新的黑墙对象
        private void checkAOverlap(){
            for (OverlapSensitive overlapSensitive : Commons.overlapSensitiveSet){
                if (Helper.checkOverlap(this,overlapSensitive) && overlapSensitive != this){
                    Helper.removeObjectFormCollectionCollection(this.collectionWhereIAm,this);
                    new BlackWall((int)(1+Math.random()*500),(int)(1+Math.random()*500),50,50);
                }
            }
        }

        //左上角xy
        private int x,y;
        //宽度高度
        private int w,h;
        //自身存在于哪些集合中
        private Collection<Collection> collectionWhereIAm;
    }
    public static void main(String[] args) {
        Tank tank = new Tank(300,30,50,50,30);
        FirstAid firstAid = new FirstAid(150,30,50,50);
        new Barrier(50,50,50,50);
        new Barrier(50,175,50,50);
        new Scoring(50,5,30,60);
        new DashBoard(510,5,30,74);
        DownCounter timer = new DownCounter(5,5,30,30);
        //创建控制面板对象，并使之关联tank对象
        KeyControlPanel keyControlPanel = new KeyControlPanel(tank);
        new BladeSwitch(400,70,70,50);
        //创建画图面板对象，并关联shapeSet数组
        DrawingPanel drawingPanel = new DrawingPanel(Commons.shapeSet);
        Commons.drawingPanel = drawingPanel;
        //创建顶层框架，并使之关联画图和控制面板对象
        MyFrame myFrame = new MyFrame(drawingPanel,keyControlPanel);
        //为顶层框架设置大小
        myFrame.setSize(600, 600);
        //使顶层框架可见
        myFrame.setVisible(true);
        Commons.executorService.execute(firstAid);
        Commons.executorService.execute(timer);
        Commons.executorService.execute(tank);
        Commons.executorService.execute(new WallFactory());

    }
}
