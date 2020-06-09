public class Test {
    public static void main(String[] args) {
        Tank tank = new Tank(500, 30, 50, 50, 30);
        FirstAid firstAid = new FirstAid(150, 30, 50, 50);
        new Barrier(50,50,50,50);
        new Barrier(50, 175, 50, 50);
        new Scoring(150, 5, 30, 60);
        new DashBoard(510, 5, 30, 74);
        DownCounter timer = new DownCounter(5, 5, 30, 100);
        new Bomb(80, 130, 50, 50, 20);
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
