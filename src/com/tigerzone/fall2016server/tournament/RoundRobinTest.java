package com.tigerzone.fall2016server.tournament;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aidan on 11/19/2016.
 */
public class RoundRobinTest {

    public static void listMatches(List<String> listTeam) {

        if (listTeam.size() % 2 != 0) {

            listTeam.add("Bye"); // If odd number of teams add a dummy
        }

        int numDays = (listTeam.size() - 1); // Days needed to complete tournament
        int halfSize = listTeam.size() / 2;

        List<String> teams = new ArrayList<>();

        teams.addAll(listTeam); // Add teams to List and remove the first team
        teams.remove(0);

        int teamsSize = teams.size();

        for (int day = 0; day < numDays; day++)
        {
            System.out.println("Day " + (day + 1));

            int teamIdx = day % teamsSize;

            System.out.println(teams.get(teamIdx) + " vs " + listTeam.get(0));

            for (int idx = 1; idx < halfSize; idx++)
            {
                int firstTeam = (day + idx) % teamsSize;
                int secondTeam = (day  + teamsSize - idx) % teamsSize;
                System.out.println((teams.get(firstTeam) +  " vs " + teams.get(secondTeam)));
            }
        }
    }

    public static void main(String [] args){
        List<String> teamList1 = new ArrayList<String>();
        teamList1.add("TEAM1");
        teamList1.add("TEAM2");
        teamList1.add("TEAM3");
        teamList1.add("TEAM4");
        listMatches(teamList1);
        System.out.println("\n***TESTING WITH ODD NUMBER OF TEAMS***\n");
        List<String> teamList2 = new ArrayList<String>();
        teamList2.add("TEAM1");
        teamList2.add("TEAM2");
        teamList2.add("TEAM3");
        teamList2.add("TEAM4");
        teamList2.add("TEAM5");
        listMatches(teamList2);
    }

}
