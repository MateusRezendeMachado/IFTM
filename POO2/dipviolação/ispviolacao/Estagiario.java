package ispviolacao;

public class Estagiario implements Funcionario{
    
    @Override
    public String getCargo(){
        return "Estagiário";
    }
    
    @Override
    public double calculaSalario(){
// lógica para calcular o salario do estagiário
        return 0;
    }
     
    @Override
    public double calcula13o(){
//  throw new Exception("Estagiário não pode 
        return 0;            // receber 13o");
    }
}