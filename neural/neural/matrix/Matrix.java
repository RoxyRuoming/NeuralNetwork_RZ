package neural.matrix;

import java.util.Arrays;

import javax.sql.RowSet;

public class Matrix {
    private int rows;
    private int cols;

    private static final String NUMBER_FORMAT = "%+12.5f";
    private static final double TOLERANCE = 0.00001;

    public interface Producer {
        double produce(int index);
    }

    public interface ValueProducer { //?
        double produce(int index, double value);
    }

    private double[] a; //? 

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        a = new double[rows * cols];
    }

    public Matrix(int rows, int cols, Producer producer) {
        this(rows, cols);
        a = new double[rows * cols];

        for (int i = 0; i < a.length; i++) {
            a[i] = producer.produce(i);
        }

    }

    public Matrix apply(ValueProducer producer) { //?
        Matrix result = new Matrix(rows, cols);

        for(int i = 0; i < a.length; i++) {
            result.a[i] = producer.produce(i,a[i]); 
        }
        return result;
    }

    public double get(int index) {
        return a[index];
    }
    

    @Override
    public int hashCode() {  
        final int prime = 31;
        int result = 1;
        result = prime * result + rows;
        result = prime * result + cols;
        result = prime * result + Arrays.hashCode(a);
        return result;
    }

    @Override
    public boolean equals(Object obj) {  //
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Matrix other = (Matrix) obj;
       
        for(int i= 0 ;i < a.length;i++) {
            if(Math.abs(a[i] - other.a[i]) > TOLERANCE) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {
                sb.append(String.format(NUMBER_FORMAT, a[index]));

                index++;
            }

            sb.append("\n");
        }

        return sb.toString();

    }

}
