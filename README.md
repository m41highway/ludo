# Ludo
Calculate the number of possible ways to win a Ludo

This program counts all the permutations of steps (dice outcomes) to
reach the destination (win). It similates all the steps in reclusive fashion,
and use a cache (remaining steps as the key, number of win as the value)
to prevent revisiting paths.
Usage: java Ludo {number of steps from the destination}