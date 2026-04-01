package prodPlan;

public class Teste {

    static Parte[] criaPartes() {
        return new Parte[] {
            new Motor(112, "motor m112", "motor de avanco do cabecote", 100.0f, 1.2f, 1.1f, 1250),
            new Motor(114, "motor m114", "motor auxiliar", 60.0f, 0.6f, 0.8f, 1250),
            new Motor(111, "motor m111", "motor de ventilador", 70.0f, 1.0f, 1.0f, 3000),
            new Motor(110, "motor m110", "motor principal", 120.0f, 1.8f, 1.5f, 1250),
            new Parafuso(231, "parafuso p1", "parafuso de fixacao do cabecote", 2.5f, 100.0f, 8.0f),
            new Parafuso(232, "parafuso p2", "parafuso de fixacao do motor", 2.5f, 80.0f, 6.0f),
            new Parafuso(233, "parafuso p3", "parafuso de fixacao do ventilador", 2.0f, 60.0f, 6.0f),
            new Parafuso(234, "parafuso p4", "parafuso de uso geral", 3.0f, 120.0f, 12.0f)
        };
    }

    static void listaPartes(String titulo, Parte[] partes) {
        System.out.println(titulo);
        for (Parte p : partes) {
            System.out.println(p.toString());
        }
    }

    static Item[] criaItens(Parte[] partes) {
        return new Item[] {
            new Item(partes[0], 10),
            new Item(partes[5], 50),
            new Item(partes[7], 30),
            new Item(partes[2], 5)
        };
    }

    static void listaItens(String titulo, Item[] itens) {
        System.out.println(titulo);
        float total = 0.0f;

        for (Item i : itens) {
            System.out.println(i.toString());
            total += i.calculaValor();
        }

        System.out.println("Valor total:" + total);
    }

    public static void main(String[] args) {
        Parte[] partes = criaPartes();
        Item[] itens = criaItens(partes);

        listaPartes("*** Partes utilizadas na producao ****", partes);
        listaItens("*** Itens solicitados ***", itens);
    }
}