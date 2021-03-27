package shops;

import cards.Card;
import lombok.Getter;

import java.util.ArrayList;
@Getter
public abstract class Shop
{
    private String name;
    private ArrayList<Card> cards;

    public Shop(String name, ArrayList<Card> cards)
    {
        this.name = name;
        this.cards = cards;
    }
    public Shop()
    {

    }
    public abstract String getCurrentStatus(Card cardToCheck);
    public abstract double getPrice();
    public ArrayList<Card> getCards()
    {
        return cards;
    }

}
