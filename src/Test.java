import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Test {
    public static void main(String[] args) {
        Tank tank = new Tank(400,30,80,80,30);
        FirstAid firstAid = new FirstAid(150,30,50,50);
        Barrier barrier1 = new Barrier(50,50,50,50);
        Barrier barrier2 = new Barrier(50,175,50,50);
        DownCounter timer = new DownCounter(5,5,30,30);
        //创建控制面板对象，并使之关联tank对象
        KeyControlPanel keyControlPanel = new KeyControlPanel(tank);
        //创建画图面板对象，并关联shapeSet数组
        DrawingPanel drawingPanel = new DrawingPanel(Commons.shapeSet);
        Commons.drawingPanel = drawingPanel;
        //创建顶层框架，并使之关联画图和控制面板对象
        MyFrame myFrame = new MyFrame(drawingPanel,keyControlPanel);
        //为顶层框架设置大小
        myFrame.setSize(500, 400);
        //使顶层框架可见
        myFrame.setVisible(true);
//        Commons.executorService.execute(tank);
        Commons.executorService.execute(firstAid);
        Commons.executorService.execute(timer);
    }
}
