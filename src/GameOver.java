public class GameOver  {
    public void defeat(){
        Commons.shapeSet.clear();
        Commons.canBeAttackedSet.clear();
        Commons.overlapSensitiveSet.clear();
        Commons.canProvideStrengthSet.clear();
        Commons.executorService.shutdown();
    }
    public void victory(){
        Commons.shapeSet.clear();
        Commons.canBeAttackedSet.clear();
        Commons.overlapSensitiveSet.clear();
        Commons.canProvideStrengthSet.clear();
        Commons.executorService.shutdown();
    }
}
