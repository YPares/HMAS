package hmas;

import java.util.Collection;


public abstract class Agent
{
    private Vector position;
    private Vector diagonal;
    
    public abstract boolean isFixed();

    public abstract Collection<Agent> getSons();
    public abstract Collection<Agent> getBrothers();
}

