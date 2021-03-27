import shops.Amazon;
import shops.Shop;

public class Main implements Runnable
{
    public static void main(String[] args)
    {
        Shop amazon = new Amazon();
        System.out.println("Beginning test!");
        int numCards = amazon.getCards().size();
        System.out.println("Number of cards: " + numCards);
        for (int i = 0; i < numCards; i++)
        {
            System.out.println("Checking " + amazon.getCards().get(i).getName());
            System.out.println(amazon.getCurrentStatus(i));
        }

    }

    @Override
    public void run() {

    }
}
