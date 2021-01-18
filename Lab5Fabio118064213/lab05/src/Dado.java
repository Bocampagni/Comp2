import java.util.Random;

public class Dado implements Sorteador{


    public int Sortear(){
        Random random = new Random();
        return random.nextInt(6);
    }
}
