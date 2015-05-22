package Module1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class FileEntry {
    
    private byte firts_file_byte; //0
    private byte[] name = new byte[10]; //1-10
    private byte attributes; //11
    private long date; //12-19
    private short start_cluster; //20-21
    private int file_size; ////22-25
    private byte[] reserved = new byte[6]; //26-31;
    
    //File attributes
    public static final byte READ_ONLY = 0x01;
    public static final byte HIDDEN_FILE = 0x02;
    public static final byte SYSTEM_FILE = 0x04;
    public static final byte VOLUME_LABEL = 0x08;
    public static final byte LONG_FILE_NAME = 0x0f;
    public static final byte DIRECTORY = 0x10;
    public static final byte ARCHIVE = 0x20;            

    public FileEntry() {
        firts_file_byte = 0;
        date = 0;
        attributes = 0;
        start_cluster = 0;
        file_size = 0;
    }

    public FileEntry(RandomAccessFile file) throws IOException {
        firts_file_byte = file.readByte();
        file.read(name);        
        attributes = file.readByte();
        date = file.readLong();
        start_cluster = file.readShort();
        file_size = file.readInt();
        file.read(reserved);
    }

    public byte getAttributes() {
        return attributes;
    }

    public void setAttributes(byte attributes) {
        this.attributes = attributes;
    }

    public byte getFirts_file_byte() {
        return firts_file_byte;
    }

    public void setFirts_file_byte(byte firts_file_byte) {
        this.firts_file_byte = firts_file_byte;
    }

    public byte[] getName() {
        return name;
    }

    public void setName(String str) {
        byte[] toByte = str.getBytes();
        for (int i = 0; i < toByte.length && i < 11; i++) {
            name[i] = toByte[i];
        }
    }

    public long getDate() {
        return date;
    }

    public void setDate() {
        Date d = new Date();
        date = d.getTime();
    }

    public short getStart_cluster() {
        return start_cluster;
    }

    public void setStart_cluster(short start_cluster) {
        this.start_cluster = start_cluster;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public void writeEntryToFile(RandomAccessFile file) throws IOException {
        file.writeByte(firts_file_byte);
        file.write(name);
        file.writeByte(attributes);
        file.writeLong(date);
        file.writeShort(start_cluster);
        file.writeInt(file_size);
        file.write(reserved);
    }
    
    public void readEntryFromFile(RandomAccessFile file) throws IOException {
        firts_file_byte = file.readByte();
        file.read(name);        
        attributes = file.readByte();
        date = file.readLong();
        start_cluster = file.readShort();
        file_size = file.readInt();
        file.read(reserved);
    }
}
