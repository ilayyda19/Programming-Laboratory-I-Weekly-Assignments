package labweek_1;

import java.util.Scanner;

public class LabWeek_1 {

    //Ilayda Ocal
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("----------MATCHS----------");
        System.out.println("1. Match: A-B");
        System.out.println("2. Match: A-C");
        System.out.println("3. Match: A-D");
        System.out.println("4. Match: B-C");
        System.out.println("5. Match: B-D");
        System.out.println("6. Match: C-D");
        System.out.println("--------------------------");
        
        String[] teamNames = {"Team A", "Team B", "Team C", "Team D"};
        
        int[][] matches = {
            {0, 1}, // Match 1: A vs B
            {0, 2}, // Match 2: A vs C
            {0, 3}, // Match 3: A vs D
            {1, 2}, // Match 4: B vs C
            {1, 3}, // Match 5: B vs D
            {2, 3}  // Match 6: C vs D
        };
        
        int[] played = new int[4];
        int[] wins = new int[4];
        int[] draws = new int[4];
        int[] losses = new int[4];
        int[] points = new int[4];
        int[] goalsFor = new int[4];
        int[] goalsAgainst = new int[4];
        
        int[][] scores = new int[6][2];
        
        for(int i=0; i< matches.length; i++){
            int team1 = matches[i][0];
            int team2 = matches[i][1];
            
            System.out.println("\nMatch " + (i+1) + ": " + teamNames[team1] + " vs " + teamNames[team2]);
            System.out.print(teamNames[team1] + " goals: ");
            int g1 = scanner.nextInt();
            System.out.print(teamNames[team2] + " goals: ");
            int g2 = scanner.nextInt();
            
            scores[i][0] = g1;
            scores[i][1] = g2;
            
            played[team1]++;
            played[team2]++;
            
            goalsFor[team1] += g1;
            goalsAgainst[team1] += g2;
            goalsFor[team2] += g2;
            goalsAgainst[team2] += g1;
            
            if(g1 > g2){
                wins[team1]++;
                losses[team2]++;
                points[team1] +=3;
                
            }else if(g2 > g1){
                wins[team2]++;
                losses[team1]++;
                points[team2] +=3;
            }else{
                draws[team1]++;
                draws[team2]++;
                points[team1] +=1;
                points[team2] +=1;
            }
            
        }
        
        System.out.println("\n=========== MATCH SCORES ===========");
        for(int i=0; i< matches.length; i++){
            int t1 = matches[i][0];
            int t2 = matches[i][1];
            System.out.println("Match " + (i+1)+ ": " + teamNames[t1] + " " +scores[i][0] + " - " + scores[i][1] + " " + teamNames[t2]);
            
        }
        
        int[] goalDifference = new int[4];
        for(int i =0; i<4; i++){
            goalDifference[i] = goalsFor[i] - goalsAgainst[i];
        }
        
        System.out.println("\n================================ STANDINGS TABLE ================================");
        System.out.printf("%-10s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s%n",
                "Team", "P", "W", "D", "L", "GF", "GA", "GD", "Pts");
        System.out.println("------------------------------------------------------------------------------");
        for(int i=0;i<4; i++){
            System.out.printf("%-10s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s%n",
                    teamNames[i], played[i], wins[i], draws[i], losses[i],
                    goalsFor[i], goalsAgainst[i], goalDifference[i], points[i]);
        }
        System.out.println("------------------------------------------------------------------------------");
        
        int championIdx = 0;
        for(int i =1; i<4; i++){
            if(points[i]> points[championIdx]){
                championIdx = i;
            }else if(points[i] == points[championIdx]){
                if(goalDifference[i]> goalDifference[championIdx]){
                    championIdx = i;
                }else if(goalDifference[i] == goalDifference[championIdx]){
                    if(goalsFor[i] > goalsFor[championIdx]){
                        championIdx = i;
                    }
                }
            }
        }
        
        System.out.println("\nTournament Champion: " + teamNames[championIdx]);
        scanner.close();
    }
    
}
