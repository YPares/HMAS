import hmas.World;
import hmas.Agent;
import hmas.agents.*;


public class Main
{
    public static void main(String[] args) throws java.io.IOException
    {
        World world = new World(args[0]);
        for(int i=0; i<1000; i++)
            new PixelAgent(world);
        for(int i=0; i<30; i++)
            new VerticalAgent(world, 1);
        for(int i=0; i<5000; i++)
        {
            world.stepLevel(0);
        }
        for(int i=0; i<5000; i++)
        {
            world.stepLevel(1);
        }
        world.displayLevel(1);
    }
}

