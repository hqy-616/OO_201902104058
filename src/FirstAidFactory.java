public class FirstAidFactory implements Runnable{
    private int x;
    private int y;
    private int number = 0;
    @Override
    public void run() {
        while (Commons.STATUS) {
            if(DownCounter.time>30&&this.number<10){
                x = (int) (Math.random() * 900 + 300);
                y = (int) (Math.random() * 200 + 200);
                Commons.executorService.execute(new FirstAid(this.x, this.y, 50, 50));
                this.number++;
                Commons.drawingPanel.repaint();
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
