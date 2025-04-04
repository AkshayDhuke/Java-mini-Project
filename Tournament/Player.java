// Player.java
 class Player {
    private String name;
    private String team;
    private int matchesPlayed;
    private int runs;
    private int ballFaced;
    private int thirty;
    private int fifty;
    private int wickets;
    private int runsConceded;
    private int fourWicketHaul;
    private int ballBowled;
    private int out;
    private String formattedNumber;
    

    public Player(String name, String team) {
        this.name = name;
        this.team = team;
        this.runs = 0;
        this.wickets = 0;
        this.matchesPlayed = 0;
        this.fifty=0;
        this.fourWicketHaul=0;
        this.ballBowled=0;
        this.ballFaced=0;
        this.thirty=0;
        this.out=0;
        this.runsConceded=0;
    }


    public void displayBattingStats()
    {

        try {
            Thread.sleep(2000);  // Pauses execution for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=0;i<90;i++)
        System.out.print("-");
        System.out.println();


        System.out.println("Batsman Name : " + this.getName());
        System.out.printf("%"+9+"s","Matches");
        System.out.printf("%"+9+"s","Runs");
        System.out.printf("%"+9+"s","STR");
        System.out.printf("%"+9+"s","AVG");
        System.out.printf("%"+5+"s","30s");
        System.out.printf("%"+5+"s","50s");
        System.out.println();

        System.out.printf("%"+9+"s",this.getMatchesPlayed());
        System.out.printf("%"+9+"s",this.runs);

        formattedNumber="-";
        if(this.getBallFaced()!=0)
        formattedNumber = String.format("%.2f", (((double)this.getRunsScored())/this.getBallFaced()*100));
        System.out.printf("%"+9+"s",formattedNumber);

        formattedNumber="-";
        if(this.getOut()!=0)
        formattedNumber = String.format("%.2f", (((double)this.getRunsScored())/this.getOut()));
        System.out.printf("%"+9+"s",formattedNumber);

        System.out.printf("%"+5+"s",this.thirty);
        System.out.printf("%"+5+"s",this.fifty);
        
        System.out.println();
        for(int i=0;i<90;i++)
        System.out.print("-");
        System.out.println();

        try {
            Thread.sleep(2000);  // Pauses execution for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    

    public int getRunsConceded() {
        return runsConceded;
    }


    public void setRunsConceded(int runsConceded) {
        this.runsConceded += runsConceded;
    }


    public int getOut() {
        return out;
    }

    public void setOut() {
        this.out++;
    }

    public int getThirty() {
        return thirty;
    }

    public void setThirty() {
        this.thirty++;
    }

    public int getBallBowled() {
        return ballBowled;
    }

    public void setBallBowled(int ballBowled) {
        this.ballBowled += ballBowled;
    }

    public int getBallFaced() {
        return ballFaced;
    }

    public void setBallFaced(int ballFaced) {
        this.ballFaced += ballFaced;
    }


    // Update player stats
    public void updateStats(int runs, int wickets) {
        this.runs += runs;
        this.wickets += wickets;
        this.matchesPlayed++;
    }

    public void updatefifty()
    {
        this.fifty++;
    }

    public int getFifty()
    {
        return fifty;
    }

    public int getRunsScored()
    {
        return runs;
    }

    public void updateFourWicketHaul()
    {
        this.fourWicketHaul++;
    }

    public int getFourWicketHaul()
    {
        return fourWicketHaul;
    }

    public int  getWicketsTaken()
    {
        return  wickets;
    }

    public String getName() {
        return name;
    }

    public String getTeamName()
    {
        return  team;
    }

    public  int getMatchesPlayed(){
        return  matchesPlayed;

    }

}
