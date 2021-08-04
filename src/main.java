import classes.Player;
import classes.Squad;
import utils.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        List<Squad> squadList = new ArrayList<>();
        Squad dinur = new Squad("dinur",squadList);
        Player roy = new Player("roy", 3.5);
        Player tom = new Player("tom", 3.5456456);
        Player amit = new Player("amit", 4.5);
        Player barak = new Player("barak", 2);
        dinur.getPlayers().add(roy);
        dinur.getPlayers().add(tom);
        dinur.getPlayers().add(amit);
        dinur.getPlayers().add(barak);
        squadList.add(dinur);

        Squad randomSquad = new Squad("random",squadList);
        for (int i = 0; i < 20; i++) {
            String name = Generator.generateName(5);
            Random rnd = new Random();
            double num = rnd.nextDouble() * 5;
            Player temp = new Player(name, num);
            randomSquad.getPlayers().add(temp);
        }
        squadList.add(randomSquad);
//        randomSquad.match(4);

        while (true) {
            System.out.println(
                    """
                                                        
                            Welcome to Team-Maker!

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
            System.out.println("""

                    Match Menu:
                    """);
            if (squadExist) {
                System.out.println("""
                        Choose existing squad - 1
                        Create new squad - 2
                        Return - 3

                        Enter number between 1-3""");
                Scanner scanner = new Scanner(System.in);
                int ans = scanner.nextInt();
                if (ans == 1) {
                    System.out.println("""

                            Choose the number of the squad, you wish to play:
                            """);
                    int index = 1;
                    for (Squad s : squadList) {
                        System.out.println(String.valueOf(index++) + ": " + s);
                    }
                    int i = scanner.nextInt() - 1;
                    createMatch(squadList.get(i),squadList);
                } else if (ans == 2) {
                    Squad newSquad = createSquad(squadList);
                    squadList.add(newSquad);
                    createMatch(newSquad,squadList);
                } else if (ans == 3) {
                    break;
                }
            } else {
                System.out.println("""
                        Create new squad - 1
                        Return - 2

                        Enter number between 1-2""");
                Scanner scanner = new Scanner(System.in);
                int ans = scanner.nextInt();
                if (ans == 1) {
                    Squad squad = createSquad(squadList);
                    squadList.add(squad);
                } else if (ans == 2) {
                    break;
                }

            }
        }
    }

    //todo: create Match
    private static void createMatch(Squad squad,List<Squad> squadList) {
        Squad participatingSquad = participatingPlayers(squad,squadList);
        int numOfTeams = numOfTeams(participatingSquad);
        if (numOfTeams > 1) {
            participatingSquad.match(numOfTeams);
        } else {
            System.out.println("Team 1:");
            participatingSquad.printSquad();
        }
    }
    // todo: make it more easy to choose participating players

    private static Squad participatingPlayers(Squad squad,List<Squad> squadList) {
        Squad participatingSquad = new Squad("participating",squadList);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the number of players who will participate in the match:" +
                    "or type 'all' for all squad");
            String ans = scanner.next();
            if (ans.equals("all") || ans.equals("All") || ans.equals("ALL")) {
                return squad;
            } else if (isNumeric(ans)) {
                int numberOfPlayers = Integer.parseInt(ans);
                if (numberOfPlayers < 1 || numberOfPlayers > squad.getSize()) {
                    System.out.println("The number that was entered is not in range" + "\n");
                } else {
                    int index = 1;
                    for (Player s : squad.getPlayers()) {
                        System.out.println(String.valueOf(index++) + ": " + s);
                    }
                    int[] playersChosen = new int[squad.getSize()];
                    for (int i = 0; i < numberOfPlayers; i++) {
                        System.out.println("Enter next player participating number:");
                        int playerIndex = scanner.nextInt() - 1;
                        if (playersChosen[playerIndex] != 0) {
                            System.out.println("Player was already chosen");
                            i--;
                        } else {
                            playersChosen[playerIndex] = 1;
                            Player playerToAdd = squad.getPlayers().get(playerIndex);
                            participatingSquad.getPlayers().add(playerToAdd);
                            System.out.println(playerToAdd.getName() + " was added to participating team");
                        }
                    }
                    return participatingSquad;
                }
            }
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


//    todo: change number of teams you're allowed to choose

    private static int numOfTeams(Squad squad) {
        int squadSize = squad.getSize();
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
                                                        
                            Manage Squads:
                                                        
                            Create new squad - 1
                            Edit squad - 2
                            Return - 3
                            """);
            System.out.println("Enter number between 1-3");
            Scanner scanner = new Scanner(System.in);
            int ans = scanner.nextInt();

            if (ans == 1) {
                Squad squadToAdd = createSquad(squadList);
                squadList.add(squadToAdd);
                System.out.println(squadToAdd.getName() + " was added to the squad list");
            } else if (ans == 2) {
                if (squadList.isEmpty()) {
                    System.out.println("No squads to edit" + "\n");
                    break;
                }
                System.out.println("""

                        Choose the number of squad you wish to edit:
                        """);
                int index = 1;
                for (Squad s : squadList) {
                    System.out.println(String.valueOf(index++) + ": " + s);
                }
                int squadIndex = scanner.nextInt() - 1;
                editSquad(squadList, squadList.get(squadIndex));
            } else if (ans == 3) {
                break;
            }
        }
    }

    private static Squad createSquad(List<Squad> squadList) {

        Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + "Enter squad's name:");
            String squadName = scanner.next();
        Squad newSquad = new Squad(squadName,squadList);
        while (true) {
            System.out.println("Do you wish to add players to the squad?" + "\n" +
                    "Enter 'y' for yes, or 'n' for no:");
            String ans = scanner.next();
            if (ans.equals("n")) {
                break;
            } else if (ans.equals("y")) {
                newSquad.addPlayer();
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
                int index = 1;
                for (Player s : squad.getPlayers()) {
                    System.out.println(String.valueOf(index++) + ": " + s);
                }
                editPlayer(squad, squad.getPlayers().get(scanner.nextInt() - 1));
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
                player.setName(scanner.next(),squad);
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

