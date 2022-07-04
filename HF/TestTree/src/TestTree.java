import java.util.TreeSet;

public class TestTree {

    public static void main(String[] args) {
        TestTree testTree = new TestTree();
        testTree.go();
    }

    public void go() {
        Book book1 = new Book("Как устроены кошки");
        Book book2 = new Book("Постройте заново своё тело");
        Book book3 = new Book("В поисках Эмо");

        TreeSet<Book> treeSet = new TreeSet<>();
        treeSet.add(book1);
        treeSet.add(book2);
        treeSet.add(book3);
        System.out.println(treeSet);

    }

    static class Book implements Comparable<Book> {
        String title;

        public Book(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public int compareTo(Book o) {
            return title.compareTo(o.getTitle());
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
