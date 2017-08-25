import java.math.BigInteger;
import java.util.HashMap;
// -------------------------------------------------------------------------
// This program counts all the permutations of steps (dice outcomes) to
// reach the destination (win). It similates all the steps in reclusive fashion,
// and use a cache (remaining steps as the key, number of win as the value)
// to prevent revisiting paths.
// Usage: java Ludo {number of steps from the destination}
// -------------------------------------------------------------------------
class Ludo {
    static BigInteger remainSteps;
    static BigInteger TWO = new BigInteger("2");
    static BigInteger THREE = new BigInteger("3");
    static BigInteger FOUR = new BigInteger("4");
    static BigInteger FIVE = new BigInteger("5");
    static BigInteger SIX = new BigInteger("6");
    static BigInteger totalWin = BigInteger.ZERO;

    // ------------------------------------------
    // key: remaining steps
    // value: successful paths
    // ------------------------------------------
    static HashMap<BigInteger, BigInteger> winCache = new HashMap<>();

    static BigInteger move(BigInteger remainSteps) {
        if (remainSteps.compareTo(BigInteger.ZERO) > 0) { // not reach yet, roll the dice again
            BigInteger win = winCache.get(remainSteps);

            if (win == null) {
                BigInteger path1 = move(remainSteps.subtract(BigInteger.ONE));
                BigInteger path2 = move(remainSteps.subtract(TWO));
                BigInteger path3 = move(remainSteps.subtract(THREE));
                BigInteger path4 = move(remainSteps.subtract(FOUR));
                BigInteger path5 = move(remainSteps.subtract(FIVE));
                BigInteger path6 = move(remainSteps.subtract(SIX));

                win = path1.add(path2).add(path3).add(path4).add(path5).add(path6);

                winCache.put(remainSteps, win); // update cache with number of wins at the current distance
            } else {
                totalWin = totalWin.add(win);
            }
            return win;

        } else if (remainSteps.compareTo(BigInteger.ZERO) == 0) { // win
            totalWin = totalWin.add(BigInteger.ONE);
            return BigInteger.ONE;
        } else { // lose
            return BigInteger.ZERO;
        }
    }

    public static void main (String args []) {
        try {
            if (args.length != 1) throw new Exception("Please input a parameter");
            BigInteger distance = new BigInteger(args[0]);
            if (distance.compareTo(BigInteger.ZERO) < 0) throw new Exception ("Please input a positive integer");

            move(distance);

            System.out.println("Success=" + totalWin );
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}