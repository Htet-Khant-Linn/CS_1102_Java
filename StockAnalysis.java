import java.util.ArrayList;
// Htet Khant Linn
// Stock Analysis
public class StockAnalysis {

    public static void main(String[] args) {
        // Setup Data
        // array of 10 days of stock prices (using float)
        float[] stockArray = {10.5f, 20.0f, 10.5f, 30.0f, 25.5f, 10.5f, 40.0f, 20.0f, 50.0f, 30.0f};

        // ArrayList for same period (populated with same data)
        ArrayList<Float> stockList = new ArrayList<>();
        for (float price : stockArray) {
            stockList.add(price);
        }

        System.out.println("============================");
        System.out.println("Stock Data Analysis Project");
        System.out.println("============================");

        // Test calculateAveragPrice
        float average = calculateAveragePrice(stockArray);
        System.out.println("1. Average Stock Price: " + average);

        // Test findMaximumPrice
        float maxPrice = findMaximumPrice(stockArray);
        System.out.println("2. Maximum Stock Price: " + maxPrice);

        // test countOcurrences
        float targetPrice = 10.5f;
        int count = countOccurrences(stockArray, targetPrice);
        System.out.println("3. Occurrences of " + targetPrice + ": " + count + " times");

        // test computeCumulativeSum
        ArrayList<Float> cumulativeList = computeCumulativeSum(stockList);
        System.out.println("4. Cumulative Sums: " + cumulativeList);
    }

    
    // METHODS

    // calculate Average
    public static float calculateAveragePrice(float[] prices) {
        float sum = 0;
        // Loop through array to add up all prices
        for (int i = 0; i < prices.length; i++) {
            sum = sum + prices[i];
        }
        // Divide sum by total numbers of elements
        return sum / prices.length;
    }

    // find Maximum Price
    public static float findMaximumPrice(float[] prices) {
        float max = prices[0]; // assume first element is max initially
        
        for (int i = 1; i < prices.length; i++) {
            // if we find price bigger than our current max, update max
            if (prices[i] > max) {
                max = prices[i];
            }
        }
        return max;
    }

    // count Occurrences of specific price
    public static int countOccurrences(float[] prices, float target) {
        int count = 0;
        for (int i = 0; i < prices.length; i++) {
            // check if current price matches the target
            if (prices[i] == target) {
                count++;
            }
        }
        return count;
    }

    // compute Cumulative Sum (Using ArrayList)
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> prices) {
        ArrayList<Float> resultList = new ArrayList<>();
        float runningTotal = 0;

        // Llop through the input ArrayList
        for (int i = 0; i < prices.size(); i++) {
            runningTotal = runningTotal + prices.get(i); // Add current price to total
            resultList.add(runningTotal); // Add the new total to our result list
        }
        
        return resultList;
    }
}