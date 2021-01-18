public class DadosDeGamao implements Sorteador{

    //ToDo sortear dois dados, caso o valor seja igual, retornar o dobro da soma.
    //não reescreva uma maneira de sortear um número entre 1 e 6. Use um objeto Dado para isso.
    Dado dado1 = new Dado();
    Dado dado2 = new Dado();
    @Override
    public int Sortear() {

        int resultado1 = dado1.Sortear();
        int resultado2 = dado2.Sortear();

        if(resultado1 != resultado2){
            return resultado1 + resultado2;
        }else{
            return 2*(resultado1 + resultado2);
        }
    }
}
