/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {
    
    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    public static void main(String[] args) {
    List list = new List();
    String str = "committee_";
    
    // מעבר על כל אות במחרוזת ועדכון הרשימה
    for (int i = 0; i < str.length(); i++) {
        list.update(str.charAt(i));
    }
    
    // הדפסת הרשימה הסופית לבדיקת השכיחויות
    System.out.println(list.toString());
}
    
    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the CharData of the first element in this list. */
    public CharData getFirst() {
        // Your code goes here
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        CharData c = new CharData(chr);
        Node newNode = new Node(c);
        newNode.next = this.first;
        this.first = newNode;
        this.size++;
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        if (size == 0) return "()";
        ListIterator lst = new ListIterator(first);
        String str = "(";

        while (lst.hasNext())
        {
            str = str + lst.next().toString() + "";   
        }
        return str.substring(0, str.length() - 1) + ")";
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        return this.indexOf(chr, this.first);
    }

    private int indexOf(char chr, Node current){
        // int ans = 0;

        // while (current != null);
        // {
        //     if (current.cp.chr == chr) return ans;

        //     current = current.next;
        //     ans++;
        // }
        // return -1;
        if (current == null) return -1;
        if (current.cp.chr == chr) return 0;

        int ans = indexOf(chr, current.next);

        if (ans == -1) return -1;
        else return ans + 1;
    }

    /** If the given character exis ts in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        Node current = first;

        if (indexOf(chr) < 0)
        {
            addFirst(chr);
        }
        else
        {
            while (current.cp.chr != chr)
                {
                    current = current.next;
                }
            current.cp.count++;
        }  
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        Node prev = null;
        Node current = first;

        while (current != null && current.cp.chr !=chr)
        {
            prev = current;
            current = current.next;
        }

        if (current == null) return false;
        if (prev == null) first = first.next;
        else prev.next = current.next;
        size--;
        return true;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current = first;
        for (int i = 0; i <index; i++)
        {
            current = current.next;
        }
        return current.cp;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}