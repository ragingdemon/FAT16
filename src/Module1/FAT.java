package Module1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.BitSet;

/**
 *
 * @author ragindemon
 */


//Bitset memory size = 65536 bit = 8 KB = 2 clusters
//short[] memory size = 65536 * 2 B = 128 KB = 32 clusters
//los bit seteado en 1 significa que el cluster es disponible
public class FAT {

    private short[] fat_array = new short[65536]; //2^16
    private BitSet bit_map;
    
    public static final int ROOT = 16384;
    public static final int CLUSTER = 4096;

    public FAT() {
        bit_map = new BitSet(65536); //2^16
        bit_map.set(37, 65535, true); //los clusters de 0-36 son reservados por el root y el fat
    }

    public FAT(RandomAccessFile file) throws IOException {
        synchronized (file) {
            file.seek(16384);
            byte[] array = new byte[8192]; //8KB
            file.read(array);
            bit_map = BitSet.valueOf(array);
            for (int i = 0; i < fat_array.length; i++) {
                fat_array[i] = file.readShort();
            }
        }
    }
    
    //-1 si no hay clusters disponibles
    public int indexNextFreeCluster(){
        return bit_map.nextSetBit(0);
    }
    
    public void freeCluster(int index){
        bit_map.set(index);
    }
    
    public void reservedCluster(int index){
        bit_map.set(index, false);
    }

    public void writeFatToFile(RandomAccessFile file) throws IOException {
        synchronized (file) {
            file.seek(16384);
            file.write(bit_map.toByteArray());
            for (short element : fat_array) {
                file.writeShort(element);
            }
        }
    }
}
