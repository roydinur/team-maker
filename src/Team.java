import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Team {
    protected List<Player> players = new ArrayList<>();
    protected String name;

    public Team(String name) {
        this.name = name;
    }

    public void editName(String name){
        this.name = name;
        System.out.println("Name has been changed");
    }
    public void addplayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player's name:");
        String name = scanner.next();
        System.out.println("Enter player's rank 0-5:");
        double rank = scanner.nextDouble();
        if(!players.contains(name)) {
            Player newPlayer = new Player(name, rank);
            players.add(newPlayer);
            System.out.println("Player has been added");
        }
        else System.out.println("Player with that name already exist");

    }

    public void removePlayer(String name){
        players.remove(name);
        System.out.println("Player has been removed");
    }

    public String toString(){
        return this.name;
    }

}
