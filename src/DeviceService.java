import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

public class DeviceService {
    public void Transfer(String devicePort, int[] data) throws Exception {
        System.out.println("DATA INT {");
        for (var d : data) {
            System.out.print(d + ",");
        }
        System.out.println("}");
        new SimpleSerial().connect(devicePort, data);
    }
}
