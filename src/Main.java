import java.lang.reflect.Array;
import java.util.*;

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}

class LinkedListProcessor {
    public LinkedList<Book> fulfillWithRandomObjects(int listMaxSize) {
        LinkedList<Book> aLinkedList = new LinkedList<>();
        for (int i = 0; i < listMaxSize; i++) {
            String randomTitle = "Random title no " + (i + 1);
            String randomAuthor = "Random author no " + (i + 1);
            Book book = new Book(randomTitle, randomAuthor);
            aLinkedList.add(book);
        }
        return aLinkedList;
    }

    public long addLastObjectTo(LinkedList<Book> aList) {
        Book aBook = new Book("aTittle", "anAuthor");
        long begin = System.nanoTime();
        aList.addLast(aBook);
        long end = System.nanoTime();
        aList.removeLast();
        return end - begin;
    }

    public long addFirstObjectTo(LinkedList<Book> aList) {
        Book aBook = new Book("aTittle", "anAuthor");
        long begin = System.nanoTime();
        aList.addFirst(aBook);
        long end = System.nanoTime();
        aList.removeFirst();
        return end - begin;
    }

    public long removeFirstObjectFrom(LinkedList<Book> aList) {
        Book temp = aList.getFirst();
        long begin = System.nanoTime();
        aList.removeFirst();
        long end = System.nanoTime();
        aList.addFirst(temp);
        return end - begin;
    }

    public long removeLastObjectFrom(LinkedList<Book> aList) {
        Book temp = aList.getLast();
        long begin = System.nanoTime();
        aList.removeLast();
        long end = System.nanoTime();
        aList.addLast(temp);
        return end - begin;
    }
}

class HashMapProcessor {
    final private Random random = new Random();

    public HashMap<Integer, Integer> generateHasHMap(int noOfMapElements) {
        HashMap<Integer, Integer> randomHashMap = new HashMap<>();
        for (int i = 0; i < noOfMapElements; i++) {
            randomHashMap.put(i, (i * 3));
        }
        return randomHashMap;
    }

    public long getTimeOfSearchingSpecificKeyIn(HashMap<Integer, Integer> aHashMap) {
        int key = random.nextInt(aHashMap.size());
        long begin = System.nanoTime();
        aHashMap.get(key);
        long end = System.nanoTime();
        return end - begin;
    }

    public long getTimeOfAddingObjectTo(HashMap<Integer, Integer> aHashMap) {
        Integer key = random.nextInt(aHashMap.size() + random.nextInt(100));
        long begin = System.nanoTime();
        aHashMap.put(key, (key * 3));
        long end = System.nanoTime();
        aHashMap.remove(key);
        return end - begin;
    }

    public long getTimeOfRemovingObjectFrom(HashMap<Integer, Integer> aHashMap) {
        Integer key = random.nextInt(aHashMap.size());
        Integer temp = aHashMap.get(key);
        long begin = System.nanoTime();
        aHashMap.remove(key);
        long end = System.nanoTime();
        aHashMap.put(temp, (temp * 3));
        return end - begin;
    }
}

class ArrayListProcessor {
    public ArrayList<Book> fulfillWithRandomObjects(int arrayMaxSize) {
        ArrayList<Book> anArrayList = new ArrayList<>();
        for (int i = 0; i < arrayMaxSize; i++) {
            String aTitle = "Random title no " + (i + 1);
            String anAuthor = "Random author no " + (i + 1);
            Book book = new Book(aTitle, anAuthor);
            anArrayList.add(book);
        }
        return anArrayList;
    }

    public long addLastObjectTo(ArrayList<Book> aList) {
        Book aBook = new Book("aTittle", "anAuthor");
        long begin = System.nanoTime();
        aList.add(aBook);
        long end = System.nanoTime();
        aList.remove(aList.size() - 1);
        return end - begin;
    }

    public long addFirstObjectTo(ArrayList<Book> aList) {
        Book aBook = new Book("aTittle", "anAuthor");
        long begin = System.nanoTime();
        aList.add(0, aBook);
        long end = System.nanoTime();
        aList.remove(0);
        return end - begin;
    }

    public long removeFirstObjectFrom(ArrayList<Book> aList) {
        Book temp = aList.get(0);
        long begin = System.nanoTime();
        aList.remove(0);
        long end = System.nanoTime();
        aList.add(0, temp);
        return end - begin;
    }

