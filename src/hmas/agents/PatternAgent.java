package hmas.agents;

import hmas.*;
import java.util.Random;


public class PatternAgent extends Agent
{
    private Integer found = null;
    private int numVFinal;
    private int numHFinal;

    public PatternAgent(World world)
    {
        super(world, 4, new Vector(0, 0), new Vector(99, 199));
    }

    public void step()
    {
        int numV = 0, numH = 0;
        for(Agent ag : fixed(partiallyUnderMe())) {
            if(ag instanceof VerticalAgent)
                numV += 1;
            else if(ag instanceof HorizontalAgent)
                numH += 1;
        }
        if(numV == 2 && numH == 2) {
            found = 0;
            setFixed();
        }
        if(numV == 1 && numH == 0) {
            found = 1;
            setFixed();
        }
        else if(numV == 2 && numH == 3) {
            found = 2;
            setFixed();
        }
        else if(numV == 1 && numH == 3) {
            found = 3;
            setFixed();
        }
        else if(numV == 2 && numH == 1) {
            found = 4;
            setFixed();
        }
        else if(numV == 1 && numH == 1) {
            found = 7;
            setFixed();
        }
        numHFinal = numH;
        numVFinal = numV;
    }

    public Integer found()
    {
        System.out.println(numHFinal);
        System.out.println(numVFinal);
        return this.found;
    }
}

