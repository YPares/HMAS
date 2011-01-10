package hmas.agents;

import hmas.*;
import java.util.Random;


// Simple agent that fixes on black pixels, but only if no agent is already fixed on that pixel
public class PixelAgent extends Agent
{

    public PixelAgent (World world)
    {
        super(world, 0, world.randomPosition(), new Vector(0,0));
    }
    public void step()
    {
        if (getPixel(getPosition()) && none(fixed(collidingWithMe())))
        {
            setFixed();
        }
        else
        {
            move(randomMove());
        }
    }
}

