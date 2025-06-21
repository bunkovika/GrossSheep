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


# Inventory

Když hráč hraje hru a chce otevřít inventory, může kliknout na "I" a objeví se inventory. Aby mohl zavřít inventář a pokračovat ve hře, může kliknout ("ESCAPE" nebo "I"). 
Se vstupem "E" si hráč může předmět typu (weapon) vzít a poté, když hráč zadá klávesu "SPACE", může jej spotřebovat. Například si může vzít maso kliknutím na „E“ a zabít vlka kliknutím na „SPACE“.
Se vstupem "C" si hráč může předmět typu (food) spotřebovat a mů se přida HP.

![image](uploads/91a5954f5cedf2b6c719971ae7cdbadc/image.png)

# End game

Pokud ovce zemře, hra začne znovu

![image](uploads/7b9103468f201fa2738d294fb31c27ac/image.png)

# Win game

Pokud ovečka vyhraje, otevře se okno:

![image](uploads/3375802c6ad723a523b499b2d98cd009/image.png)

# Technický popis:


# Kostry hlavních tříd
![package](uploads/ce373cad6d933b32bb0d93e3d50c4d70/package.png)


# Projekt je navržen pomocí paternu MVC:
**Model:** Sprite, Sheep, Wolf <br>
**View:** View(abstraktní), MainMenu, GameField, GameMenu, GameRules, Inventory, GameOver<br>
**Controller:** Controller(abstraktní), MainMenuController, GameFieldController, GameMenuController, GameRulesController, InventoryController, GameOverController.<br>

- Existuje několik tříd konfigurace, které spravuje hru (**„StateManager“** – změna obrazovek a práce s gameLoopem, **„SpriteManager“** – přejíždění skřítků (ovce, vlk), **„WindowConfig“** – odpovídá za velikost otevřených obrazovek, **„ Tile"** - má podrobný popis jedné dlaždice mapy dlaždic (pozice, obrázek). Hodilo by se to pro uložení hry. 
- Existuje také map.txt, který má schéma dlaždicové mapy, která se skládá z několika písmen( "A" a "C"), které představovaly přístupné a nepřístupné dlaždice.
- Pro grafiku se používá knihovna JavaFX.
- Verze javy 1.8
