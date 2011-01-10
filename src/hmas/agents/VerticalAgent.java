package hmas.agents;

import hmas.*;
import java.util.Random;


public class VerticalAgent extends Agent
{
    public static Vector makeDiagonal(int level)
    {
        switch(level)
        {
            case 1: return new Vector(0, 19);
            case 2: return new Vector(6, 99);
            case 3: return new Vector(33, 199);
        }
        return new Vector(0, 0);
    }

    public VerticalAgent(World world, int level)
    {
        super(world, level, world.randomPosition(), makeDiagonal(level));
    }

    public VerticalAgent(World world, int level, Vector position)
    {
        super(world, level, position, makeDiagonal(level));
    }

    public void step()
    {
        int minUnder = 1;
        switch(getLevel())
        {
            case 1: minUnder = 8;
                    break;
            case 2: minUnder = 3;
                    break;
            case 3: minUnder = 2;
                    break;
        }
        if(count(fixed(completelyUnderMe())) >= minUnder && none(fixed(ofMyType(collidingWithMe()))))
            setFixed();
        else
            move(randomMove().mul(getLevel()));   
    }
}

