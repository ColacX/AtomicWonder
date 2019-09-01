import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

public class ImageService {
    public BufferedImage Load(String filePath) throws Exception {
        return ImageIO.read(new File(filePath));
    }

    public void Save(BufferedImage bufferedImage, String filePath) throws Exception {
        ImageIO.write(bufferedImage, "png", new File(filePath));
    }

    public BufferedImage Convert(BufferedImage sourceImage) throws Exception {
        return sourceImage;
    }
}
