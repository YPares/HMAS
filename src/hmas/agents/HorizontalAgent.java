package hmas.agents;

import hmas.*;
import java.util.Random;


public class HorizontalAgent extends Agent
{
    public static Vector makeDiagonal(int level)
    {
        switch(level)
        {
            case 1: return new Vector(9, 1);
            case 2: return new Vector(49, 4);
            case 3: return new Vector(99, 66);
        }
        return new Vector(0, 0);
    }

    public HorizontalAgent(World world, int level)
    {
        super(world, level, world.randomPosition(), makeDiagonal(level));
    }

    public HorizontalAgent(World world, int level, Vector position)
    {
        super(world, level, position, makeDiagonal(level));
    }

    public void step()
    {
        int minUnder = 1;
        switch(getLevel())
        {
            case 1: minUnder = 15;
                    break;
            case 2: minUnder = 3;
                    break;
            case 3: minUnder = 1;
                    break;
        }
        if(count(fixed(completelyUnderMe())) >= minUnder && none(fixed(ofMyType(collidingWithMe()))))
            setFixed();
        else
            move(randomMove().mul(getLevel()));   
    }
}

