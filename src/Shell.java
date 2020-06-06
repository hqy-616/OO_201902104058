
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Shell implements Shape,Runnable, OverlapSensitive, CanAttack, CanBeAttacked, Movable {
    {
        this.collectionsWhereIAm = new ArrayList<>();
        /*
            调用addObjectToCollectionCollection将自己分别加入canBeAttackedSet，shapeSet，overlapSensitiveSet，
            以便能被 Commons.drawingPanelForSet通知画出，可以被检查重叠，可以被攻击，
            并且将自己所在的集合都加入到collectionCollection这个集合中
        */
        Helper.addObjectToCollectionCollection(
			this.collectionsWhereIAm,
			this,               
			Commons.shapeSet,
			Commons.overlapSensitiveSet,
			Commons.canBeAttackedSet
        );
    }
    public Shell() {
    }
    public Shell(int x, int y, int w, int h, int direction) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.direction = direction;
    }

    @Override
    public void moveRight() {
        img = ImgHelper.getImage("资源/Shell_Right.png");
        //右移
        this.x += 10;
        //检测重叠
        this.checkOverlap();
    }

    @Override
    public void moveLeft() {
        img = ImgHelper.getImage("资源/Shell_Left.png");
        //左移
        this.x -= 10;
        //检测重叠
        this.checkOverlap();
    }

    @Override
    public void moveUp() {
        img = ImgHelper.getImage("资源/Shell_Up.png");
        //上移
        this.y -= 10;
        //检测重叠
        this.checkOverlap();
    }

    @Override
    public void moveDown() {
        img = ImgHelper.getImage("资源/Shell_Down.png");
        //下移
        this.y += 10;
        //检测重叠
        this.checkOverlap();
    }
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

    //检测重叠
    private void checkOverlap(){
        for (OverlapSensitive overlapSensitive: Commons.overlapSensitiveSet) {
            //判断自己是否存在
            if(this.isAlive) {
                //判断是否与其他对象重叠
                if (OverlapUtil.checkOverlap(this, overlapSensitive) && overlapSensitive != this) {
                    //判断重叠的对象是否在canBeAttackedSet数组里
                    if (Commons.canBeAttackedSet.contains(overlapSensitive)){
                        //发动攻击
                        ((CanBeAttacked) overlapSensitive).attacked(this);
                    }
                }
            }
        }
    }

    /**
     * 作为CanBeAttack，向受害者提供伤害值
     * @return 让受害者获得伤害值
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    //作为CanBeAttack，受到受害者的反击时的行为
    @Override
    public void counterAttacked(int damage) {
        //消失
        this.die();
    }

    /**
     * 作为CanBeAttacked 受到对方攻击时的行为
     * @param offender
     */
    @Override
    public void attacked(CanAttack offender) {
        //受到攻击，直接消失
        this.die();
        //以一半的伤害反击攻击者
        offender.counterAttacked(this.damage/2);
    }

    @Override
    public void drawMyself(Graphics g) {
            //画出角色
            g.drawImage(img, this.x, this.y, this.w, this.h, null);
            g.drawRect(this.x, this.y, this.w, this.h);
    }
    //炮弹发射
    @Override
    public void run() {
        //移动30次后消失
        for (int i = 0; i < 30; i++) {
            //如果不再活着(被其它物体消灭)，则退出循环
            if (!isAlive) {
                break;
            }
            //移动
            switch (direction) {
                case Movable.RIGHT:
                    this.moveRight();
                    break;
                case Movable.DOWN:
                    this.moveDown();
                    break;
                case Movable.LEFT:
                    this.moveLeft();
                    break;
                case Movable.UP:
                    this.moveUp();
                    break;
            }
            //重绘画板
            Commons.drawingPanel.repaint();
            Helper.sleep(100);
        }
        //飞行30次后消失
        if(isAlive){
            this.die();
            //重绘面板
            Commons.drawingPanel.repaint();
        }
    }
    //消失
    private void die(){
        this.isAlive = false;
        //从自己所在的集合中删除
        Helper.removeObjectFormCollectionCollection(this.collectionsWhereIAm,this);
    }
    Image img;
    //x,y为左上角坐标
    private int x;
    private int y;
    //w，h为宽度和高度
    private int h;
    private int w;
    private boolean isAlive = true;
    //攻击时，对对方的伤害
    private int damage = 10;
    private int direction = Movable.RIGHT;
    //自身存在与哪些集合中
    private Collection<Collection> collectionsWhereIAm;
}
