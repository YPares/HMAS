package hmas.agents;

import hmas.*;
import java.util.Random;

public class PixelAgent extends Agent
{

    public PixelAgent (World world)
    {
        super(world, 0, randomVector() , new Vector(0,0));
    }
    public void step()
    {
        int stepX, stepY, lower, higher;
        lower = 0;
        higher = 1;

        if (getPixel(getPosition()) && ! collidingWithMe().iterator().hasNext())
        {
            setFixed();
        }
        else
        {
            move(randomVector());
        }
    }
}
