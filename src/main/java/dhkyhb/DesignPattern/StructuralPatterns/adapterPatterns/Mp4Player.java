package dhkyhb.DesignPattern.StructuralPatterns.adapterPatterns;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}
