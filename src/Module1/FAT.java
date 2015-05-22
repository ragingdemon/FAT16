package Module1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.BitSet;

/**
 *
 * @author ragindemon
 */
class fatElement {

    public short next;

    public fatElement() {
        next = 0;
    }

    public fatElement(short next) {
        this.next = next;
    }

    public fatElement(RandomAccessFile file) throws IOException {
        next = file.readShort();
    }

    public void readFatElementFromFile(RandomAccessFile file) throws IOException{
        next = file.readShort();
    }
    
    public void writeFatElementToFile(RandomAccessFile file) throws IOException {
        file.writeShort(next);
    }
}

//Bitset memory size = 65536 bit = 8 KB = 2 clusters
//fatElement[] memory size = 65536 * 2 B = 128 KB = 32 clusters
//los bit seteado en 1 significa que el cluster es disponible
public class FAT {

    private fatElement[] fat_array = new fatElement[65536]; //2^16
    private BitSet bit_map;

    public FAT() {
        bit_map = new BitSet(65536); //2^16
        bit_map.set(37, 65535,true); //los clusters de 0-36 son reservados por el root y el fat
        for (int i = 0; i < fat_array.length; i++) {
            fat_array[i] = new fatElement();
        }
    }

    public FAT(RandomAccessFile file) throws IOException {
        byte[] array = new byte[8192]; //8KB
        file.read(array);
        bit_map = BitSet.valueOf(array);
        for (fatElement f : fat_array) {
            f.readFatElementFromFile(file);
        }
    }

    public fatElement[] getFat_array() {
        return fat_array;
    }

    public void setFat_array(fatElement[] fat_array) {
        this.fat_array = fat_array;
    }

    public BitSet getBit_map() {
        return bit_map;
    }

    public void setBit_map(BitSet bit_map) {
        this.bit_map = bit_map;
    }

    
    public void writeFatToFile(RandomAccessFile file) throws IOException{
        file.write(bit_map.toByteArray());
        for (fatElement element : fat_array) {
            element.writeFatElementToFile(file);
        }
    }
}
