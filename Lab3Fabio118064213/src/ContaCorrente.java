import java.util.ArrayList;
import java.util.Date;

public class ContaCorrente {

    // o banco já te dá algo como estímulo :-)
    public static final float SALDO_INICIAL_DE_NOVAS_CONTAS = 10.0f;

    public static int identificador = 1;

    private final long numeroDaConta;

    private final Agencia agencia;

    private float saldoEmReais;

    private Date dataDeCriacao;

    private Pessoa correntista;

    private Pessoa gerenteDaConta;

    private ArrayList<String> historicoDeOperacoes;

    public ContaCorrente(Pessoa correntista, Agencia agencia) {
        this.historicoDeOperacoes = new ArrayList<>();
        this.dataDeCriacao = new Date();  // data corrente
        this.saldoEmReais = SALDO_INICIAL_DE_NOVAS_CONTAS;
        this.numeroDaConta = identificador;
        this.correntista = correntista;
        this.agencia = agencia;
        incrementaIdentificador();
    }

    public void incrementaIdentificador(){
        identificador+=1;
    }


    /*
    *Eu fiz esse método porque eu quero ter duas contas padrão instanciadas toda vez que
    * a aplicaçao começar, porem, a forma que eu implementei o identicador, essas contas irao influenciar
    * nas instancias que sao feitas no teste, e como nao podemos mexer no teste, eu preciso de uma forma
    * de limpar os identificadores, logo, esse metodo se mostra necessario.
    *
     */
    public static void vanishIdentificador(){
        identificador = 1;
    }

    public String relatorioDeMovimentacoes(){
        return "Último evento: " + historicoDeOperacoes.get(historicoDeOperacoes.size() - 1);
        //Retorna a última ocorrencia de movimentação
    }

    public long getNumeroDaConta() {
        return numeroDaConta;
    }

    public float getSaldoEmReais() {  // getter (métodos de acesso para leitura)
        return saldoEmReais;
    }

    public void depositar(float valor)  {
        // valida o parâmetro
        if (valor <= 0) {
            System.out.println("Valor inválido");
            return;
        }

        // altera o saldo
        saldoEmReais += valor;

        historicoDeOperacoes.add("Deposito em dinheiro: R$" + valor +
                " na data " + new Date());
    }

    public void sacar(float valor)  {
        // valida o parâmetro
        if (valor <= 0) {
            System.out.println("Valor inválido");
            return;
        }

        // verifica se há fundos na conta
        if (valor > saldoEmReais) {
            System.out.println("Saldo insuficiente");
            return;
        }

        saldoEmReais -= valor;

        historicoDeOperacoes.add(String.format(
                "Saque em dinheiro: R$%.2f na data %s",
                valor, new Date()));
    }

    /**
     * Transfere um valor desta conta para a conta destino informada, se houver saldo suficiente
     * nesta conta.
     *
     * @param valor o valor desejado
     * @param contaDestino a conta de destino
     */
    public void transferir(float valor, ContaCorrente contaDestino)  {
        // valida o parâmetro
        if (valor <= 0) {
            System.out.println("Valor inválido");
            return;
        }

        // verifica se há fundos na conta
        if (valor > saldoEmReais) {
            System.out.println("Saldo insuficiente");
            return; //
        }

        saldoEmReais -= valor;
        contaDestino.saldoEmReais += valor;

        historicoDeOperacoes.add(String.format(
                "Transferência efetuada para a conta %d: R$%.2f na data %s",
                contaDestino.numeroDaConta, valor, new Date()));

        contaDestino.historicoDeOperacoes.add(String.format(
                "Transferência recebida da conta %d: R$%.2f na data %s",
                this.numeroDaConta, valor, new Date()));
    }
}
