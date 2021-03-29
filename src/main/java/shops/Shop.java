package shops;

import cards.Card;
import lombok.Getter;

import java.io.IOException;

@Getter

public abstract class Shop
{
    private String name;
    private int timeout;

    public Shop(String name, int timeout)
    {
        this.name = name;
        this.timeout = timeout;
    }
    public Shop()
    {

    }
    public abstract int checkCurrentStatus(Card cardToCheck);
    public abstract double checkPrice(Card cardToCheck);
    public abstract void checkPage(Card cardToCheck) throws IOException;
}
