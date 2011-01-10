package hmas.agents;

import hmas.*;
import java.util.Random;


public class HorizontalAgent extends Agent
{
    public static Vector makeDiagonal(int level)
    {
        switch(level)
        {
            case 1: return new Vector(10, 1);
            case 2: return new Vector(50, 5);
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
        switch(getLevel())
        {
            case 1: minUnder = 8; break;
            case 2: minUnder = 5; break;
        }
        if(count(fixed(completelyUnderMe())) >= minUnder)
            setFixed();
    }
}

