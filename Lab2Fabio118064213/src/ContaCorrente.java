import java.util.ArrayList;
import java.util.Date;

public class ContaCorrente {

    private final long numeroDaConta;

    private final Agencia agencia;

    private float saldoEmReais;

    private Date dataDeCriacao;

    private Pessoa correntista;

    private Pessoa gerenteDaConta;

    private ArrayList<String> historicoDeOperacoes;

    public ContaCorrente(long numeroDaConta, Pessoa correntista, Agencia agencia) {
        this.historicoDeOperacoes = new ArrayList<>();
        this.dataDeCriacao = new Date();  // data corrente
        this.saldoEmReais = 10;  // o banco dá 10 reais de estímulo para a abertura de conta
        this.numeroDaConta = numeroDaConta;
        this.correntista = correntista;
        this.agencia = agencia;
    }

    public float getSaldoEmReais() {  // Getter (métodos de acesso para leitura)
        return saldoEmReais;
    }

    public void depositar(float valor) {
        // valida o parâmetro
        if (valor <= 0) {
            System.out.println("Valor inválido");
            return;
            //Se lançar exception, não passa no teste.
        }

        // altera o saldo
        saldoEmReais += valor;

        historicoDeOperacoes.add("Deposito em dinheiro: " + valor +
                "na data " + new Date());
    }

    /**
     * Transfere um valor desta conta para a conta destino informada, se houver saldo suficiente
     * nesta conta.
     *
     * @param valor o valor desejado
     * @param contaDestino a conta de destino
     */
    public void transferir(float valor, ContaCorrente contaDestino){
        if(valor > this.saldoEmReais || valor < 1){
            System.out.println("Valor inválido");
        }else{
            this.saldoEmReais -= valor;
            contaDestino.depositar(valor);

        }
    }

    public void saque(float valor){
        if(valor > this.saldoEmReais || valor < 0){
            //throw new IllegalArgumentException("Valor de saque maior que o saldo em conta");
            System.out.println("Valor de saque maior que o saldo em conta");
        }
        else{
            this.saldoEmReais -= valor;
            historicoDeOperacoes.add("Saque:" + valor + "na data de" + new Date());
        }
    }
}
