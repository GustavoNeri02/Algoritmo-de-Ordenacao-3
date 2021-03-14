import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static long movimentos = 0;

    public static void main(String[] args){
        StringBuilder texto = new StringBuilder("[");

        //---Coloque aqui seu documento .txt (Ex: 2, 42, -23, 31) para ordená-lo---
        int[] vetor = leitura("C:\\Users\\Gustavo\\Desktop\\dadosC.txt");

        //---Inicio da contagem---
        long tempoInicial = System.currentTimeMillis();

        heapSort(vetor);

        //---fim da contagem---
        long tempoFinal = System.currentTimeMillis();

        System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");

        for (int i = 0; i < vetor.length; i++) {
            texto.append(vetor[i]);
            if (i == vetor.length - 1) {
                texto.append("]");
            } else {
                texto.append(", ");
            }
        }
        System.out.println(texto);
        System.out.println("Total de " + movimentos + " movimentos.");
    }

    //leitura do arquivo a ser ordenado
    public static int[] leitura(String arquivo) {

        Path pasta = Paths.get(arquivo);

        try{
            byte[] text = Files.readAllBytes(pasta);

            String[] ler = new String(text).split(", ");
            int[] vetor = new int[ler.length];

            for(int i = 0; i < ler.length; i++) {
                vetor[i] = Integer.parseInt(ler[i]);
            }

            return vetor;
        }

        catch(Exception erro){
            return null;
        }
    }


    //Algoritmo de Ordenacao
    //HeapSort
    public static void heapSort(int[] vetor) {


        //metade - 1 para montar o heap
        for (int i = vetor.length / 2 - 1; i >= 0; i--) {
            //primeira rearranjada no vetor (Quase ordenado)
            heapify(vetor, vetor.length, i);
        }


        //diminuir o tamanho do vetor levando o maior (vetor[0]) para o final
        for (int i = vetor.length - 1; i > 0; i--) {
            int temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;
            movimentos++;

            //garantir que o maior elemento esteja na posição 0
            heapify(vetor, i, 0);
        }
    }

    static void heapify(int[] vetor, int tamanho, int i) {
        int maior = i; // Inicializar o maior como raiz de modo a ser CRESCENTE
        int filhoEsq = 2 * i + 1; // filho da esquerda = 2*i + 1
        int filhoDir = 2 * i + 2; // filho da direita = 2*i + 2

        //se filho da esquerda é maior que o pai
        if (filhoEsq < tamanho && vetor[filhoEsq] > vetor[maior]) {
            maior = filhoEsq;
            movimentos++;
        }
        if (filhoDir < tamanho && vetor[filhoDir] > vetor[maior]) {
            maior = filhoDir;
            movimentos++;
        }

        //se o pai for agora um dos filhos
        if (maior != i) {
            int temp = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = temp;
            movimentos++;

            //necessario usar recursividade
            heapify(vetor, tamanho, maior);
        }
    }

}
