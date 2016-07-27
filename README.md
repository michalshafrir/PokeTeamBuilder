# PokeTeamBuilder
**How to run:**

Run the Main file in any IDE and interact with the command line to input what kind of Pokémon you’re looking for in a team.

**Team Balancing:**

My code is intended to take in user input for what factors they desire to be emphasized in a Pokémon team, whether they want it to be mostly Fire types, mostly blue Pokémon, or mostly Pokémon with high defense stats, and weighs a Pokémon’s ability to fit these criteria with their given Pokémon ranking. This generates a sorted list of the strongest Pokémon that suit the user’s requirements. From this list, I determine the most balanced team you could make. I keep in mind that sometimes compromise is necessary to ensure the strongest team, so maybe only 5/6 Pokémon are blue but they all have high defense. This program iterates through the sorted list of suitable Pokémon and checks if its presence in the team will improve or worsen the balance, and swaps out weaker Pokémon accordingly. 

**My Ranking Methods:**

For each of a user’s criteria a Pokémon meets, they are given higher presence on a generated list. However, it’s important to note that a weak and essentially useless Pokemon such as a Magikarp fulfills the criteria of “Water Type” just as much as the powerful Kyogre. Or there may be situations where you’re ranking based on type and color, and decide you like pink water-type Pokémon, such as luvdisc, but it’s not as powerful as many other water-type or pink Pokémon that should likely be considered more seriously for a strong competitive Pokémon team. This is why my program only has user criteria as a portion of the team-building algorithm. The rest of the selection process considers a Pokémon’s rank (calculations described below) and effects of the team’s overall strengths and weaknesses to great deal when adding them to a generated team.

**Pokémon Rank:**

A Pokémon’s rank is calculated based on multiple factors: stats (60%), moves (25%), and a ratio of its strengths to weaknesses (15%). The move scores of the Pokémon’s top 10 scoring moves are used in this calculation, and the end result is that a Pokémon will have a high rank value if it has high total stats, powerful moves, and has more strengths than it has weaknesses. My code assigns the highest rankings to legendary Pokémon at the top of the highest tier in competitive play, so I know this is a good measurement of powerful Pokémon.

**Move Scores:**

Each move has a score based on its power/effects, PP, and accuracy. A Physically damaging move, for example, would be assigned a score that’s 70% power (normalized by 100), 30% PP (normalized by 35), and then multiplied by the accuracy rate. So a strong move like Hyper Beam would have very strong power (150/100), a low PP (5/35) and then a fair accuracy rate of 90%. This allows me to balance the ultra strong moves with the fairly strong moves due to the variance in PP and accuracy, resulting in a relatively normalized Move Score.

#Some Example Teams:

Note: My program built the team itself but visual type chart is taken from http://www.azurilland.com/tools/team-builder

**All Bug Type Team:**

Slot 1: volcarona

Slot 2: durant

Slot 3: forretress

Slot 4: accelgor

Slot 5: scizor

Slot 6: escavalier

Your team's collective balance (positive means more pokemon are resistant to the type than there are pokemon weak to it & vice versa):

{DARK=0.0, ROCK=-2.5, STEEL=3.5, ELECTRIC=0.0, GROUND=1.75, FAIRY=4.5, POISON=2.5, ICE=2.5, FLYING=-2.75, DRAGON=3.0, BUG=4.5, FIGHTING=3.25, FIRE=-3.25, GHOST=0.0, GRASS=4.25, WATER=0.0, PSYCHIC=2.5, NORMAL=3.0}

Your team's balance score: 26.75

![Image](http://i.imgur.com/YbCMxrI.png?1?raw=true)




**Team built with no user generated criteria:**

Slot 1: jirachi

Slot 2: ho-oh

Slot 3: zygarde

Slot 4: xerneas

Slot 5: yveltal

Slot 6: registeel

Your team's collective balance (positive means more pokemon are strong to the type than weak to it & vice versa):

{DARK=0.0, ROCK=1.25, STEEL=2.0, ELECTRIC=1.5, GROUND=-2.0, FAIRY=1.0, POISON=2.5, ICE=1.25, FLYING=2.0, DRAGON=0.5, BUG=1.75, FIGHTING=-0.5, FIRE=0.75, GHOST=0.5, GRASS=4.0, WATER=0.5, PSYCHIC=2.5, NORMAL=2.0}

Your team's balance score: 21.5

![Image](http://i.imgur.com/8JBvp5B.png?raw=true)



