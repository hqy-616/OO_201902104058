import javax.swing.*;
import java.awt.*;

/**
 * @author 佀同光 STGLJY@126.COM
 *
 */
public class ImgHelper {
	/**
	 * @param filename 文件名
	 * @return Image对象
	 */
	public static Image getImage(String filename) {
		ImageIcon imageIcon = new ImageIcon(filename);
		Image image = imageIcon.getImage();
		return image;		
	}	

}
