
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Tank implements Shape, Movable, OverlapSensitive, CanAttack, CanBeAttacked,CanReceiveStrength,Controllable,Runnable,CanReceiveAmmunition{
    {

        this.collectionsWhereIAm = new ArrayList<>();
        /*
            调用addObjectToCollectionCollection将自己分别加入canBeAttackedSet，shapeSet，overlapSensitiveSet，
            以便能被 Commons.drawingPanelForSet通知画出，可以被检查重叠，可以被攻击，
            并且将自己所在的集合都加入到collectionCollection这个集合中
        */
        Helper.addObjectToCollectionCollection(this.collectionsWhereIAm,this,
               Commons.shapeSet, Commons.overlapSensitiveSet, Commons.canBeAttackedSet
        );
    }
    public Tank(){

    }
    public Tank(int x,int y,int w,int h,int shellNumber){
        this.x = x;
        this.y= y;
        this.w= w;
        this.h = h;
        this.shellNumber = shellNumber;
    }

    //右移
    @Override
    public void moveRight() {
        //当油量大于等于5且存活可以移动时
        if (oil >= 5 && this.strength>0&&Commons.isStart==Commons.start) {
            img = ImgHelper.getImage("imgs/Tank_Right.png");
            //移动距离15
            this.x += 10;
            //油量-5
            this.oil -= 5;
            //方向向右
            this.currentDirection = Movable.RIGHT;
            //检查重叠
            this.checkOverlap();
        }
    }

    //左移
    @Override
    public void moveLeft() {
        //当油量大于等于5且存活可以移动时
        if (oil >= 5 && this.strength>0&&Commons.isStart==Commons.start) {
            img = ImgHelper.getImage("imgs/Tank_Left.png");
            //移动距离15
            this.x -= 10;
            //油量-5
            this.oil -= 5;
            //方向向左
            this.currentDirection = Movable.LEFT;
            //检查重叠
            this.checkOverlap();
        }
    }
    //上移
    @Override
    public void moveUp() {
        //当油量大于等于5且存活可以移动时
        if (oil >= 5 && this.strength>0&&Commons.isStart==Commons.start) {
            img = ImgHelper.getImage("imgs/Tank_Up.png");
            //移动距离15
            this.y -= 10;
            //油量-5
            this.oil -= 5;
            //方向向上
            this.currentDirection = Movable.UP;
            //检查重叠
            this.checkOverlap();
        }
    }
    //下移
    @Override
    public void moveDown() {
        //当油量大于等于5且存活可以移动时
        if (oil >= 5 && this.strength>0&&Commons.isStart==Commons.start) {
            img = ImgHelper.getImage("imgs/Tank_Down.png");
            //移动距离15
            this.y += 10;
            //油量-5
            this.oil -= 5;
            //方向向下
            this.currentDirection = Movable.DOWN;
            //检查重叠
            this.checkOverlap();
        }
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
    //检查重叠
    private void checkOverlap(){
        for (OverlapSensitive overlapSensitive: Commons.overlapSensitiveSet) {
            //判断是否与其他对象重叠
            if(OverlapUtil.checkOverlap(this,overlapSensitive) && overlapSensitive != this){
                //判断重叠的对象是否在canTransferStrengthSet集合里
                if(Commons.canProvideStrengthSet.contains(overlapSensitive)){
                   this.receiveStrength((CanProvideStrength)overlapSensitive);
                }
                //判断重叠的对象是否在canBeAttackedSet集合里
                if(Commons.canBeAttackedSet.contains(overlapSensitive)){
                    ((CanBeAttacked)overlapSensitive).attacked(this);
                }
                //判断重叠的对象是否在provideAmmunitionSet集合里
                if(Commons.provideAmmunitionSet.contains(overlapSensitive)){
                    this.receiveShell((CanProvideAmmunition) overlapSensitive);
                }
            }
        }
    }

    //加血
    @Override
    public void receiveStrength(CanProvideStrength object){
        this.strength += object.transferStrength();
    }

    @Override
    public void receiveShell(CanProvideAmmunition object) {
        this.shellNumber += object.provideShell();
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
    public void counterActed(int damage) {
        //减少生命值
        this.strength -= damage;
        //检查存活状态
        if (this.strength < 0) {
            Helper.removeObjectFormCollectionCollection(this.collectionsWhereIAm, this);
        } else {
            this.cancelMove(this.currentDirection);
        }
       /*if (this.checkAlive()){
            //对方反击，说明对方仍然存活，应撤销刚才的移动.
            this.cancelMove(this.currentDirection);
        }*/
    }

    /**
     * 根据最后一次移动，恢复坐标
     * @param lastMoveDirection
     */
    private void cancelMove(int lastMoveDirection){
        switch (lastMoveDirection) {
            case Movable.RIGHT:
                this.x -= 10;
                break;
            case Movable.DOWN:
                this.y -= 10;
                break;
            case Movable.LEFT:
                this.x += 10;
                break;
            case Movable.UP:
                this.y += 10;
                break;
        }
    }

    /**
     * 作为CanBeAttacked 受到对方攻击时的行为
     * @param offender
     */
    @Override
    public void attacked(CanAttack offender) {
        //减少生命值
        this.strength -= offender.getDamage();
        if(this.strength<0){
            Commons.gameOver.defeat();
        }
        //检查存活状态
        //检查存活状态
        if(this.strength<0){
            Helper.removeObjectFormCollectionCollection(this.collectionsWhereIAm,this);
        }
        else {
            //以一半的伤害反击攻击者
            offender.counterActed(this.damage / 2);
        }
       /* if (this.checkAlive()) {
            //以一半的伤害反击攻击者
            offender.beCounterAttacked(this.damage / 2);
        }*/
    }


    /**
     * 检查本对象的存活状态，如果生命值不大于0，
     * 将isAlive设置为false，从各集合中删除本对象。
     */

   /* private boolean checkAlive(){
        if (this.strength <= 0) {
            this.isAlive = false;
            Helper.removeObjectFormCollectionCollection(this.collectionsWhereIAm, this);
        }
        return isAlive;
    }*/

    //画出自己
    @Override
    public void drawMyself(Graphics g) {
        //画出Tank信息
        g.drawString("tank "+"油量"+this.oil+"血量"+this.strength+"炮弹剩余:"+this.shellNumber +"个",this.x,this.y-10);
        //画出角色
        g.drawImage(img,this.x,this.y,this.w,this.h,null);
        g.drawRect(this.x,this.y,this.w,this.h);
    }

    //攻击
    @Override
    public void fire(){
       if(Commons.isStart==Commons.start) {
           //当CD为0时执行攻击
           if (coolDown) {
               //攻击后CD为1
               this.coolDown = false;
               //shellX,shellY为炮弹左上角坐标
               int shellX = 0;
               int shellY = 0;
               //shellW,shellH为炮弹宽和高
               int shellW = 0;
               int shellH = 0;
               //炮弹方向取决于坦克当前的方向。
               switch (this.currentDirection) {
                   case Movable.RIGHT:
                       shellX = this.x + 50;
                       shellY = this.y + 15;
                       shellW = 50;
                       shellH = 15;
                       break;
                   case Movable.DOWN:
                       shellX = this.x + 20;
                       shellY = this.y + 45;
                       shellW = 15;
                       shellH = 50;
                       break;
                   case Movable.LEFT:
                       shellX = this.x - 40;
                       shellY = this.y + 15;
                       shellW = 50;
                       shellH = 15;
                       break;
                   case Movable.UP:
                       shellX = this.x + 20;
                       shellY = this.y - 45;
                       shellW = 15;
                       shellH = 50;
                       break;
               }
               Shell shell = new Shell(shellX, shellY, shellW, shellH, this.currentDirection);
               Commons.executorService.execute(shell);
               //炮弹数量减一
               this.shellNumber--;
           }
       }
    }
    //改变冷却状态
    public void changeCoolDown(){
        if(!this.coolDown){
            Helper.sleep(1000);
            this.coolDown = true;
        }
    }
    @Override
    public void callForArtillery() {

    }
    //攻击冷却
    @Override
    public void run() {
        while (Commons.STATUS){
            Helper.sleep(200);
            //休眠1000毫秒
            this.changeCoolDown();
        }
    }
    //获得tank.png文件对应的Image类型对象，初始方向为向左
    Image img = ImgHelper.getImage("imgs/Tank_Left.png");
    //x,y为左上角坐标
    private int x;
    private int y;
    //w，h为宽度和高度
    private int h;
    private int w;
    //血量
    private int strength = 200;
    //攻击冷却状态
    private boolean coolDown = true;
    //油量
    private int oil = 5000;
    //炮弹剩余量
    private int shellNumber;
    //最后一次移动的方向
    private int currentDirection = Movable.LEFT;
    //攻击时，对对方的伤害
    private int damage = 10;
    //自身存在与哪些集合中
    private Collection<Collection> collectionsWhereIAm;
}
