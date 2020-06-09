
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 描述两个把刀片，相向运动，背向分离，周而复始。两个刀片重叠任何物体，即为攻击，伤害值为1000。
 */
public class BladeSwitch {

    //x,y为左上角坐标
    private int x;
    private int y;
    //w，h为宽度和高度
    private int h;
    private int w;

    public BladeSwitch(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        //上刀刃的左上角坐标和bladeSwitch相同，w也相同，但高度为一半
        Blade upBlade = new Blade(Movable.UP, this.x, this.y, this.w, this.h / 2,"imgs/Blade_Up.png");
        //下刀刃的左上角坐标x，y坐标为bladeSwitch竖向中点，w相同，高度为一半
        Blade downBlade = new Blade(Movable.DOWN, this.x, this.y + this.h / 2, this.w, this.h / 2,"imgs/Blade_Down.png");
            Commons.executorService.execute(upBlade);
            Commons.executorService.execute(downBlade);
    }

    /**
     * 运动的刀片
     */
    private class Blade implements CanAttack, OverlapSensitive, Runnable,Shape{
        //刀片位置
        private int attackDirection = Movable.DOWN;
        private int strength = 100;
        //x,y为左上角坐标
        private int x;
        private int y;
        //w，h为宽度和高度
        private int h;
        private int w;
        //图片名
        private String name;
        //自身存在与哪些集合中
        private Collection<Collection> collectionCollection;

        {
        /*
            调用addObjectToCollectionCollection将自己分别加入shapeSet，overlapSensitiveSet，
            以便能被 Commons.drawingPanelForSet通知画出，可以被检查重叠，
            并且将自己所在的集合都加入到collectionCollection这个集合中
        */
            this.collectionCollection = new ArrayList<>();
            Helper.addObjectToCollectionCollection(this.collectionCollection, this,
                    Commons.shapeSet, Commons.overlapSensitiveSet
            );
        }

        public Blade(int attackDirection, int x, int y, int w, int h,String name) {
            this.attackDirection = attackDirection;
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
            this.name = name;
        }

        @Override
        public void run() {
            if (Commons.isStart == Commons.start) {
                //周而复始地运动
                //相背运动缩小，相对运动增大
                //如果是下刀片，则先向下移再向上移
                if (this.attackDirection == Movable.DOWN) {
                    while (Commons.STATUS) {
                        for (int i = 0; i < 5; i++) {
                            //下刀片下移
                            this.y += 10;
                            //缩小
                            this.x += 5;
                            this.w -= 5;
                            this.checkOverlap();
                            Helper.delay(100);
                            Commons.drawingPanel.repaint();
                        }
                        for (int i = 0; i < 5; i++) {
                            //下刀片上移
                            this.y -= 10;
                            //放大
                            this.x -= 5;
                            this.w += 5;
                            this.checkOverlap();
                            Helper.delay(100);
                            Commons.drawingPanel.repaint();
                        }
                    }
                }
                //如果是上刀片，则先向上移再向下移
                if (this.attackDirection == Movable.UP) {
                    while (Commons.STATUS) {
                        for (int i = 0; i < 5; i++) {
                            //上刀片上移
                            this.y -= 10;
                            //缩小
                            this.x += 5;
                            this.w -= 5;
                            this.checkOverlap();
                            Helper.delay(100);
                            Commons.drawingPanel.repaint();
                        }
                        for (int i = 0; i < 5; i++) {
                            //上刀片下移
                            this.y += 10;
                            //放大
                            this.x -= 5;
                            this.w += 5;
                            this.checkOverlap();
                            Helper.delay(100);
                            Commons.drawingPanel.repaint();
                        }
                    }
                }
            }
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

        @Override
        public int getDamage() {
            return 1000;
        }

        @Override
        public void counterActed(int damage) {
            this.strength -= damage;
        }

        @Override
        public void drawMyself(Graphics g) {
            g.drawRect(this.x, this.y, this.w, this.h);
            g.drawImage(ImgHelper.getImage(this.name), this.x, this.y, this.w, this.h, null);
        }
        //检测重叠
        private void checkOverlap(){
            for (OverlapSensitive overlapSensitive: Commons.overlapSensitiveSet) {
                //判断是否与其他对象重叠
                if(OverlapUtil.checkOverlap(this,overlapSensitive) && overlapSensitive != this){
                    //判断重叠的对象是否在canBeAttackedSet集合里
                    if(Commons.canBeAttackedSet.contains(overlapSensitive)){
                        ((CanBeAttacked)overlapSensitive).attacked(this);
                    }
                }
            }
        }

    }
    public static void main(String[] args) {
        Tank tank = new Tank(300,30,50,50,30);
        FirstAid firstAid = new FirstAid(150,30,50,50);
        new Barrier(50,50,50,50);
        new Barrier(50,175,50,50);
        new Scoring(50,5,30,30);
        new BladeSwitch(400,70,70,40);
        DownCounter timer = new DownCounter(5,5,30,30);
        //创建控制面板对象，并使之关联tank对象
        KeyControlPanel keyControlPanel = new KeyControlPanel(tank);
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

    }
}
