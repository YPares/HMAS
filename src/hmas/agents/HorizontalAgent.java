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
            case 3: return new Vector(99, 4);
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
            case 2: minUnder = 4; break;
            case 3: minUnder = 2; break;
        }
        if(count(fixed(completelyUnderMe())) >= minUnder && none(fixed(collidingWithMe())))
            setFixed();
        else
            move(randomMove());
    }
}

