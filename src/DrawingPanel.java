import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Set;

//类定义的开始
public final class DrawingPanel extends JPanel{
    //创建无参构造器
    public DrawingPanel() {
    }

    /**
     * 令本对象关联一个Shape[]类型的对象
     * @param shapeSet 被关联的Shape[]类型的对象
     */
    //定义参数为Shape类型的构造器
    public DrawingPanel(Set<Shape> shapeSet) {
        super();
        this.shapeSet = shapeSet;
    }

    //定义paintComponent方法，参数为Graphics类型的对象
    protected void paintComponent(Graphics g) {
        //调用超类的方法
        super.paintComponent(g);
        Iterator<Shape> it = shapeSet.iterator();
        while (it.hasNext()){
            it.next().drawMyself(g);
        }
    }

    //设置关联字段关联方法
    public void setShape(Set<Shape> shapeSet) {
        //字段进行关联
        this.shapeSet = shapeSet;
    }

    //定义关联字段
    private Set<Shape> shapeSet;
}

