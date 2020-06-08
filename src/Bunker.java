import java.awt.*;

public class Bunker implements Shape,Runnable{
    {
        Commons.shapeSet.add(this);
    }
    public Bunker(){}
    public Bunker(int x,int y,int w,int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void run() {
        while(Commons.STATUS&& DownCounter.time > 1 ) {
            Helper.sleep(2000, 3000);
            Commons.executorService.execute(new Shell(
                    this.x,this.y,20,20,(int)(Math.random()*4)));
            Helper.sleep(4000, 6000);
        }
    }


    //画出自己
    @Override
    public void drawMyself(Graphics g) {
        g.drawRect(this.x,this.y,this.w,this.h);
        //获得barrier.png文件对应的Image类型对象
        Image image = ImgHelper.getImage("imgs/bunker.png");
        //画出自己
        g.drawImage(image,this.x,this.y,this.w,this.h,null);
    }

    //x,y为左上角坐标
    private int x;
    private int y;
    //w，h为宽度和高度
    private int h;
    private int w;

}
