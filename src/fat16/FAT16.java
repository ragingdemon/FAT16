

package fat16;

import java.util.BitSet;

/**
 *
 * @author ragindemon
 */
public class FAT16 {
    
    public static void main(String[] args) {
        byte[] values = new byte[10];
        for (byte b : values) {
            System.out.println(b);
        }
        BitSet bits = new BitSet(128);
        bits.set(63);
        //bits.set(0, bits.size(), false);
        /*
        for (int i = 0; i < bits.size(); i++) {
            if (i % 2 == 0) {
                bits.set(i);
            }
        }
        */
        System.out.println("size of bitset: "+bits.size()+ " bits en el bit set: \n" + bits.toString());
        System.out.println("bitset to byte array:");        
        byte[] array = bits.toByteArray();
        System.out.println("size:" + array.length);
        for (byte b : array) {
            System.out.print(b + ", ");
        }        
    }
    
}
