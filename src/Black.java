import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Black extends Chips {
    static Image img;

    static {
        try {
            img = ImageIO.read(new File("res/black.png"));
        } catch (IOException e) {
        }
    }
    public Image getImg() {
        return img;
    }
    Black(){
    }
    Black(int X, int Y){
        x = X;
        y = Y;
    }
}
