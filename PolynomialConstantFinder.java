import java.math.BigDecimal;
import java.math.BigInteger;

public class PolynomialConstantFinder {
    public static void main(String[] args) {
        
        int n = 10; 
        int k = 7;  
        Object[][] pointsData = {
            {1, 6, "13444211440455345511"}, // x=1, base=6, value
            {2, 15, "aed7015a346d63"},      // x=2, base=15, value
            {3, 15, "6aeeb69631c227c"},     // x=3, base=15, value
            {4, 16, "e1b5e05623d881f"},     // x=4, base=16, value
            {5, 8, "316034514573652620673"}, // x=5, base=8, value
            {6, 3, "2122212201122002221120200210011020220200"}, // x=6, base=3, value
            {7, 3, "20120221122211000100210021102001201112121"}, // x=7, base=3, value
            {8, 6, "20220554335330240002224253"}, // x=8, base=6, value
            {9, 12, "45153788322a1255483"}, // x=9, base=12, value
            {10, 7, "1101613130313526312514143"} 
        };

        
        BigDecimal[] xValues = new BigDecimal[k];
        BigDecimal[] yValues = new BigDecimal[k];
        
        
        for (int i = 0; i < k; i++) {
            xValues[i] = new BigDecimal((int) pointsData[i][0]);
            
            int base = (int) pointsData[i][1];
            String value = (String) pointsData[i][2];
            yValues[i] = new BigDecimal(new BigInteger(value, base));
        }

     
        BigDecimal constantTerm = lagrangeInterpolation(xValues, yValues, k);
        System.out.println("The constant term c is: " + constantTerm.toBigInteger());
    }

    private static BigDecimal lagrangeInterpolation(BigDecimal[] xValues, BigDecimal[] yValues, int k) {
        BigDecimal result = BigDecimal.ZERO;
        
        for (int i = 0; i < k; i++) {
            BigDecimal xi = xValues[i];
            BigDecimal yi = yValues[i];
            BigDecimal term = yi;
            for (int j = 0; j < k; j++) {
                if (j != i) {
                    BigDecimal xj = xValues[j];
                    
                    term = term.multiply(xj.negate()).divide(xi.subtract(xj), 10, BigDecimal.ROUND_HALF_UP);
                }
            }
            result = result.add(term);
        }
        return result;
    }
}