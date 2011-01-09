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
        int stepX, stepY;

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
