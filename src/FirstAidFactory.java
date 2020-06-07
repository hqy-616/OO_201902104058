public class FirstAidFactory implements Runnable{
    private int x;
    private int y;
    private int number = 0;
    @Override
    public void run() {
        //当游戏开始是执行
        while (Commons.STATUS) {
            //当时间大于30且生产的数量少于10个时，在2-3秒内随机生产一个急救包
            if(DownCounter.time>30&&this.number<10){
                //产生急救包的随机范围
                x = (int) (Math.random() * 900 + 300);
                y = (int) (Math.random() * 200 + 200);
                Commons.executorService.execute(new FirstAid(this.x, this.y, 50, 50));
                //数量+1
                this.number++;
                //刷新面板
                Commons.drawingPanel.repaint();
                //睡眠2-3秒
                Helper.sleep(2000, 3000);
            }
        }
    }
    public static void main(String[] args) {
        Tank tank = new Tank(400,30,50,50,30);
        FirstAid firstAid = new FirstAid(150,30,50,50);
        new Barrier(50,50,50,50);
        new Barrier(50,175,50,50);
        new Scoring(50,5,30,30);
        DownCounter timer = new DownCounter(5,5,30,30);
        FirstAidFactory firstAidFactory = new FirstAidFactory();
        BarrierFactory barrierFactory = new BarrierFactory();
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
        Commons.executorService.execute(firstAidFactory);
        Commons.executorService.execute(barrierFactory);
    }
}