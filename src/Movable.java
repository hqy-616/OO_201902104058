//胡庆阳201902104058
public interface Movable{
    //右移
    void moveRight();
    //左移
    void moveLeft();
    //上移
    void moveUp();
    //下移
    void moveDown();
    //方向
    final static int LEFT = 0;
    final static int DOWN = 1;
    final static int RIGHT = 2;
    final static int UP = 3;
}
