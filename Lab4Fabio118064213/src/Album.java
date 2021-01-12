public class Album {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;  // 90%

    private int quantFigurinhasPorPacotinho;
    private int[] listaDeFigurinhas;
    private int[] listaDeFigurinhasRepetidas;

    public Album(int tamanhoDoAlbum, int quantFigurinhasPorPacotinho) {
        this.quantFigurinhasPorPacotinho = quantFigurinhasPorPacotinho;
        this.listaDeFigurinhas = new int[tamanhoDoAlbum];
        this.listaDeFigurinhasRepetidas = new int[tamanhoDoAlbum];

        this.listaDeFigurinhasRepetidas[0] = -1; //Invalidar a ocorrencia 0
        this.listaDeFigurinhas[0] = -1; //Invalidar a ocorrencia 0
        // ToDo IMPLEMENT ME!!!!
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        for (Figurinha fig : pacotinho) {
            if(this.listaDeFigurinhas[fig.getPosicao()] == 0 && fig.getPosicao() != 0) {
                this.listaDeFigurinhas[fig.getPosicao()] = fig.getPosicao();
            }
            else{
                this.listaDeFigurinhasRepetidas[fig.getPosicao()] = fig.getPosicao();
            }
            // ToDo IMPLEMENT ME!!
        }
    }

    public void autoCompletar() {
        // verifica se o álbum já está suficientemente cheio
        if( ((this.getQuantFigurinhasColadas() * 100)/ this.getTamanho()) >= PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR ){
            for(int i = 0; i < this.getTamanho(); i++){
                if(this.listaDeFigurinhas[i] != i){
                    this.listaDeFigurinhas[i] = i;
                }
            }
        }
        // ToDo IMPLEMENT ME!!
    }

    public int getTamanho() {
        // ToDo IMPLEMENT ME!!!
        return this.listaDeFigurinhas.length;
    }

    public int getQuantFigurinhasPorPacotinho() {
        // ToDo IMPLEMENT ME!!!
        return this.quantFigurinhasPorPacotinho;
    }

    public int getQuantFigurinhasColadas() {
        // ToDo IMPLEMENT ME!!!
        int contador = 0;
        for(int i = 0; i< this.listaDeFigurinhas.length;i++){
            if(this.listaDeFigurinhas[i] == i){
                contador++;
            }
        }
        return contador;
    }

    public int getQuantFigurinhasRepetidas() {
        // ToDo IMPLEMENT ME!!!
        int contador = 0;
        for(int i = 0; i < this.getTamanho(); i++){
            if(this.listaDeFigurinhasRepetidas[i] == i){
                contador++;
            }
        }
        return contador;
    }

    public boolean possuiFigurinhaColada(int posicao) {
        // ToDo IMPLEMENT ME!!!
        if(posicao > this.getTamanho() || posicao <= 0){
            return false;
        }
        else{
            return this.listaDeFigurinhas[posicao] == posicao;
        }
    }

    public boolean possuiFigurinhaRepetida(int posicao) {
        // ToDo IMPLEMENT ME!!!
        return this.listaDeFigurinhasRepetidas[posicao] == posicao;
    }

    public int getQuantFigurinhasFaltantes() {
        // ToDo IMPLEMENT ME!!!
        //Figurinhas faltantes = [i] != i
        int contador = 0;
        for(int i = 0; i < this.getTamanho(); i++){
            if(this.listaDeFigurinhas[i] != i){
                contador++;
            }
        }
        return contador;
    }
}
