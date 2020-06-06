/**
 * 描述可以控制的行为
 * 扩展Movable接口
 */
public interface Controllable extends Movable {
    //开火
    void fire();
    //呼叫炮火支援
    void callForArtillery();
}
