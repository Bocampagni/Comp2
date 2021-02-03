import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MapaUsandoArrayUnico<C, V> implements Map<C, V> {

    private ArrayList<ParChaveValor<C, V>> minhaListaDePares;

    public MapaUsandoArrayUnico() {
        this.minhaListaDePares = new ArrayList<>();
    }

    @Override
    public int size() {
        return minhaListaDePares.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public boolean containsKey(Object key) {

        for (int i = 0; i < this.minhaListaDePares.size(); i++) {
            if (this.minhaListaDePares.get(i).getChave().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(int i = 0; i<size();i++){
            if(this.minhaListaDePares.get(i).getValor() == value){
                return true;
            }
        }
        return false;
    }

    @Override
    public V put(C chave, V valor) {
        ParChaveValor<C, V> parPreExistente = obterParChaveValor(chave);
        V valorPreExistente = null;
        if (parPreExistente == null) {  // chave inédita!!
            this.minhaListaDePares.add(new ParChaveValor<>(chave, valor));

        } else {  // chave pré-existente
            valorPreExistente = parPreExistente.getValor();
            parPreExistente.setValor(valor);
        }
        return valorPreExistente;
    }
    public int containsKeyOnSteroids(Object key) {

        for (int i = 0; i < this.minhaListaDePares.size(); i++) {
            if (this.minhaListaDePares.get(i).getChave().equals(key)) {
                return i;
            }
        }
        return 0;
    }
    @Override
    public V remove(Object key) {

        int index = this.containsKeyOnSteroids(key);
        V valorParaRetorno = this.minhaListaDePares.get(index).getValor();
        this.minhaListaDePares.remove(index);
        return valorParaRetorno;
    }

    @Override
    public void putAll(Map<? extends C, ? extends V> m) {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public void clear() {
        this.minhaListaDePares.removeAll(this.minhaListaDePares);
    }

    @Override
    public Set<C> keySet() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public Collection<V> values() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public Set<Entry<C, V>> entrySet() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public V get(Object chaveDeBusca) {
        ParChaveValor<C, V> par = obterParChaveValor(chaveDeBusca);
        return par == null
                ? null
                : par.getValor();
    }

    private ParChaveValor<C, V> obterParChaveValor(Object chave) {
        for (ParChaveValor<C, V> par : this.minhaListaDePares) {
            if (par.getChave().equals(chave)) {
                return par;
            }
        }
        return null;
    }
}
