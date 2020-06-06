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
}
