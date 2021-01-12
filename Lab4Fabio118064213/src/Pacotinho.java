import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pacotinho extends ArrayList<Figurinha> {

    private Album album;
    private List listaDeFigurinhas = new ArrayList<Figurinha>();
    // ToDo atributo que seja uma estrutura para guardar as figurinhas deste pacotinho

    public Pacotinho(Album album) {
        this.album = album;
        adicionarFigurinhasAleatorias();

    }

    // sobrecarga no costrutor, passando aqui as posições desejadas
    public Pacotinho(Album album, int[] posicoes) {
        this.album = album;
        if(posicoes.length != album.getQuantFigurinhasPorPacotinho()){
            throw new RuntimeException("Pacotinho no tamanho errado!");
        }
        else{
            for(int i = 0; i< posicoes.length; i++){
                Figurinha figurinha = new Figurinha(posicoes[i]);
                listaDeFigurinhas.add(figurinha);
                add(figurinha);
            }
        }
    }

    private void adicionarFigurinhasAleatorias() {
        int maxPosicao = album.getTamanho();
        int quantFigurinhasPorPacotinho = album.getQuantFigurinhasPorPacotinho();

        for (int i = 1; i <= quantFigurinhasPorPacotinho; i++) {
            // ToDo sorteia uma posição entre 1 e o tamanho do álbum
            Random random = new Random();
            int posicao = random.nextInt(album.getTamanho());


            // ToDo cria um novo objeto Figurinha informando a posição sorteada

            // ToDo adiciona ao pacotinho

            Figurinha figurinha = new Figurinha(posicao);
            listaDeFigurinhas.add(figurinha);
            add(figurinha);
        }
    }

    public Album getAlbum() {
        return this.album;
    }
}
