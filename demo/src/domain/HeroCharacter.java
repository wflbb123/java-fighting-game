package domain;

import java.util.ArrayList;

public class HeroCharacter extends Character{
    ArrayList<String> skilllist;

    public HeroCharacter() {
        super();
        this.skilllist = new ArrayList<>();
    }

    public HeroCharacter(String name, int hp, int attack, int defence) {
        super(name, hp, attack, defence);
        this.skilllist = new ArrayList<>();
    }

    public ArrayList<String> getSkilllist() {
        return skilllist;
    }

    public void setSkilllist(ArrayList<String> skilllist) {
        this.skilllist = skilllist;
    }
    
    public String showskill()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < skilllist.size();i++)
        {
            sb.append(skilllist.get(i));
            if(i < skilllist.size()-1) sb.append("、");
        }

        return sb.toString();
    }
    
}
