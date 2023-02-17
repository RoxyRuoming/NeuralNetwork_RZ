package matrix;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.xml.transform.Source;

import org.junit.Test;

import neural.matrix.Matrix;


public class MatrixTest { 
    @Test

    public void testEquals(){
        Matrix m1 = new Matrix(3,4, i -> 0.5 * (i-6));
        Matrix m2 = new Matrix(3,4, i -> 0.5 * (i-6));
        Matrix m3 = new Matrix(3,4, i -> 0.5 * (i-6.2));
        assertTrue(m1.equals(m2));
        assertFalse(m1.equals(m3));
    }

    @Test

    public void testAddMatrix() {
        Matrix m1 = new Matrix(2,2, i -> i);
        Matrix m2 = new Matrix(2,2, i -> 1.5 * i);
        Matrix expected = new Matrix(2,2, i -> 2.5 * i);

        Matrix result = m1.apply((index,value) -> value + m2.get(index));
        
        assertTrue(expected.equals(result));
    }

    @Test

    public void testMultiplyDouble() {
        Matrix m = new Matrix(3,4, i -> 0.5 * (i-6));
        double x  = 0.5;

        Matrix expected = new Matrix(3,4,i -> x * 0.5 * (i-6));
        Matrix result = m.apply((index, value)-> x * value);
        
        System.out.println(result);
        assertTrue(result.equals(expected));
        assertTrue(Math.abs(result.get(1)+ 1.25000) < 0.00001);


    }

    @Test

    public void testToString() {
        Matrix m = new Matrix(3, 4, i->i*2);

        // System.out.println(m);
        String text = m.toString(); // got a matrix of string

        double[] expected = new double[12];

        for(int i = 0; i < expected.length; i++) {
            expected[i] = i * 2;
        }
        var rows = text.split("\n"); // var - detects automatically the datatype of a variable based on the surrounding context

        assertTrue(rows.length == 3);

        int index = 0;
        
        for (var row : rows) {
            var values = row.split("\\s+"); // "\\s" - regex
            for(var textValue : values) {

                if(textValue.length() == 0) {
                    continue;
                }

                var doubleValue = Double.valueOf(textValue); // ?
                // System.out.println(v);
                assertTrue(Math.abs(doubleValue - expected[index]) < 0.0001); // Math.abs - absolute value

                index++;
            }
            

        }
         
    }
    
}
