import java.util.Scanner;

/**
 * Challenge #350 Balance My Spending
 * link: https://www.reddit.com/r/dailyprogrammer/comments/7vx85p/20180207_challenge_350_intermediate_balancing_my/
 *
 * Description
 *
 * Given my bank account transactions - debits and credits - as a sequence of integers, at what points do my behaviors show the same sub-sums of all transactions before or after. Basically can you find the equilibria points of my bank account?
 *
 * Input Description
 *
 * You'll be given input over two lines. The first line tells you how many distinct values to read in the following line. The next line is sequence of integers showing credits and debits. Example:
 *
 * 8
 * 0 -3 5 -4 -2 3 1 0
 *
 * Output Description
 *
 * Your program should emit the positions (0-indexed) where the sum of the sub-sequences before and after the position are the same. For the above:
 * 0 3 7
 */


public class BalanceSpending {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] transactions = new int[length];

        for (int i = 0; i < transactions.length; i++) {
            transactions[i] = in.nextInt();
        }

        //O(n) solution
        int[] totals = new int[length];
        int sum = 0;
        for (int i = 1; i < length; i++) {
            sum += transactions[i - 1];
            totals[i] += sum;
        }

        sum = 0;
        for (int i = length - 2; i >= 0; i--) {
            sum += transactions[i + 1];
            totals[i] -= sum;
        }

        for (int i = 0; i < length; i++) {
            if (totals[i] == 0) {
                System.out.print(i + " ");
            }
        }
    }


}