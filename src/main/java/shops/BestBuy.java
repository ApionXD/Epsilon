package shops;

import cards.Card;

import java.io.IOException;

public class BestBuy extends Shop
{

    @Override
    public int checkCurrentStatus(Card cardToCheck) {
        return 0;
    }

    @Override
    public double checkPrice(Card cardToCheck) {
        return 0;
    }

    @Override
    public void checkPage(Card cardToCheck) throws IOException {

    }
}
