import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String [] args){
        Path task1_small = Paths.get("data/task1_small.txt");
        Path task1_big = Paths.get("data/task1_big.txt");

        Path task2_small = Paths.get("data/task2_small.txt");
        Path task2_big = Paths.get("data/task2_big.txt");

        Path task3_small = Paths.get("data/task2_small.txt");
        Path task3_big = Paths.get("data/task2_big.txt");

        ArrayList<Bookie> bookies = new ArrayList<>();

        try {
            BufferedReader br = Files.newBufferedReader(task1_big);
            String line = "";
            while((line = br.readLine()) != null){
                String[] splitLine = line.split(" ");
                bookies.add(new Bookie(splitLine[0], round(Float.parseFloat(splitLine[1])), round(Float.parseFloat(splitLine[2])), round(Float.parseFloat(splitLine[3]))));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println("All arbs:");
        ArrayList<Float> allArbs = getAllArbitrages(bookies); // Task 1
    }

    // Task 1
    public static ArrayList<Float> getAllArbitrages(ArrayList<Bookie> bookies){
        ArrayList<Float> arbitrages = new ArrayList<>();
        HashMap<String, Float> sortedWinOdds = new HashMap<>();
        HashMap<String, Float> sortedDrawOdds = new HashMap<>();
        HashMap<String, Float> sortedLossOdds = new HashMap<>();

        for(Bookie b : bookies){
            sortedWinOdds.put(b.getName(), 1/b.getWinOdd());
            sortedDrawOdds.put(b.getName(), 1/b.getDrawOdd());
            sortedLossOdds.put(b.getName(), 1/b.getLossOdd());
        }

        // sort HashMaps in asc order
        sortedWinOdds = sortOdds(sortedWinOdds);
        sortedLossOdds = sortOdds(sortedLossOdds);
        sortedDrawOdds = sortOdds(sortedDrawOdds);
        boolean arb = false;
        for (String i : sortedWinOdds.keySet()) {
            if(arb){
                break;
            }
            for(String j : sortedDrawOdds.keySet()){
                for(String k : sortedLossOdds.keySet()){
                    arb = sortedDrawOdds.get(j) + sortedLossOdds.get(k) + sortedWinOdds.get(i) > 1.0000;
                    if(sortedDrawOdds.get(j) + sortedLossOdds.get(k) + sortedWinOdds.get(i) < 1.0000){
                        float winArb = sortedWinOdds.get(i);
                        float drawArb = sortedDrawOdds.get(j);
                        float lossArb = sortedLossOdds.get(k);
                        float totalArb = winArb + drawArb + lossArb;
                        System.out.println("bookie Win: " + i + " bookie Draw: " + j + " bookie Loss: " + k + " arb value: " + totalArb);
                        arbitrages.add(totalArb);
                    }
                }
            }
        }
        return arbitrages;
    }

    // Task 2
    public static void getHighestProfit(ArrayList<Bookie> bookies){
        Bookie highestWin = new Bookie("Temp", -999, -999, -999);
        Bookie highestDraw = new Bookie("Temp", -999, -999, -999);
        Bookie highestLoss = new Bookie("Temp", -999, -999, -999);
        for(Bookie b : bookies){
            if(b.getWinOdd() > highestWin.getWinOdd()){
                highestWin = b;
            }
            if(b.getDrawOdd() > highestDraw.getDrawOdd()){
                highestDraw = b;
            }
            if(b.getLossOdd() > highestLoss.getLossOdd()){
                highestLoss = b;
            }
        }

        System.out.println("Highest win odds is " + highestWin.getName() + ": " + highestWin.getWinOdd());
        System.out.println("Highest draw odds is " + highestDraw.getName() + ": " + highestDraw.getDrawOdd());
        System.out.println("Highest loss odds is " + highestLoss.getName() + ": " + highestLoss.getLossOdd());
        float totalArb = (1/highestWin.getWinOdd()) + (1/highestDraw.getDrawOdd()) + (1/highestLoss.getLossOdd());

        System.out.println("Total arb is: " + totalArb);
    }

    // Task 3
    public static ArrayList<Float> topKProfit(ArrayList<Float> arbs, int quantity){
        ArrayList<Float> returns = new ArrayList<>();
        for(int i = 0; i < quantity; i++){
            returns.add(arbs.get(i));
        }
        return returns;
    }

    // Other methods
    public static HashMap<String, Float> sortOdds(HashMap<String, Float> givenMap){
        return givenMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Float>comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public static float round(float val){
        return (float) (Math.round(val * 100.0) / 100.0);
    }
}
