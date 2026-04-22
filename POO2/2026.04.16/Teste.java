public class Teste {
    public static void main(String[] args) {

        EmpregadoAssalariado e1 = new EmpregadoAssalariado("John", "Smith", "111-11-1111", 800);
        EmpregadoHorista e2 = new EmpregadoHorista("Karen", "Price", "222-22-2222", 16.75, 40);
        EmpregadoComissionado e3 = new EmpregadoComissionado("Sue", "Jones", "333-33-3333", 10000, 0.06);
        EmpregadoMisto e4 = new EmpregadoMisto("Bob", "Lewis", "444-44-4444", 10000, 0.06, 300);

        System.out.println("Empregados Processados Individualmente:\n");

        System.out.println(e1 + "\nTotal Ganho: $" + e1.calculaPagamento());
        System.out.println(e2 + "\nTotal Ganho: $" + e2.calculaPagamento());
        System.out.println(e3 + "\nTotal Ganho: $" + e3.calculaPagamento());
        System.out.println(e4 + "\nTotal Ganho: $" + e4.calculaPagamento());

        System.out.println("\nEmpregados Processados Polimorficamente:\n");

        Empregado[] empregados = {e1, e2, e3, e4};

        for (Empregado emp : empregados) {

            System.out.println(emp);

            if (emp instanceof EmpregadoMisto) {
                EmpregadoMisto em = (EmpregadoMisto) emp;
                double novoSalario = em.getSalarioBase() * 1.1;
                System.out.println("Novo Salário-base com 10% de aumento é: $" + novoSalario);
            }

            System.out.println("Total Ganho: $" + emp.calculaPagamento() + "\n");
        }
    }
}