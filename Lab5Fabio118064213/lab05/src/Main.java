public class Main {

    /*
    * Resposta das perguntas extras
    *
    * a) O melhor sorteador é o triplo. Sim, a possibilidade dos dois inteiros do DadosDeGamao serem
    * iguais é muito baixa, exatamente (1/36), o que abre um intervalo de crescimento muito grande pro DadosTriplos.
    *
    *
    *b) Sim, como mencionado no comentário sobre a primeira questão, a possibilidade de cair dois
    * números iguais é baixa, logo, quanto maior o tempo de exposição, maior a vantagem do
    * DadosTriplos, que na maioria das vezes terá um dado a mais pra ser somado em comparação com o DadosDeGamao.
    * */


    public static void main(String[] args){
        JogoMalucoComSorteadores jogo = new JogoMalucoComSorteadores(
                                                                    new DadosDeGamao(),
                                                                    new DadosTriplos(),
                                                                    "Jogo Maluco",
                                                                    "Fabio",
                                                                    "Gusmao",
                                                                    110);


        System.out.println(jogo.jogar());

    }
}
