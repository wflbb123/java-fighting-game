package i18n;

import java.util.HashMap;
import java.util.Map;

/**
 * Tiny bilingual (English / Chinese) message helper.
 *
 * The key IS the English text, so English is the implicit default. The ZH map
 * provides the Chinese translation for each key. When the current language is
 * "zh" and a translation exists, it is returned; otherwise the (English) key is
 * returned unchanged.
 */
public class Messages {
    private static String lang = "en"; // "en" or "zh"
    private static final Map<String, String> ZH = new HashMap<>();

    static {
        // --- Login / shared ---
        ZH.put("Welcome to the login page!", "欢迎来到登录页面！");
        ZH.put("Choose an option: 1. Login   2. Register   3. Play as guest   4. Exit",
                "请选择：1. 登录   2. 注册   3. 游客游玩   4. 退出");
        ZH.put("Playing as guest", "以游客身份游玩");
        ZH.put("Invalid input. Please try again.", "输入无效，请重试。");
        ZH.put("login", "登录");
        ZH.put("register", "注册");
        ZH.put("Account does not exist. Please type again.", "账号不存在，请重新输入。");
        ZH.put("Your account has been locked. Please contact your administrator or log in with another account.",
                "你的账号已被锁定，请联系管理员或换一个账号登录。");
        ZH.put("Account found. Please enter your password.", "账号已找到，请输入密码。");
        ZH.put("Incorrect password. Please try again.", "密码错误，请重试。");
        ZH.put("You have entered the wrong password 3 times. Your account is now locked. Please contact your administrator.",
                "密码连续输错 3 次，账号已被锁定，请联系管理员。");
        ZH.put("Login successful! The game will start shortly.", "登录成功！游戏即将开始。");
        ZH.put("Account available. Please enter your password.", "账号可用，请输入密码。");
        ZH.put("Registration successful. Please log in.", "注册成功，请登录。");
        ZH.put("please input your account", "请输入账号");
        ZH.put("Account cannot be null. Please enter a valid account (3-16 characters).",
                "账号不能为空，请输入有效账号（3-16 个字符）。");
        ZH.put("Invalid account. Please enter a valid account (3-16 characters).",
                "账号无效，长度需为 3-16 个字符。");
        ZH.put("Invalid account. Please enter a valid account (letters and numbers only).",
                "账号无效，只能包含字母和数字。");
        ZH.put("Invalid account. Please enter a valid account (cannot be numbers only).",
                "账号无效，不能全是数字。");
        ZH.put("please input password: ", "请输入密码：");
        ZH.put("Password cannot be null. Please enter a valid password.",
                "密码不能为空，请输入有效密码。");
        ZH.put("Invalid password. Please enter a valid password (6-20 characters).",
                "密码无效，长度需为 6-20 个字符。");
        ZH.put("Invalid password. Please enter a valid password (letters and numbers only).",
                "密码无效，只能包含字母和数字。");
        ZH.put("Invalid password. Please enter a valid password (cannot be numbers only).",
                "密码无效，不能全是数字。");
        ZH.put("please confirm your password: ", "请确认密码：");
        ZH.put("Passwords do not match. Please try again.", "两次密码不一致，请重试。");
        ZH.put("Please type the verification code (case-insensitive):", "请输入验证码（不区分大小写）：");
        ZH.put("Correct!", "正确！");
        ZH.put("Incorrect! Please try again.", "错误！请重试。");
        ZH.put("exit", "退出");
        ZH.put("Account already exists. Please choose a different account.",
                "账号已存在，请更换账号。");

        // --- FightingGame ---
        ZH.put("Welcome, {0}!!!!", "欢迎，{0}！！！！");
        ZH.put("Character created successfully!", "角色创建成功！");
        ZH.put("Your original attributes are: {0}", "你的初始属性是：{0}");
        ZH.put("Your original skills are: {0}", "你的初始技能是：{0}");
        ZH.put("Your enemy is: {0}", "你的敌人是：{0}");
        ZH.put("Game Start", "游戏开始");
        ZH.put("Count {0}", "第 {0} 轮");
        ZH.put("Round {0}", "第 {0} 回合");
        ZH.put("You defeated {0}! You win!!!", "你击败了 {0}！你赢了！！！");
        ZH.put("You lost!!!", "你输了！！！");
        ZH.put("You recovered {0} HP", "你恢复了 {0} 点生命值");
        ZH.put("You have won 3 times! You will receive an attribute boost.",
                "你已连胜 3 场！将获得属性提升。");
        ZH.put("+30 HP, +5 attack, +3 defence", "+30 生命值，+5 攻击，+3 防御");
        ZH.put("Your attributes: {0}", "你的属性：{0}");
        ZH.put("Would you like to continue? (y/n)", "是否继续？（y/n）");
        ZH.put("Exit", "退出");
        ZH.put("Invalid input. Continuing by default.", "输入无效，默认继续。");
        ZH.put("Game over!", "游戏结束！");
        ZH.put("You have defeated {0} enemies!!!", "你总共击败了 {0} 个敌人！！！");
        ZH.put("Thank you for playing!", "感谢游玩！");
        ZH.put("Create your character: ", "创建你的角色：");
        ZH.put("Your name is: {0}", "你的名字是：{0}");
        ZH.put("You have 20 points. Please allocate skill points. (You have basic attributes.)",
                "你有 20 点属性点，请分配。（你拥有基础属性。）");
        ZH.put("Allocate your {0}: ", "分配你的{0}：");
        ZH.put("Cannot be negative! Allocating 0 points by default.", "不能为负数！默认分配 0 点。");
        ZH.put("Exceeds available points! Allocating all remaining points.", "超出剩余点数！分配全部剩余点数。");
        ZH.put("Your turn", "你的回合");
        ZH.put("Choose your action:\n1. Attack\n2. Super attack (costs 10 HP, deals heavy damage)\n3. Heal\n",
                "请选择行动：\n1. 攻击\n2. 强力攻击（消耗10生命值，造成大量伤害）\n3. 治疗\n");
        ZH.put("Invalid choice. Defaulting to attack.", "选择无效，默认使用攻击。");
        ZH.put("You chose attack. You dealt {0} damage to {1}.", "你选择了攻击。你对 {1} 造成了 {0} 点伤害。");
        ZH.put("You chose super attack.", "你选择了强力攻击。");
        ZH.put(" You lost 10 HP.", " 你失去了 10 点生命值。");
        ZH.put("You dealt {0} damage to {1}.", "你对 {1} 造成了 {0} 点伤害。");
        ZH.put(" Your HP is 10 or below; you cannot use this.", " 你的生命值不高于 10，无法使用。");
        ZH.put("You chose heal.", "你选择了治疗。");
        ZH.put(" You recovered {0} HP.", " 你恢复了 {0} 点生命值。");
        ZH.put("_____ {0}'s turn_____", "_____ {0} 的回合 _____");
        ZH.put("{0} uses {1}", "{0} 使用了 {1}");
        ZH.put("{0} deals {1} damage to you", "{0} 对你造成了 {1} 点伤害");
        ZH.put("{0} reduces incoming damage by 50% for one turn.", "{0} 本回合伤害减免 50%。");

        // --- Character.show labels ---
        ZH.put("name: ", "名称：");
        ZH.put(" hp: ", " 生命：");
        ZH.put(" attack: ", " 攻击：");
        ZH.put(" defence: ", " 防御：");
        ZH.put("list.separator", "、");
    }

    /** Set the active language. Anything but "zh"/"cn" is treated as English. */
    public static void setLang(String l) {
        lang = ("zh".equalsIgnoreCase(l) || "cn".equalsIgnoreCase(l)) ? "zh" : "en";
    }

    public static String getLang() {
        return lang;
    }

    /** Return the localized text for the given key (English is the default). */
    public static String t(String key) {
        if ("zh".equals(lang)) {
            String z = ZH.get(key);
            if (z != null) {
                return z;
            }
        }
        return key;
    }

    /** Like {@link #t(String)} but replaces {0}, {1}, ... with the arguments. */
    public static String tf(String key, Object... args) {
        String s = t(key);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                s = s.replace("{" + i + "}", String.valueOf(args[i]));
            }
        }
        return s;
    }
}
