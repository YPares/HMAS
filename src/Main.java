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
        for(int i=0; i<50; i++)
            new VerticalAgent(world, 1);
        for(int i=0; i<5; i++)
            new VerticalAgent(world, 2);
        for(int i=0; i<2000; i++)
        {
            world.stepLevel(0);
        }
        for(int i=0; i<2000; i++)
        {
            world.stepLevel(0);
            world.stepLevel(1);
        }
        for(int i=0; i<2000; i++)
            world.stepAll();
        //world.displayLevel(Integer.parseInt(args[1]));
        //int[] levels = {0,1,2};
        //world.displayLevels(levels);
    }
}

