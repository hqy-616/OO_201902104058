import java.util.Collection;

public class Helper {
    /**
     * 休眠一段随机时间
     * @param low 下限
     * @param high 上限
     */
    public static void sleep(int low, int high) {
        int millSeconds = (int)(Math.random() * (high - low));
        Helper.sleep(millSeconds);
    }

    /**
     * 休眠一段时间
     * @param millSeconds
     */
    public static void sleep(int millSeconds) {
        try {
            Thread.sleep(millSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*public static boolean checkStatus(){
        boolean isContinue = true;
        if(DownCounter.time == 0 && !Commons.STATUS){
            isContinue = false;
        }
        return isContinue;
    }*/
    //将Object类型的对象加入所有小集合collection当中，并把每个小集合加入到大集合collectionCollection当中
    public static void addObjectToCollectionCollection(Collection<Collection> collectionCollection, Object toAdd, Collection...collections){
        for (Collection collection : collections) {
            collection.add(toAdd);
            collectionCollection.add(collection);
        }
    }
    //将Object类型从collectionCollection里的每个集合中移除
    public static void removeObjectFormCollectionCollection(Collection<Collection> collectionCollection, Object toRemove){
        for (Collection collection : collectionCollection) {
            collection.remove(toRemove);
        }
    }

    //线程延迟
    public static void delay(int time){
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //判断是否重叠
    public static boolean checkOverlap(OverlapSensitive overlaping, OverlapSensitive overlapped) {
        //两个对象的的中心坐标
        int cx1 = overlaping.getCx();
        int cy1 = overlaping.getCy();
        int cx2 = overlapped.getCx();
        int cy2 = overlapped.getCy();
        //两个对象的宽和高
        int w1 = overlaping.getW();
        int h1 = overlaping.getH();
        int w2 = overlapped.getW();
        int h2 = overlapped.getH();
        //两个对象中心点坐标差值的绝对值
        int dx = Math.abs(cx1 - cx2);
        int dy = Math.abs(cy1 - cy2);
        //如果满足条件就是重叠，返回true，反之false
        return (dx < (w1 + w2) / 2 && dy < (h1 + h2) / 2);
    }

    //判断是否在攻击范围内
    public static boolean checkAttackRange(OverlapSensitive overlaping, OverlapSensitive overlapped){
        //两个对象的的中心坐标
        int cx1 = overlaping.getCx();
        int cy1 = overlaping.getCy();
        int cx2 = overlapped.getCx();
        int cy2 = overlapped.getCy();
        //两个对象中心点之间的距离
        double distance=Math.pow(Math.pow(cx1-cx2,2)+Math.pow(cy1-cy2,2),0.5);
        //距离小于200即可攻击
        return distance<200;
    }
}
