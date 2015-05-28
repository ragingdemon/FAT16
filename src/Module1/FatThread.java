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
public class FatThread extends Thread {

    private final FAT fat;
    private final RandomAccessFile file;

    public FatThread(FAT fat, RandomAccessFile file) {
        this.fat = fat;
        this.file = file;
    }

    @Override
    public void run() {
        synchronized (file) {
            try {
                fat.writeFatToFile(file);
                wait(5000);
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }        
    }
}
