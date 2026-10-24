import ui.FightingGame;
import ui.Login;

import java.util.ArrayList;
import java.util.Scanner;

import domain.User;
import i18n.Messages;

public class App {
    public static void main(String[] args){
        // test the account uniqueness
        User user = new User("zhangs","s123456");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        // language selection (bilingual support)
        System.out.println("Language / 语言: 1. English   2. 中文");
        Scanner langSc = new Scanner(System.in);
        String lc = langSc.next();
        if ("2".equals(lc)) Messages.setLang("zh");
        else Messages.setLang("en");

        Login login = new Login();
        login.start(users);
        
        // FightingGame fg = new FightingGame();
        // fg.gameStart("zhangs");
    }
}
