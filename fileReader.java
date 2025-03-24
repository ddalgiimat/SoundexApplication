import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//bufferedReader = helps read line by line
//filereader = helps open file and read it 
//IOException = an exception when it cannot find file 
public class fileReader
{
   public List<String> readNames(String lastnames)
   {
      List<String> lastNames = new ArrayList<>();//makes an array to put in names
      try (BufferedReader reader = new BufferedReader(new FileReader(lastnames)))//this creates an instance where it uses filereader.io and buffered reader wrapped 
      {
         String line = "";
         while((line = reader.readLine()) != null)//mehtod readLine() reads line as a String 
         {
            lastNames.add(line);//adds line in array
         }
      }
         catch(IOException e)
         {
            System.out.println("Error: Could Not Find the FIle");
         }
        return lastNames;
     

   }
}
