/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ragindemon
 */
public class FatThread extends Thread{
    private FAT fat;
    private RandomAccessFile file;

    public FatThread(FAT fat, RandomAccessFile file) {
        this.fat = fat;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            fat.writeFatToFile(file);
        } catch (IOException ex) {
            Logger.getLogger(FatThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
