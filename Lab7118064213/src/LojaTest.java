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


        livro1 = new Livro(12345, "Da Terra à Lua", "Julio Verne", null, 1865);
        livro1.setPrecoEmReais(25);

        livro2 = new Livro(12446, "Dom Quixote", "Miguel de Cervantes", null, 1605);
        livro2.setPrecoEmReais(42.30f);

        cd1 = new CD(121223, "Ride The Lightning", "Metallica", 1985);
        cd1.setPrecoEmReais(18.50f);

        bicicleta1 = new Bicicleta(9999, 700, "Pinarello");
        bicicleta1.setPrecoEmReais(580);

        loja.incluirItemComQuantidade(livro1,10);
        loja.incluirItemComQuantidade(livro2,10);
        loja.incluirItemComQuantidade(cd1,10);
        loja.incluirItemComQuantidade(bicicleta1,10);

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


        loja.incluirItemComQuantidade(new Livro(342123,"Antifragil","Nassim Taleb",null, 2010),10);
        recibo = loja.receberPedido(new Livro(342123,"Antifragil","Nassim Taleb",null, 2010), 5, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);

        loja.incluirItemComQuantidade(new CD(2133098,"...And Justice for All", "Metallica", 1988),10);
        recibo = loja.receberPedido(new CD(2133098,"...And Justice for All", "Metallica", 1988), 3, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);
    }

    @Test(expected = ItemNaoExisteNoCatalogoException.class)
    public void testarVendaParaProdutoNaoExistente() throws
            EnderecoInvalidoException, ItemNaoExisteNoCatalogoException,
            EstoqueInsuficienteException, ErroNoPagamentoException {

        Livro livroNaoExistente = new Livro(1010101, "Blah", "Qualquer coisa", null, 2000);
        String recibo = loja.receberPedido(livroNaoExistente, 5, comprador);
    }

    @Test
    public void testarQuantidadeDeItensNoEstoque() throws
            EnderecoInvalidoException, ItemNaoExisteNoCatalogoException,
            EstoqueInsuficienteException, ErroNoPagamentoException{
        Integer VALOR_PADRAO_DE_ESTOQUE = 40;
        assertEquals(VALOR_PADRAO_DE_ESTOQUE,loja.quantidadeDeItens());

        String recibo = null;
        recibo = loja.receberPedido(cd1, 1, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);

        assertEquals(Integer.valueOf(39),loja.quantidadeDeItens());

        recibo = loja.receberPedido(bicicleta1, 3, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);

        assertEquals(Integer.valueOf(36),loja.quantidadeDeItens());
    }
}