/**
 * 功能 ：设计一个定时炸弹。时间到达后，发生爆炸，期体积扩大到4倍（中心不变），攻击它覆盖范围的物体。
 *
 * @author 王兆娣 李笑
 * @version 0.1
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 定时炸弹
 * 左上角显示剩余时间，时间到达后，变成一个爆炸的图片，而且会越来越大。它重叠的物体都被攻击。
 */
public final class Bomb implements Shape, Runnable, OverlapSensitive, CanBeAttacked, CanAttack {
    //杀伤力
    private int damage;
    //炮弹是否还存在
    private boolean exist = true;
    //炮弹所在的所有集合
    private Collection<Collection> whereIamIn;
    //剩余时间
    private int time = 6;
    //左上角横坐标
    private int x = 250;
    //左上角纵坐标
    private int y = 300;
    //宽度
    private int w = 50;
    //高度
    private int h = 50;

    {
        //执行器对象执行任务类对象
        Commons.executorService.execute(this);
        this.whereIamIn = new ArrayList<Collection>();
        Helper.addObjectToCollectionCollection(this.whereIamIn, this, Commons.shapeSet, Commons.canBeAttackedSet, Commons.overlapSensitiveSet);
    }

    public Bomb() {
    }

    public Bomb(int x, int y, int w, int h, int damage) {
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }


    //返回中心点x坐标
    @Override
    public int getCx() {
        return this.x + this.w / 2;
    }

    //返回中心点y坐标
    @Override
    public int getCy() {
        return this.y + this.h / 2;
    }

    //返回宽度
    @Override
    public int getW() {
        return w;
    }

    //返回高度
    @Override
    public int getH() {
        return h;
    }

    public static void main(String[] args) {
        Tank tank = new Tank(400, 30, 50, 50, 30);
        FirstAid firstAid = new FirstAid(150, 30, 50, 50);
        new Barrier(50, 120, 50, 50);
        new Barrier(50, 175, 50, 50);
        new Scoring(50, 5, 30, 30);
        new Bomb(80, 130, 50, 50, 20);

        DownCounter timer = new DownCounter(5, 5, 30, 30);
        //创建控制面板对象，并使之关联tank对象
        KeyControlPanel keyControlPanel = new KeyControlPanel(tank);
        //创建画图面板对象，并关联shapeSet数组
        DrawingPanel drawingPanel = new DrawingPanel(Commons.shapeSet);
        Commons.drawingPanel = drawingPanel;
        //创建顶层框架，并使之关联画图和控制面板对象
        MyFrame myFrame = new MyFrame(drawingPanel, keyControlPanel);
        //为顶层框架设置大小
        myFrame.setSize(600, 600);
        //使顶层框架可见
        myFrame.setVisible(true);
        Commons.executorService.execute(firstAid);
        Commons.executorService.execute(timer);
        Commons.executorService.execute(tank);
    }

    //画出自己
    @Override
    public void drawMyself(Graphics g) {
        g.drawOval(this.x, this.y, this.w, this.h);
        //根据左上角位置及长、宽画出当前的图片，观察者对象设置为null
        g.drawImage(ImgHelper.getImage("imgs/timedBomb.png"), this.x, this.y, this.w, this.h, null);
    }

    @Override
    public void run() {
        for(int i=0;i<6;i++){
            Helper.delay(1000);
            this.time--;
            Commons.drawingPanel.repaint();
        }
        //体积扩大四倍
        this.w *= 2;
        this.h *= 2;
        //延迟150毫秒，以便观察
        Helper.delay(600);
        Commons.drawingPanel.repaint();
        //炮弹6秒后消失
        this.detectShellOverlap();
        this.die();
    }

    public void detectShellOverlap() {
        for (OverlapSensitive overlapSensitive : Commons.overlapSensitiveSet) {
            //判断公共的重叠集合中的对象是否在自己的攻击范围内
            boolean overlapped = Helper.checkOverlap(this, overlapSensitive);
            //公共的重叠集合中可能含有本对象，如果是本对象，跳过重叠
            if (overlapSensitive == this) {
                continue;
            }
            //如果有元素在自己的攻击范围内并且炮弹存在
            if (overlapped && this.exist) {
                //如果对方在公共的被攻击集合中
                if (Commons.canBeAttackedSet.contains(overlapSensitive)) {
                    //则进行强制类型转换
                    CanBeAttacked toAttack = (CanBeAttacked) overlapSensitive;
                    //并向他发送被攻击的消息
                    toAttack.attacked(this);
                    this.exist = true;
                }
            }
        }this.exist = false;
    }

    @Override
    public void attacked(CanAttack attacker) {
        //炮弹被攻击后直接“挂掉”
        this.die();
    }

    @Override
    public int getDamage() {
        //返回杀伤力
        return this.damage;
    }

    @Override
    public void counterActed(int counterAttackDamageFromVicitim) {
        //被反击后，直接“挂掉”
        this.die();
    }

    public void die() {
        //“挂掉”即不存在，所以将炮弹的存在状态设为false
        this.exist = false;
        //从自己所在的集合中删除
        Helper.removeObjectFormCollectionCollection(this.whereIamIn, this);
    }
}
