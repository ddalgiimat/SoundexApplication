import java.util.Scanner;
import java.util.List;
public class soundexLookUp
{
    public static void main(String[] args)
     {
     //this make hashtable,filerader,snd soundex objects
      HashTable<String, String> hashtable = new HashTable<>();
      fileReader fileReader = new fileReader();
      Soundex soundex = new Soundex();
      List<String> names = fileReader.readNames("lastnames.txt");
      //this fills in hashtable
      for(String name:names)
      {   
         String soundexCode= soundex.soundexCode(name);
         hashtable.put(name, soundexCode);
      }
      System.out.println("Enter a name to look up (or type 'EXIT' to exit)");
      Scanner scanner = new Scanner(System.in);
      while(true)
      {
         System.out.println("Enter a name: ");
         String name = scanner.nextLine().toUpperCase();//make uppercase
         if(name.equalsIgnoreCase("EXIT"))
         {
            break;
         }
        
      
         String soundexx = hashtable.get(name);
         if(soundexx !=null)
         {
            System.out.printf("Name:%s Soundex: %s",name,soundexx);
            System.out.println();
            
         }
         else 
         {
            System.out.println("Name not found in database. Sorry!");
      
         }
     }
     scanner.close();
     System.out.println("Goodbye");
     
   }
   
 }