    public long removeLastObjectFrom(ArrayList<Book> aList) {
        Book temp = aList.get(aList.size() - 1);
        long begin = System.nanoTime();
        aList.remove(aList.size() - 1);
        long end = System.nanoTime();
        aList.add(temp);
        return end - begin;
    }
}

class App {

    public static void main(String[] args) {
        /* First part of exercise: */
        final LinkedListProcessor linkedListProcessor = new LinkedListProcessor();

        long listCreateStart = System.nanoTime();
        final LinkedList<Book> aRandomList = linkedListProcessor.fulfillWithRandomObjects(2000000);
        long listCreateEnd = System.nanoTime();
        System.out.println("It takes " + (listCreateEnd - listCreateStart) + "ns to create a LinkedLIst, which contains " + aRandomList.size() + " elements.");

        /* To make a test on LinkedList, which contains this same no of elements - all methods after specific time measure returns list with starting value: */
        System.out.println("\nIt takes " + linkedListProcessor.removeFirstObjectFrom(aRandomList) + "ns to find and remove first object from LinkedList.");
        System.out.println("\nIt takes " + linkedListProcessor.removeLastObjectFrom(aRandomList) + "ns to find and remove last object from LinkedList.");
        System.out.println("\nIt takes " + linkedListProcessor.addFirstObjectTo(aRandomList) + "ns to add an Object at first position of LInkedList.");
        System.out.println("\nIt takes " + linkedListProcessor.addLastObjectTo(aRandomList) + "ns to add an Object at last position of LinkedList.");

        /* Second part of exercise: */
        final HashMapProcessor hashMapProcessor = new HashMapProcessor();

        long mapCreateStart = System.nanoTime();
        final HashMap<Integer, Integer> aRandomHashMap = hashMapProcessor.generateHasHMap(200000);
        long mapCreateEnd = System.nanoTime();
        System.out.println("\n\nIt takes " + (mapCreateEnd - mapCreateStart) + "ns to create HasHMap, which contains " + aRandomHashMap.size() + " elements.\n");

        System.out.println("It takes " + hashMapProcessor.getTimeOfSearchingSpecificKeyIn(aRandomHashMap) + "ns to find random key.");
        System.out.println("It takes " + hashMapProcessor.getTimeOfSearchingSpecificKeyIn(aRandomHashMap) + "ns to find random key.");
        System.out.println("It takes " + hashMapProcessor.getTimeOfSearchingSpecificKeyIn(aRandomHashMap) + "ns to find random key.");

        System.out.println("\nIt takes " + hashMapProcessor.getTimeOfAddingObjectTo(aRandomHashMap) + "ns to add random object.");
        System.out.println("It takes " + hashMapProcessor.getTimeOfAddingObjectTo(aRandomHashMap) + "ns to add random object.");
        System.out.println("It takes " + hashMapProcessor.getTimeOfAddingObjectTo(aRandomHashMap) + "ns to add random object.");

        System.out.println("\nIt takes " + hashMapProcessor.getTimeOfRemovingObjectFrom(aRandomHashMap) + "ns to remove random object.");
        System.out.println("It takes " + hashMapProcessor.getTimeOfRemovingObjectFrom(aRandomHashMap) + "ns to remove random object.");
        System.out.println("It takes " + hashMapProcessor.getTimeOfRemovingObjectFrom(aRandomHashMap) + "ns to remove random object.");

        /*Checking ArrayLists */
        final ArrayListProcessor arrayListProcessor = new ArrayListProcessor();

        long arrayCreateStart = System.nanoTime();
        final ArrayList<Book> aRandomArray = arrayListProcessor.fulfillWithRandomObjects(2000000);
        long arrayCreateEnd = System.nanoTime();
        System.out.println("\n\nIt takes " + (arrayCreateEnd - arrayCreateStart) + "ns to create a n ArrayList, which contains " + aRandomArray.size() + " elements.");

        System.out.println("\nIt takes " + arrayListProcessor.removeFirstObjectFrom(aRandomArray) + "ns to find and remove first object from ArrayList.");
        System.out.println("\nIt takes " + arrayListProcessor.removeLastObjectFrom(aRandomArray) + "ns to find and remove last object from ArrayList.");
        System.out.println("\nIt takes " + arrayListProcessor.addFirstObjectTo(aRandomArray) + "ns to add an Object at first position of ArrayList.");
        System.out.println("\nIt takes " + arrayListProcessor.addLastObjectTo(aRandomArray) + "ns to add an Object at last position of ArrayList.");
    }
}