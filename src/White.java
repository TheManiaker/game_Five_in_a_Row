import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class White extends Chips {
    static Image img;

    static {
        try {
            img = ImageIO.read(new File("res/white.png"));
        } catch (IOException e) {
        }
    }
    public Image getImg() {
        return img;
    }
    White(){
    }
    White(int X, int Y){
        x = X;
        y = Y;
    }
}
