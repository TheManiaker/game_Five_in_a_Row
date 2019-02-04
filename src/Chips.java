import java.awt.*;

// класс абстракции игровых фишек

public abstract class Chips {
    int x,y;
    Chips(){
        x = Window.x-(Window.x -35)%50;
        y = Window.y-(Window.y -35)%50;
    }

    abstract Image getImg();
}

