/**
 * 功能 ：终止游戏，显示游戏成功结束
 *
 * @author 侯广鑫 郭天宇
 * @author 这里写修改者
 * @author 这里写修改者
 * @author 这里写修改者
 * @version 0.1
 */
public class GameOver  {
    public void defeat(){
        Commons.shapeSet.clear();
        Commons.canBeAttackedSet.clear();
        Commons.overlapSensitiveSet.clear();
        Commons.canProvideStrengthSet.clear();
        new Endinterface(0,0,600,600,"imgs/defeat.png");
        Commons.executorService.shutdown();
        Commons.isStart=Commons.end;
        Commons.isSuccess=Commons.loser;
    }
    public void victory(){
        Commons.shapeSet.clear();
        Commons.canBeAttackedSet.clear();
        Commons.overlapSensitiveSet.clear();
        Commons.canProvideStrengthSet.clear();
        new Endinterface(0,0,600,600,"imgs/victory.png");
        Commons.executorService.shutdown();
        Commons.isStart=Commons.end;
        Commons.isSuccess=Commons.success;
    }
}

