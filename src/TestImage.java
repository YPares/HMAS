import hmas.ImageOpener;

import java.io.IOException;


public class TestImage
{
    public static void main(String[] args) throws IOException {
       ImageOpener.displayBoolArray(ImageOpener.urlToBoolArray(args[1])); 
    }
}

