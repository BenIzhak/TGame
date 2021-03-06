# TGame

Game Flow:
1. The player's goal is to kill all monsters in every world (map).
2. If the player kills the monsters in one world he is automatically moved to the next world (map) if available.
3. The player can teleport himself between trees. To do that the player needs to stand near a tree and press the up key. Use trees to teleport yourself to higher ground.
4. If the player health points (HP) is 0 the game over.
5. The player has stamina points (SP), if the SP is greater than zero the player can run using the SHIFT key. 
6. If the player got all the experience points (EXP) his HP set to max and level up. 

Build A New Map:
1. The map name should be "world$", $ is the map index start in 1.
2. First line contains two numbers. The first one is the map width. the second one is the map height.
3. The second line contains two numbers also, the player's starting point. The first one is the x coordinate and the second one is the y coordinate. The left upper corner is x = 0, y = 0.  
4. The next lines contain the map structure, use the next dictionary:
0 = air, 1 = left corner, 2 = ground, 3 = right corner, 4 = upper left corner, 5 = upper middle corner, 6 = upper right corner, 7 = saved for future use, 8 = tree, 9 = monster starting point.

Credit:
1. Sprites taken from: https://www.gameart2d.com
2. Music: Ornery Brunt (New Combination) by Speck (c) copyright 2019 Licensed under a Creative Commons Attribution (3.0) license. http://dig.ccmixter.org/files/speck/59700 Ft: Martijn de Boer, Apoxode

Things to add:
1. Player attack animation.
2. Add metadata for world (music, max time)
3. Add sound effects


Images:

![Game screenshot](res/readmeimages/Tgame_img1.jpg?raw=true "Game screenshot")