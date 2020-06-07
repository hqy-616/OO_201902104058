import java.awt.*;

public class Endinterface implements Shape{
    {
        Commons.shapeSet.add(this);
    }
    public Endinterface(){
    }
    public Endinterface(int x,int y,int w,int h,String name){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.name = name;
    }
    @Override
    public void drawMyself(Graphics g) {
        g.drawImage(ImgHelper.getImage(this.name),this.x,this.y,this.w,this.h,null);
        Commons.drawingPanel.repaint();
    }
    private int x;
    private int y;
    private int w;
    private int h;
    private String name;
}
