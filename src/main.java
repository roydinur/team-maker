import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        List<Squad> squadList = new ArrayList<>();
        Squad dinur = new Squad("dinur");
        Player roy = new Player("roy", 3.5);
        Player tom = new Player("tom", 3.5456456);
        Player amit = new Player("amit", 4.5);
        Player barak = new Player("barak", 2);
        dinur.getPlayers().add(roy);
        dinur.getPlayers().add(tom);
        dinur.getPlayers().add(amit);
        dinur.getPlayers().add(barak);
        squadList.add(dinur);

        Squad randomSquad = new Squad("random");
        for (int i = 0; i < 20; i++) {
            String name = Generator.generateRandomPassword(5);
            Random rnd = new Random();
            double num = rnd.nextDouble() * 5;
            Player temp = new Player(name, num);
            randomSquad.getPlayers().add(temp);
        }
        squadList.add(randomSquad);
        randomSquad.match(4);

        while (true) {
            System.out.println(
                    """
                            \nWelcome to Team-Maker!

                            Create new match - 1
                            Manage squads - 2
                            info - 3
                            Quit - 4
                            """);
            System.out.println("Enter number between 1-4");
            Scanner scanner = new Scanner(System.in);
            int ans = scanner.nextInt();
            if (ans == 1) {
                matchMenu(squadList);
            } else if (ans == 2) {
                manageSquadsMenu(squadList);
            } else if (ans == 3) {
                info();
            } else if (ans == 4) {
                break;
            }
        }

    }

    private static void matchMenu(List<Squad> squadList) {
        while (true) {
            boolean squadExist = !squadList.isEmpty();
            int num = 1;
            System.out.println("\n" + "Choose squad:" + "\n");
            if (squadExist) {
                System.out.println("Choose existing squad - 1" + "\n" +
                        "Create new squad - 2" + "\n" +
                        "Return - 3" + "\n" + "\n" +
                        "Enter number between 1-3");
                Scanner scanner = new Scanner(System.in);
                int ans = scanner.nextInt();
                if (ans == 1) {
                    System.out.println("""

                            Choose the number of the squad, you wish to match:
                            """);
                    int index = 1;
                    for (Squad s : squadList) {
                        System.out.println(String.valueOf(index++) + ": " + s);
                    }
                    int i = scanner.nextInt();
                    createMatch(squadList.get(i - 1));
                } else if (ans == 2) {
                    Squad squad = createSquad();
                    squadList.add(squad);
                } else if (ans == 3) {
                    break;
                }
            } else {
                System.out.println("Create new squad - 1" + "\n" +
                        "Return - 2" + "\n" + "\n" +
                        "Enter number between 1-2");
                Scanner scanner = new Scanner(System.in);
                int ans = scanner.nextInt();
                if (ans == 1) {
                    Squad squad = createSquad();
                    squadList.add(squad);
                } else if (ans == 2) {
                    break;
                }

            }
        }
    }

    //todo: create Match
    private static void createMatch(Squad squad) {
        int numOfTeams = numOfTeams(squad);
        if (numOfTeams > 1) {
            squad.match(numOfTeams);
        }
        else squad.printSquad();
    }

//    todo: change number of teams you're allowed to choose

    private static int numOfTeams(Squad squad) {
        int squadSize = squad.getPlayers().size();
        int ans;
        if (squadSize == 1) {
            return 1;
        } else {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Choose number of teams within the range:" +
                        "1-" + squadSize);
                ans = scanner.nextInt();
                if (ans >= 1 && ans <= squadSize) {
                    break;
                } else {
                    System.out.println("The number that was chosen is not in legal range");
                }
            }
            return ans;
        }

    }

    private static void manageSquadsMenu(List<Squad> squadList) {
        while (true) {
            System.out.println(
                    """ 
                            \nManage Squads:
                                                        
                            Create new squad - 1
                            Edit squad - 2
                            Return - 3
                            """);
            System.out.println("Enter number between 1-3");
            Scanner scanner = new Scanner(System.in);
            int ans = scanner.nextInt();

            if (ans == 1) {
                squadList.add(createSquad());
            } else if (ans == 2) {
                if (squadList.isEmpty()) {
                    System.out.println("No squads to edit" + "\n");
                    break;
                }
                System.out.println("""

                        Choose the number of squad you wish to edit:
                        """);
                int index = 0;
                for (Squad s : squadList) {
                    System.out.println(String.valueOf(index++) + ": " + s);
                }
                int squad = scanner.nextInt();
                editSquad(squadList, squadList.get(squad));
            } else if (ans == 3) {
                break;
            }
        }
    }

    private static Squad createSquad() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + "Enter squad's name:");
        String squadName = scanner.next();
        Squad newSquad = new Squad(squadName);
        boolean first = true;
        while (true) {
            if (first) {
                System.out.println("Do you wish to add players for the squad?" + "\n" +
                        "Enter 'n' for no, or type the first player's name:");
                first = false;
            } else {
                System.out.println("Do you wish to add more players for the squad?" + "\n" +
                        "Enter 'n' for no, or type next player's name:");
            }
            String ans = scanner.next();
            if (ans.equals("n")) {
                break;
            } else {
                newSquad.addPlayer(ans);
            }
        }
        return newSquad;
    }

    private static void editSquad(List<Squad> squadList, Squad squad) {
        while (true) {
            System.out.println("\n" + "Editing: " + squad.getName() + "\n" +
                    """
                            Add player - 1
                            Edit player - 2
                            Delete squad - 3
                            Return - 4
                            """);

            System.out.println("Enter number between 1-4");
            Scanner scanner = new Scanner(System.in);
            int ans = scanner.nextInt();
            if (ans == 1) {
                squad.addPlayer();
            } else if (ans == 2) {
                System.out.println("Choose which player to edit:");
                int index = 0;
                for (Player s : squad.getPlayers()) {
                    System.out.println(String.valueOf(index++) + ": " + s);
                }
                editPlayer(squad, squad.getPlayers().get(scanner.nextInt()));
            } else if (ans == 3) {
                System.out.println("Are you sure you want to delete this squad? enter 'y' for 'yes' or any other key for 'no'");
                if (scanner.next().equals("y")) {
                    squadList.remove(squad);
                    break;
                }
            } else if (ans == 4) {
                break;
            }
        }
    }

    private static void editPlayer(Squad squad, Player player) {
        while (true) {
            System.out.println("\n" + "Editing: " + player.getName() + " " + player.getRank() + " stars " + "\n" +

                    """
                            Edit player's name - 1
                            Edit player's rank - 2
                            Delete player - 3
                            Return - 4
                            """);

            System.out.println("enter number between 1-4");
            Scanner scanner = new Scanner(System.in);
            int ans = scanner.nextInt();
            if (ans == 1) {
                System.out.println("enter new name:");
                player.setName(scanner.next());
            } else if (ans == 2) {
                System.out.println("enter new rank:");
                player.setRank(scanner.nextDouble());
            } else if (ans == 3) {
                squad.removePlayer(player);
                break;
            } else if (ans == 4) {
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

