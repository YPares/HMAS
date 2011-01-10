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
        for(int i=0; i<100; i++)
        {
            new VerticalAgent(world, 1);
        }
        for(int i=0; i<200; i++)
        {
            new HorizontalAgent(world, 1);
        }
        for(int i=0; i<10; i++)
        {
            new VerticalAgent(world, 2);
            new HorizontalAgent(world, 2);
        }
        for(int i=0; i<5; i++)
        {
            new VerticalAgent(world, 3);
            new HorizontalAgent(world, 3);
            new HorizontalAgent(world, 3);
        }
        PatternAgent top = new PatternAgent(world);

        
        for(int level=0; level <= 1; level++)
        {
            for(int i=0; i<8000; i++)
            {
                world.stepLevel(level);
            }
        }
        for(int i=0; i<3000; i++)
        {
            world.stepLevel(2);
        }
        for(int i=0; i<1000; i++)
        {
            world.stepLevel(3);
            world.stepLevel(4);
        }

        //world.displayLevel(Integer.parseInt(args[1]));
        int[] levels = {3};
        world.displayLevels(levels);
        System.out.println(top.found());
    }
}

