import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LojaTest {

    Loja loja;
    Livro livro1;
    Livro livro2;
    CD cd1;
    Bicicleta bicicleta1;
    Usuario comprador;
    Transportadora gatoPreto;
    ImpressoraJatoDeTinta impressoraJatoDeTinta1;
    Grafica grafica1;

    @Before
    public void setUp() {
        gatoPreto = new Transportadora();
        impressoraJatoDeTinta1 = new ImpressoraJatoDeTinta("HP", 2018);
        grafica1 = new Grafica();

        loja = new Loja(
                gatoPreto,                // informamos à loja qual a transportadora que ela vai usar (agregação)
                impressoraJatoDeTinta1);  // ...e o serviço de impressão que ela vai usar (agregação tb)


        livro1 = new Livro(12345, "Da Terra à Lua", "Julio Verne", null, 1865, Categoria.Enciclopedia );
        livro1.setPrecoEmReais(25);

        livro2 = new Livro(12446, "Dom Quixote", "Miguel de Cervantes", null, 1605, Categoria.Enciclopedia);
        livro2.setPrecoEmReais(42.30f);

        cd1 = new CD(121223, "Ride The Lightning", "Metallica", 1985);
        cd1.setPrecoEmReais(18.50f);

        bicicleta1 = new Bicicleta(9999, 700, "Pinarello");
        bicicleta1.setPrecoEmReais(580);

        loja.incluirItem(livro1);
        loja.incluirItemParaOMapa(livro1,20);
        loja.incluirItem(livro2);
        loja.incluirItemParaOMapa(livro2,20);
        loja.incluirItem(cd1);
        loja.incluirItemParaOMapa(cd1,20);
        loja.incluirItem(bicicleta1);
        loja.incluirItemParaOMapa(bicicleta1,20);

        comprador = new Usuario(111111, "Maria");
        comprador.setEndereco("Rua Tal, Numero Tal");
    }

    @Test
    public void testarVendaParaProdutoExistente() throws
            ItemNaoExisteNoCatalogoException, EstoqueInsuficienteException,
            EnderecoInvalidoException, ErroNoPagamentoException {

        String recibo = null;
        recibo = loja.receberPedido(livro2, 5, comprador);

        assertNotNull(recibo);
        System.out.println(recibo);

        recibo = loja.receberPedido(cd1, 1, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);

        recibo = loja.receberPedido(bicicleta1, 3, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);
    }

    @Test
    public void testarVendaParaProdutoNaoExistente() throws
            EnderecoInvalidoException, ItemNaoExisteNoCatalogoException,
            EstoqueInsuficienteException, ErroNoPagamentoException {

        Livro livroNaoExistente = new Livro(1010101, "Blah", "Qualquer coisa", null, 2000, Categoria.Enciclopedia);
        String recibo = null;
        try{
            recibo = loja.receberPedido(livroNaoExistente, 5, comprador);
        }catch (ItemNaoExisteNoCatalogoException ignored){

        }
        assertNull(recibo);
    }

    @Test
    public void testarDescontoDeVenda() throws
            EnderecoInvalidoException, ItemNaoExisteNoCatalogoException,
            EstoqueInsuficienteException, ErroNoPagamentoException {

        Livro taleb = new Livro(12446, "Iludido pelo Acaso", "Nicolas Nassim Taleb", null, 1605, Categoria.Nãoficcao);
        taleb.setPrecoEmReais(50);
        loja.incluirItem(taleb);
        loja.incluirItemParaOMapa(taleb,20);

        Livro diomara = new Livro(12446, "Cálculo Diferencial e Integral de Funções de Várias variaveis", "Diomara", null, 1605, Categoria.Didatico);
        diomara.setPrecoEmReais(25);
        loja.incluirItem(diomara);
        loja.incluirItemParaOMapa(diomara,20);


        //Não deve aplicar o disconto, logo, o valor será 50*5 = 250
        String recibo = null;
        recibo = loja.receberPedido(taleb, 5, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);

        //Deve aplicar o disconto, porque foi comprado mais que 3 unidades e o livro é didático.
        //O valor será 125-(125*0.2) = 100
        String recibo2 = null;

        recibo = loja.receberPedido(diomara, 5, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);
    }
}