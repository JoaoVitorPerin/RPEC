//Alunos:
//João Vitor Perin
//Igor Rubio Lazaroto

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static int linhaInicial = 1;
    static int colunaInicial = 1;
    static int comandosEnfileirados  = 0;
    static CircularQueue filaLinha = new CircularQueue(15);
    static CircularQueue filaColuna = new CircularQueue(15);

    static boolean jogo = true;
    static int[][] labirinto = {
            // 1 = paredes do labirinto; 2 = inicio; 0 = espaço vazio; 5 = chegada; 8 = por onde passou;

            {1,1,1,1,1,1},
            {1,2,1,0,5,1},
            {1,0,1,0,1,1},
            {1,0,1,0,1,1},
            {1,0,1,0,1,1},
            {1,0,0,0,1,1},
            {1,1,1,1,1,1}
    };

    //funcao que criamos para "chamar" o menu do labirinto
    public static void Menu(){
        while (jogo) {
            System.out.println("\nBEM VINDO AO SUPER LABIRINTO - ESCAPE OU MORRA! :)\n");

            //printar o labirinto:
            for (int[] strings : labirinto) {
                System.out.println(Arrays.toString(strings));
            }

            //printar o "menu"
            System.out.println("\nO seu objetivo nesse labirinto e chegar no S (saida)!");
            System.out.println("Comandos enfileirados: " + 0);
            System.out.println("Comandos disponiveis:\n1 - DIREITA\n2 - ESQUERDA\n3 - CIMA\n4 - BAIXO\n5 - INICIAR\n6 - RESTART");
            int opcao = scan.nextInt();
            if (opcao == 1){
                AndarDireita();
            }else if (opcao == 2){
                AndarEsquerda();
            }else if (opcao == 3){
                AndarCima();
            }else if (opcao == 4){
                AndarBaixo();
            }else if(opcao == 5){
                Iniciar();
            }else if(opcao == 6){
                Reiniciar();
            }
        }
    }

    public static void Iniciar(){
        int comando = 0;

        //fizemos adicionando os elementos na mão mesmo pois não conseguimos implementar apartir do labirinto;
        //criamos duas filas, uma para representar as linhas do "labirinto":
        filaLinha.add(2);
        filaLinha.add(3);
        filaLinha.add(4);
        filaLinha.add(5);
        filaLinha.add(5);
        filaLinha.add(5);
        filaLinha.add(4);
        filaLinha.add(3);
        filaLinha.add(2);
        filaLinha.add(1);
        filaLinha.add(1);

        //e outra para representar as colunas do "labirinto":
        filaColuna.add(1);
        filaColuna.add(1);
        filaColuna.add(1);
        filaColuna.add(1);
        filaColuna.add(2);
        filaColuna.add(3);
        filaColuna.add(3);
        filaColuna.add(3);
        filaColuna.add(3);
        filaColuna.add(3);
        filaColuna.add(4);

        //a ideia aqui seria criar um for para ele passar por cada escolha de movimento que o usuario realizou no menu
       for (int i = 0; i < filaColuna.getCapacity(); i++) {
           comando ++;
           System.out.printf("\n=======================\n");
           System.out.printf("Comando : " + comando);
           System.out.printf("\n=======================\n");
           //labirinto[filaColuna.getData()][filaLinha[i]] = 8;   <-- a ideia seria mudar o conteudo da posicao apartir da lista mas também não conseguimos implentar com a circularqueue;
           //printar o labirinto:
           for (int[] strings : labirinto) {
               System.out.println(Arrays.toString(strings));
           }
       }
    }

    //funcao para o usuario reiniciar o "labirinto"
    public static void Reiniciar(){
        System.out.println("\n===================\n");
        System.out.printf("JOGO REINICIADO");
        System.out.println("\n===================\n");
        filaLinha.clear();
        filaColuna.clear();
    }

    //funcao que criamos para o usuario andar para a direita
    public static void AndarDireita(){
        //posicao para andar para a direita
        var posicao = labirinto[colunaInicial][linhaInicial + 1];

        if (posicao == 0 && posicao != 5){
            System.out.println("\nVoce andou para a direita!\n");
            linhaInicial += 1;
            comandosEnfileirados += 1;
        }else if (posicao == 5){
            System.out.println("\n===================================\n");
            System.out.println("Parabens voce terminou o labirinto!");
            System.out.println("\n===================================\n");
            jogo = false;
        }else{
            System.out.printf("\nNao e possivel andar para a direita!\n");
        }

    }

    //funcao que criamos para o usuario andar para a esquerda
    public static void AndarEsquerda(){
        //posicao para andar para a esquerda
        var posicao = labirinto[colunaInicial][linhaInicial - 1];
        if (posicao == 0 && posicao != 5){
            System.out.println("\nVoce andou para a esquerda!\n");
            linhaInicial -= 1;
            comandosEnfileirados += 1;
        }else if (posicao == 5){
            System.out.println("\n===================================\n");
            System.out.println("Parabens voce terminou o labirinto!");
            System.out.println("\n===================================\n");
            jogo = false;
        }else{
            System.out.printf("\nNao e possivel andar para a esquerda!\n");
        }
    }

    //funcao que criamos para o usuario andar para cima
    public static void AndarCima(){
        //posicao para andar para cima
        var posicao = labirinto[colunaInicial - 1][linhaInicial];
        if (posicao == 0 && posicao != 5){
            System.out.println("\nVoce andou para cima!\n");
            colunaInicial -= 1;
            comandosEnfileirados += 1;
        }else if (posicao == 5){
            System.out.println("\n===================================\n");
            System.out.println("Parabens voce terminou o labirinto!");
            System.out.println("\n===================================\n");
            jogo = false;
        }else{
            System.out.printf("\nNao e possivel andar para a cima!\n");
        }
    }

    //funcao que criamos para o usuario andar para baixo
    public static void AndarBaixo(){
        //posicao para andar para baixo
        var posicao = labirinto[colunaInicial + 1][linhaInicial];
        if (posicao == 0 && posicao != 5){
            System.out.println("\nVoce andou para baixo!\n");
            colunaInicial += 1;
            comandosEnfileirados += 1;
        }else if (posicao == 5){
            System.out.println("\n===================================\n");
            System.out.println("Parabens voce terminou o labirinto!");
            System.out.println("\n===================================\n");
            jogo = false;
        }else{
            System.out.printf("\nNao e possivel andar para a baixo!\n");
        }
    }

    public static void main(String[] args) {
        Menu();
    }
}


