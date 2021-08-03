import java.util.*;

public class Squad {

    private final List<Player> players = new ArrayList<>();
    private String name;

    public Squad(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 1 || name.length() > 20) {
            System.out.println("Name length needs to between 1-20 characters");
        } else {
            this.name = name;
            System.out.println("Name has been changed");
        }

    }

    public void addPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player's name:");
        String name = scanner.next();
        boolean isPlayerWithNameExist = players.stream().anyMatch(player -> player.getName().equals(name));
        if (!isPlayerWithNameExist) {
            System.out.println("Enter player's rank 0-5:");
            double rank = scanner.nextDouble();
            Player newPlayer = new Player(name, rank);
            players.add(newPlayer);
            System.out.println("Player has been added" + "\n");
        } else System.out.println("Player with that name already exist" + "\n");
    }

    public void addPlayer(String name) {
        Scanner scanner = new Scanner(System.in);
        boolean isPlayerWithNameExist = players.stream().anyMatch(player -> player.getName().equals(name));
        if (!isPlayerWithNameExist) {
            System.out.println("Enter player's rank 0-5:");
            double rank = scanner.nextDouble();
            Player newPlayer = new Player(name, rank);
            players.add(newPlayer);
            System.out.println("Player has been added" + "\n");
        } else System.out.println("Player with that name already exist" + "\n");
    }


    public void removePlayer(Player player) {
        players.remove(player);
        System.out.println("Player has been removed from squad" + "\n");
    }

    // gets n - number of teams
    // prints n equals teams
    public void match(int n) {
        List<List<Player>> equalTeams = randomTeams(n);
        double dif = maxDiff(equalTeams);
        List<List<Player>> mutation = clone(equalTeams);
        mutate(mutation);
        int countNotImproved = 0;
        while (countNotImproved < 15) {
            double mutatedDiff = maxDiff(mutation);
            if (mutatedDiff < dif) {
                countNotImproved = 0;
                equalTeams = mutation;
                dif = mutatedDiff;

            } else {
                countNotImproved++;
            }
            mutation = clone(equalTeams);
            mutate(mutation);
        }
        int index = 1;
        for (List<Player> team : equalTeams) {
            System.out.println("Team " + String.valueOf(index++) + " :");
            printSquad(team);
            System.out.println("average = " + round(averageRank(team),2) + "\n");
        }

    }

    // generates n random teams out of the squad:
    private List<List<Player>> randomTeams(int n) {
        List<List<Player>> teams = new ArrayList<>();
        int m = getPlayers().size() / n;
        for (int i = 0; i < n; i++) {
            List<Player> team = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                team.add(getPlayers().get(j + i * m));
            }
            teams.add(team);
        }
        return teams;
    }
    //todo: no use
    //returns the average rank of player in the team:
    private double averageRank(List<Player> team) {
        double sum = 0;
        for (Player p : team) {
            sum += p.getRank();
        }
        return sum / team.size();
    }

    // returns the difference between worse and best team's average:
    private double maxDiff(List<List<Player>> teams) {
        double maxAvg = averageRank(teams.get(0));
        double minAvg = averageRank(teams.get(0));
        for (List<Player> team : teams) {
            double avg = averageRank(team);
            if (avg > maxAvg) {
                maxAvg = avg;
            }
            if (avg < minAvg) {
                minAvg = avg;
            }
        }
        return maxAvg - minAvg;
    }

    private void mutate(List<List<Player>> teams) {
        Random rnd = new Random();
        int n = rnd.nextInt(5) + 1; // number of mutation to create
        for (int i = 0; i < n; i++) {
            int team1 = rnd.nextInt(teams.size());
            int team2 = rnd.nextInt(teams.size());
            while (team2 == team1) {
                team2 = rnd.nextInt(teams.size());
            }
            int pl1 = rnd.nextInt(teams.get(team1).size());
            int pl2 = rnd.nextInt(teams.get(team2).size());

            swap(teams.get(team1), teams.get(team2), pl1, pl2);
        }
    }

    private void swap(List<Player> team1, List<Player> team2, int pl1, int pl2) {
        Player player1 = team1.get(pl1);
        Player player2 = team2.get(pl2);
        team1.remove(player1);
        team2.remove(player2);
        team1.add(player2);
        team2.add(player1);
    }

    private List<List<Player>> clone(List<List<Player>> original){
        List<List<Player>> clone = new ArrayList<>();
        for (List<Player> team : original){
            List<Player> t = new ArrayList<>(team);
            clone.add(t);
        }
        return clone;
    }
//    //todo: no use for now
//    private List<Player> getSortedSquad() {
//        List<Player> cloned = new ArrayList(getPlayers());
//        cloned.sort(new RankSort());
//        return cloned;
//    }

    public void printSquad(List<Player> squad) {
        int index = 1;
        for (Player s : squad) {
            System.out.println(String.valueOf(index++) + ": " + s);
        }
    }

    public void printSquad() {
        int index = 1;
        for (Player s : this.getPlayers()) {
            System.out.println(String.valueOf(index++) + ": " + s);
        }
    }

    public String toString() {
        return this.getName();
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
