import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
/**
 * 功能 ：终止游戏，显示游戏成功结束
 *
 * @author 侯广鑫 郭天宇
 * @author 这里写修改者
 * @author 这里写修改者
 * @author 这里写修改者
 * @version 0.1
 */

public class Scoring implements Shape {
     {
         /*
            调用addObjectToCollectionCollection将自己分别加入shapeSet，
            以便能被 Commons.drawingPanelForSet通知画出，
            并且将自己所在的集合都加入到collectionCollection这个集合中
        */
        this.collectionCollection = new ArrayList<>();
        Helper.addObjectToCollectionCollection(this.collectionCollection, this, Commons.shapeSet);
    }
    public Scoring() {
    }
    public Scoring(int x, int y, int h, int w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    //画出自己
    @Override
    public void drawMyself(Graphics g) {
        //画出加积分器当前的状态
        g.drawString("积分:" + Commons.integral, this.x + 8, this.y + 17);
        //画出自己
        g.drawRect(this.x, this.y, this.w, this.h);
    }

    //x,y为左上角坐标
    private int x;
    private int y;
    //w，h为宽度和高度
    private int h = 10;
    private int w = 20;
    //自身存在与哪些集合中
    private Collection<Collection> collectionCollection;
}
