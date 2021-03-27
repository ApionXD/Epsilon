package shops;

import cards.Card;

import java.util.ArrayList;
import java.util.Map;

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
    public void setName(String name)
    {
        this.name = name;
    }
    public void setCards(ArrayList<Card> cards)
    {
        this.cards = cards;
    }
    public abstract String getCurrentStatus(int numCard);
    public abstract double getPrice();
    public ArrayList<Card> getCards()
    {
        return cards;
    }

}
