public class List<T> {
    public int length;
    public Node<T> head;

    public List()
    {
        head = null;
        length = 0;
    }

    public String toString()
    {
        String str = "";
        Node<T> nodePtr = head;
        while (nodePtr != null)
        {
            str = str + nodePtr.data.toString() + ",";
            nodePtr = nodePtr.next;
        }
        if(str == ""){
            return "";
        }
        return str.substring(0, str.length() - 1);
    }

    public void append(T val)
    {
        if (head == null)
        {
            head = new Node<T>(val);
            length = length + 1;
        }
        else
        {
            Node<T> nodePtr = head;
            while (nodePtr.next != null)
            {
                nodePtr = nodePtr.next;
            }
            nodePtr.next = new Node<T>(val);
            length = length + 1;
        }
    }

    public boolean remove(T val)
    {
        boolean removed = false;
        if (head != null){
            if (head.data.equals(val) == true)
            {
                head = head.next;
                length = length - 1;
                removed = true;
            }
            else
            {
                Node<T> nodePtr = head;
                while (nodePtr.next != null)
                {
                    if (nodePtr.next.data.equals(val) == true)
                    {
                        nodePtr.next = nodePtr.next.next;
                        length = length - 1;
                        removed = true;
                        break;
                    }
                    else
                    {
                        nodePtr = nodePtr.next;
                    }
                }
            }
        }
        return removed;
    }

    public boolean remove(List<T> val)
    {
        Node<T> nodePtr = val.head;
        boolean removed = false;
        while (nodePtr != null)
        {
            boolean found = remove(nodePtr.data);
            if (found == true)
            {
                removed = true;
            }
            nodePtr = nodePtr.next;
        }
        return removed;
    }

    public boolean contains(T search)
    {
        Node<T> nodePtr = head;
        boolean found = false;
        while (nodePtr != null)
        {
            if (nodePtr.data.equals(search) == true)
            {
                found = true;
                break;
            }
            else
            {
                nodePtr = nodePtr.next;
            }
        }
        return found;
    }

    public boolean equals(List<T> other)
    {
        if (other != null && length == other.length)
        {
            Node<T> nodePtr = head;
            Node<T> otherNodePtr = other.head;
            while (nodePtr != null && otherNodePtr != null)
            {
                if (nodePtr.data.equals(otherNodePtr.data) == false)
                {
                    return false;
                }
                else
                {
                    otherNodePtr = otherNodePtr.next;
                    nodePtr = nodePtr.next;
                }
            }
            return true;
        }
        return false;
    }
}
