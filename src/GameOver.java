//侯广鑫 郭天宇
public class GameOver  {
    public void defeat(){
        Commons.shapeSet.clear();
        Commons.canBeAttackedSet.clear();
        Commons.overlapSensitiveSet.clear();
        Commons.canProvideStrengthSet.clear();
        new Endinterface(0,0,600,600,"imgs/defeat.png");
        Commons.executorService.shutdown();
    }
    public void victory(){
        Commons.shapeSet.clear();
        Commons.canBeAttackedSet.clear();
        Commons.overlapSensitiveSet.clear();
        Commons.canProvideStrengthSet.clear();
        new Endinterface(0,0,600,600,"imgs/victory.png");
        Commons.executorService.shutdown();
    }
}

