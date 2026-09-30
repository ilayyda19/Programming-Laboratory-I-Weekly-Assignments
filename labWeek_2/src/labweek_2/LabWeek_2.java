package labweek_2;

 import java.util.ArrayList;
 import java.util.Scanner;

public class LabWeek_2 {

    //Ilayda Ocal
    public static void main(String[] args) {
       
    
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("How many stops are on the route? ");
        int numStops = scanner.nextInt();

        System.out.print("What is the bus's seating capacity? ");
        int busCapacity = scanner.nextInt();
        scanner.nextLine(); 

        ArrayList<String> stopNames = new ArrayList<>();
        ArrayList<Integer> passengersBoarding = new ArrayList<>();
        ArrayList<Integer> passengersAlighting = new ArrayList<>();
        ArrayList<Integer> occupancyHistory = new ArrayList<>();

        System.out.println("\n--- Enter Stop Details ---");
        for (int i = 0; i < numStops; i++) {
            System.out.println("\nStop " + (i + 1) + ":");
            System.out.print("  Stop name: ");
            stopNames.add(scanner.nextLine());

            System.out.print("  Passengers boarding: ");
            passengersBoarding.add(scanner.nextInt());

            System.out.print("  Passengers alighting: ");
            passengersAlighting.add(scanner.nextInt());
            scanner.nextLine(); 
        }

        
        System.out.println("\n--- Route Progression ---");
        int currentPassengers = 0;
        int overCapacityCount = 0;

        for (int i = 0; i < numStops; i++) {
            String stop = stopNames.get(i);
            int boarding = passengersBoarding.get(i);
            int alighting = passengersAlighting.get(i);

            int potentialPassengers = currentPassengers + boarding - alighting;

            
            if (potentialPassengers < 0) {
                System.out.println("Data error at " + stop + ": cannot have more passengers alighting than are currently on the bus. Occupancy set to 0.");
                currentPassengers = 0;
            } else {
                currentPassengers = potentialPassengers;
            }

            occupancyHistory.add(currentPassengers);
            System.out.println("[" + stop + "] Current passengers on bus: " + currentPassengers);

           
            if (currentPassengers > busCapacity) {
                System.out.println("Warning: Bus is over capacity at " + stop + "!");
                overCapacityCount++;
            }
        }

        
        System.out.println("\n--- Route Summary ---");
        System.out.printf("%-20s %-12s %-14s %-18s%n", "Stop Name", "Boarding", "Alighting", "Current Occupancy");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < numStops; i++) {
            System.out.printf("%-20s %-12d %-14d %-18d%n",
                    stopNames.get(i),
                    passengersBoarding.get(i),
                    passengersAlighting.get(i),
                    occupancyHistory.get(i));
        }

        
        System.out.println("\n--- Statistics ---");

       
        int maxBoarding = passengersBoarding.get(0);
        int busiestIndex = 0;
        int totalOccupancySum = 0;

        for (int i = 0; i < numStops; i++) {
            if (passengersBoarding.get(i) > maxBoarding) {
                maxBoarding = passengersBoarding.get(i);
                busiestIndex = i;
            }
            totalOccupancySum += occupancyHistory.get(i);
        }

        System.out.println("Busiest stop: " + stopNames.get(busiestIndex) + " (" + maxBoarding + " passengers boarding)");

        
        double avgOccupancy = (double) totalOccupancySum / numStops;
        System.out.printf("Average occupancy across all stops: %.2f%n", avgOccupancy);

        
        System.out.println("Stops exceeding bus capacity: " + overCapacityCount);

       
        int finalOccupancy = currentPassengers;
        if (finalOccupancy != 0) {
            System.out.println("Warning: " + finalOccupancy + " passengers still on the bus after the final stop please check your data.");
        } else {
            System.out.println("Final stop reached: Bus is fully empty as expected.");
        }

        scanner.close();
   
    }
    
}
