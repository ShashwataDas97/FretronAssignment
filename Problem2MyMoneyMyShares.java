import java.util.*;
public class Problem2MyMoneyMyShares {
    // Function to allocate apples to Ram
    public static boolean shareForRam(ArrayList< Integer > appleWeightList, int[] weights, boolean[] visited, int startIndex, int ramTotalWeight, String ramAns) {
        // Base Condition for Ram
        if (ramTotalWeight == 0) {
            System.out.println("Ram: " + ramAns);
            return true;
        }
        if (ramTotalWeight < 0 || startIndex >= visited.length) {
            return false;
        }
        if (visited[startIndex] == false) {
            // Pick
            visited[startIndex] = true;
            if (shareForRam(appleWeightList, weights, visited, startIndex + 1, ramTotalWeight - appleWeightList.get(startIndex), ramAns + appleWeightList.get(startIndex) + " ") == true) {
                return true;
            }
            visited[startIndex] = false;
            // Not pick
            if (shareForRam(appleWeightList, weights, visited, startIndex + 1, ramTotalWeight, ramAns) == true) {
                return true;
            }
        } else {
            // Move to the next apple if already visited
            if (shareForRam(appleWeightList, weights, visited, startIndex + 1, ramTotalWeight, ramAns) == true) {
                return true;
            }
        }
        return false;
    }
    // Function to allocate apples to Sham
    public static boolean shareForSham(ArrayList < Integer > appleWeightList, int[] weights, boolean[] visited, int startIndex, int shamTotalWeight, String shamAns) {
        // Base Condition for Sham
        if (shamTotalWeight == 0) {
            boolean ans = shareForRam(appleWeightList, weights, visited, 0, weights[2], "");
            if (ans == true) {
                System.out.println("Sham: " + shamAns);
                return true;
            }
            return false;
        }
        if (shamTotalWeight < 0 || startIndex >= visited.length) {
            return false;
        }
        if (visited[startIndex] == false) {
            // Pick
            visited[startIndex] = true;
            if (shareForSham(appleWeightList, weights, visited, startIndex + 1, shamTotalWeight - appleWeightList.get(startIndex), shamAns + appleWeightList.get(startIndex) + " ") == true) {
                return true;
            }
            visited[startIndex] = false;
            // Not pick
            if (shareForSham(appleWeightList, weights, visited, startIndex + 1, shamTotalWeight, shamAns) == true) {
                return true;
            }
        } else {
            // Move to the next apple if already visited
            if (shareForSham(appleWeightList, weights, visited, startIndex + 1, shamTotalWeight, shamAns) == true) {
                return true;
            }
        }
        return false;
    }
    // Function to allocate apples to Rahim
    public static boolean shareForRahim(ArrayList < Integer > appleWeightList, int[] weights, boolean[] visited, int startIndex, int rahimTotalWeight, String rahimAns) {
        // Base Condition for Rahim
        if (rahimTotalWeight == 0) {
            boolean ans = shareForSham(appleWeightList, weights, visited, 0, weights[1], "");
            if (ans == true) {
                System.out.println("Rahim: " + rahimAns);
                return true;
            }
            return false;
        }
        if (rahimTotalWeight < 0 || startIndex >= visited.length) {
            return false;
        }
        if (visited[startIndex] == false) {
            // Pick
            visited[startIndex] = true;
            if (shareForRahim(appleWeightList, weights, visited, startIndex + 1, rahimTotalWeight - appleWeightList.get(startIndex), rahimAns + appleWeightList.get(startIndex) + " ") == true) {
                return true;
            }
            visited[startIndex] = false;
            // Not pick
            if (shareForRahim(appleWeightList, weights, visited, startIndex + 1, rahimTotalWeight, rahimAns) == true) {
                return true;
            }
        } else {
            // Move to the next apple if already visited
            if (shareForRahim(appleWeightList, weights, visited, startIndex + 1, rahimTotalWeight, rahimAns) == true) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList < Integer > appleWeightList = new ArrayList < > ();
        System.out.println("Enter apple weight in grams (-1 to stop ): ");
        int weight = sc.nextInt();
        while (weight != -1) {
            appleWeightList.add(weight);
            System.out.println("Enter apple weight in grams (-1 to stop ): ");
            weight = sc.nextInt();
        }
        // Calculate the total weight of apples
        int totalWeight = 0;
        for (int i = 0; i < appleWeightList.size(); i++) {
            totalWeight = totalWeight + appleWeightList.get(i);
        }
        // Calculate the target weights for Ram, Sham, and Rahim
        double ramProportion = 50.0 / 100;
        double shamProportion = 30.0 / 100;
        double rahimProportion = 20.0 / 100;
        int ramWeight = (int)(totalWeight * ramProportion);
        int shamWeight = (int)(totalWeight * shamProportion);
        int rahimWeight = (int)(totalWeight * rahimProportion);
        int[] weights = {rahimWeight, shamWeight, ramWeight};
        boolean[] visited = new boolean[appleWeightList.size()];
        // Start the allocation process with Rahim
        if (shareForRahim(appleWeightList, weights, visited, 0, rahimWeight, "") == false) {
            System.out.println("No valid distribution found.");
        }
    }
}