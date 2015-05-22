

package fat16;

import java.util.BitSet;
import Module1.*;
import java.io.RandomAccessFile;
/**
 *
 * @author ragindemon
 */
public class FAT16 {
    
    public static void main(String[] args) {
        try {
            RandomAccessFile f = new RandomAccessFile("FAT16","rw");
            FAT fat = new FAT();
            FileEntryArray fea = new FileEntryArray(512);
            fea.writeFileEntryArrayToFile(f);
            fat.writeFatToFile(f);
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
