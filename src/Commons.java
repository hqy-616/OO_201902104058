import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Commons {
    //关联DrawingPanelForArray类型的对象
    protected static DrawingPanel drawingPanel = null;
    //游戏是否终止
    public static boolean STATUS = true;
    public static ExecutorService executorService = Executors.newCachedThreadPool();
    //可能重叠的对象的集合
    public static Set<OverlapSensitive> overlapSensitiveSet = new CopyOnWriteArraySet<OverlapSensitive>();
    //可以索要生命值的对象的集合
    public static Set<CanProvideStrength> canProvideStrengthSet = new CopyOnWriteArraySet<CanProvideStrength>();
    //可以攻击的对象的集合
    public static Set<CanBeAttacked> canBeAttackedSet= new CopyOnWriteArraySet<CanBeAttacked>();
    //可以被画出的对象的集合
    public static Set<Shape> shapeSet = new CopyOnWriteArraySet<Shape>();
    //可以给加子弹的集合
    public static Set<CanProvideAmmunition> canProvideAmmunitions = new CopyOnWriteArraySet<>();
    //可以接收子弹的集合
    public static Set<CanReceiveAmmunition> canReceiveAmmunitions = new CopyOnWriteArraySet<>();
    //游戏终止器
    public static GameOver gameOver=new GameOver();
    //路障存活数量
    public static int survive=0;
    //积分
    public static int integral=0;
    //开始标志
    public static final int start=0;
    //结束标志
    public static final int end=1;
    //胜利标志
    public static final int success=2;
    //失败标志
    public static final int loser=3;
    public static int isStart;
    public static int isSuccess;
}
