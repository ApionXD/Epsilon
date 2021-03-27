package cards;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;

@Getter
public class Card
{
    private String name;
    private String link;
    public Card(String name, String link)
    {
        this.name = name;
        this.link = link;
    }

}
