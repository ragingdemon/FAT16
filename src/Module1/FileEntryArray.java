package Module1;

import java.io.IOException;
import java.io.RandomAccessFile;

//si el tamaño del arreglo es de 512 es el root
//si es de 128 es un directorio normal
public class FileEntryArray {
    public FileEntry[] array;

    public FileEntryArray(int n) {
        array = new FileEntry[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = new FileEntry();
        }
    }

    public FileEntryArray(RandomAccessFile file, int n) throws IOException {
        array = new FileEntry[n];
        for (FileEntry fileEntry : array) {
            fileEntry.readEntryFromFile(file);
        }
    }
    
    public void writeFileEntryArrayToFile(RandomAccessFile file) throws IOException{
        for (FileEntry fileEntry : array) {
            fileEntry.writeEntryToFile(file);
        }
    }
}
