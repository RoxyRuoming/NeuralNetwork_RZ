package neural.neuralnetwork;

// @formatter: off
// @formatter: on
public class App {

    static double neuron(double[] x, double[]w, double b){
        double z = 0.0;

        for(int i = 0; i < x.length; i++){
            z += x[i] * w[i];
        }

        z += b;

        // activation function
        return  z > 0 ? 1.0: 0.0;
    }
    
    // logic gates
    static double and(double x1, double x2){
        return neuron(new double[] {x1,x2}, new double[] {1,1}, -1); // new double
    }
    static double or(double x1, double x2){
        return neuron(new double[] {x1,x2}, new double[] {1,1},0); //new double
    }
    static double xor(double x1, double x2){
        return and(or(x1,x2),nand(x1,x2));
    }
    static double nor(double x1, double x2){
        return neuron(new double[] {x1,x2}, new double[] {-1,-1}, 1); // new double
    }
    static double nand(double x1, double x2){
        return neuron(new double[] {x1,x2}, new double[] {-1,-1}, 2); // new double
    }
    static double xnor(double x1, double x2){
        return or(and(x1,x2) ,nor(x1,x2));
    }


    public static void main(String[] args) {
        for(int i = 0; i < 4; i++){
            double x1 = i / 2;
            double x2 = i % 2;
            double output = xnor(x1, x2);
            System.out.printf("%d%d\t%d\n", (int)x1, (int)x2, (int)output); // (int), "%d%d\t%d\n"
        }


    }
}