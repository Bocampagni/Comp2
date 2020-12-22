import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {


    /* Explicação do que foi feito.
     *
     * Fiz uma lista de contas correntes para simular um banco de dados
     *
     * Usei o número da conta como um identificador porque o mesmo é único
     *
     * Já que é único e agora temos um pseudo banco de dados, podemos validar se o número
     * que foi digitado realmente corresponde a uma conta corrente
     *
     * Fiz, na classe da conta corrente, um atributo static que compartilha seu comportamento com as demais instancias.
     *
     * Isso me permitiu instanciar o número correto das contas, o que faz o teste testarNumerosAutomaticosDeContas passar.
     *
     * Precisei fazer um método para limpar os identificadores porque queria ter contas já instanciadas na hora
     * que a aplicação começasse e também queria que os testes passasem todos, logo, no método com a annotation @Before, eu limpei os identificadores.
     * Mais informações sobre estão disponíveis na classe contaCorrente, em um comentário em cima do método de limpeza, chamado vanishIdentificador.
     *
     *
     */


    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        Pessoa fulano = new Pessoa("Fulano de Tal", 12345);
        Funcionario paiva;
        paiva = new Funcionario("Paiva", 234567, 1111);
        Agencia minhaAgencia = new Agencia();
        ContaCorrente contaDoPaiva = new ContaCorrente(paiva, minhaAgencia);
        ContaCorrente contaDoFulano = new ContaCorrente(fulano, minhaAgencia);
        List<ContaCorrente> listaDeContasCorrentes = new ArrayList<>();

        listaDeContasCorrentes.add(contaDoFulano);
        listaDeContasCorrentes.add(contaDoPaiva);
        while(true){
            System.out.println("   (D)epositar\n" +
                    "   (S)acar\n" +
                    "   (T)ransferir\n" +
                    "   (C)onsultar saldo\n" +
                    "   Cadastrar (P)essoa como correntista\n" +
                    "   Criar (N)ova conta\n" +
                    "   (X) para sair\n" +
                    "\n" +
                    "   Opção desejada:");

            char escolha = sc.next().toLowerCase().charAt(0);

            switch (escolha){
                case 'd': menuDeposito(listaDeContasCorrentes); break;
                case 't': menuTransferencia(listaDeContasCorrentes); break;
                case 's': menuSacar(listaDeContasCorrentes); break;
                case 'c': menuSaldo(listaDeContasCorrentes); break;
                case 'p': menuCadastroCorrentista(); break;
                case 'n': menuCadastroCriarNovaConta(listaDeContasCorrentes,minhaAgencia); break;
                case 'x': System.exit(0);
            }
        }
    }


    private static void menuDeposito(List<ContaCorrente> listaDeContasCorrentes)  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Conta de Origem: ");
        float contaOrigem = sc.nextFloat();

        System.out.println("Valor: ");
        float valor = sc.nextFloat();
        ContaCorrente contaParaDeposito = validaSeExiste(listaDeContasCorrentes, contaOrigem);
        if(contaParaDeposito != null){
            contaParaDeposito.depositar(valor);
            System.out.println(contaParaDeposito.relatorioDeMovimentacoes());
        }
        else{
            System.out.println("Conta indicada não existe");
        }
    }

    private static void menuSacar(List<ContaCorrente> listaDeContasCorrentes)  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Conta de Origem: ");
        float contaOrigem = sc.nextFloat();

        System.out.println("Valor: ");
        float valor = sc.nextFloat();

        ContaCorrente contaParaSacar = validaSeExiste(listaDeContasCorrentes, contaOrigem);
        if(contaParaSacar != null){
            contaParaSacar.sacar(valor);
            System.out.println(contaParaSacar.relatorioDeMovimentacoes());
        }
        else{
            System.out.println("Conta indicada não existe");
        }
    }

    private static void menuTransferencia(List<ContaCorrente> listaDeContasCorrentes)  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Conta de Origem: ");
        float contaOrigem = sc.nextFloat();

        System.out.println("Valor: ");
        float valor = sc.nextFloat();

        System.out.println("Conta de Destino: ");
        float contaDestino = sc.nextFloat();

        ContaCorrente origem = validaSeExiste(listaDeContasCorrentes, contaOrigem);
        ContaCorrente destino = validaSeExiste(listaDeContasCorrentes, contaDestino);

        if(origem != null && destino != null){
            origem.transferir(valor, destino);
            System.out.println(origem.relatorioDeMovimentacoes());
        }
        else{
            System.out.println("Conta indicada não existe");
        }
    }

    private static void menuSaldo(List<ContaCorrente> listaDeContasCorrentes){
        Scanner sc = new Scanner(System.in);
        System.out.println("Conta de Origem: ");
        float contaOrigem = sc.nextFloat();

        ContaCorrente conta = validaSeExiste(listaDeContasCorrentes, contaOrigem);
        if(conta != null){
            System.out.println(conta.getSaldoEmReais());
        }
        else{
            System.out.println("Conta indicada não existe");
        }
    }

    private static Pessoa menuCadastroCorrentista(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nome: ");
        String nome = sc.nextLine();

        System.out.println("Cpf: ");
        long cpf = sc.nextLong();

        return new Pessoa(nome, cpf);
    }

    private static ContaCorrente menuCadastroCriarNovaConta(List<ContaCorrente> listaDeContaCorrente, Agencia agencia){
        Pessoa correntista = menuCadastroCorrentista();
        ContaCorrente novaConta = new ContaCorrente(correntista, agencia);
        listaDeContaCorrente.add(novaConta);
        System.out.println("Conta criada. Nome do Correntista: " + correntista.getNome() + ". Número da conta" +
                ": " + novaConta.getNumeroDaConta());
        return novaConta;
    }


    //É preciso validar se o número da conta que foi passado corresponde a algum correntista
    private static ContaCorrente validaSeExiste(List<ContaCorrente> listaDeContas, float numeroDaConta){
        for (ContaCorrente listaDeConta : listaDeContas) {
            if (listaDeConta.getNumeroDaConta() == numeroDaConta) {
                return listaDeConta;
            }
        }

        return null;
    }




    private static Pessoa criarFuncionario() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome: ");
        String nome = sc.nextLine();

        System.out.println("CPF: ");
        long cpf = sc.nextLong();

        // consome o "\n" que o nextLong não irá consumir
        sc.nextLine();

        System.out.println("É funcionário (S|N)? ");
        String resposta = sc.nextLine();
        boolean ehFuncionario = resposta.equals("S") ||
                resposta.equals("s");

        if (ehFuncionario) {
            System.out.println("Matrícula: ");
            int matricula = sc.nextInt();
            return new Funcionario(nome, cpf, matricula);
        }

        //return new Object();    // não compilaria!!!!!

        return new Pessoa(nome, cpf);
    }
}
