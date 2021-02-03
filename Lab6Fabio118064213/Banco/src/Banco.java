import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Banco {

    private ArrayList<ContaCorrente> contas;

    private ArrayList<Pessoa> correntistas; //  [xxxx]  [yyyy]  [$FF0C12]  [ ] ...
                                            //   0         1        2       3  ...

    private Agencia agenciaUnica;

    public Banco() {
        contas = new ArrayList<>();
        correntistas = new ArrayList<>();
        agenciaUnica = new Agencia();
    }

    public Pessoa cadastrarCorrentista(String nome, long cpf) {
        Pessoa p = localizarCorrentista(cpf);
        if (p != null) {
            // Localizei o correntista! Não é preciso recriá-lo! Podemos atualizar o nome, se for o caso.
            p.setNome(nome);
        } else {
            // Correntista novo
            p = new Pessoa(nome, cpf);
            correntistas.add(p);
        }
        return p;
    }

    public ContaCorrente cadastrarConta(Pessoa correntista) {
        // verifica correntista
        if (localizarCorrentista(correntista.getCpf()) == null) {
            // correntista não existe!!
            // ToDo lançar uma exceção!
            return null;  // não vou criar conta coisíssima nenhuma!
        }

        // aceitamos mais de uma conta para o mesmo correntista

        ContaCorrente novaConta = new ContaCorrente(correntista, this.agenciaUnica);
        this.contas.add(novaConta);

        return novaConta;
    }

    public Pessoa localizarCorrentista(long cpf) {
//        for (int i = 0; i < correntistas.size(); i++) {
//            Pessoa correntista = correntistas.get(i);  // x = y
//            if (correntista.getCpf() == cpf) {
//                return correntista;
//            }
//        }
//

        Map<Long,Pessoa> map = new HashMap<Long,Pessoa>();

        for (Pessoa pessoa : this.correntistas) {
            map.put(pessoa.getCpf(), pessoa);
        }

        return map.get(cpf);
    }

    public ContaCorrente localizarConta(long numeroDaConta) {
        for (ContaCorrente contaCandidata : this.contas) {
            if (contaCandidata.getNumeroDaConta() == numeroDaConta) {
                return contaCandidata;  // achei!!!!
            }
        }
        return null;  // não achei!
    }





}
