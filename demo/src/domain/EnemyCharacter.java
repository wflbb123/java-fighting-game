package domain;

public class EnemyCharacter extends Character{
    String skill;
    Boolean defending;

    public EnemyCharacter() {
        super();
        this.defending = false;
    }
    public EnemyCharacter(String name, int hp, int attack, int defence, String skill) {
        super(name, hp, attack, defence);
        this.skill = skill;
        this.defending = false;
    }
    
    @Override 
    public void takeDamage(int damage)
    {
        if(defending) 
            {
                damage = (damage/2 > 1?damage/2:1);
                defending = false;
            }
        super.takeDamage(damage);
    }
    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
    public Boolean getDefending() {
        return defending;
    }
    public void setDefending(Boolean defending) {
        this.defending = defending;
    }
}
