# Java Fighting Game (Console)

A simple turn-based console fighting game written in Java. It features an account
system (login / register / guest play) and a battle loop where you fight waves of
enemies, spend skill points, and grow stronger as you win.

> **Bilingual:** the game supports **English / 中文** — pick a language at startup.
> 中文说明见 [`README.zh.md`](README.zh.md).
> A visual project page is available at [`index.html`](index.html).

## Features

- **Account system** — register a new account or log in with an existing one.
  - Account rules: 3–16 characters, letters and numbers only, not numbers-only.
  - Password rules: 6–20 characters, letters and numbers only, not numbers-only.
  - A captcha is shown on login.
  - Accounts are locked after 3 failed password attempts.
- **Guest mode** — play immediately without registering.
- **Character creation** — allocate 20 skill points across HP / attack / defence.
- **Turn-based combat** — fight randomly selected enemies with three actions:
  1. Attack
  2. Super attack (costs 10 HP, deals heavy damage)
  3. Heal
- **Progression** — enemies grow stronger each round; win 3 times in a row for an
  attribute boost.
- **Bilingual UI** — choose English or 中文 at startup; all text switches via a tiny
  `i18n.Messages` helper.

## Requirements

- Java JDK 8 or newer (tested with JDK 25).

## How to build and run

From the `demo` directory:

```bash
# Compile
javac -d bin src/App.java src/i18n/*.java src/domain/*.java src/ui/*.java

# Run
java -cp bin App
```

Or open the `demo` folder in Visual Studio Code with the Java Extension Pack and
use the provided launch configuration.

## How to play

1. Choose a language at startup: `1` = English, `2` = 中文.
2. Choose an option from the login menu: `1` login, `2` register, `3` play as
   guest, `4` exit.
3. Allocate your 20 skill points when creating a character.
4. Each turn, pick an action (`1` / `2` / `3`). Type `y`/`n` to continue or quit
   between battles.

## Project structure

```
demo/
├── src/
│   ├── App.java              # Entry point (language selection)
│   ├── i18n/                 # Bilingual text (Messages)
│   ├── domain/               # Game model (Character, Hero, Enemy, User)
│   └── ui/                   # Login and FightingGame (game loop)
├── lib/                      # Dependencies (currently none)
└── bin/                      # Compiled output (git-ignored)
```

## Notes

- All progress is in-memory; accounts are not persisted between runs.
- See `OPTIMIZATION.md` for known issues and improvement ideas.
