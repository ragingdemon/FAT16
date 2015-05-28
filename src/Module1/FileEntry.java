package Module1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class FileEntry {

    private byte first_file_byte; //0
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
    public static final int SIZE = 32;

    public FileEntry() {
        first_file_byte = 0;
        date = 0;
        attributes = 0;
        start_cluster = 0;
        file_size = 0;
    }

    public FileEntry(byte attributes, String name, long date, short start_cluster, int file_size) {
        first_file_byte = 0x0f;
        this.attributes = attributes;
        setName(name);
        this.date = date;
        this.start_cluster = start_cluster;
        this.file_size = file_size;
    }

    public FileEntry(RandomAccessFile file, long offset) throws IOException {
        synchronized (file) {
            file.seek(offset);
            first_file_byte = file.readByte();
            file.read(name);
            attributes = file.readByte();
            date = file.readLong();
            start_cluster = file.readShort();
            file_size = file.readInt();
            file.read(reserved);
        }
    }

    public byte getAttributes() {
        return attributes;
    }

    public void setAttributes(byte attributes) {
        this.attributes = attributes;
    }

    public byte getFirst_file_byte() {
        return first_file_byte;
    }

    public void setFirst_file_byte(byte first_file_byte) {
        this.first_file_byte = first_file_byte;
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

    public void writeEntryToFile(RandomAccessFile file, long offset) throws IOException {
        synchronized (file) {
            file.seek(offset);
            file.writeByte(first_file_byte);
            file.write(name);
            file.writeByte(attributes);
            file.writeLong(date);
            file.writeShort(start_cluster);
            file.writeInt(file_size);
            file.write(reserved);
        }
    }

    public void readEntryFromFile(RandomAccessFile file, long offset) throws IOException {
        synchronized (file) {
            file.seek(offset);
            first_file_byte = file.readByte();
            file.read(name);
            attributes = file.readByte();
            date = file.readLong();
            start_cluster = file.readShort();
            file_size = file.readInt();
            file.read(reserved);
        }
    }
}
