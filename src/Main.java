import hmas.World;
import hmas.Agent;
import hmas.agents.*;
import hmas.Vector;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args) throws java.io.IOException
    {
        World world = new World(args[0]);
        for(int i=0; i<2000; i++)
            new PixelAgent(world);
        for(int i=0; i<100; i++)
        {
            new VerticalAgent(world, 1);
        }
        for(int i=0; i<200; i++)
        {
            new HorizontalAgent(world, 1);
        }
        for(int i=0; i<20; i++)
        {
            new VerticalAgent(world, 2);
            new HorizontalAgent(world, 2);
        }
        for(int i=1; i<=3; i++)
        {
            new VerticalAgent(world, 3, new Vector(34*(i-1),0) );
            new HorizontalAgent(world, 3, new Vector(0,67*(i-1)) );
        }
        PatternAgent top = new PatternAgent(world);

        
        for(int level=0; level <= 1; level++)
        {
            for(int i=0; i<4000; i++)
            {
                world.stepLevel(level);
            }
        }
        for(int i=0; i<3000; i++)
        {
            world.stepLevel(2);
        }
        for(int i=0; i<2; i++)
        {
            world.stepLevel(3);
            world.stepLevel(4);
        }

        //world.displayLevel(Integer.parseInt(args[1]));
        //int[] levels = {1};
        //world.displayLevels(levels);
        
        Scanner sc = new Scanner(System.in);
        int level;
        displayFound(top);
        while((level = sc.nextInt()) != 9){
            world.displayLevel(level);
            displayFound(top);
         }
        
    }

    public static void displayFound(PatternAgent top)
    {
        Integer found = top.found();
        System.out.println("H: " + top.numHFinal());
        System.out.println("V: " + top.numVFinal());
        System.out.println("Found: " + (found == 2 ? "2, 5, 6, 8 ou 9" : found) );
    }
}

