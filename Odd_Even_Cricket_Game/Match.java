
// Match.java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class Match {
    private Team team1 = null;
    private Team team2 = null;
    private int overs;
    private Scanner sc;
    Random random;
    ArrayList<Integer> guess;
    ArrayList<Integer> userbat;
    ArrayList<Integer> bowlingIndex;
    ArrayList<Integer> team1Batting;
    ArrayList<Integer> team2Batting;
    ArrayList<Integer> team1Bowling;
    ArrayList<Integer> team2Bowling;
    private int team1Score = 0;
    private int team2Score = 0;
    private int team1Wickets = 0;
    private int team2Wickets = 0;
    private int team1Balls;
    private int team2Balls;
    ArrayList<Integer> team1BallFaced;
    ArrayList<Integer> team1BallBowled;
    ArrayList<Integer> team2BallFaced;
    ArrayList<Integer> team2BallBowled;

    public Match(Team team1, Team team2, int overs, Scanner sc) {
        this.team1 = team1;
        this.team2 = team2;
        this.sc = sc;
        this.overs = overs;
        this.random = new Random();
        this.guess = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6));
        this.userbat = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6));

        this.team1Batting = new ArrayList<Integer>(Collections.nCopies(this.team1.getPlayers().size(), 0));
        this.team1Bowling = new ArrayList<Integer>(Collections.nCopies(this.team1.getPlayers().size(), 0));
        this.team2Batting = new ArrayList<Integer>(Collections.nCopies(this.team1.getPlayers().size(), 0));
        this.team2Bowling = new ArrayList<Integer>(Collections.nCopies(this.team1.getPlayers().size(), 0));
        this.bowlingIndex = new ArrayList<>();
        for (int i = 0; i < overs / 2; i++) {
            bowlingIndex.add(team1.getPlayers().size() - i - 1);
        }
        if (overs % 2 == 1)
            this.bowlingIndex.add(team1.getPlayers().size() - 1 - overs / 2);
        for (int i = overs / 2 - 1; i >= 0; i--)
            this.bowlingIndex.add(team1.getPlayers().size() - i - 1);
        System.out.println();

        this.team1BallBowled = new ArrayList<Integer>(Collections.nCopies(this.team1.getPlayers().size(), 0));
        this.team1BallFaced = new ArrayList<Integer>(Collections.nCopies(this.team1.getPlayers().size(), 0));
        this.team2BallBowled = new ArrayList<Integer>(Collections.nCopies(this.team1.getPlayers().size(), 0));
        this.team2BallFaced = new ArrayList<Integer>(Collections.nCopies(this.team1.getPlayers().size(), 0));

    }

    private boolean handleToss() {
        System.out.println("\nLet's do the toss!\n");
        System.out.print(team1.getName() + " select Odd or Even (Odd/Even): ");
        String userChoice = sc.next();

        System.out.print(team1.getName() + " enter your number (0-6): ");
        int userNum;
        while (true) { // Infinite loop
            try {
                userNum = sc.nextInt();
                break; // Exit the loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine(); // Clear the invalid input from the scanner
            }
        }
        int compNum = random.nextInt(7);
        System.out.println(team2.getName() + "'s number: " + compNum);

        int sum = userNum + compNum;
        return (sum % 2 == 0 && userChoice.equalsIgnoreCase("Even"))
                || (sum % 2 != 0 && userChoice.equalsIgnoreCase("Odd"));
    }

    private int getValidInput(String s, int index, String name) {
        int input;
        System.out.print(name + "'s " + s + ": ");
        do {
            while (true) { // Infinite loop
                try {
                    input = sc.nextInt();
                    break; // Exit the loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    sc.nextLine(); // Clear the invalid input from the scanner
                }
            }
            if (input < 0 || input > 6) {
                System.out.println("Invalid input! Please select a number from 0-6:");
            }
        } while (input < 0 || input > 6);

        return input;
    }

    private int inn1(String s, int index, String name) {
        int g = guess.get(random.nextInt(guess.size()));
        System.out.println(name + "'s " + s + ": " + g);
        return g;
    }

    private int compBowling(String s, int index, String name) {
        int g = userbat.get(random.nextInt(guess.size()));
        System.out.println(name + "'s " + s + ": " + g);
        return g;
    }

    private int inn2(String str, int s, int r, int index, String name) {
        int g = random.nextInt(7);

        while (s / (r + 1) <= 5 && g < s / (r + 1) - 1)
            g = random.nextInt(7);

        System.out.println(name + "'s " + str + ": " + g);
        return g;
    }

    public void userBatting(int target) {
        System.out.println(team1.getName() + " is batting now.");
        if (target != -1)
            System.out.println(
                    "Target for  " + team1.getName() + " is " + target + " runs." + " in " + overs + " overs.");
        System.out.println();

        team1Score = 0;
        team1Wickets = 0;
        int over;
        int i = 0;

        int strikerIndex = 1;
        int nonStrikerIndex = 0;
        int temp;

        team1.getPlayers().get(nonStrikerIndex).displayBattingStats();
        team1.getPlayers().get(strikerIndex).displayBattingStats();
        System.out.println();

        for (over = 0; over < overs && team1Wickets < team1.getPlayers().size() - 1; over++) {
            temp = strikerIndex;
            strikerIndex = nonStrikerIndex;
            nonStrikerIndex = temp;

            for (i = 1; i <= 6 && team1Wickets < team1.getPlayers().size() - 1; i++) {
                int run = getValidInput("Bat", strikerIndex, team1.getPlayers().get(strikerIndex).getName());
                userbat.addLast(run);
                userbat.removeFirst();
                // System.out.println(userbat);
                int ball = compBowling("ball", bowlingIndex.get(over),
                        team2.getPlayers().get(bowlingIndex.get(over)).getName());
                team1BallFaced.set(strikerIndex, team1BallFaced.get(strikerIndex) + 1);
                team2BallBowled.set(bowlingIndex.get(over), team2BallBowled.get(bowlingIndex.get(over)) + 1);
                // if(target==-1)
                // ball=inn1("ball",bowlingIndex.get(over),team2.getPlayers().get(bowlingIndex.get(over)).getName());
                // else
                // ball=inn2("ball",target-team1Score+1,(overs*6)-(over*6)-i+1,bowlingIndex.get(over),team2.getPlayers().get(bowlingIndex.get(over)).getName());

                if (run == ball) {
                    System.out.println("Wicket!!!... " + team1.getPlayers().get(strikerIndex).getName() + " out!!!");
                    team2Bowling.set(bowlingIndex.get(over), team2Bowling.get(bowlingIndex.get(over)) + 1);
                    team1Wickets++;
                    team1.getPlayers().get(strikerIndex).setOut();
                    strikerIndex = (strikerIndex > nonStrikerIndex) ? strikerIndex + 1 : nonStrikerIndex + 1;

                    if(!((over+1==overs && i==6) || (team1Wickets==team1.getPlayers().size()-1)))
                    team1.getPlayers().get(strikerIndex).displayBattingStats();

                } else {
                    team1Score += run;
                    team2.getPlayers().get(bowlingIndex.get(over)).setRunsConceded(run);
                    team1Batting.set(strikerIndex, team1Batting.get(strikerIndex) + run);
                    if (run % 2 == 1) {
                        temp = strikerIndex;
                        strikerIndex = nonStrikerIndex;
                        nonStrikerIndex = temp;
                    }
                }

                System.out.println(team1.getName() + " score : " + team1Score + "/" + team1Wickets);
                if (i == 6)
                    System.out.println("Overs : " + (over + 1));
                else
                    System.out.println("Overs : " + over + "." + i);
                if (team1Wickets == (team1.getPlayers().size() - 1)) {
                    System.out.println(team1.getName() + " all out!!!");
                    break;
                }
                System.out.println(
                        team1.getPlayers().get(strikerIndex).getName() + " : " + team1Batting.get(strikerIndex));
                System.out.println(
                        team1.getPlayers().get(nonStrikerIndex).getName() + " : " + team1Batting.get(nonStrikerIndex));

                if (target != -1 && team1Score >= target) {
                    break;
                }
                if (target != -1) {
                    System.out.println(team1.getName() + " needs " + (target - team1Score) + " runs in "
                            + ((overs * 6) - (over * 6) - i));
                }

                System.out.println("\n");

            }
            if (team1Wickets == team1.getPlayers().size() - 1)
                break;
            if (target != -1 && team1Score >= target)
                break;
        }
        if (team1Wickets == team1.getPlayers().size() - 1)
            team1Balls = over * 6 + i;
        else if (target != -1 && team1Score >= target)
            team1Balls = over * 6 + i;
        else
            team1Balls = (over) * 6;

    }

    public void computerBatting(int target) {
        System.out.println(team2.getName() + " is batting now.");
        if (target != -1)
            System.out
                    .println("Target for  " + team2.getName() + " is " + target + " runs" + " in " + overs + " overs.");
        System.out.println();

        team2Score = 0;
        team2Wickets = 0;
        int over = 0;
        int i = 0;

        int strikerIndex = 1;
        int nonStrikerIndex = 0;
        int temp;

        team2.getPlayers().get(nonStrikerIndex).displayBattingStats();
        team2.getPlayers().get(strikerIndex).displayBattingStats();
        System.out.println();

        for (over = 0; over < overs && team2Wickets < team1.getPlayers().size() - 1; over++) {
            temp = strikerIndex;
            strikerIndex = nonStrikerIndex;
            nonStrikerIndex = temp;
            for (i = 1; i <= 6 && team2Wickets < team1.getPlayers().size() - 1; i++) {
                int ball = getValidInput("ball", bowlingIndex.get(over),
                        team1.getPlayers().get(bowlingIndex.get(over)).getName());
                team2BallFaced.set(strikerIndex, team2BallFaced.get(strikerIndex) + 1);
                team1BallBowled.set(bowlingIndex.get(over), team1BallBowled.get(bowlingIndex.get(over)) + 1);

                int run = 0;
                if (target == -1)
                    run = inn1("bat", strikerIndex, team2.getPlayers().get(strikerIndex).getName());
                else
                    run = inn2("bat", target - team2Score + 1, (overs * 6) - (over * 6) - i, strikerIndex,
                            team2.getPlayers().get(strikerIndex).getName());

                if (run == ball) {
                    System.out.println("Wicket!!!... " + team2.getPlayers().get(strikerIndex).getName() + " out!!!");
                    team1Bowling.set(bowlingIndex.get(over), team1Bowling.get(bowlingIndex.get(over)) + 1);
                    team2Wickets++;
                    team2.getPlayers().get(strikerIndex).setOut();
                    strikerIndex = (strikerIndex > nonStrikerIndex) ? strikerIndex + 1 : nonStrikerIndex + 1;
                    if(!((over+1==overs && i==6) || (team2Wickets==team2.getPlayers().size()-1)))
                    team2.getPlayers().get(strikerIndex).displayBattingStats();
                } else {
                    team2Score += run;
                    team2Batting.set(strikerIndex, team2Batting.get(strikerIndex) + run);
                    team1.getPlayers().get(bowlingIndex.get(over)).setRunsConceded(run);
                    if (run % 2 == 1) {
                        temp = strikerIndex;
                        strikerIndex = nonStrikerIndex;
                        nonStrikerIndex = temp;
                    }
                }

                System.out.println(team2.getName() + " score : " + team2Score + "/" + team2Wickets);
                if (i == 6)
                    System.out.println("Overs : " + (over + 1));
                else
                    System.out.println("Overs : " + over + "." + i);
                if (team2Wickets == (team2.getPlayers().size() - 1)) {
                    System.out.println(team2.getName() + " all out!!!");
                    break;
                }
                System.out.println(
                        team2.getPlayers().get(strikerIndex).getName() + " : " + team2Batting.get(strikerIndex));
                System.out.println(
                        team2.getPlayers().get(nonStrikerIndex).getName() + " : " + team2Batting.get(nonStrikerIndex));

                if (target != -1 && team2Score >= target) {
                    break;
                }
                if (target != -1) {
                    System.out.println(team2.getName() + " needs " + (target - team2Score) + " runs in "
                            + ((overs * 6) - (over * 6) - i));
                }

                System.out.println("\n");
            }
            if (team2Wickets == team2.getPlayers().size() - 1)
                break;
            if (target != -1 && team2Score >= target)
                break;
        }
        if (team2Wickets == team2.getPlayers().size() - 1)
            team2Balls = over * 6 + i;
        else if (target != -1 && team2Score >= target)
            team2Balls = over * 6 + i;
        else
            team2Balls = (over) * 6;
    }

    public void startMatch() {
        System.out.println("......Match has been started..........");
        String ch;
        if (handleToss()) {
            System.out.println(team1.getName() + " won the toss!");
            System.out.print(team1.getName() + " want to Bat or Bowl? (Bat/Bowl): ");
            ch = sc.next();
            while (!ch.equalsIgnoreCase("bat") && !ch.equalsIgnoreCase("bowl"))
                ch = sc.next();
            if (ch.equalsIgnoreCase("Bat")) {
                System.out.println("\n\nInning 1 : ");
                userBatting(-1);
                System.out.println("\n\nInning 2 : ");
                computerBatting(team1Score + 1);
                System.out.println();
                if (team1Score > team2Score)
                    System.out.println(team1.getName() + " wins by " + (team1Score - team2Score) + " runs");
                else if (team1Score < team2Score)
                    System.out.println(team2.getName() + " wins by " + (team2.getPlayers().size() - 1 - team2Wickets)
                            + " wickets");
                else
                    System.out.println("Match is a tie");
            } else {
                System.out.println("\n\nInning 1 : ");
                computerBatting(-1);
                System.out.println("\n\nInning 2 : ");
                userBatting(team2Score + 1);
                System.out.println();
                if (team1Score > team2Score)
                    System.out.println(team1.getName() + " wins by " + (team1.getPlayers().size() - 1 - team1Wickets)
                            + " wickets");
                else if (team1Score < team2Score)
                    System.out.println(team2.getName() + " wins by " + (team2Score - team1Score) + " runs");
                else
                    System.out.println("Match is a tie");
            }
        } else {
            System.out.println(team2.getName() + " won the toss!");
            System.out.print(team2.getName() + " want to Bat or Bowl? (Bat/Bowl): ");
            ch = sc.next();
            while (!ch.equalsIgnoreCase("bat") && !ch.equalsIgnoreCase("bowl"))
                ch = sc.next();
            if (ch.equalsIgnoreCase("Bat")) {
                System.out.println("\n\nInning 1 : ");
                computerBatting(-1);
                System.out.println("\n\nInning 2 : ");
                userBatting(team2Score + 1);
                System.out.println();
                if (team1Score > team2Score)
                    System.out.println(team1.getName() + " wins by " + (team1.getPlayers().size() - 1 - team1Wickets)
                            + " wickets");
                else if (team1Score < team2Score)
                    System.out.println(team2.getName() + " wins by " + (team2Score - team1Score) + " runs");
                else
                    System.out.println("Match is a tie");
            } else {
                System.out.println("\n\nInning 1 : ");
                userBatting(-1);
                System.out.println("\n\nInning 2 : ");
                computerBatting(team1Score + 1);
                System.out.println();
                if (team1Score > team2Score)
                    System.out.println(team1.getName() + " wins by " + (team1Score - team2Score) + " runs");
                else if (team1Score < team2Score)
                    System.out.println(team2.getName() + " wins by " + (team2.getPlayers().size() - 1 - team2Wickets)
                            + " wickets");
                else
                    System.out.println("Match is a tie");
            }
        }

        displayResult();

    }

    public void displayResult() {
        try {
            Thread.sleep(5000); // Pauses execution for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 90; i++)
            System.out.print("-");
        System.out.println();

        System.out.print("\nMatch Result:");
        if (team1Score > team2Score)
            System.out.println(" " + team1.getName() + " wins.");
        else if (team1Score < team2Score)
            System.out.println(" " + team2.getName() + " wins.");
        else
            System.out.println(" Match is a tie");

        System.out.println();
        System.out.println("Match Scorecard : ");
        System.out.println();

        String[] headers = { "Players", "runs", "wickets", "Players", "Runs", "Wickets" };

        System.out.print(team1.getName());
        System.out.printf("%" + 37 + "s", team1Score + "/" + team1Wickets + " (" + ((int) (team1Balls / 6)) + "."
                + ((int) team1Balls % 6) + ")        ");
        System.out.print(team2.getName());
        System.out.printf("%" + 37 + "s", team2Score + "/" + team2Wickets + " (" + ((int) (team2Balls / 6)) + "."
                + ((int) team2Balls % 6) + ")        ");
        System.out.println();

        int[] columnWidths = { 20, 12, 10, 20, 10, 10 };

        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%" + columnWidths[i] + "s", headers[i]);
        }
        System.out.println();

        for (int i = 0; i < team1.getPlayers().size(); i++) {
            System.out.printf("%" + columnWidths[0] + "s", team1.getPlayers().get(i).getName());
            System.out.printf("%" + columnWidths[1] + "s", (team1Batting.get(i) + "(" + team1BallFaced.get(i) + ")"));
            System.out.printf("%" + columnWidths[2] + "s", team1Bowling.get(i));
            team1.getPlayers().get(i).updateStats(team1Batting.get(i), team1Bowling.get(i));
            if (team1Batting.get(i) >= 50)
                team1.getPlayers().get(i).updatefifty();
            else if (team1Batting.get(i) >= 30)
                team1.getPlayers().get(i).setThirty();

            if (team1Bowling.get(i) >= 4)
                team1.getPlayers().get(i).updateFourWicketHaul();

            team1.getPlayers().get(i).setBallFaced(team1BallFaced.get(i));
            team1.getPlayers().get(i).setBallBowled(team1BallBowled.get(i));

            System.out.printf("%" + columnWidths[3] + "s", team2.getPlayers().get(i).getName());
            System.out.printf("%" + columnWidths[4] + "s", (team2Batting.get(i) + "(" + team2BallFaced.get(i) + ")"));
            System.out.printf("%" + columnWidths[5] + "s", team2Bowling.get(i));
            team2.getPlayers().get(i).updateStats(team2Batting.get(i), team2Bowling.get(i));
            if (team2Batting.get(i) >= 50)
                team2.getPlayers().get(i).updatefifty();
            else if (team2Batting.get(i) >= 30)
                team2.getPlayers().get(i).setThirty();

            if (team2Bowling.get(i) >= 4)
                team2.getPlayers().get(i).updateFourWicketHaul();

            team2.getPlayers().get(i).setBallFaced(team2BallFaced.get(i));
            team2.getPlayers().get(i).setBallBowled(team2BallBowled.get(i));

            System.out.println();

        }

        if (team1Score > team2Score) {
            team1.updateStats(1, 0, team1Score, team1Balls, team2Score, team2Balls);
            team2.updateStats(0, 1, team2Score, team2Balls, team1Score, team1Balls);
        } else if (team2Score > team1Score) {
            team1.updateStats(0, 1, team1Score, team1Balls, team2Score, team2Balls);
            team2.updateStats(1, 0, team2Score, team2Balls, team1Score, team1Balls);
        } else {
            team1.updateStats(0, 0, team1Score, team1Balls, team2Score, team2Balls);
            team2.updateStats(0, 0, team2Score, team2Balls, team1Score, team1Balls);
        }

    }

}
