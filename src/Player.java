public class Player {
//    TODO: private + getter + setters
//    TODO: id (also to team)
    private String name;
    protected double rank;

    public Player(String name, double rank) {
        this.name = name;
        this.rank = rank;
    }

    public Player(String name) {
        this.name = name;
        this.rank = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

//    // TODO: setRank
//    public void editRank(double newRank) {
////        if (newRank < 0 || newRank > 5) {
////            throw new IllegalArgumentException("Rank needs to be between 0 to 5");
////        }
//        this.rank = newRank;
//    }
//
//    public void editName(String newName) {
////        if(newName.length()< 1 || newName.length()>20)
////            throw new IllegalArgumentException("Name length needs to between 1-20 characters");
//        this.name = newName;
//    }

    @Override
    public String toString() {
        return name;
    }
}
