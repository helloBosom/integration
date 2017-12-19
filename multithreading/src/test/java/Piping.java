import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Piping {
    public static void main(String[] args) {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        try {
            pipedOutputStream.connect(pipedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
