/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.karatsuba;
import java.math.BigInteger;
/**
 *
 * @author Jhon F Triana
 */
public class AlgoritmoKaratsuba {
    
     /**
   * @param u BigInteger Entero grande 1.
   * @param v BigInteger Entero grande 2.
   * @return BigInteger Resultado
   */
  public static BigInteger karatsuba(BigInteger u, BigInteger v) {
    int posiciones = Math.max(u.bitLength(), v.bitLength());
    //Para n menor que mil, es más eficiente la multiplicación normal.
    if (posiciones <= 1000) {
        return u.multiply(v);
    }
    posiciones = posiciones / 2;
    /*
     * Repartimos en trocitos:
     * u = w * 2^n + x
     * v = y * 2^n + z
     */
    BigInteger w = u.shiftRight(posiciones);
    BigInteger x = u.subtract(w.shiftLeft(posiciones));
    BigInteger y = v.shiftRight(posiciones);
    BigInteger z = v.subtract(y.shiftLeft(posiciones));
    // Calculamos los resultados parciales
    BigInteger p = karatsuba(w, y); //p=w*y
    BigInteger q = karatsuba(x, z); //q=x*z
    BigInteger r = karatsuba(x.add(w), z.add(y)); //r=(x+w)*(z+y)
    BigInteger z1 = r.subtract(p).subtract(q); //r-p-q
    // Se juntan los resultados parciales para obtener el resultado global.
    return p.shiftLeft(2 * posiciones).add(z1.shiftLeft(posiciones)).add(q);
  }
    /**
     * @param args the command line arguments
     */
    
}