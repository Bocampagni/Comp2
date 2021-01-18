import java.util.ArrayList;

public abstract class JogoDeDoisJogadores {

    private String nomeJogo;
    private String nomeJogador1;
    private String nomeJogador2;
    private int numeroDeRodadas;
    ArrayList<Integer> historicoResultados;


    public JogoDeDoisJogadores(String nomeJogo,
                               String nomeJogador1,
                               String nomeJogador2,
                               int numeroDeRodadas)
    {
        this.numeroDeRodadas = numeroDeRodadas;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.nomeJogo = nomeJogo;
        historicoResultados = new ArrayList<>();

    }

    public String jogar(){
        int pontos1 = 0;
        int pontos2 = 0;
        int pontosVencedor;
        int pontosPerdedor;
        String nome;
        for(int rodada = 0; rodada < this.numeroDeRodadas; rodada++){
            int resultado = executarRodadaDoJogo();

            if(resultado == 1){
                pontos1++;
            }
            else if(resultado == 2) {
                pontos2++;
            }
        }

        if(pontos1 != pontos2){
            if(pontos1 > pontos2){
                 pontosVencedor = pontos1;
                 nome = this.getNomeJogador1();
                 pontosPerdedor = pontos2;
                 this.historicoResultados.add(1);
            }else{
                 pontosVencedor = pontos2;
                 nome = this.getNomeJogador2();
                 pontosPerdedor = pontos1;
                 this.historicoResultados.add(2);
            }
            return "O jogador "+ nome +" venceu o jogo por\n" +
                    pontosVencedor+ " a " + pontosPerdedor;
        }
        return "O jogo terminou em empate após " + this.getNumeroDeRodadas() +  " rodadas.";
    }


    public String getNomeJogo() {
        return nomeJogo;
    }

    public String getNomeJogador1() {
        return nomeJogador1;
    }

    public String getNomeJogador2() {
        return nomeJogador2;
    }

    public int getNumeroDeRodadas() {
        return numeroDeRodadas;
    }

    public ArrayList<Integer> getHistoricoResultados() {
        return historicoResultados;
    }

    abstract int executarRodadaDoJogo();
}


