package A4;

import java.util.Scanner;

//refernece: chapter 6 of ADA manual
public class BestConversionFinder {

    private static final int INFINITY = Integer.MAX_VALUE;
    private static final int NO_EXCHANGE_RATE = 0;
    private int n; // number of vertices in the graph
    private double[][][] d; //d[k][i][i] is weight of path from v_i to v_j
    private double[][][] p; //p[k][i][i] is penultimate vertex in path

    public BestConversionFinder(double[][] weights) {
        n = weights.length;
        d = new double[n + 1][][];
        d[0] = weights;
        // create p[0]
        p = new double[n + 1][][];
        p[0] = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (weights[i][j] < INFINITY) {
                    p[0][i][j] = i;
                } else {
                    p[0][i][j] = NO_EXCHANGE_RATE;
                }
            }
        }
        // build d[1],...,d[n] and p[1],...,p[n] dynamically
        for (int k = 1; k <= n; k++) {
            d[k] = new double[n][n];
            p[k] = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double s;
                    if (d[k - 1][i][k - 1] != INFINITY && d[k - 1][k - 1][j] != INFINITY) {
                        s = d[k - 1][i][k - 1] + d[k - 1][k - 1][j];
                    } else {
                        s = INFINITY;
                    }
                    if (d[k - 1][i][j] <= s) {
                        d[k][i][j] = d[k - 1][i][j];
                        p[k][i][j] = p[k - 1][i][j];
                    } else {
                        d[k][i][j] = s;
                        p[k][i][j] = p[k - 1][k - 1][j];
                    }
                }
            }
        }
    }

    // returns a string representation of matrix d[n] and p[n]
    public String toString() {
        String output = "Shortest lengths\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (d[n][i][j] != INFINITY) {
                    output += ("\t" + d[n][i][j]);
                } else {
                    output += "\tinfin";
                }
            }
            output += "\n";
        }
        output += "Previous vertices on shortest paths\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (p[n][i][j] != NO_EXCHANGE_RATE) {
                    output += ("\t" + p[n][i][j]);
                } else {
                    output += "\tnull";
                }
            }
            output += "\n";
        }
        return output;
    }

    public static void main(String[] args) {
        double[][] pickedRate = {
            {1, 0.61, 0, 1.08, 0.72},
            {1.64, 1, 0, 1.77, 1.18},
            {0, 0, 1, 0, 0.047},
            {0.92, 0.56, 0, 1, 0.67},
            {1.39, 0.85, 21.19, 1.5, 1}};

        double[][] rates = {
            {1, 0.61, 0, 1.08, 0.72},
            {1.64, 1, 0, 1.77, 1.18},
            {0, 0, 1, 0, 0.047},
            {0.92, 0.56, 0, 1, 0.67},
            {1.39, 0.85, 21.19, 1.5, 1}};

        double[][] weighted_rate = {
            {0, 0.494296, 0, -0.07696, 0.328504},
            {-0.4947, 0, 0, -0.57098, -0.16551},
            {0, 0, 0, 0, 3.057608},
            {0.083382, 0.579818, 0, 0, 0.400478},
            {-0.3293, 0.162519, -3.05353, -0.40547, 0}};

        Scanner input = new Scanner(System.in);
        System.out.println("Pick your conversion from NZD");
        System.out.println("Enter ONE of: EUR, USD, AUD, MXN for the conversion from NZD");
        String currency = input.next();

        if (currency == "EUR") {
            //pickedRate = rates[4][2];
            // graph variable at nzd and eur
        }
        if (currency == "USD") {
            //pickedRate = rates[4][5];
        }
        if (currency == "AUD") {
            //pickedRate = rates[4][1];
        }
        if (currency == "MXN") {
            //pickedRate = rates[4][3];
        } else {
            pickedRate = rates;
        }

        BestConversionFinder apfw = new BestConversionFinder(pickedRate);
        System.out.println(apfw);
    }
}
