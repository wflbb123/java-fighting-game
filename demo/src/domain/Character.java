package domain;

public class Character {
    String name;
    int hp;
    int Max_Hp;
    int attack;
    int defence;

    public Character()
    {

    }
    public Character(String name, int hp, int attack, int defence) 
    {
        this.name = name;
        this.hp = hp;
        Max_Hp = hp;
        this.attack = attack;
        this.defence = defence;
    }

    //判断是否存活
    public Boolean isAlive() {return hp > 0;} 

    // 回复血量
    public void heal(int amount)
    {
        hp += amount;
        if(hp > Max_Hp) hp = Max_Hp;
    }
    // 收到伤害
    public void takeDamage(int damage)
    {
        hp -= damage;
        if(hp < 0) hp = 0;
        
    }

    // 展示属性
    public String show()
    {
        return "name: " + name+" hp: " + hp+" attack: "+attack+" defence: "+defence;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getMax_Hp() {
        return Max_Hp;
    }
    public void setMax_Hp(int max_Hp) {
        Max_Hp = max_Hp;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefence() {
        return defence;
    }
    public void setDefence(int defence) {
        this.defence = defence;
    }
}
