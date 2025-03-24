public class Soundex
{                     
   public String soundexCode(String name)
   {
  
      if(name == null || name.isEmpty())
      {
        return "Input name is empty";//handles if name==null or isempty
      }
      
      char firstLetter = Character.toUpperCase(name.charAt(0));//grabs first letter
      StringBuilder soundex = new StringBuilder();//uses StringBuilder
      soundex.append(firstLetter);//append=attach.soundex object adds firstletter.
      
      int prevcode =-1;
      
      for(int i = 1; i< name.length();i++)
      {
        char index = Character.toUpperCase(name.charAt(i));//loops throught the name to uppercase(also index);
        int code = -1;//SoundexCode
        //pretty self-explanatory
        if(index =='A' ||index=='E' || index=='I'||index== 'O'||
        index== 'U'||index== 'H' ||index== 'W'||index=='Y')
        {
            code=0;
            
        }
        else if(index=='B'||index=='F'||index=='P'||index=='V')
        {
            code=1;
        }
        else if(index=='C'||index=='G'||index=='J'||index=='K'||
        index=='Q'||index=='S'|| index=='X'|| index == 'Z')
        {
            code=2;
        }
        else if(index=='D'||index=='T')
        {
            code=3;
        }
        else if(index=='L')
        {
            code=4;
        }
        else if(index =='M' || index== 'N')
        {
            code=5;
        }
        else if(index =='R')
        {
            code=6;
        }
        
        if( code != prevcode)//only if code does not = prevcode can u...
        {
            if(code >0) //ignores vowels 
            {
            soundex.append(code);//add
            }
            
            prevcode = code;//updates prevode
            //append can handle any type of data types.
        }
        
     }
      
      //make sure the size is 4 
      //cuts out the extra
      while(soundex.length() < 4)
      {
         soundex.append("0");
      }
      return soundex.substring(0,4);
     //return soundex.toString(); 
   }                  
   
  
   
   
   
}
