package shops;

import cards.Card;
import lombok.Getter;

import java.util.ArrayList;
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
    public abstract int getCurrentStatus(Card cardToCheck);
    public abstract double getPrice(Card cardToCheck);
}
