package Module1;

import java.io.IOException;
import java.io.RandomAccessFile;

//si el tamaño del arreglo es de 512 es el root
//si es de 128 es un directorio normal
public class FileEntryArray {

    public FileEntry[] array;
    private long offset;
    
    public static final int ROOT = 512;
    public static final int DIR = 128;

    public FileEntryArray(int n, long offset) {
        array = new FileEntry[n];
        this.offset = offset;
        for (int i = 0; i < array.length; i++) {
            array[i] = new FileEntry();
        }
    }

    public FileEntryArray(RandomAccessFile file, int n, long offset) throws IOException {
        array = new FileEntry[n];
        this.offset = offset;
        for (int i = 0; i < array.length; i++) {
            array[i] = new FileEntry(file, offset + 32 * i);
        }
    }

    public void writeFileEntryArrayToFile(RandomAccessFile file) throws IOException {
        for (int i = 0; i < array.length; i++) {
            array[i].writeEntryToFile(file, offset + 32 * i);
        }
    }

    public int indexFreeEntry() {
        for (int i = 0; i < array.length; i++) {
            if (array[i].getStart_cluster() == 0) {
                return i;
            }
        }
        return -1;
    }
        
    public long offsetFreeEntry() {
        for (int i = 0; i < array.length; i++) {
            if (array[i].getStart_cluster() == 0) {
                return offset + i * 32;
            }
        }
        return -1;
    }    
    
    public void setFileEntry(FileEntry entry){
        for (int i = 0; i < array.length; i++) {
            if (array[i].getStart_cluster() == 0) {
                array[i] = entry;
                break;
            }
        }
    }
    
    public void setFileEntry(FileEntry entry, int n){
        array[n] = entry;
    }
}
