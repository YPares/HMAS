package hmas.agents;

import hmas.*;
import java.util.Random;

public class PixelAgent extends Agent
{

    public PixelAgent (World world)
    {
        super(world, 0, world.randomPosition() , new Vector(0,0));
    }
    public void step()
    {
        if (getPixel(getPosition()) && ! collidingWithMe().iterator().hasNext())
        {
            setFixed();
        }
        else
        {
            move(randomMove());
        }
    }
}
