import ui.FightingGame;
import ui.Login;

import java.util.ArrayList;

import domain.User;

public class App {
    public static void main(String[] args){
        // test the account uniqueness
        User user = new User("zhangs","s123456");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        Login login = new Login();
        login.start(users);
        
        // FightingGame fg = new FightingGame();
        // fg.gameStart("zhangs");
    }
}
