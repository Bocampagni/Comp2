public class JogoMalucoComSorteadores extends JogoDeDoisJogadores{

    private Sorteador sorteador1;
    private Sorteador sorteador2;

    public JogoMalucoComSorteadores(Sorteador sorteador1, Sorteador sorteador2,String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas) {
        super(nomeJogo, nomeJogador1, nomeJogador2, numeroDeRodadas);
        this.sorteador1 = sorteador1;
        this.sorteador2 = sorteador2;
    }

    @Override
    int executarRodadaDoJogo() {

        int numero1 = this.sorteador1.Sortear();
        int numero2 = this.sorteador2.Sortear();

        if(numero1 > numero2){
            return 1;
        }else if(numero1 < numero2){
            return 2;
        }else{
            return 0;
        }
    }
}
