//classe de implementação da fila circular (possui capacidade limitada mas reaproveita espaços que foram esvaziados)
//implementa os métodos da interface Queue
public class CircularQueue implements Queue{
    //variável local para armazenar a posição inicial da fila
    private int base = 0;
    //variável local para armazenar a posição final da fila (último elemento da fila)
    private int top = -1;

    //array local para armazenar os dados inseridos (elementos). Representa a fila.
    private int[] data;

    public int[] getData() {
        return data;
    }

    //Construtor da fila circular, recebe o tamanho da fila
    public CircularQueue(int size) {
        //inicializa a fila com o tamanho informado
        this.data = new int[size];
    }

    //Para conseguir efetuar a movimentação circular da fila implementamos o método move
    //Esse método recebe como parâmetro uma posição da fila e retorna a próxima posição vazia disponível
    private int move(int position) {
        //a condição de retorno (linha 26) pode ser lida e entendida da seguinte forma:
        //se a próxima posição (position + 1) for igual ao tamanho da fila (atingiu a capacidade máxima)
        //então o retorno é 0 (fazendo com que a próxima posição seja o início da minha fila)
        //caso contrário, se não for igual ao tamanho da fila (NÃO atingiu a capacidade máxima)
        //então o retorno é a próxima posição disponível
        return position + 1 == data.length ? 0 : position + 1;
    }

    //método para adicionar elementos à fila
    @Override
    public void add(int element) {
        //caso a fila esteja REALMENTE cheia, é disparada uma exceção
        //na fila circular não é possível inserir mais elementos uma vez que ela está REALMENTE cheia
        if(isFull()) {
            throw new IllegalStateException("Queue is full!");
        }
        //movimentação da posição final da fila para a posição do novo elemento que será inserido (linha 35)
        top = move(top);
        //insere o elemento no topo da fila
        data[top] = element;
    }

    //método que remove um elemento da fila
    @Override
    public int remove() {
        //caso a fila esteja vazia, uma exceção é disparada (erro)
        //não é possível remover um elemento da fila se não existir nenhum elemento, certo?
        if(isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        //armazenamento do primeiro elemento da fila em uma variável temporária
        int element = data[base];
        //caso esse seja o único elemento da fila (posição da base é a mesma posição do topo)
        if(base == top) {
            //limpamos a fila (esvazia)
            clear();
            //caso contrário, se não for o único elemento da fila
        } else {
            //atribuimos o valor 0 para a primeira posição da fila, indicando que esta posição está agora vazia e disponível
            //no caso usamos o 0, mas poderia ser algum número negativo, ou no caso de Objetos Java serial null
            data[base] = 0;
            //movemos a variável base para a próxima posição, indicando que o próximo elemento é agora o primeiro da fila
            base = move(base);
        }
        //retorna o primeiro elemento da fila que foi removido
        return element;
    }

    //método para esvaziar a fila
    @Override
    public void clear() {
        //definimos as variáveis de top e base para os mesmos valores de início (linhas 5 e 7)
        top = -1;
        base = 0;
        //realizamos uma iteração para alterar todos os valores da fila para 0 (indicando que estão todos vazios e disnponíveis)
        for (int i = 0; i < data.length; i++) {
            data[i] = 0;
        }
    }

    //função que retorna verdadeiro caso a fila esteja vazia e falso se não estiver vazia
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    //Sabemos que uma fila está cheia quando o a próxima posição do topo é igual à posição da base, certo?
    //Porém, ao inserir o PRIMEIRO elemento na fila a base e o topo também terão o mesmo valor e isso não significa que ela está cheia
    //Portanto temos aqui uma situação especial, pois não podemos considerar que ao inserir o primeiro elemento a fila está cheia
    //Ou seja, quando o valor da base for 0 e o valor do topo for -1, essa é uma situação de fila vazia
    //Não podemos confundir a situação da fila vazia, com a situação da fila cheia. Como podemos resolver isso no código?
    @Override
    public boolean isFull() {
        //a condição de retorno (linha 96) pode ser lida e entendida da seguinte forma:
        //se a minha fila não estiver vazia E a próxima posição final da fila (top) NÃO FOR IGUAL a posição inicial da fila (base)
        //então o retorno é verdadeiro
        //caso minha fila esteja vazia OU a próxima posição final da fila (top) FOR IGUAL a posição inicial da fila
        //então o retorno é falso
        //Isso garante que na condição especial de fila vazia (base 0 e topo -1) essa função não vai retornar verdadeiro
        //ou seja, evitamos que o programa confunda o caso de pilha vazia com o caso da pilha cheia
        return !isEmpty() && move(top) == base;
    }

    //função que retorna a capacidade da fila
    public int getCapacity() {
        return data.length;
    }

    //método de formatação de string para imprimir a fila no formato: [1, 2, 3]
    @Override
    public String toString() {
        String out = "[";
        if(getCapacity() > 0) {
            out += this.data[0];
        }
        for (int i = 1; i < getCapacity(); i++) {
            out += ", " + this.data[i];
        }
        out += "]";
        return out;
    }
}