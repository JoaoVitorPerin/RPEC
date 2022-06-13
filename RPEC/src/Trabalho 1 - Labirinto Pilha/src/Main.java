import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        LostOne lostOne = new LostOne();
        lostOne.walk();

    }
}

class LostOne {

    String[][] map = Map.map;

    ArrayStack lines = new ArrayStack(4, 1);
    ArrayStack columns = new ArrayStack(4, 1);
    ArrayStack lastLook = new ArrayStack(4, 1);

    public LostOne() {
        findEntrance();
    }

    public void findEntrance() {
        for (int l = 0; l < Map.map.length; l++) {
            for (int c = 0; c < Map.map[l].length; c++) {
                if (Objects.equals(Map.map[l][c], "E")) {
                    lines.push(l);
                    columns.push(c);
                    lastLook.push(0);
                    System.out.println("Comecei na posicao: " + l + " , " + c);
                }
            }
        }
    }

    public void walk() {
        while (!findExit()) {

            System.out.println("Posição atual: ( " + lines.last() + " , " + columns.last() + " )");
            System.out.println("Ultimo caminho percorrido " + lastLook.last());
            mark();
            printMap();

            if (walkUp()) {
                System.out.println("Andei para cima");
                lines.push(lines.last() - 1);
                lastLook.push(0);
            } else if (walkRight()) {
                System.out.println("Andei para direita");
                columns.push(columns.last() + 1);
                lastLook.push(1);
            } else if (walkDown()) {
                System.out.println("Andei para baixo");
                lines.push(lines.last() + 1);
                lastLook.push(2);
            } else if (walkLeft()) {
                System.out.println("Andei para esquerda");
                columns.push(columns.last() - 1);
                lastLook.push(3);
            } else {
                switch (lastLook.last()) {
                    case 0 :
                        System.out.println("Voltando para baixo");
                        lines.pop();
                        break;
                    case 1 :
                        System.out.println("Voltando para esquerda");
                        columns.pop();
                        break;
                    case 2:
                        System.out.println("Voltando para cima");
                        lines.pop();
                        break;
                    case 3:
                        System.out.println("Voltando para direita");
                        columns.pop();
                        break;
                }
                lastLook.pop();
            }
        }
    }

    public void mark() {
        map[lines.last()][columns.last()] = "*";
    }

    public boolean findExit(){
        if (Objects.equals(map[lines.last()][columns.last()], "S")) {
            System.out.println("Parabéns! Saida encontrada na posição:" + " ( " + lines.last() + " , " + columns.last() + " )");
            return true;
        }
        return false;
    }

    public boolean walkUp() {
        System.out.println("Olhei para Cima");
        //System.out.println(map[lines.last() - 1][columns.last()]);
        var position = map[lines.last() - 1][columns.last()];
        return (Objects.equals(position, " ") || Objects.equals(position, "S"));
    }
    public boolean walkRight() {
        System.out.println("Olhei para Direita");
        //System.out.println(map[lines.last()][columns.last() + 1]);
        var position = map[lines.last()][columns.last() + 1];
        return (Objects.equals(position, " ") || Objects.equals(position, "S"));
    }
    public boolean walkDown() {
        System.out.println("Olhei para Baixo");
        //System.out.println(map[lines.last() + 1][columns.last()]);
        var position = map[lines.last() + 1][columns.last()];
        return (Objects.equals(position, " ") || Objects.equals(position, "S"));
    }
    public boolean walkLeft() {
        System.out.println("Olhei para Esquerda");
        //System.out.println(map[lines.last()][columns.last() - 1]);
        var position = map[lines.last()][columns.last() - 1];
        return (Objects.equals(position, " ") || Objects.equals(position, "S"));
    }

    public void printMap() {
        for (String[] strings : map) {
            System.out.println(Arrays.toString(strings));
        }
    }
}


class Map {
    static String[][] map = {
            {"#","#","#","#","#","#","#","#","#","#","#","#"},
            {"#","E","#"," "," "," "," "," "," "," "," ","#"},
            {"#"," "," "," ","#","#","#"," ","#","#","#","#"},
            {"#"," ","#"," ","#","#","#"," ","#","#","#","#"},
            {"#"," ","#"," ","#","#","#"," ","#","#","#","#"},
            {"#"," ","#"," ","#"," ","#"," ","#","#","#","#"},
            {"#"," "," "," ","#"," "," "," "," "," ","S","#"},
            {"#"," ","#","#","#"," ","#"," ","#","#","#","#"},
            {"#"," ","#","#","#"," ","#"," ","#","#","#","#"},
            {"#"," ","#","#","#","#","#"," ","#","#","#","#"},
            {"#"," ","#"," "," "," "," "," ","#","#","#","#"},
            {"#","#","#","#","#","#","#","#","#","#","#","#"}
    };
}