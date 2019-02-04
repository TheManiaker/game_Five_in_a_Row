import java.util.Vector;

// класс логики приложения и игры

public class Habitat {
    Window frame;
    Vector<Chips> vec;
    Vector<Integer> layout;
    int value;
    public Habitat (){
        vec = new Vector<>();
        frame = new Window(this);
    }
    public int draw(){
        int flag = 1;
        for (int i =0; i< frame.fieldSize; i++ ) {
            m: for (int j = 0; j < frame.fieldSize; j++) {
                for (int l = 0; l < vec.size(); l++) {
                    if (j * 50 + 35 == vec.get(l).x && i * 50 + 35 == vec.get(l).y) {
                        continue m;
                    }
                }
                flag = 0;
            }
        }
        return flag;
    }
    public int checking(int x, int y, int turn){
        layout = new Vector<>();
        for (int i = 0; i < vec.size(); i++) {
            if (vec.get(i) instanceof White){
                layout.add(1);
            }
            else {
                layout.add(2);
            }
        }
        u: for (int i = 0; i<5; i++){
            if ((x-(4-i)*50<0)){
                continue;
            }
            if (x + i*50 >= frame.W-35) break;
            t: for (int j = 0; j < 5; j++) {
                for (int l = 0; l < vec.size(); l++) {
                    if (x -(4-i-j)*50 == vec.get(l).x && y == vec.get(l).y){
                        if (layout.get(l) == turn) {
                            value ++;
                            continue t;
                        }
                        else{
                            value = 0;
                            continue u;
                        }
                    }
                }
            }
            if (value == 5){
                return 1;
            }
            value = 0;
        }

        u: for (int i = 0; i<5; i++){
            if ((y-(4-i)*50<0)){
                continue;
            }
            if (y + i*50 >= frame.H-55) break;
            t: for (int j = 0; j < 5; j++) {
                for (int l = 0; l < vec.size(); l++) {
                    if (x == vec.get(l).x && y-(4-i-j)*50 == vec.get(l).y){
                        if (layout.get(l) == turn) {
                            value ++;
                            continue t;
                        }
                        else{
                            value = 0;
                            continue u;
                        }
                    }
                }
            }
            if (value == 5){
                return 1;
            }
            value = 0;
        }

        u: for (int i = 0; i<5; i++){
            if ((x-(4-i)*50<0)){
                continue;
            }
            if ((y-(4-i)*50<0)){
                continue;
            }
            if (x + i*50 >= frame.W-35) break;
            if (y + i*50 >= frame.H-55) break;
            t: for (int j = 0; j < 5; j++) {
                for (int l = 0; l < vec.size(); l++) {
                    if (x-(4-i-j)*50 == vec.get(l).x && y-(4-i-j)*50 == vec.get(l).y){
                        if (layout.get(l) == turn) {
                            value ++;
                            continue t;
                        }
                        else{
                            value = 0;
                            continue u;
                        }
                    }
                }
            }
            if (value == 5){
                return 1;
            }
            value = 0;
        }

        u: for (int i = 0; i<5; i++){
            if ((x-(4-i)*50<0)){
                continue;
            }
            if ((y+(4-i)*50>=frame.H-55)){
                continue;
            }
            if (x + i*50 >= frame.W-35) break;
            if (y - i*50 < 0) break;
            t: for (int j = 0; j < 5; j++) {
                for (int l = 0; l < vec.size(); l++) {
                    if (x-(4-i-j)*50 == vec.get(l).x && y+(4-i-j)*50 == vec.get(l).y){
                        if (layout.get(l) == turn) {
                            value ++;
                            continue t;
                        }
                        else{
                            value = 0;
                            continue u;
                        }
                    }
                }
            }
            if (value == 5){
                return 1;
            }
            value = 0;
        }
        return 0;
    }
}
