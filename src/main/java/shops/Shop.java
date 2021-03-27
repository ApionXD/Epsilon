package shops;

import cards.Card;
import lombok.Getter;

import java.util.ArrayList;
@Getter

public abstract class Shop
{
    private String name;

    public Shop(String name)
    {
        this.name = name;
    }
    public Shop()
    {

    }
    public abstract String getCurrentStatus(Card cardToCheck);
    public abstract double getPrice();
}
