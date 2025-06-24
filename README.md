# Gross Sheep - Java Game

<img src="https://github.com/user-attachments/assets/feb9cb84-c101-4b99-9ce7-50ddaee70c90" alt="meeee" width="300" height="300">

# Téma projektu:

Tématem projektu je vytvoření herního enginu pro RPG hru s 2D pohledem shora.

Veškeré ovládání ve hře bude pomocí klávesnice. Hráč (růžová ovce) bude mít inventář k uložení různé předměty a také bude moci některé z nich vybavit, aby se zlepšily jejich vlastnosti (ochrana nebo poškození).

Ve hře bude také vlk, se kterým bude moci bojovat růžová ovečka.

# Cíle projektu:

- Navrhnout a vyvinout herní engine s intuitivním uživatelským rozhraním
který umožňuje uživateli vytvářet jednoduché hry s minimálními změnami
do kódu zadáním konfigurace.

- Naučit se OOP.
- Naučit se JavaFX

# Basic user documentation:

# Main Menu
Jakmile uživatel spustí aplikaci, zobrazí se hlavní nabídka.

<img width="500" alt="2" src="https://github.com/user-attachments/assets/7f8193bf-ba4e-46eb-9496-a3ed79f4caf7" />

# Game Field 

Když hráč otevře hru, může vidět pole hry s HP boxem.

<img width="500" alt="3" src="https://github.com/user-attachments/assets/22a0873e-6eb5-4b74-a586-7a62f0286012" />


# Game Menu

Když hráč hraje hru a chce otevřít menu, může kliknout na "ESCAPE" a objeví se hlavní menu.V nabídce GameMenu může hráč pokračovat ve hře, uložit hru nebo přečíst si pravidla hry.

<img width="500" alt="4" src="https://github.com/user-attachments/assets/f4ec4b18-fa01-48cb-981c-7958b55ab40c" />


# Game Rules

Hráč může otevřít pravidla hry v menu hry nebo v hlavním menu.
Chcete-li zavřít pravidla hry, hráč by měl kliknout na "ESC".

<img width="500" alt="5" src="https://github.com/user-attachments/assets/e144ec83-b9b0-4b43-b15c-ef444f321930" />


# Inventory

Když hráč hraje hru a chce otevřít inventory, může kliknout na "I" a objeví se inventory. Aby mohl zavřít inventář a pokračovat ve hře, může kliknout ("ESCAPE" nebo "I"). 
Se vstupem "E" si hráč může předmět typu (weapon) vzít a poté, když hráč zadá klávesu "SPACE", může jej spotřebovat. Například si může vzít maso kliknutím na „E“ a zabít vlka kliknutím na „SPACE“.
Se vstupem "C" si hráč může předmět typu (food) spotřebovat a mů se přida HP.

<img width="500" alt="6" src="https://github.com/user-attachments/assets/68c6d9f7-02df-4651-ab16-1667721d41ae" />


# End game

Pokud ovce zemře, hra začne znovu

<img width="500" alt="7" src="https://github.com/user-attachments/assets/1b8c211d-185f-43e7-932b-e51ccfbaa7f5" />


# Win game

Pokud ovečka vyhraje, otevře se okno:

<img width="500" alt="8" src="https://github.com/user-attachments/assets/25f2e0a3-111d-453d-a37a-42fc8d71f5e7" />


# Technický popis:


# Kostry hlavních tříd

![9](https://github.com/user-attachments/assets/37aa5229-0fac-476e-9f45-ec61783fdeaf)


# Projekt je navržen pomocí paternu MVC:
**Model:** Sprite, Sheep, Wolf <br>
**View:** View(abstraktní), MainMenu, GameField, GameMenu, GameRules, Inventory, GameOver<br>
**Controller:** Controller(abstraktní), MainMenuController, GameFieldController, GameMenuController, GameRulesController, InventoryController, GameOverController.<br>

- Existuje několik tříd konfigurace, které spravuje hru (**„StateManager“** – změna obrazovek a práce s gameLoopem, **„SpriteManager“** – přejíždění skřítků (ovce, vlk), **„WindowConfig“** – odpovídá za velikost otevřených obrazovek, **„ Tile"** - má podrobný popis jedné dlaždice mapy dlaždic (pozice, obrázek). Hodilo by se to pro uložení hry. 
- Existuje také map.txt, který má schéma dlaždicové mapy, která se skládá z několika písmen( "A" a "C"), které představovaly přístupné a nepřístupné dlaždice.
- Pro grafiku se používá knihovna JavaFX.
- Verze javy 1.8
