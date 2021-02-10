public class CD extends ArtigoCultural {

    private String nomeDoDisco;

    private String artista;

    private int anoGravacao;

    public CD(long numeroIdentificacao, String nomeDoDisco, String artista, int anoGravacao) {

        super(numeroIdentificacao, String.format("CD: %s (%s, %d)",
                nomeDoDisco, artista, anoGravacao));

        this.nomeDoDisco = nomeDoDisco;
        this.artista = artista;
    }

    public String getNomeDoDisco() {
        return nomeDoDisco;
    }

    public void setNomeDoDisco(String nomeDoDisco) {
        this.nomeDoDisco = nomeDoDisco;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getAnoGravacao() {
        return anoGravacao;
    }

    public void setAnoGravacao(int anoGravacao) {
        this.anoGravacao = anoGravacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CD cd = (CD) o;

        return nomeDoDisco.equals(cd.nomeDoDisco);
    }

    @Override
    public int hashCode() {
        return nomeDoDisco.hashCode();
    }
}
