package Module1;

import java.io.IOException;
import java.io.RandomAccessFile;

//si el tamaño del arreglo es de 512 es el root
//si es de 128 es un directorio normal
public class FileEntryArray {

    public FileEntry[] array;
    private long offset;

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
}
