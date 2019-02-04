import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

//окно с отрисовкой всех объектов и диалогов

public class Window extends JFrame {
    Habitat Hab;
    static int x, y;
    int buttonNum, W, H, fieldSize = 5, turn = 1, player = 0, turnOpponent = 2;
    boolean flag;
    JLabel sizeLabel, winLabel;
    TextField setupSize;
    ButtonGroup gr;
    JRadioButton white, black;
    JDialog D;
    JPanel M, S;
    JButton ok, start, exit;
    Image img;
    Graph workplace;
    AI ai;

    public Window(Habitat Hab) {
        super("Five in a Row");
        this.Hab = Hab;
        dialog();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        W = (fieldSize + 1) * 50 + 30;
        H = (fieldSize + 1) * 50 + 40;
        setSize(W, H);
        setResizable(false);
        setLocationRelativeTo(null);
        try {
            img = ImageIO.read(new File("res/back.jpg"));
        } catch (IOException e) {
            System.out.println(e);
        }
        workplace = new Graph();
        add(workplace, BorderLayout.CENTER);
        setVisible(true);
    }

    public void dialog() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        sizeLabel = new JLabel("Размер поля (5 - 15): ");
        setupSize = new TextField(String.valueOf(fieldSize));
        gr = new ButtonGroup();
        white = new JRadioButton("White");
        white.setSelected(true);
        black = new JRadioButton("Black");
        gr.add(white);
        gr.add(black);
        D = new JDialog(this, "Setup", true);
        D.setSize(300, 150);
        D.setResizable(false);
        D.setLocationRelativeTo(null);
        M = new JPanel();
        D.add(M, BorderLayout.CENTER);
        M.setLayout(new GridLayout(3, 2));
        S = new JPanel();
        D.add(S, BorderLayout.SOUTH);
        S.setLayout(new FlowLayout());
        start = new JButton("Start");
        exit = new JButton("Exit");
        setupSize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setupSize.setFocusable(true);
            }
        });


        M.add(sizeLabel);
        M.add(setupSize);
        M.add(white);
        M.add(black);
        S.add(start).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (Integer.parseInt(setupSize.getText()) > 15)
                    setupSize.setText("15");
                else
                    setupSize.setText("5");
                fieldSize = Integer.parseInt(setupSize.getText());
                D.dispose();
            }
        });
        S.add(exit).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        white.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                turn = 1;
                turnOpponent = 2;
            }
        });
        black.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                turn = 2;
                turnOpponent = 1;
            }
        });
        D.setVisible(true);
    }

    public void winDialog(int check) {
        D = new JDialog(this, "Game Over", true);
        D.setSize(300, 100);
        D.setResizable(false);
        D.setLocationRelativeTo(null);
        ok = new JButton("Ok");
        M = new JPanel();
        D.add(M, BorderLayout.CENTER);
        S = new JPanel();
        D.add(S, BorderLayout.SOUTH);
        S.setLayout(new FlowLayout());
        S.add(ok).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        if (check == 3) {
            winLabel = new JLabel("Ничья");
        }
        if (check == turn) {
            winLabel = new JLabel("Победил Игрок");
        }
        if (check == turnOpponent) {
            winLabel = new JLabel("Победил Компьютер");
        }
        M.add(winLabel);
        setLocationRelativeTo(null);
        D.setVisible(true);
    }

    class Graph extends JPanel implements ImageObserver {
        {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    x = e.getX() + 10;
                    y = e.getY() + 10;
                    buttonNum = e.getButton();
                    if (buttonNum == 1) {
                        if (player == 0) {
                            if (turn == 1) {
                                Hab.vec.add(new White());
                            } else {
                                Hab.vec.add(new Black());
                            }
                            if (Hab.vec.lastElement().x == W - 35 || Hab.vec.lastElement().y == H - 55) {
                                Hab.vec.remove(Hab.vec.lastElement());
                                flag = true;
                            }
                            for (int i = 0; i < Hab.vec.size() - 1; i++) {
                                if (Hab.vec.get(i).x == Hab.vec.lastElement().x && Hab.vec.get(i).y == Hab.vec.lastElement().y) {
                                    Hab.vec.remove(Hab.vec.lastElement());
                                    flag = true;
                                }
                            }
                            if (flag == false) {
                                workplace.repaint();
                                if (Hab.checking(Hab.vec.lastElement().x, Hab.vec.lastElement().y, turn) == 1) {
                                    winDialog(turn);
                                }
                                if (Hab.draw() == 1) {
                                    winDialog(3);
                                }
                                ai = new AI(Hab, fieldSize, H, W, turn);
                                if (turn == 1) {
                                    Hab.vec.add(new Black(ai.X, ai.Y));
                                } else {
                                    Hab.vec.add(new White(ai.X, ai.Y));
                                }
                                workplace.repaint();
                                if (Hab.checking(Hab.vec.lastElement().x, Hab.vec.lastElement().y, turnOpponent) == 1) {
                                    winDialog(turnOpponent);
                                }
                                if (Hab.draw() == 1) {
                                    winDialog(3);
                                }
                            } else {
                                flag = false;
                            }
                        } else {
                            if (turn == 1) {
                                Hab.vec.add(new White());
                            } else {
                                Hab.vec.add(new Black());
                            }
                            if (Hab.vec.lastElement().x == W - 35 || Hab.vec.lastElement().y == H - 55) {
                                Hab.vec.remove(Hab.vec.lastElement());
                                flag = true;
                            }
                            for (int i = 0; i < Hab.vec.size() - 1; i++) {
                                if (Hab.vec.get(i).x == Hab.vec.lastElement().x && Hab.vec.get(i).y == Hab.vec.lastElement().y) {
                                    Hab.vec.remove(Hab.vec.lastElement());
                                    flag = true;
                                }
                            }
                            if (flag == false) {
                                workplace.repaint();
                                if (Hab.checking(Hab.vec.lastElement().x, Hab.vec.lastElement().y, turn) == 1) {
                                    winDialog(turn);
                                }
                                if (Hab.draw() == 1) {
                                    winDialog(3);
                                }

                            }
                        }
                        workplace.repaint();
                    }
                }
            });
        }

        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, workplace.getWidth(), workplace.getHeight(), this);
            g.setColor(Color.black);
            for (int i = 0; i < fieldSize - 1; i++) {
                g.fillRect(25 + (i + 1) * 50, 25, 5, fieldSize * 50);
                g.fillRect(25, 25 + (i + 1) * 50, fieldSize * 50, 5);
            }
            for (int i = 0; i < Hab.vec.size(); i++) {
                g.drawImage(Hab.vec.get(i).getImg(), Hab.vec.get(i).x, Hab.vec.get(i).y, this);
            }
        }
    }
}
