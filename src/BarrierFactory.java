public class BarrierFactory implements Runnable{
    private int x;
    private int y;
    private int number = 0;
    @Override
    public void run() {
        while (Commons.STATUS) {
            if(DownCounter.time>30&&this.number<10){
                x = (int) (Math.random() * 900 + 300);
                y = (int) (Math.random() * 200 + 200);
                new Barrier(this.x, this.y, 50, 50);
                this.number++;
                Commons.drawingPanel.repaint();
                Helper.sleep(2000, 3000);
            }
        }
    }
}

