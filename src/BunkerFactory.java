/**
 * 功能 ：随机位置产生10个碉堡，它们随机时间向随机方向发射Shell对象。这些Shell不能攻击碉堡。
 *
 * @author 李笑 王兆娣
 * @version 0.1
 */
public class BunkerFactory implements Runnable {
    @Override
    public void run() {
        //当游戏开始时执行
        while (Commons.STATUS) {
            if(this.number < 10){
                //产生堡垒的随机范围
                int x = (int) (Math.random() * 800 + 400);
                int y = (int) (Math.random() * 200 + 200);
                Bunker bunker = new Bunker(x,y,30,50);
                Commons.executorService.execute(bunker);
                //数量+1
                this.number++;
                //刷新面板
                Commons.drawingPanel.repaint();
            }
        }
    }

    private int number = 0;

    public static void main(String[] args) {
        Tank tank = new Tank(400,30,50,50,30);
        FirstAid firstAid = new FirstAid(150,30,50,50);
        new Barrier(50,50,50,50);
        new Barrier(50,175,50,50);
        new Scoring(50,5,30,30);
        new DashBoard(510,5,30,74);
        DownCounter timer = new DownCounter(5,5,30,30);
        BunkerFactory bunkerFactory = new BunkerFactory();
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
        Commons.executorService.execute(bunkerFactory);
    }
}
