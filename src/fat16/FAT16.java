

package fat16;

import java.util.BitSet;

/**
 *
 * @author ragindemon
 */
public class FAT16 {
    
    public static void main(String[] args) {
        BitSet bits = new BitSet(128);
        for (int i = 0; i < bits.size(); i++) {
            if (i % 2 == 0) {
                bits.set(i);
            }
        }
        System.out.println("size of bitset: "+bits.size()+ " bits en el bit set: \n" + bits.toString());
        System.out.println("bitset to byte array:");
        byte[] array = bits.toByteArray();        
        for (byte b : array) {
            System.out.print(b + ", ");
        }        
    }
    
}
