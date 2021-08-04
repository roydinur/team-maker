package classes;

import java.util.Scanner;

public class Player {
    //    TODO: id (also to team)
    private String name;
    private double rank;

    public Player(String name, double rank) {
        this.name = name;
        this.rank = round(rank, 2);
    }

    public Player(String name) {
        this.name = name;
        this.rank = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String playerName,Squad squad) {
        while (true) {
            if(playerName.equals(name)){
                break;
            }
            else if (playerNameLegal(playerName, squad)) {
                this.name = playerName;
                System.out.println("Player's name has been updated");
                break;
            }
            else {
                System.out.println("Enter legal player name:");
                Scanner scanner = new Scanner(System.in);
                playerName = scanner.next();
            }
        }
    }
    private boolean playerNameLegal(String playerName,Squad squad) {
        if (name.length() < 1 || name.length() > 20) {
            System.out.println("Name length needs to between 1-20 characters");
            return false;
        } else if (squad.getPlayers().stream().anyMatch(player -> player.getName().equals(playerName))) {
            System.out.println("Player with that name already exist in the squad");
            return false;
        } else {
            return true;
        }
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        while (true)
            if (rankInRange(rank)) {
                this.rank = round(rank, 2);
                System.out.println("Rank has been updated");
                break;
            } else {
                System.out.println("enter legal rank:");
                Scanner scanner = new Scanner(System.in);
                rank = scanner.nextDouble();
            }
    }

    private boolean rankInRange(double rank) {
        if (rank >= 0 && rank <= 5) {
            return true;
        } else {
            System.out.println("The rank submitted is not in legal range");
            return false;
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
        return (name + " " + this.getRank());
    }
}
