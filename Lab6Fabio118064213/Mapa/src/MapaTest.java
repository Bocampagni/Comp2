import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class MapaTest {

    private Random random = new Random();

    private Map<Long, String> mapaUsandoDoisArraysParalelos;
    private Map<Long, String> mapaUsandoArrayUnico;
    private Map<Long, String> mapaUsandoArrayOrdenado;
    private Map<Long, String> hashMap;

    @Before
    public void setUp() {
        mapaUsandoDoisArraysParalelos = new MapaUsandoDoisArraysParalelos<>();
        mapaUsandoArrayUnico = new MapaUsandoArrayUnico<>();
        mapaUsandoArrayOrdenado = new MapaUsandoArrayOrdenado<>();
        hashMap = new HashMap<>();
    }

    @Test
    public void testaRetornoPut(){
        rodarTesteRetornoPut(mapaUsandoArrayUnico);
        rodarTesteRetornoPut(mapaUsandoArrayOrdenado);
        rodarTesteRetornoPut(mapaUsandoDoisArraysParalelos);
        rodarTesteRetornoPut(hashMap);
    }

    private void rodarTesteRetornoPut(Map<Long,String> mapa){
        assertEquals(null ,mapa.put(1234L, "Qualquer Coisa"));
    }



    @Test
    public void testeClear(){
        MapaUsandoArrayUnico<Long,String> mapa = new MapaUsandoArrayUnico<>();
        mapa.put(1234L, "Qualquer Coisa");
        mapa.put(2222L, "Outra Coisa Qualquer");
        mapa.clear();
        assertEquals(0, mapa.size());
    }

    @Test
    public void testaRemove(){
        rodarTesteRemove(mapaUsandoArrayUnico);
        rodarTesteRemove(hashMap);
    }

    private void rodarTesteRemove(Map<Long,String> mapa){
        mapa.put(1234L, "Qualquer Coisa");
        mapa.put(2222L, "Outra Coisa Qualquer");

        assertEquals(2,mapa.size());

        assertEquals("Qualquer Coisa", mapa.remove(1234L));
        assertEquals(1,mapa.size());
    }



    @Test
    public void testaConstainsForValueAndkey(){
        rodarTesteConstains(mapaUsandoArrayUnico);
        rodarTesteConstains(mapaUsandoArrayOrdenado);
        rodarTesteConstains(mapaUsandoDoisArraysParalelos);
        rodarTesteConstains(hashMap);
    }

    private void rodarTesteConstains(Map<Long,String> mapa){
        assertFalse(mapa.containsKey(123L));
        assertFalse(mapa.containsValue("abc"));
        mapa.put(1234L, "Qualquer Coisa");
        assertTrue(mapa.containsKey(1234L));
        assertTrue(mapa.containsValue("Qualquer Coisa"));

    }


    @Test
    public void testaIsEmpty(){
        rodarTesteIsEmpty(mapaUsandoArrayUnico);
        rodarTesteIsEmpty(mapaUsandoArrayOrdenado);
        rodarTesteIsEmpty(mapaUsandoDoisArraysParalelos);
        rodarTesteIsEmpty(hashMap);
    }

    private void rodarTesteIsEmpty(Map<Long,String> mapa){
        assertTrue(mapa.isEmpty());
        mapa.put(1234L, "Qualquer Coisa");
        assertFalse(mapa.isEmpty());

    }


    @Test
    public void testaSize(){
        rodarTesteDeSize(mapaUsandoArrayUnico);
        rodarTesteDeSize(mapaUsandoArrayOrdenado);
        rodarTesteDeSize(mapaUsandoDoisArraysParalelos);
        rodarTesteDeSize(hashMap);
    }

    private void rodarTesteDeSize(Map<Long,String> mapa){
        assertEquals(0,mapa.size());
        mapa.put(1234L, "Qualquer Coisa");
        mapa.put(2222L, "Outra Coisa Qualquer");
        assertEquals(2,mapa.size());
    }

    @Test
    public void testeFuncionalidadeBasica() {
        rodarTesteDaFuncionalidadeBasica(mapaUsandoArrayUnico);
        rodarTesteDaFuncionalidadeBasica(mapaUsandoArrayOrdenado);
        rodarTesteDaFuncionalidadeBasica(mapaUsandoDoisArraysParalelos);
        rodarTesteDaFuncionalidadeBasica(hashMap);
    }

    private void rodarTesteDaFuncionalidadeBasica(Map<Long, String> mapa) {
        mapa.put(1234L, "Qualquer Coisa");
        mapa.put(2222L, "Outra Coisa Qualquer");

        assertEquals("Outra Coisa Qualquer", mapa.get(2222L));
        assertEquals("Qualquer Coisa", mapa.get(1234L));
        assertNull(mapa.get(8798798L));
    }

    @Test
    public void testeAtualizacaoParaChaveExistente() {
        rodarTesteAtualizacaoParaChaveExistente(mapaUsandoArrayUnico);
        rodarTesteAtualizacaoParaChaveExistente(mapaUsandoArrayOrdenado);
        rodarTesteAtualizacaoParaChaveExistente(mapaUsandoDoisArraysParalelos);
        rodarTesteAtualizacaoParaChaveExistente(hashMap);
    }

    private void rodarTesteAtualizacaoParaChaveExistente(Map<Long, String> mapa) {
        mapa.put(1234L, "Qualquer Coisa");
        mapa.put(1234L, "Qualquer Coisa Modificada");

        assertEquals("Qualquer Coisa Modificada", mapa.get(1234L));
    }

    @Test
    public void testarPerformance() {
        rodarTesteDePerformance(mapaUsandoArrayUnico);
        rodarTesteDePerformance(mapaUsandoArrayOrdenado);
        rodarTesteDePerformance(mapaUsandoDoisArraysParalelos);
        rodarTesteDePerformance(hashMap);
    }

    private void rodarTesteDePerformance(Map<Long, String> mapa) {
        System.out.println("\nRodando teste de performance para a classe " +
                mapa.getClass().getName() + "...");

        final int TAMANHO = 40_000;

        System.out.println("Vou fazer as inserções...");

        long inicio = System.currentTimeMillis();
        for (long i = 0; i < TAMANHO; i++) {
            long x = random.nextInt(1_000_000);
            mapa.put(x, String.format("%d^2 = %d", x, x*x));
        }
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("tamanho = %d --- duracao = %.3f segundos\n",
                TAMANHO, duracao / 1000f);

        System.out.println("Vou fazer as buscas...");
        inicio = System.currentTimeMillis();
        for (long i = 0; i < TAMANHO; i++) {
            mapa.get(random.nextLong());
        }
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("tamanho = %d --- duracao = %.3f segundos\n",
                TAMANHO, duracao / 1000f);
    }
}