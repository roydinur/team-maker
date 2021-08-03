public class Player {
//    TODO: private + getter + setters
//    TODO: id (also to team)
    private String name;
    private double rank;

    public Player(String name, double rank) {
        this.name = name;
        this.rank = round(rank,2);
    }

    public Player(String name) {
        this.name = name;
        this.rank = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 1 || name.length() > 20) {
            System.out.println("Name length needs to between 1-20 characters");
        } else {
            this.name = name;
        }
    }
    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        if (rank < 0 || rank > 5) {
            System.out.println("Rank needs to be between 0 to 5");
        } else {
            this.rank = round(rank, 2);

        }
    }
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public String toString() {
        return (name+" "+this.getRank());
    }
}
