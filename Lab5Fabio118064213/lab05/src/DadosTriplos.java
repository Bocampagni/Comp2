public class DadosTriplos implements Sorteador{


    Dado dado = new Dado();
    @Override
    public int Sortear() {
        return dado.Sortear() + dado.Sortear() + dado.Sortear();
    }
}
