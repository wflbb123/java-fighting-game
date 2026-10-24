package ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import domain.User;
import i18n.Messages;

public class Login {
    public void start(ArrayList<User> users){
        // create an user database

        System.out.println("________________________________");
        System.out.println("___" + Messages.t("Welcome to the login page!") + "___");
        System.out.println("________________________________");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(Messages.t("Choose an option: 1. Login   2. Register   3. Play as guest   4. Exit"));

            String choice = sc.next();

            switch(choice){

                case "1":
                    login(users, sc);
                    break;
                case "2":
                    register(users, sc);
                    break;
                case "4":
                    exit();
                    break;
                case "3":
                    System.out.println(Messages.t("Playing as guest"));
                    FightingGame fg = new FightingGame();
                    fg.gameStart("user");
                default:
                    System.out.println(Messages.t("Invalid input. Please try again."));
                    start(users);

            }
        }
    }




    public Login() {}

    public void login(ArrayList<User> users, Scanner sc){

        System.out.println(Messages.t("login"));
        String account;

        User u;

        while(true){
            account = verifyaccount(sc);

            if(findindex(users, account) == -1) 
                {
                    System.out.println(Messages.t("Account does not exist. Please type again."));
                    continue;
                }

            u = users.get(findindex(users, account));

            if (!u.getState())
                {
                System.out.println(Messages.t("Your account has been locked. Please contact your administrator or log in with another account."));
            }
            else {
                System.out.println(Messages.t("Account found. Please enter your password."));
                break;
                }
            }
        String password;

        int LoginTimes = 0;
        while(true){
            password = verifypassword1(sc);
            if(!u.getPassword().equals(password))          
            {
                System.out.println(Messages.t("Incorrect password. Please try again."));
                LoginTimes++;                   
                if(LoginTimes == 3){
                    System.out.println(Messages.t("You have entered the wrong password 3 times. Your account is now locked. Please contact your administrator."));
                    u.setState(false);
                    break;
                }

            }
            // 启动游戏
            else {
                System.out.println(Messages.t("Login successful! The game will start shortly."));
                FightingGame fg = new FightingGame();
                fg.gameStart(u.getAccount());
                break;
            }
        }
    }

    public void register(ArrayList<User> users, Scanner sc){

        System.out.println(Messages.t("register"));

        String account;
        
        while(true){
            account = verifyaccount(sc);
            
            if(findindex(users, account) >= 0) System.out.println(Messages.t("Account already exists. Please choose a different account."));
            else {
                System.out.println(Messages.t("Account available. Please enter your password."));
                break;
            }
        }

        String password = null;
        while(true){
            password = verifypassword2(sc);

            Boolean result = confirmPassword(password,sc);
            if(result) break;
            
            }

        User user = new User(account,password);

        users.add(user);

        System.out.println(Messages.t("Registration successful. Please log in."));
    }

    // 检查账户是否合法
    public String verifyaccount(Scanner sc){
        System.out.println(Messages.t("please input your account"));

        String account = null;
        while(true){
            account = sc.next();

            if (account == null) {
                System.out.println(Messages.t("Account cannot be null. Please enter a valid account (3-16 characters)."));
            } else if (account.length() < 3 || account.length() > 16) {
                System.out.println(Messages.t("Invalid account. Please enter a valid account (3-16 characters)."));
            } else if (!account.matches("^[a-zA-Z0-9]+$")) {
                System.out.println(Messages.t("Invalid account. Please enter a valid account (letters and numbers only)."));
            } else if (account.matches("^[0-9]+$")) {
                System.out.println(Messages.t("Invalid account. Please enter a valid account (cannot be numbers only)."));
            } else {
                break;
            }
        }
        return account;
    }

    // 检查密码是否合法
    public String verifypassword(String password)
    {
        if (password == null) {
            System.out.println(Messages.t("Password cannot be null. Please enter a valid password."));
        } else if (password.length() < 6 || password.length() > 20) {
            System.out.println(Messages.t("Invalid password. Please enter a valid password (6-20 characters)."));
        } else if (!password.matches("^[a-zA-Z0-9]+$")) {
            System.out.println(Messages.t("Invalid password. Please enter a valid password (letters and numbers only)."));
        } else if (password.matches("^[0-9]+$")) {
            System.out.println(Messages.t("Invalid password. Please enter a valid password (cannot be numbers only)."));
        } else {
                return password;
            }
        return null;
            
    }

    // 注册时获取密码
    public String verifypassword2(Scanner sc){
        System.out.println(Messages.t("please input password: "));
        String password = null;
        
        while(true){
            password = sc.next();

            if(verifypassword(password) != null) return password;;
                
            }
    }

    // 登录时获取密码
    public String verifypassword1(Scanner sc){
        System.out.println(Messages.t("please input password: "));
        String password = null;

        // 验证码

        while(true){
            password = sc.next();

            testverifycode(sc);

            if (password == null) {
                System.out.println(Messages.t("Password cannot be null. Please enter a valid password."));
            } else if (password.length() < 6 || password.length() > 20) {
                System.out.println(Messages.t("Invalid password. Please enter a valid password (6-20 characters)."));
            } else if (!password.matches("^[a-zA-Z0-9]+$")) {
                System.out.println(Messages.t("Invalid password. Please enter a valid password (letters and numbers only)."));
            } else if (password.matches("^[0-9]+$")) {
                System.out.println(Messages.t("Invalid password. Please enter a valid password (cannot be numbers only)."));
            } else {
                    return password;
                }

            }
    }

    // 检验用户名是否存在,存在返回索引,不存在为-1
    public int findindex(ArrayList<User> users,String username)
    {
        for(int i = 0;i < users.size();i++)
        {
            User u = users.get(i);
            if(u.getAccount().equals(username)) return i;
        }
        return -1;
    }
    
    // 产生随机验证码
    public String verifycode()
    {
        ArrayList<Character> list = new ArrayList<>();
        for(int i = 0;i < 26;i++)
        {
            list.add((char)('a'+i));
            list.add((char)('A'+i));
        }

        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        
        for(int i = 0;i < 4;i++)
        {
            int index = r.nextInt(52);
            sb.append(list.get(index));
        }
        sb.append(r.nextInt(10));
        char[] arr = sb.toString().toCharArray();
        int i = r.nextInt(arr.length);
        char te = arr[4];
        arr[4] = arr[i];
        arr[i] = te;
        String raw = new String(arr);
        return raw;
    }
    
    // 检测验证码
    public void testverifycode(Scanner sc)
    {
        while(true)
            {
                String rcode = verifycode();
                System.out.println(rcode);

                System.out.println(Messages.t("Please type the verification code (case-insensitive):"));
                String incode = sc.next();
                if(rcode.equalsIgnoreCase(incode)) 
                {
                    System.err.println(Messages.t("Correct!"));
                    break;
                }
                else System.err.println(Messages.t("Incorrect! Please try again."));
            }
    }

    // 检测账户是否锁定
    public Boolean findstate(ArrayList<User> users,String username)
    {
        if (users.get(findindex(users, username)).getState() == true) return true;
        
        return false;
    }

    // 确定密码,注册时
    public Boolean confirmPassword(String password,Scanner sc)
    {
        String confirmPassword = null;

            System.out.println(Messages.t("please confirm your password: "));
            confirmPassword = sc.next();

            if (!password.equals(confirmPassword)){
        
                System.out.println(Messages.t("Passwords do not match. Please try again."));
                return false;
            }
            
        return true;
    }
    
    public void exit(){
        
        System.out.println(Messages.t("exit"));
        System.exit(0);
    }
}
