package hmas.agents;

import hmas.*;
import java.util.Random;


// Top agent that recognizes a digit given the fixed vertical and horizontal agents under it
// Only appears at level 4.
public class PatternAgent extends Agent
{
    private Integer found = null;
    private Integer numVFinal = 0;
    private Integer numHFinal = 0;

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
        else if(numV == 1 && numH == 0) {
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

    // Gives the digit that has been found, or null if it is still to be found
    public Integer found()
    {
        return this.found;
    }

    // Gives the number of HorizontalAgents of level N-1 that have been found
    public Integer numHFinal()
    {
        return numHFinal;
    }

    // Gives the number of VerticalAgents of level N-1 that have been found
    public Integer numVFinal()
    {
        return numVFinal;
    }
}

