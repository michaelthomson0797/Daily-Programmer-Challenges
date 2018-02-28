import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Challenge #351 Permutation Madness
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/7xkhar/20180214_challenge_351_intermediate_permutation/
 *
 * ---Description---
 * A group of programs has gotten together in a line and have started dancing
 * They appear to have 3 dance moves.
 * - Try Spin: that's a good trick, the right end swaps up most left keeing their order
 * - Exchange: Two programs appear to swap depending on the numbers given
 * - Partner: Two programs that know eachother swaps
 *
 * ---Input Description---
 * a list of programs in their initial order
 * First you will be given a string of characters, each character is an individual program
 * On the next line you will get a list of moves split by ,
 * The moves work as following:
 * - Spin is given as SN where N is a positive integer. This moves N programs from the right up front, keeping their order
 * - Exchange is given as xA/B where A and B are the positions of two programs that will swap positions
 * - Partner is given as pA/B where A and B refer to the original positions of the programs and swaps them whereever they currently are
 *
 * ---Input Example---
 * abcde
 * s1,x3/4,p4/1
 *
 * ---Output---
 * baedc
 */
public class PermutationMadness {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //save original copy of programs
        final ArrayList<String> originalPrograms = new ArrayList<String>(Arrays.asList(in.nextLine().split("")));

        //make an editable copy of programs and read commands list
        ArrayList<String> programs = new ArrayList<String>(originalPrograms);
        ArrayList<String> commands = new ArrayList<String>(Arrays.asList(in.nextLine().split(",")));

        //read all commands
        for (int i = 0; i < commands.size(); i++) {
            String command = commands.get(i);

            int[] positions = parseCommand(command);

            switch (command.charAt(0)) {
                case 's':
                    spin(programs, positions[0]);
                    break;
                case 'x':
                    exchange(programs, positions[0], positions[1]);
                    break;
                case 'p':
                    partner(programs, originalPrograms, positions[0], positions[1]);
                    break;
            }
        }

        //print output
        for (int i = 0; i < programs.size(); i++) {
            System.out.print(programs.get(i) + " ");
        }

    }

    /**
     * takes a command and returns the number associated to it
     *
     * @param command command to be parsed
     * @return array of numbers associated to the command
     */
    public static int[] parseCommand(String command) {
        String[] positionStrings = command.substring(1).split("/");

        if (command.charAt(0) == 's') {
            int[] positions = {Integer.parseInt(positionStrings[0])};
            return positions;
        } else {
            int[] positions = {Integer.parseInt(positionStrings[0]), Integer.parseInt(positionStrings[1])};
            return positions;
        }
    }

    /**
     * Spins the given arraylist by a given number of times
     *
     * @param programs  list of programs to spin
     * @param rotations how many times to spin
     */
    public static void spin(ArrayList<String> programs, int rotations) {
        for (int i = 0; i < rotations; i++) {
            programs.add(0, programs.get(programs.size() - 1));
            programs.remove(programs.size() - 1);
        }
    }

    /**
     * Swaps the position of two elements in an arraylist
     *
     * @param programs arraylist of programs
     * @param pos1     first index of element to be swapped
     * @param pos2     second index of element to be swapped
     */
    public static void exchange(ArrayList<String> programs, int pos1, int pos2) {
        Collections.swap(programs, pos1, pos2);
    }

    /**
     * finds the programs at pos1 and pos2 in the original program list and swaps them wherever they are now
     *
     * @param programs         current program order
     * @param originalPrograms original program order
     * @param pos1             index 1 of element in original programs
     * @param pos2             index 2 of element in original programs
     */
    public static void partner(ArrayList<String> programs, ArrayList<String> originalPrograms, int pos1, int pos2) {
        String p1 = originalPrograms.get(pos1);
        String p2 = originalPrograms.get(pos2);

        for (int i = 0; i < programs.size(); i++) {
            if (programs.get(i).equals(p1)) {
                pos1 = i;
            } else if (programs.get(i).equals(p2)) {
                pos2 = i;
            }
        }

        exchange(programs, pos1, pos2);
    }
}
