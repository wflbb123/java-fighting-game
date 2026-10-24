package ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import domain.EnemyCharacter;
import domain.HeroCharacter;
import i18n.Messages;

public class FightingGame {
    public void gameStart(String username, Scanner sc)
    {
        System.err.println("---------------------------");
        System.err.println("-- " + Messages.tf("Welcome, {0}!!!!", username) + " --");
        System.err.println("---------------------------");

        HeroCharacter player = creatPlayerCharacter(username, sc);
        System.out.println(Messages.t("Character created successfully!"));
        System.out.println(Messages.tf("Your original attributes are: {0}", player.show()));
        System.out.println(Messages.tf("Your original skills are: {0}", player.showskill()));
        
        // 创建敌方角色
        ArrayList<EnemyCharacter> enemylist = new ArrayList<>();
        enemylist.add(new EnemyCharacter("e1",80,15,10,"super attack"));
        enemylist.add(new EnemyCharacter("e2",60,20,5,"rapid attack"));
        enemylist.add(new EnemyCharacter("e3",120,10,20,"defending"));
        enemylist.add(new EnemyCharacter("e4",70,25,8,"fire"));

        // 准备战斗
        int count = 1;
        int win = 0;

        Random r = new Random();
        // 战斗
        while(player.isAlive())
        {
            if(count > 1)
            {
                for(int i = 0;i < enemylist.size();i++)
                {
                    EnemyCharacter c = enemylist.get(i);
                    c.setMax_Hp(c.getMax_Hp() + 10);
                    c.setHp(c.getMax_Hp());
                    c.setAttack(c.getAttack() +3);
                    c.setDefence(c.getDefence()+2);
                }
            }
            EnemyCharacter enemy = enemylist.get(r.nextInt(enemylist.size()));
            System.out.println(Messages.tf("Your enemy is: {0}", enemy.show()));

            // 战斗开始
            System.out.println("---------------------------");
            System.out.println("__________ " + Messages.t("Game Start") + " _______");
            System.out.println("");
            System.out.println(Messages.tf("Count {0}", count));
            System.out.println("");

            int round = 1;

            while(player.isAlive())
            {
                System.out.println("___________________________");
                System.out.println(Messages.tf("Round {0}", round));

                System.out.println(getHealthBar(player.getName(),player.getHp(),player.getMax_Hp()));
                System.out.println(getHealthBar(enemy.getName(),enemy.getHp(),enemy.getMax_Hp()));

                // 玩家回合
                playerTurn(player,enemy,sc);

                if(!enemy.isAlive())
                {
                    win++;
                    System.out.println(Messages.tf("You defeated {0}! You win!!!", enemy.getName()));
                    
                    break;
                }

                // 敌人回合
                enemyTurn(enemy,player);

                if(!player.isAlive())
                {
                    System.err.println(Messages.t("You lost!!!"));
                    
                    break;
                }
                
                round++;
                
            }
            
            if(player.isAlive())
            {
                int h = r.nextInt(21) + 20;
                player.heal(h);
                System.out.println(Messages.tf("You recovered {0} HP", h));
                System.out.println("_____________________________");
            }

            if(player.isAlive() && win % 3 == 0)
            {
                System.out.println(Messages.t("You have won 3 times! You will receive an attribute boost."));

                player.setMax_Hp(player.getMax_Hp() + 30);
                player.setAttack(player.getAttack() + 5);
                player.setDefence(player.getDefence() + 3);

                System.out.println(Messages.t("+30 HP, +5 attack, +3 defence"));
                System.out.println(Messages.tf("Your attributes: {0}", player.show()));
            }

            if(player.isAlive())
            {
                System.out.println(Messages.t("Would you like to continue? (y/n)"));
                String s = sc.next();

                if(s.equalsIgnoreCase("y") ||s.equalsIgnoreCase("yes") )
                {
                    count++;
                    continue;
                }
                else if(s.equalsIgnoreCase("n") ||s.equalsIgnoreCase("no"))
                {
                    System.out.println(Messages.t("Exit"));
                    break;
                }
                else
                {
                    System.out.println(Messages.t("Invalid input. Continuing by default."));
                    count++;
                    continue;
                }
            }
        }

        // 结算
        System.out.println("__________________");
        System.out.println(Messages.t("Game over!"));
        System.err.println(Messages.tf("You have defeated {0} enemies!!!", win));
        System.out.println(Messages.t("Thank you for playing!"));

        System.exit(0);

    }

    // 创建玩家
    public HeroCharacter creatPlayerCharacter(String username, Scanner sc)

