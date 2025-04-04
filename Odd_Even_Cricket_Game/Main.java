// Main.java

import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        System.out.println("\n******************** LUCK PREMIER LEAGUE ********************\n");

        System.out.print("Enter the number of Overs : ");
        int overs=sc.nextInt();
        System.out.print("Enter the number of wickets : ");
        int maxWickets=sc.nextInt();

        Tournament tournament = new Tournament("LUCK Premier League" , overs, maxWickets,sc);
        System.out.println();
        int n;
        System.out.print("Enter the number of teams : ");
        n=sc.nextInt();
        System.out.print("Enter the number of players : ");
        int noOfPlayers=sc.nextInt();
        System.out.println();

        String s;
        for(int i=0;i<n;i++)
        {
            System.out.print("Enter name of team " + (i+1) + " : ");
            s=sc.nextLine();
            if(i==0)
            {
                s=sc.nextLine();
            }
            Team t=new Team(s);

            for(int j=0;j<noOfPlayers;j++)
            {
                System.out.print("Enter the name of player " + (j+1) +" : ");
                Player p=new Player(sc.nextLine(), s);

                tournament.addPlayer(p);
                t.addPlayer(p);
            }

            tournament.addTeam(t);

        }

        tournament.startTournament();

        sc.close();
    }
}
