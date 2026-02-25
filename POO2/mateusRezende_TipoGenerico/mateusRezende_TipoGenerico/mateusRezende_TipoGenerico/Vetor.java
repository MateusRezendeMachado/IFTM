package mateusRezende_TipoGenerico;

import java.util.ArrayList;
import java.util.List;

public class Vetor<T> {
    private List<T> vet;
    private int tamanho;

    public Vetor(int capacidade) {
        this.vet = new ArrayList<>(capacidade);
        this.tamanho = 0;
    }

    public void adicionar(T elemento) {
        vet.add(elemento);
        tamanho++;
    }

    public List<T> getVet() {
        return vet;
    }

    public void setVet(List<T> vet) {
        this.vet = vet;
        this.tamanho = vet.size();
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public T buscarPorNome(String nome) {
        for (T elemento : vet) {
            if (elemento instanceof Pessoa) {
                Pessoa p = (Pessoa) elemento;
                if (p.getNome().equalsIgnoreCase(nome)) {
                    return elemento;
                }
            } else if (elemento instanceof Cidade) {
                Cidade c = (Cidade) elemento;
                if (c.getNome().equalsIgnoreCase(nome)) {
                    return elemento;
                }
            }
        }
        return null;
    }
}