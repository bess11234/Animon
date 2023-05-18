
public interface Attackable {
    public abstract void nomalAttack(Animon attacked);
    public abstract void attacked(Animon attacked, int damage);
    public abstract void useskill(Animon atked, String skillName);
}
