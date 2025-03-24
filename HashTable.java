import java.util.Collection;
import java.util.Map;
import java.util.Set;

import java.util.HashSet;

public class HashTable<K, V> implements Map<K, V>
{
   private Map.Entry<K, V>[] data;
   private int size;
   private static final int DEFAULT_SIZE = 11;
   
   public HashTable()
   {
      this(DEFAULT_SIZE);
   }
   
   @SuppressWarnings("unchecked")
   public HashTable(int initialCapacity)
   {
      if(initialCapacity < 0)
      {
         throw new IllegalArgumentException();
      }
      data = new Map.Entry[initialCapacity];
      size=0;
   } 
  
   
   public void clear()
   {
      for(int i = 0; i <data.length;i++)
      {
         data[i] = null;
      }
      size=0;
   }
   
   public boolean containsKey(Object key)
   {
      return get(key) != null;
   }
   public boolean containsValue(Object value)
   {
      for(int i = 0; i <data.length;i++)
      {
         if(data[i] !=null)
         {
            if(data[i].getValue().equals(value))
            {
               return true;
            }
         }
      }
      return false;
   }
   
   public Set<Map.Entry<K, V>> entrySet()//shows list of everything 
   {
      Set<Map.Entry<K, V>> entrySet = new HashSet<>();
      for(int i = 0; i<data.length;i++)
      {
         if(data[i] != null)
         {
           entrySet.add(data[i]); 
         }
      }
      return entrySet;
   }
   
   public V get(Object key)
   {
      int index = Math.abs(key.hashCode() % data.length);//places the key hash into index 
      
      for(int i =0; i<data.length;i++)//runs thru the array
      {
         if(data[index]==null)
         {
            return null;//if the index is empty then it returns V null
         }
         else if(data[index].getKey().equals(key))
         {
            return data[index].getValue();//if the index's key = key we are looking for then
         }
         index=(index+1)%data.length;//linear probing 
      }
      return null; //key is not found 
   }
   
   public Set<K> keySet()
   {
      Set<K> keys = new HashSet<>();
      for(int i = 0; i < data.length;i++)
      {
         if(data[i] != null)
         {
            keys.add(data[i].getKey());
         }
      }
      return keys;
   }
   
   public boolean isEmpty()
   {
      return size == 0; 
   }
   
   public Collection<V> values()//calls out all values 
   {
      HashSet<V> values = new HashSet<>();
      for(Map.Entry<K,V> entry: data)
      {
         if(entry != null)
         {
            values.add(entry.getValue());
         }
         
      }
      return values;
   }
   
   public V put(K key, V value)
   { 
     
     if(key == null)//key cannot be null
     {
         throw new NullPointerException("Key cannot be null");
     }
     
      
     if(size >= data.length *0.75)//fixes problem with full hashtable
     {
         resize();
       
     }
     Map.Entry<K, V> entry = new MapEntry<>(key,value); //creates object
     int index = Math.abs(key.hashCode() % data.length);//finds what index to put in by hashing
     V oldValue = null;//must return value  
     while(data[index] != null)
     {
         if(data[index].getKey().equals(key))
         {
            oldValue = data[index].getValue();
            data[index].setValue(value);
            return oldValue;
         }
         //if key is occupied then linear probing move to next index
         index = (index+1)% data.length;
     } 
     
      //if empty
      data[index] = entry;
      size++;
      return null;   
         
   }
   @SuppressWarnings("unchecked")
   private void resize()
   {
      Map.Entry<K,V>[] oldData = data;
      data = new Map.Entry[data.length *2];
      
      
      for(Map.Entry<K,V> entry : oldData)
      {
         if(entry != null)
         {
            put(entry.getKey(), entry.getValue());
         }
      }
   }
   //Map type(?=wildcard) m = map variable.
   public void putAll(Map<? extends K, ? extends V> m)//adds from another map to your map
   {
      for(Map.Entry <? extends K, ? extends V> entry : m.entrySet())
      {
         put(entry.getKey(), entry.getValue());
         
      } 
   }    
   
   public V remove(Object key)
   {
      if(key == null)//Case 1:if key given is null return null;
      {
         return null;
      }
      int index = Math.abs(key.hashCode() % data.length);
      V oldValue = null;
      
      while(data[index] != null)//uses while loop instead of for loop for efficiency
      {
         if(data[index].getKey().equals(key))//find key
         {
            oldValue = data[index].getValue();//stores old value
            data[index] = null; //removes
            size--;
            
            //rehash after deletion because some entries after the fact may get lost
            index = (index +1)% data.length;//move to next element
            while(data[index] != null)
            {
               Map.Entry<K,V> entryrehash = data[index];//save it
               data[index] = null;//removes
               size--;//temporarily because we are going to use the put method to insert it into the correct spot
               
               put(entryrehash.getKey(), entryrehash.getValue());//put
               index = (index+1)% data.length; //update
               
            }
            return oldValue;
         }
         index = (index+1) % data.length;//linear probing
      }
      
      return null;//if no key found...
   }
   
   public int size()
   {
      return size; 
   }
}
