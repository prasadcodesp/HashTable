
import java.io.*;
import java.util.*;
import java.util.Scanner;
 
class LP {
   
    private int currentSize, maxSize;
    private String[] keys;
    private String[] vals;
 
    public LP(int capacity)
    {
        currentSize = 0;
        maxSize = capacity;
        keys = new String[maxSize];
        vals = new String[maxSize];
    }
 
    public void makeEmpty()	 
    {
        currentSize = 0;
        keys = new String[maxSize];
        vals = new String[maxSize];
    }
  
    public int getSize() 	
    { 
	return currentSize; 
    }
 
    public boolean isFull()	
    {
        return currentSize == maxSize;
    }
 
    public boolean isEmpty() 	
    { 
	return getSize() == 0; 
    }
   
    public boolean contains(String k)	 
    {
        return get(k) != null;
    }
 
    private int hash(String k)  
    {
        
        return Integer.parseInt(k)%maxSize;
    }
 
    public void insert(String k, String v)	    
    {
        int tmp = hash(k);
        int i = tmp;
        do {
            if (keys[i] == null) {
                keys[i] = k;
                vals[i] = v;
                currentSize++;
                return;
            }
 
            if (keys[i].equals(k)) {
                vals[i] = v;
                return;
            }

            i = (i + 1) % maxSize;
        }
        while (i != tmp);
    }

    public String get(String k)     
    {
        int i = hash(k);
        while (keys[i] != null) {
            if (keys[i].equals(k))
                return vals[i];
            i = (i + 1) % maxSize;
        }
        return null;
    }
 
    public void remove(String k)	    
     {
        if (!contains(k))
            return;
        int i = hash(k);		
        while (!k.equals(keys[i]))
            i = (i + 1) % maxSize;
        keys[i] = vals[i] = null;
         
        for (i = (i + 1) % maxSize; keys[i] != null; i = (i + 1) % maxSize) 
	{
            String tmp1 = keys[i], tmp2 = vals[i];
            keys[i] = vals[i] = null;
            currentSize--;
            insert(tmp1, tmp2);
        }
        currentSize--;
    }
 
    public void printHashTable()	   
    {
        System.out.println("\nHash Table: ");
        for (int i = 0; i < maxSize; i++)
            if (keys[i] != null)
                System.out.println(keys[i] + " " + vals[i]);
        System.out.println();
    }
}
 
public class Hashtable {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hash Table Test\n\n");
        System.out.println("Enter size");
 
        LP l = new LP(scan.nextInt());
 
        char ch;
 
        do
        {
            System.out.println("\nHash Table Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. remove");
            System.out.println("3. get");
            System.out.println("4. clear");
            System.out.println("5. size");
            int choice = scan.nextInt();
            switch (choice) {
            case 1:
                System.out.println("Enter key and value");
                l.insert(scan.next(), scan.next());
                break;
 	     
            case 2:
                System.out.println("Enter key");
                l.remove(scan.next());
                break;
 
            case 3:
                System.out.println("Enter key");
                System.out.println("Value = "+ l.get(scan.next()));
                break;
 
            case 4:
                l.makeEmpty();
                System.out.println("Hash Table Cleared\n");
                break;
 
            case 5:
                System.out.println("Size = "+ l.getSize());
                break;
 
            default:
                System.out.println("Wrong Entry \n ");
                break;
            }
 
            l.printHashTable();		 
            
            System.out.println("\nDo you want to continue (Type y or n) \n");
 
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}
