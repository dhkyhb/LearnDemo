package dhkyhb.retrofit;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:
 */
@Data
@ToString
public class Info {

    private String title;
    private List<Tr> tr;
    private List<Bottomlink> bottomlink;

}
