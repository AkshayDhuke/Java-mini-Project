// Team.java
import java.util.ArrayList;

class Team {
    private String name;
    private ArrayList<Player> players;
    private int matchesPlayed;
    private int wins;
    private int losses;
    private int draws;
    @SuppressWarnings("unused")
    private int runsScored;
    @SuppressWarnings("unused")
    private int ballsPlayed;
    @SuppressWarnings("unused")
    private int runsConceded;
    @SuppressWarnings("unused")
    private int ballsBowled;
    private double netRunRate;

    // Constructor
    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
        this.matchesPlayed = 0;
        this.wins = 0;
        this.losses = 0;
        this.draws=0;
        this.runsScored = 0;
        this.ballsPlayed = 0;
        this.runsConceded=0;
        this.ballsBowled=0;
        this.netRunRate=0;
    }

    // Add player to the team
    public void addPlayer(Player player) {
        players.add(player);
    }

    public void updateStats(int wins,int  losses,int runsScored,int ballsPlayed,int runsConceded,int ballsBowled) {

        this.wins+=wins;
        this.losses+=losses;
        if(wins+losses==0)
        this.draws++;
        this.matchesPlayed++;
        this.runsScored+=runsScored;
        this.ballsPlayed+=ballsPlayed;
        this.runsConceded+=runsConceded;
        this.ballsBowled+=ballsBowled;

        this.netRunRate=(((double)this.runsScored*6)/this.ballsPlayed)-(((double)this.runsConceded*6)/(this.ballsBowled));
    }

    public String getName() {
        return name;
    }

    public  int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getWins(){
        return wins;
    }

    public int getLosses(){
        return losses;
    }

    public int getpoints()
    {
        return wins*2+draws;
    }

    public double getNetRunRate(){
        return netRunRate;
    }

    public ArrayList<Player>  getPlayers(){
    return  players;
    }

    public  int getDraws(){
        return draws;
    }
}
