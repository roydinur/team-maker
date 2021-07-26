import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        List<Team> teams = new ArrayList<>();
        Team dinur = new Team("dinur");
        Player roy = new Player("roy", 3.5);
        Player tom = new Player("tom", 3.5);
        Player amit = new Player("amit", 4.5);
        dinur.players.add(roy);
        dinur.players.add(tom);
        dinur.players.add(amit);
        teams.add(dinur);
        while (true) {
            System.out.println(
                    """
                            Hello!

                            Create new match - 1
                            Manage teams - 2
                            info - 3
                            Quit - 4
                            """);
            System.out.println("enter number between 1-4");
            Scanner scanner = new Scanner(System.in);
            int ans = scanner.nextInt();
            if (ans == 1) {
                //create Match
            } else if (ans == 2) {
                manageTeams(teams);
            } else if (ans == 3) {
                info();
            } else if (ans == 4) {
                break;
            }
        }

    }

    private static void manageTeams(List<Team> teams) {
        while (true) {
            System.out.println(
                    """ 
                            Manage Teams:
                            Create new team - 1
                            Edit team - 2
                            Return - 3
                            """);
            System.out.println("enter number between 1-3");
            Scanner scanner = new Scanner(System.in);
            int ans = scanner.nextInt();

            if (ans == 1) {
                teams.add(createTeam());
            } else if (ans == 2) {
                if(teams.isEmpty()){
                    System.out.println("no teams to edit"+ "\n");
                    break;
                }
                System.out.println("""

                        choose which team you wish to edit:
                        """);
                System.out.println(teams.toString());
                int team = scanner.nextInt();
                editTeam(teams, teams.get(team - 1));
            } else if (ans == 3) {
                break;
            }
        }
    }

    private static Team createTeam() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter team's name:");
        String teamName = scanner.next();
        Team newTeam = new Team(teamName);
        do {
            newTeam.addplayer();
            System.out.println("Enter '0' if you've finish adding players, or any other key otherwise");
        } while (!scanner.next().equals("0"));
        return newTeam;
    }

    private static void editTeam(List<Team> teams, Team team) {
        while (true) {
            System.out.println("Editing: "+team.name+"\n"+
                    """
                            Add player - 1
                            Edit player - 2
                            Delete team - 3
                            Return - 4
                            """);

            System.out.println("enter number between 1-4");
            Scanner scanner = new Scanner(System.in);
            int ans = scanner.nextInt();
            if (ans == 1) {
                team.addplayer();
            } else if (ans == 2) {
                System.out.println("choose which player to edit:");
                System.out.println(team.players.toString());
                editPlayer(team, team.players.get(scanner.nextInt()-1));
            } else if (ans == 3) {
                System.out.println("are you sure you wish to delete this team? enter 'y' for 'yes' or any other key for 'no'");
                if (scanner.next().equals("y")) {
                    teams.remove(team);
                    break;
                }
            } else if (ans == 4) {
                break;
            }
        }
    }

    private static void editPlayer(Team team, Player player) {
        while (true) {
            System.out.println("Editing: "+player.name +" "+ player.rank+ " stars :"+"\n"+

                    """
                            Edit player's name - 1
                            Edit player's rank - 2
                            Delete player - 3
                            Return - 4
                            """);

            System.out.println("enter number between 1-4");
            Scanner scanner = new Scanner(System.in);
            int ans = scanner.nextInt();
            if(ans==1){
                System.out.println("enter new name:");
                player.editName(scanner.next());
            }
            else if(ans==2){
                System.out.println("enter new rank:");
                player.editRank(scanner.nextDouble());
            }
            else if(ans==3){
                team.players.remove(player);
            }
            else if(ans == 4){
                break;
            }

        }
    }

    private static void info() {
        System.out.println(" all rights reserved to Roy Dinur");
        System.out.println("Enter any key to return");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }
}

