import hmas.World;
import hmas.Agent;
import hmas.agents.*;


public class Main
{
    public static void main(String[] args) throws java.io.IOException
    {
        World world = new World(args[0]);
        for(int i=0; i<10; i++)
            new PixelAgent(world);
        world.displayLevel(0);
    }
}

