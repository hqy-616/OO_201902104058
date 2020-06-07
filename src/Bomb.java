import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
public final class Bomb implements Shape,Runnable,OverlapSensitive,CanBeAttacked,CanAttack{
    {
        //执行器对象执行任务类对象
        Commons.executorService.execute(this);
        this.whereIamIn = new ArrayList<Collection>();
        Helper.addObjectToCollectionCollection(this.whereIamIn,this, Commons.shapeSet,Commons.canBeAttackedSet,Commons.overlapSensitiveSet);
    }

    public Bomb() {
    }
    public Bomb(int x, int y, int w, int h,int damage) {
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

    //画出自己
    @Override
    public void drawMyself(Graphics g) {
        //根据左上角位置及长、宽画出当前的图片，观察者对象设置为null
        g.drawImage(ImgHelper.getImage("OO_git/src/bomb.png"), this.x, this.y, this.w, this.h, null);
    }

    @Override
    public void run() {
        Helper.delay(10000);
        Commons.drawingPanel.repaint();
        //炮弹10秒后消失
        this.detectShellOverlap();
        this.die();
    }

    public void detectShellOverlap(){
        for(OverlapSensitive overlapSensitive:Commons.overlapSensitiveSet){
            //判断公共的重叠集合中的对象是否在自己的攻击范围内
            boolean overlapped = Helper.checkAttackRange(this, overlapSensitive);
            //公共的重叠集合中可能含有本对象，如果是本对象，跳过重叠
            if(overlapSensitive==this){
                continue;
            }
            //如果有元素在自己的攻击范围内并且炮弹存在
            if(overlapped&&this.exist){
                //炮弹“挂掉”
                this.die();
                //如果对方在公共的被攻击集合中
                if(Commons.canBeAttackedSet.contains(overlapSensitive)){
                    //则进行强制类型转换
                    CanBeAttacked toAttack = (CanBeAttacked) overlapSensitive;
                    //并向他发送被攻击的消息
                    toAttack.attacked(this);
                }
            }
        }
    }

    public void die(){
        //“挂掉”即不存在，所以将炮弹的存在状态设为false
        this.exist = false;
        //从自己所在的集合中删除
        Helper.removeObjectFormCollectionCollection(this.whereIamIn,this);
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
    public void beCounterAttacked(int counterAttackDamageFromVicitim) {
        //被反击后，直接“挂掉”
        this.die();
    }

    //杀伤力
    private int damage;
    //炮弹是否还存在
    private boolean exist = true;
    //炮弹所在的所有集合
    private Collection<Collection> whereIamIn;
    //左上角横坐标
    private int x = 250;
    //左上角纵坐标
    private int y = 300;
    //宽度
    private int w = 50;
    //高度
    private int h = 50;

}
