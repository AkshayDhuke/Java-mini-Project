// Tournament.java
import java.util.ArrayList;
import java.util.Scanner;

 class Tournament {
    @SuppressWarnings("unused")
    private String name;
    private ArrayList<Team> teams;
    private ArrayList<Player> players;
    private Scanner sc;


    int overs;
    int maxWickets;

    public Tournament(String name,int overs,int maxWickets,Scanner sc) {
        this.sc=sc;
        this.name = name;
        this.overs=overs;
        this.maxWickets=maxWickets;
        this.teams = new ArrayList<>();
        this.players=new ArrayList<>();
    }

    // Add team to the tournament
    public void addTeam(Team team) {
        teams.add(team);
    }

    public void addPlayer(Player p)
    {
        players.add(p);
    }

    // Start tournament
    public void startTournament() {

        

        // System.out.println("\n\n***************** LUCK PREMIER LEAGUE STARTED *****************");

        int matchnum=0;
        
        int count=2;
        String s1,s2;

    while(count!=0)
    {
        for(int i=0;i<teams.size()*(teams.size()-1)/2;i++)
        {
            System.out.println("\n******************** LUCK PREMIER LEAGUE ********************");
            Team t1=null,t2=null;

            matchnum++;
            System.out.println("\nMatch Number: "+matchnum);
            // System.out.println("******************** WELCOME TO FINAL MATCH ********************");
            int flag=0;
            while(flag==0)
            {
                System.out.print("Enter the name of Team 1:");
                s1=sc.next();
                for(Team t:teams)
                {
                    if(t.getName().equals(s1))
                    {
                        t1=t;
                        flag=1;
                    }
                }
            }
            flag=0;
            while(flag==0)
            {
                System.out.print("Enter the name of Team 2:");
                s2=sc.next();
                for(Team t:teams)
                {
                    if(t.getName().equals(s2))
                    {
                        t2=t;
                        flag=1;
                    }
                }
            }


            Match m=new Match(t1,t2,overs,sc);
            m.startMatch();

            
            try {
                Thread.sleep(1000);  // Pauses execution for 5000 milliseconds (5 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            displayOrangeCap();
            try {
                Thread.sleep(5000);  // Pauses execution for 5000 milliseconds (5 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            displayPurpleCap();
            try {
                Thread.sleep(5000);  // Pauses execution for 5000 milliseconds (5 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            displayPointsTable();
            try {
                Thread.sleep(5000);  // Pauses execution for 5000 milliseconds (5 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        count--;
    }


        
    }

    public void displayOrangeCap() {

        players.sort((player1, player2) -> {
            int pointComparison = Integer.compare(player2.getRunsScored(), player1.getRunsScored());
            if (pointComparison != 0) {
                return pointComparison; 
            }
            if (player2.getBallFaced() == 0 && player1.getBallFaced() == 0) {
                return 0; // Both players have 0 balls faced, consider them equal in comparison
            } else if (player2.getBallFaced() == 0) {
                return 1; // Place player1 before player2 if player2 has faced 0 balls
            } else if (player1.getBallFaced() == 0) {
                return -1; // Place player2 before player1 if player1 has faced 0 balls
            }
            return Double.compare(((double)player2.getRunsScored())/player2.getBallFaced(), ((double)player1.getRunsScored())/player1.getBallFaced());
        });  
        

        for(int i=0;i<90;i++)
        System.out.print("-");
        System.out.println();
        
        System.out.println("\nOrange Cap Holders:\n");

        String[] headers = {"Player", "Team","Matches", "Runs","SR","avg" ,"30s", "50s"};
        
        int[] columnWidths = {20, 10, 10,10,10,8,6,6};

        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%" + columnWidths[i] + "s", headers[i]);
        }
        System.out.println();
    
        for (int i=0;i<10 && i<players.size() && players.get(i).getRunsScored()>=10;i++) {
            System.out.printf("%" + columnWidths[0] + "s",players.get(i).getName());
            System.out.printf("%" + columnWidths[1] + "s",players.get(i).getTeamName());
            System.out.printf("%" + columnWidths[2] + "s",players.get(i).getMatchesPlayed());
            System.out.printf("%" + columnWidths[3] + "s",players.get(i).getRunsScored());

            if(players.get(i).getBallFaced()==0)
            System.out.printf("%" + columnWidths[4] + "s",players.get(i).getRunsScored());
            else
            {
                String formattedNumber = String.format("%.2f", (((double)players.get(i).getRunsScored())/players.get(i).getBallFaced()*100));
                System.out.printf("%" + columnWidths[4] + "s",formattedNumber);
            }


            if(players.get(i).getOut()==0)
            System.out.printf("%" + columnWidths[5] + "s","-");
            else
            {
                String formattedNumber = String.format("%.2f", (((double)players.get(i).getRunsScored())/players.get(i).getOut()));
                System.out.printf("%" + columnWidths[5] + "s",formattedNumber);

            }
            System.out.printf("%" + columnWidths[6] + "s",players.get(i).getThirty());
            System.out.printf("%" + columnWidths[7] + "s",players.get(i).getFifty());
            System.out.println();
        }
    }

    public void displayPurpleCap() {
        // players.sort((player1, player2) -> Integer.compare(player2.getWicketsTaken(), player1.getWicketsTaken()));

        players.sort((player1, player2) -> {
            // Compare by wickets in descending order
            int wicketComparison = Integer.compare(player2.getWicketsTaken(), player1.getWicketsTaken());
            if (wicketComparison != 0) {
                return wicketComparison; 
            }
            // Compare by economy in ascending order
            double economy1 = (double) player1.getRunsConceded() / player1.getBallBowled();
            double economy2 = (double) player2.getRunsConceded() / player2.getBallBowled();
            return Double.compare(economy1, economy2);
        });
        
        for(int i=0;i<90;i++)
        System.out.print("-");
        System.out.println();

        System.out.println("\nPurple Cap Holder(s):\n");


        String[] headers = {"Player", "Team","Matches", "Wickets","eco","4+"};
        
        int[] columnWidths = {20, 10, 10,10,8,5};

        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%" + columnWidths[i] + "s", headers[i]);
        }
        System.out.println();
    
        for (int i=0;i<10 && i<players.size() && players.get(i).getWicketsTaken()>0;i++) {
            System.out.printf("%" + columnWidths[0] + "s",players.get(i).getName());
            System.out.printf("%" + columnWidths[1] + "s",players.get(i).getTeamName());
            System.out.printf("%" + columnWidths[2] + "s",players.get(i).getMatchesPlayed());
            System.out.printf("%" + columnWidths[2] + "s",players.get(i).getWicketsTaken());
            
            String formattedNumber = String.format("%.2f", (((double)players.get(i).getRunsConceded())/players.get(i).getBallBowled()*6));
            System.out.printf("%" + columnWidths[4] + "s",formattedNumber);            

            System.out.printf("%" + columnWidths[5] + "s",players.get(i).getFourWicketHaul());            

            System.out.println();
        }
    
    }


    public void displayPointsTable() {
        teams.sort((team1, team2) -> {
            int pointComparison = Integer.compare(team2.getpoints(), team1.getpoints());
            if (pointComparison != 0) {
                return pointComparison; 
            }
            return Double.compare(team2.getNetRunRate(), team1.getNetRunRate());
        });        
        
        System.out.println();

        for(int i=0;i<90;i++)
        System.out.print("-");
        System.out.println();

        System.out.println("Points Table : \n");


        String[] headers = {"Team","Matches","Wins","Losses","Points","NRR"};
        
        int[] columnWidths = {10, 10, 8,8,8,8};

        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%" + columnWidths[i] + "s", headers[i]);
        }
        System.out.println();
    
        for (int i=0; i<teams.size();i++) {
            System.out.printf("%" + columnWidths[0] + "s",teams.get(i).getName());
            System.out.printf("%" + columnWidths[1] + "s",teams.get(i).getMatchesPlayed());
            System.out.printf("%" + columnWidths[2] + "s",teams.get(i).getWins());
            System.out.printf("%" + columnWidths[3] + "s",teams.get(i).getLosses());
            System.out.printf("%" + columnWidths[4] + "s",teams.get(i).getpoints());
            String formattedNumber = String.format("%.3f", teams.get(i).getNetRunRate());
            System.out.printf("%" + columnWidths[5] + "s",formattedNumber);

            System.out.println();
        }
        
        for(int i=0;i<90;i++)
        System.out.print("-");
        System.out.println();
        
    }

}
