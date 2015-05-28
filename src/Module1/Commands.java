/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module1;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author ragindemon
 */
public class Commands {

    private RandomAccessFile file;
    private FileEntryArray root;
    private FAT fat;

    public Commands(RandomAccessFile file) throws IOException {
        this.file = file;
        fat = new FAT(file);
        root = new FileEntryArray(file, 512, 0);
    }

    public RandomAccessFile getFile() {
        return file;
    }

    public void setFile(RandomAccessFile file) {
        this.file = file;
    }

    public FileEntryArray getRoot() {
        return root;
    }

    public void setRoot(FileEntryArray root) {
        this.root = root;
    }

    public FAT getFat() {
        return fat;
    }

    public void setFat(FAT fat) {
        this.fat = fat;
    }
    
    public void writeRoot(){
        try {
            root.writeFileEntryArrayToFile(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void writeFat(){
        try {
            fat.writeFatToFile(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public FileEntryArray mkdir(FileEntryArray array, String dir_name) {
        //comprobar que hay espacio para otro directorio
        int index = array.indexFreeEntry();
        if (index == -1) {
            return null;
        }
        //
        short cluster = (short) fat.indexNextFreeCluster();
        //crear el directorio
        FileEntry entry = new FileEntry(FileEntry.DIRECTORY, dir_name, System.currentTimeMillis(), cluster, FAT.CLUSTER);
        array.setFileEntry(entry, index);
        //crear la estructura del nuevo directorio y guardarla en la seccion de datos
        FileEntryArray new_array = new FileEntryArray(FileEntryArray.DIR, cluster * FAT.CLUSTER);        
        try {
            array.writeFileEntryArrayToFile(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        fat.reservedCluster(cluster); //setar bit de cluster usado a 0
        return new_array;
    }
}
