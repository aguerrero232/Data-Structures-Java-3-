import java.io.*;

public class Student extends Personal {
    public int size() {
        return super.size() + majorLen*2;
    }
    protected String major;
    protected final int majorLen = 10;
    Student() {
        super();
    }
    Student(String ssn, String n, String c, int y, long s, String m) {
        super(ssn,n,c,y,s);
        major = m;
    }
    public void writeToFile(RandomAccessFile out) throws IOException {
        super.writeToFile(out);
        writeString(major,out);
    }
    public void readFromFile(RandomAccessFile in) throws IOException {
        super.readFromFile(in);
        major = readString(majorLen,in);
    }
    public void readFromConsole() throws IOException {
        super.readFromConsole();
        System.out.print("Enter major: ");
        major = readLine();
        for (int i = major.length(); i < nameLen; i++)
            major += ' '; 
    }
    public void writeLegibly() {
        super.writeLegibly();
        System.out.print(", major = " + major.trim());
    }
    public void copy(DbObject[] d) {
        d[0] = new Student(SSN,name,city,year,salary,major);
    }
}
