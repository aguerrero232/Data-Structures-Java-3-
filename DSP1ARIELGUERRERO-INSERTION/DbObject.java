import java.io.*;

public interface DbObject {
    public void writeToFile(RandomAccessFile out) throws IOException;
    public void readFromFile(RandomAccessFile in) throws IOException;
    public void readFromConsole() throws IOException;
    public void writeLegibly() throws IOException;
    public void readKey() throws IOException;
    public void copy(DbObject[] db);
    public int size();
}

