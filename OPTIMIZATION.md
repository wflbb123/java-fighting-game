# Optimization & Known Issues

This document lists issues found in the current code, ordered roughly by impact.
Most are easy wins; a few require design decisions. Nothing here blocks the game
from running — it compiles and plays — but addressing these will make the project
cleaner and safer to publish.

## Bugs (should fix)

1. **`show()` result is discarded in `FightingGame.gameStart`**
   `System.out.print("you attribute: "); player.show();` calls `show()` but never
   prints its return value, so the post-battle attributes are never shown.
   Fixed in the current revision to `System.out.println("Your attributes: " + player.show());`.

2. **Heal action text vs. behavior mismatch (`FightingGame.playerTurn`, option 3)**
   The original menu said "cost 10hp, recover 10-20hp", but the code neither
   deducts HP nor recovers 10–20 (it recovers `0–10`). The current revision makes
   the text truthful (just "Heal"), but the intended design (cost 10 HP, recover
   10–20) is still not implemented. Decide whether heal should cost HP and fix both
   the code and the message together.

3. **`Login.start` recurses on invalid input**
   The `default` branch calls `start(users)` again, and option `3` (play as guest)
   falls through to `default`, so after a guest game it prints "invalid input" and
   recurses. Repeated bad input can grow the call stack. Replace the recursion with
   a plain `continue`/`while` loop.

4. **No numeric input validation (`sc.nextInt()`)**
   In `creatPlayerCharacter`, `sc.nextInt()` crashes with `InputMismatchException`
   if the user types a non-number. Wrap reads in a helper that re-prompts on bad input.

## Code quality / style

5. **Naming conventions**
   - `Max_Hp` (snake_case field) → `maxHp` (camelCase).
   - `creatPlayerCharacter` (typo) → `createPlayerCharacter`.
   - `showskill` / `showSkill` → `showSkills`.
   Java standard is camelCase for methods and fields.

6. **`Character` shadows `java.lang.Character`**
   The domain class `Character` hides the JDK type. Rename to `GameCharacter` /
   `BaseCharacter` to avoid confusion (e.g. `ArrayList<Character>` in
   `Login.verifycode` actually means `java.lang.Character`).

7. **Multiple `Scanner(System.in)` instances**
   New `Scanner` objects are created in `Login.start`, `creatPlayerCharacter`, and
   `playerTurn`. Reuse a single `Scanner` passed through, or close them properly.

8. **`System.err` used for normal output**
   Welcome banners, prompts, and the win/lose summaries use `System.err`. Use
   `System.out` for normal messages and reserve `System.err` for errors.

9. **`System.exit(0)` inside game logic**
   `FightingGame.gameStart` and `Login.exit` call `System.exit(0)`, which ends the
   whole JVM. After a game, control never returns to the login menu. Prefer
   returning from `gameStart` and letting `main` decide what to do next.

10. **Unused code**
    `Login.findstate` and the commented-out block in `App.main` are dead code.
    `App.main` also seeds a hardcoded test account (`zhangs` / `s123456`); remove it
    or move it behind a clear demo flag before publishing.

## Design / security

11. **Captcha shown in plaintext every login**
    The captcha is printed directly to the console, which defeats its purpose. For a
    real login it should be displayed in a GUI/HTML surface, not echoed in cleartext.

12. **`User.setId()` is not unique**
    IDs use `Random` in `10000–99999`, so collisions are possible and uniqueness is
    never checked. For anything beyond a demo, use a UUID or a persistent counter.

13. **No persistence**
    Accounts and progress live only in memory; a restart loses everything. Consider a
    file or database if this should be a real game.

14. **Chinese comments in source**
    Comments are written in Chinese. For an international open-source release, English
    comments (or bilingual) would be more accessible.

## Build / repo hygiene

15. **`demo/README.md` was the default VS Code template**
    Replaced with a real project README. A `LICENSE` file is recommended before
    publishing so others know how they may use the code.
