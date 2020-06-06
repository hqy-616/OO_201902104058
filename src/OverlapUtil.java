//胡庆阳201902104058
public final class OverlapUtil{
    //判断两个对象是否重叠
    public static boolean checkOverlap(OverlapSensitive canMove, OverlapSensitive toOverlap){
        int dx = Math.abs(canMove.getCx()-toOverlap.getCx());
        int dy = Math.abs(canMove.getCy()-toOverlap.getCy());
        int dw = (canMove.getW()+toOverlap.getW())/2;
        int dh = (canMove.getH()+toOverlap.getH())/2;
        return (dx<dw && dy<dh);
    }
}
