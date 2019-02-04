import java.util.Vector;

// класс вычисления ходов компьютера

public class AI {
    int X,Y, value, w, h, size, rate, sum = 0, rand, turn;
    Habitat Hab;
    Vector<Integer> R, xVec, yVec, layout;
    AI(Habitat Hab1, int size1, int H, int W, int tur){
        if (tur == 1) {
            turn = 2;
        }
        else {
            turn = 1;
        }
        Hab = Hab1;
        size = size1;
        w =W;
        h =H;
        R = new Vector<>();
        xVec = new Vector<>();
        yVec = new Vector<>();
        layout = new Vector<>();
        for (int i = 0; i < Hab.vec.size(); i++) {
            if (Hab.vec.get(i) instanceof White){
                layout.add(1);
            }
            else {
                layout.add(2);
            }
        }
        for (int i =0; i< size; i++ ){
            m: for (int j = 0; j< size; j++) {
                for (int l = 0; l < Hab.vec.size(); l++) {
                    if (j*50+35 == Hab.vec.get(l).x && i*50+35 == Hab.vec.get(l).y){
                        continue m;
                    }
                }
                rate = calculate(j*50+35, i*50+35, tur);
                rate = rate + calculate(j*50+35, i*50+35, turn);
                if (R.isEmpty()){
                    R.add(rate);
                    xVec.add(j * 50 + 35);
                    yVec.add(i * 50 + 35);
                }
                else {
                    if (rate == R.lastElement()) {
                        R.add(rate);
                        xVec.add(j * 50 + 35);
                        yVec.add(i * 50 + 35);
                    }
                    if (rate > R.lastElement()) {
                        R.removeAllElements();
                        xVec.removeAllElements();
                        yVec.removeAllElements();
                        R.add(rate);
                        xVec.add(j * 50 + 35);
                        yVec.add(i * 50 + 35);
                    }
                }
            }
        }
        rand = (int) (Math.random() * R.size());
        X = xVec.get(rand);
        Y = yVec.get(rand);
    }
    public int calculate(int x, int y, int turn){
        int calc = 0;
        u: for (int i = 0; i<5; i++){
            if ((x-(4-i)*50<0)){
                continue;
            }
            if (x + i*50 >= w-35) break;
            t: for (int j = 0; j < 5; j++) {
                for (int l = 0; l < Hab.vec.size(); l++) {
                    if (x -(4-i-j)*50 == Hab.vec.get(l).x && y == Hab.vec.get(l).y){
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
            sum = 4;
            if (value == 4) {
                sum = 10000;
            }
            else {
                for (int k = 0; k< value; k++){
                    sum *= 5;
                }
            }
            calc += sum;
            value = 0;
        }

        u: for (int i = 0; i<5; i++){
            if ((y-(4-i)*50<0)){
                continue;
            }
            if (y + i*50 >= h-55) break;
            t: for (int j = 0; j < 5; j++) {
                for (int l = 0; l < Hab.vec.size(); l++) {
                    if (x == Hab.vec.get(l).x && y-(4-i-j)*50 == Hab.vec.get(l).y){
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
            sum = 4;
            if (value == 4) {
                sum = 10000;
            }
            else {
                for (int k = 0; k< value; k++){
                    sum *= 5;
                }
            }
            calc += sum;
            value = 0;
        }

        u: for (int i = 0; i<5; i++){
            if ((x-(4-i)*50<0)){
                continue;
            }
            if ((y-(4-i)*50<0)){
                continue;
            }
            if (x + i*50 >= w-35) break;
            if (y + i*50 >= h-55) break;
            t: for (int j = 0; j < 5; j++) {
                for (int l = 0; l < Hab.vec.size(); l++) {
                    if (x-(4-i-j)*50 == Hab.vec.get(l).x && y-(4-i-j)*50 == Hab.vec.get(l).y){
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
            sum = 4;
            if (value == 4) {
                sum = 10000;
            }
            else {
                for (int k = 0; k< value; k++){
                    sum *= 5;
                }
            }
            calc += sum;
            value = 0;
        }

        u: for (int i = 0; i<5; i++){
            if ((x-(4-i)*50<0)){
                continue;
            }
            if ((y+(4-i)*50>=h-55)){
                continue;
            }
            if (x + i*50 >= w-35) break;
            if (y - i*50 < 0) break;
            t: for (int j = 0; j < 5; j++) {
                for (int l = 0; l < Hab.vec.size(); l++) {
                    if (x-(4-i-j)*50 == Hab.vec.get(l).x && y+(4-i-j)*50 == Hab.vec.get(l).y){
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
            sum = 4;
            if (value == 4) {
                sum = 10000;
            }
            else {
                for (int k = 0; k< value; k++){
                    sum *= 5;
                }
            }
            calc += sum;
            value = 0;
        }

        return calc;
    }
}
