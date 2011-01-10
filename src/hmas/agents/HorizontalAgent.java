package hmas.agents;

import hmas.*;
import java.util.Random;


public class HorizontalAgent extends Agent
{
    public static Vector makeDiagonal(int level)
    {
        switch(level)
        {
            case 1: return new Vector(9, 0);
            case 2: return new Vector(49, 4);
            case 3: return new Vector(99, 9);
        }
        return new Vector(0, 0);
    }

    public HorizontalAgent(World world, int level)
    {
        super(world, level, world.randomPosition(), makeDiagonal(level));
    }

    public void step()
    {
        int minUnder = 1;
        Iterable<Agent> sonsToConsider = null;
        switch(getLevel())
        {
            case 1: minUnder = 8;
                    sonsToConsider = completelyUnderMe();
                    break;
            case 2: minUnder = 4;
                    sonsToConsider = completelyUnderMe();
                    break;
            case 3: minUnder = 2;
                    sonsToConsider = partiallyUnderMe();
                    break;
        }
        if(count(fixed(sonsToConsider)) >= minUnder && none(fixed(collidingWithMe())))
            setFixed();
        else
            move(randomMove());   
    }
}

