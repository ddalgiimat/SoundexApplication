import java.util.Map;
public class MapEntry<K,V> implements Map.Entry<K, V>

{
   private K key;
   private V value;
   
   public MapEntry(K key, V value)
   {
      this.key=key;
      this.value=value;
   }
   public K getKey()
   {
      return key;
   }
   public V getValue()
   {
      return value;
   }
   public V setValue(V value)
   {
       V oldValue = this.value;
       this.value = value;
       return oldValue;
   }
   
} 
