public class Program {
    public ImageService ImageService = new ImageService();

    public void Run() throws Exception {
        var bufferedImage = ImageService.Load("RainbowHeart.png");
        bufferedImage = ImageService.Convert(bufferedImage);
        ImageService.Save(bufferedImage, "RainbowHeart_converted.png");
        Designer.main(new String[] {});
    }

    public static void main(final String[] args) throws Exception {
        Program p = new Program();
        p.Run();
    }
}
