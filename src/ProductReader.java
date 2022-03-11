import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.*;

public class ProductReader
{
    static ArrayList<Product> ProductRecordArray = new ArrayList<Product>();

    public static void ReadFileDataToArrayList()
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try
        {
            // uses a fixed known path:
            //  Path file = Paths.get("c:\\My Documents\\data.txt");

            // use the toolkit to get the current working directory of the IDE
            // Not sure if the toolkit is thread safe...
            File workingDirectory = new File(System.getProperty("user.dir"));

            // Typiacally, we want the user to pick the file so we use a file chooser
            // kind of ugly code to make the chooser work with NIO.
            // Because the chooser is part of Swing it should be thread safe.
            chooser.setCurrentDirectory(workingDirectory);
            // Using the chooser adds some complexity to the code.
            // we have to code the complete program within the conditional return of
            // the filechooser because the user can close it without picking a file

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                // Finally we can read the file LOL!
                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    String [] CSVArray = rec.split(",");

                    if(CSVArray.length == 4)
                    {
                        Product ProductRecord = new Product();

                        ProductRecord.setM_sID(CSVArray[0]);
                        ProductRecord.setM_sName(CSVArray[1]);
                        ProductRecord.setM_sDescription(CSVArray[2]);
                        ProductRecord.setM_fCost(Double.parseDouble(CSVArray[3].trim()));

                        ProductRecordArray.add(ProductRecord);
                        line++;
                        // echo to screen
                        System.out.printf("\nLine %4d %-60s ", line, ProductRecord.toCSVDataRecord());
                    }
                }

                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void DisplayProductRecords()
    {
        //ID#           Name     Decription      Cost
        String strDisplayHeader = String.format("%-11s","ID#");
        strDisplayHeader += String.format("%-30s", "Product Name");
        strDisplayHeader += String.format("%-50s", "Product Description");
        strDisplayHeader += String.format("%-10s", "Product Cost");

        System.out.println(strDisplayHeader);
        String strDisplayUnderLine = String.format("%101s", " ").replace(' ', '=');
        System.out.println(strDisplayUnderLine);

        for(Product ProductRecord : ProductRecordArray)
        {
            String strDisplayRec = "";

            strDisplayRec = String.format("%8s", ProductRecord.getM_sID()).replace(' ', '0');
            strDisplayRec += String.format("%2s", " ");

            strDisplayRec += String.format("%-30s", ProductRecord.getM_sName());

            strDisplayRec += String.format("%-50s", ProductRecord.getM_sDescription());

            strDisplayRec += String.format("%-10s", ProductRecord.getM_fCost());

            System.out.println(strDisplayRec);
        }
    }

    public static void main(String[] args)
    {
        ReadFileDataToArrayList();

        DisplayProductRecords();
    }
}
