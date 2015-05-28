

package fat16;

import Module1.*;
import java.io.RandomAccessFile;
/**
 *
 * @author ragindemon
 */
public class FAT16 {
    
    public static void main(String[] args) {
//        try {
//            RandomAccessFile f = new RandomAccessFile("FAT16","rw");
//            f.setLength(268435456);
//            FAT fat = new FAT();
//            FileEntryArray fea = new FileEntryArray(512,0);
//            fea.writeFileEntryArrayToFile(f);
//            fat.writeFatToFile(f);
//            f.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            RandomAccessFile f = new RandomAccessFile("FAT16","rw");            
            Commands cmd = new Commands(f);
            FileEntryArray new_dir = cmd.mkdir(cmd.getRoot(), "xfiles.txt");
            cmd.mkdir(new_dir, "xfiles.txt");
            cmd.writeRoot();
            cmd.writeFat();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
