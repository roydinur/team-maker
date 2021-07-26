public class Player {
    protected String name;
    protected double rank;

    public Player(String name, double rank) {
        this.name = name;
        this.rank = rank;
    }
    public Player(String name){
        this.name = name;
        this.rank = 0;
    }

    public void editRank(double newRank) {
//        if (newRank < 0 || newRank > 5) {
//            throw new IllegalArgumentException("Rank needs to be between 0 to 5");
//        }
        this.rank = newRank;
    }

    public void editName(String newName){
//        if(newName.length()< 1 || newName.length()>20)
//            throw new IllegalArgumentException("Name length needs to between 1-20 characters");
        this.name = newName;
    }

    public boolean equals(Player pl2){
        if(pl2 == this) {
            return true;
        }
        return pl2.name.equals(this.name);

    }

    @Override
    public String toString() {
        return name;
    }
}
