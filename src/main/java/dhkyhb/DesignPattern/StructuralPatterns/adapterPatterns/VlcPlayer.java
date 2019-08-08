package dhkyhb.DesignPattern.StructuralPatterns.adapterPatterns;

/**
 * Create by hl
 * Data by 2019-05-15
 * Description:
 */
public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
