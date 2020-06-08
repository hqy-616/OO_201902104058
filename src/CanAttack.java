//描述攻击者
public interface CanAttack {
    //攻击行为不会被其它对象调用，故不必成为接口方法
    //    void attack(CanBeAttacked victim);
    //受害者要获得攻击者的伤害值
    int getDamage();

    //受害者让攻击者受到反击。
    // 一般情况下，受到反击，说明受害者仍然存存活，应让攻击者返回移动之前的位置。
    //void beCounterAttacked(int damage);
    //受到反击
    void counterActed(int counterAttackDamageFromVictim);
}
