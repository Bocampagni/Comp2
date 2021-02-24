public class Livro extends ArtigoCultural {

    private final int codigoISBN;

    //Vai entrar um enumerado aqui
    private Categoria categoria;

    private String titulo;

    private String autor;

    private String editora;

    private int anoPublicacao;

    private int numeroDePaginas;

    public Livro(int codigoISBN,
                 String titulo, String autor, String editora, int anoPublicacao, Categoria categoria) {

        super(codigoISBN,
                String.format("Livro: %s (%s, %d)",
                titulo, autor, anoPublicacao));

        this.codigoISBN = codigoISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getCodigoISBN() {
        return codigoISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "codigoISBN=" + codigoISBN +
                ", categoria=" + categoria +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editora='" + editora + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", numeroDePaginas=" + numeroDePaginas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livro livro = (Livro) o;

        if (codigoISBN != livro.codigoISBN) return false;
        return categoria == livro.categoria;
    }

    @Override
    public int hashCode() {
        int result = codigoISBN;
        result = 31 * result + categoria.hashCode();
        return result;
    }
}
