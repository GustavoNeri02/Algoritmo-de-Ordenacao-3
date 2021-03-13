import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static long movimentos = 0;

    public static void main(String[] args){
        StringBuilder texto = new StringBuilder("[");

        //---Coloque aqui seu documento .txt (Ex: 2, 42, -23, 31) para ordená-lo---
        int[] vetor = leitura("C:\\Users\\Gustavo\\Desktop\\dados.txt");

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
        int n = vetor.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;

            heapify(vetor, i, 0);
        }
    }

    static void heapify(int[] vetor, int tamanho, int i) {
        int maior = i; // Inicializar o maior como raiz
        int esq = 2 * i + 1; // esquerda = 2*i + 1
        int dir = 2 * i + 2; // direita = 2*i + 2
        movimentos++;
        if (esq < tamanho && vetor[esq] > vetor[maior]) {
            maior = esq;
        }
        if (dir < tamanho && vetor[dir] > vetor[maior]) {
            maior = dir;
        }
        if (maior != i) {
            int temp = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = temp;

            heapify(vetor, tamanho, maior);
        }
    }

}
