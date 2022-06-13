//ALUNO: João Vitor Perin

import java.util.Objects;

public class ListaDuplamenteEncadeada<T> {
    private Node<T> base = null;
    private Node<T> top = null;
    private int size = 0;

    private static class Node<T>{
        Node<T> anterior;
        Node<T> proximo;
        T dado;

        public Node(T valor){
            this.dado = valor;
        }
    }

    //informações padrão:
    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return false;
    }

    public int getSize(){
        return size;
    }

    //outras informações
    public void add(T valor){
        var no = new Node<T>(valor);

        if(isEmpty()){
            base = no;
        } else {
            no.anterior = top;
            top.proximo = no;
        }
        top = no;
        size ++;
    }

    public T remove(Node<T> no){
        T dado = no.dado;
        var anterior = no.anterior;
        var proximo = no.proximo;

        if(anterior == null){
            base = proximo;
        } else {
            anterior.proximo = proximo;
        }

        if(proximo == null) {
            top = anterior;
        } else {
            proximo.anterior = anterior;
        }

        size --;
        return dado;
    }

    public Node<T> getNode(int pos){
        Objects.checkIndex(pos, size);
        int meio = size;

        if(pos <= meio){
            Node<T> atual = base;
            for (int i = 0; i < pos; i++) {
                atual = atual.proximo;
            }
            return atual;
        }
        Node<T> atual = top;
        for (int i = size-1; i != pos ; i--) {
            atual = atual.anterior;
        }
        return atual;
    }

    public T remove(int pos){
        return remove(getNode(pos));
    }

    public T get(int pos){
        return getNode(pos).dado;
    }

    public void set(int pos, T valor){
        getNode(pos).dado = valor;
    }

}