    {
        System.err.println(Messages.t("Create your character: "));
        System.err.println(Messages.tf("Your name is: {0}", username));

        int point = 20;
        System.err.println(Messages.t("You have 20 points. Please allocate skill points. (You have basic attributes.)"));

        String[] attributes = {"hp","attack","defence"};
        int[] values = new int[3];

        for(int i = 0;i < attributes.length;i++)
        {
            System.out.println(Messages.tf("Allocate your {0}: ", attributes[i]));
            int input = sc.nextInt();
            if(input < 0)
            {
                System.err.println(Messages.t("Cannot be negative! Allocating 0 points by default."));
                input = 0;
            }
            if(input > point)
            {
                System.err.println(Messages.t("Exceeds available points! Allocating all remaining points."));
                input = point;
                values[i] = input;
                break;
            }
            point -= input;

            values[i] = input;
        }
        
        HeroCharacter hero = new HeroCharacter(
            username,
            100 + values[0]*10,
            10 + values[1] *2,
            values[2]
        );

        hero.getSkilllist().add("normal attack");
        hero.getSkilllist().add("super attack");
        hero.getSkilllist().add("heal");

        return hero;
    }

    // 打印血条
    public String getHealthBar(String name,int hp,int maxhp)
    {
        int barlength = 20;
        int filled = (int)(hp * 1.0 / maxhp * barlength);

        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":【");
        for(int i = 0;i < 20;i++)
        {
            if(i < filled)
            {
                sb.append("■");
            }
            else
            {
                sb.append(" ");
            }
        }
        sb.append("】" + hp+"/"+maxhp+ " HP");

        return sb.toString();

    }
    
    // 计算伤害
    public int  calculateDamage(int attack,int defense)
    {
        int damage = attack - defense;
        if(damage <= 0) damage = 1;
        return damage;
    }

    // 玩家回合
    public void playerTurn(HeroCharacter player,EnemyCharacter enemy, Scanner sc)
    {
        System.out.println(Messages.t("Your turn"));
        System.out.println(Messages.t("Choose your action:\n1. Attack\n2. Super attack (costs 10 HP, deals heavy damage)\n3. Heal\n"));

        String choice = sc.next();
        switch (choice) {
            default:
                System.out.println(Messages.t("Invalid choice. Defaulting to attack."));
            case "1":
                System.out.println(Messages.tf("You chose attack. You dealt {0} damage to {1}.",
                        calculateDamage(player.getAttack(), enemy.getDefence()), enemy.getName()));
                enemy.takeDamage(calculateDamage(player.getAttack(), enemy.getDefence()));
                break;
            case "2":
                System.out.print(Messages.t("You chose super attack."));
                if(player.getHp() <= 10) System.out.println(Messages.t(" Your HP is 10 or below; you cannot use this."));
                else
                {
                    System.out.println(Messages.t(" You lost 10 HP."));
                    player.takeDamage(10);
                    int damage1 = calculateDamage((int)(player.getAttack() * 1.8), enemy.getDefence());
                    enemy.takeDamage(damage1);
                    System.out.println(Messages.tf("You dealt {0} damage to {1}.", damage1, enemy.getName()));

                }
                break;
            case "3":
                System.out.println(Messages.t("You chose heal."));
                if(player.getHp() <= 10) System.out.println(Messages.t(" Your HP is 10 or below; you cannot use this."));
                else
                {
                    Random r = new Random();
                    int heal = r.nextInt(11);
                    System.out.println(Messages.tf(" You recovered {0} HP.", heal));
                    player.heal(heal);
                    break;
                }
                
        }
    }

    // 敌人回合
    public void enemyTurn(EnemyCharacter enemy,HeroCharacter player)
    {
        System.out.println(Messages.tf("_____ {0}'s turn_____", enemy.getName()));

        Random r = new Random();

        String action = "normal attack";

        if(r.nextInt(2) == 1)
        {
            action = enemy.getSkill();
        }

        System.out.println(Messages.tf("{0} uses {1}", enemy.getName(), action));

        switch (action) {
            case "normal attack":
                int damage = calculateDamage(enemy.getAttack(), player.getDefence());
                System.out.println(Messages.tf("{0} deals {1} damage to you", enemy.getName(), damage));
                player.takeDamage(damage);
                break;
            
            case "super attack":
                int damage1 = calculateDamage((int)(enemy.getAttack() * 1.5), player.getDefence());
                System.out.println(Messages.tf("{0} deals {1} damage to you", enemy.getName(), damage1));
                player.takeDamage(damage1);
                break;
            
            case "rapid attack":
                int damage2 = 0;
                for(int i = 0;i < 2;i++)
                    damage2 += calculateDamage((int)(enemy.getAttack() * 0.75), player.getDefence());
                System.out.println(Messages.tf("{0} deals {1} damage to you", enemy.getName(), damage2));
                player.takeDamage(damage2);
                break;

            case "defending":
                enemy.setDefending(true);
                System.out.println(Messages.tf("{0} reduces incoming damage by 50% for one turn.", enemy.getName()));
 
                break;
            
            case "fire":
                int damage3 = calculateDamage((int)(enemy.getAttack() * 1.8), player.getDefence());
                System.out.println(Messages.tf("{0} deals {1} damage to you", enemy.getName(), damage3));
                player.takeDamage(damage3);
                break;

            default:
                break;
        }
        
        
        
    }

}
