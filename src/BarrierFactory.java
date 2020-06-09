/**
 * 功能 ：30秒内随机产生10个路障
 *
 * @author 胡庆阳 谷娟娟
 * @version 0.1
 */
public class BarrierFactory implements Runnable{
    private int x;
    private int y;
    private int number = 0;
    @Override
    public void run() {
        //当游戏开始是执行
        while (Commons.STATUS&&Commons.isStart == Commons.start) {
            //当时间大于30且生产的数量少于10个时，在2-3秒内随机生产一个路障
            if(DownCounter.time>30&&this.number<10){
                //产生路障的随机范围
                x = (int) (Math.random() * 900 + 100);
                y = (int) (Math.random() * 200 + 150);
                new Barrier(this.x, this.y, 50, 50);
                //数量+1
                this.number++;
                //刷新面板
                Commons.drawingPanel.repaint();
                //睡眠2-3秒
                Helper.sleep(2000, 3000);
            }
        }
    }
}

