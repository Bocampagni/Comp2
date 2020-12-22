import java.util.Date;

public class Pessoa {

    protected String nome;  // protected é "package private" + subclasses

    protected final long cpf;  // final indica que o campo JAMAIS poderá ser atualizado

    private Date dataDeNascimento;

    private String endereco;

    // overload do construtor
    public Pessoa(String nomeDaPessoa, long cpfDaPessoa) {
        this.nome = nomeDaPessoa;
        this.cpf = cpfDaPessoa;
        this.endereco = "Endereço desconhecido";
    }

    public void setEndereco(String endereco) {
        if (endereco.length() > 40) {  // tamanho máximo permitido para endereços
            return;  // ToDo lançar exceção!
        }
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public long getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return String.format("%s (CPF: %d)",
                nome,
                cpf);
    }
}
