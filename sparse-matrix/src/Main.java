import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" row && column ");

        int numberOf_row = sc.nextInt();
        int numberOf_column = sc.nextInt();
        System.out.println("1- add node ");
        System.out.println("2- delete node");
        System.out.println("3- search node");
        System.out.println("4- update ");
        System.out.println("5- print");

        int function = sc.nextInt();
        //-----------------------------------------
        DoublyLinkedList list1 = new DoublyLinkedList();
        DoublyLinkedList list2 = new DoublyLinkedList();
        DoublyLinkedList list3 = new DoublyLinkedList();

        DoublyLinkedList[] row = new DoublyLinkedList[5];
        row[1] = list1;
        row[2] = list2;
        row[3] = list3;


        Matrix matrix = new Matrix(numberOf_column, numberOf_row);

        //----------------------------------------------------
        System.out.println(" Enter nodes ");
        // sc.nextLine();
        // Enter row

            matrix.addRow(1, 0, 0);
            matrix.addRow(2, 0, 1);
            matrix.addRow(3, 0, 2);
            matrix.addRow(4, 1, 0);
            matrix.addRow(5, 1, 1);
            matrix.addRow(6, 1, 2);
            matrix.addRow(8, 1, 1);

            matrix.addColumn(1,0,0);
            matrix.addColumn(2,1,0);
            matrix.addColumn(3,2,0);
            matrix.addColumn(4,0,1);
            matrix.addColumn(5,1,1);
            matrix.addColumn(6,2,1);
            matrix.addColumn(7,0,2);
            matrix.addColumn(8,1,2);
            matrix.addColumn(9,2,2);
            matrix.addColumn(10,2,2);



        // Enter column
        //----------------------------------------------------


        //----------------------------------------------------
        if (function == 1) {
            System.out.println(" data && index_row && index_column");
            int index_row = sc.nextInt();
            int index_column = sc.nextInt();
            int data = sc.nextInt();
            //  row[index_row][index_column].add(data, index_row, index_column);
        } else if (function == 2) {
            System.out.println(" data && index_row && index_column");
            int index_row = sc.nextInt();
            int index_column = sc.nextInt();
            int data = sc.nextInt();
            // row[index_row][index_column].delete(data, index_column);

        } else if (function == 3) {
            System.out.println(" Enter data");
            int data = sc.nextInt();
            boolean find = false;
            matrix.search(data);
        } else if (function == 4) {
            System.out.println(" data && index_row && index_column");
            int index_row = sc.nextInt();
            int index_column = sc.nextInt();
            int data = sc.nextInt();
            //  row[index_row][index_column].delete(data, index_column);
        } else if (function == 5) {

        }
    }

}

class Matrix {

    DoublyLinkedList.Node[] header_row;
    DoublyLinkedList.Node[] header_column;
    DoublyLinkedList row;
    DoublyLinkedList column;

    public Matrix(int numberOf_column, int numberOf_row) {
        header_row = new DoublyLinkedList.Node[numberOf_row];
        header_column = new DoublyLinkedList.Node[numberOf_column];
        row = new DoublyLinkedList();
        column = new DoublyLinkedList();
    }

    public void addRow(int value, int index_row, int index_column) {
        DoublyLinkedList.Node node = new DoublyLinkedList.Node(value, index_column);
        row.head = header_row[index_row];
        if (row.head == null) {
            row.head = node;
            header_row[index_row]=node;
            row.tail=node;
            row.tail.setIndex(index_column);
        } else {
            DoublyLinkedList.Node current = row.head;

            while (current.getIndex() < index_column - 1) {
                current = current.next_column;
            }
            if (current.next_column != null) {
                DoublyLinkedList.Node next = current.next_column;
                node.next_column = next;
            }
            current.next_column = node;
            row.tail=node;
            row.tail.setIndex(index_column);

        }
        if (index_row != 0) {
            row.head = header_row[index_row - 1];
            DoublyLinkedList.Node next;
            DoublyLinkedList.Node current = row.head;
            while (current!=null && node!=null){

                if (current.getIndex() == node.getIndex()) {
                    current.next_row = node;
                    current = current.next_column;
                    node = node.next_column;
                    if(node!=null)
                     node.setIndex(node.getIndex()+1);
                } else {
                    current = current.next_column;
                }
            }
        }

    }
    public void addColumn(int value, int index_row, int index_column){
        DoublyLinkedList.Node node = new DoublyLinkedList.Node(value, index_row);
        column.head=header_column[index_column];
        if (column.head == null) {
            column.head = node;
            header_column[index_column]=node;
            column.tail=node;
            column.tail.setIndex(index_row);
        }
        else {
            DoublyLinkedList.Node current = column.head;

            while (current.getIndex() < index_row - 1) {
                current = current.next_row;
            }
            if (current.next_row != null) {
                DoublyLinkedList.Node next = current.next_row;
                node.next_row = next;
            }
            current.next_row = node;
            column.tail=node;
            column.tail.setIndex(index_row);
        }
        if (index_column != 0) {
            column.head = header_column[index_column - 1];
            DoublyLinkedList.Node current = column.head;
            while (current!=null && node!=null){
                if (current.getIndex() == node.getIndex()) {
                    current.next_column = node;
                    current = current.next_row;
                    node = node.next_row;
                    if(node!=null)
                        node.setIndex(node.getIndex()+1);
                } else {
                    current = current.next_row;
                }
            }
        }


    }

    public boolean search(int data) {
        boolean find = false;
        // searching at row linkedList
        for (int i = 0; i < header_row.length; i++) {
            find = row.search(data, header_row[i]);
            if (find) {
                return find;
            }
        }
        // searching at column linkedList
        for (int i = 0; i < header_column.length; i++) {
            find = column.search(data, header_column[i]);
        }
        if (find) {
            return find;
        }
        return false;
    }
    //--------------------------------------------------

}


class DoublyLinkedList {

    static class Node {
        private int data;
        private int index;
        Node next_row;
        Node next_column;

        public Node(int item, int index_row) {
            this.data = item;
            this.index = index_row;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    Node head, tail = null;


    public void delete(int data, int column) {
        Node temp;
        Node privies = head;
        Node next;
        // Previous stays Previous position of target
        for (int i = 1; i < column; i++) {
            privies = privies.next_row;
        }
        // to check data
//        if (privies.next.data == data) {
//            temp = privies.next.next;
//            next = privies.next.next_column;
//            privies.next_column = next;
//
//            Node current_row = next;
//            // to shift nodes of next row
//            while (current_row.next_column != null && temp.next_column != null) {
//                current_row.next_row = temp;
//                temp = temp.next_column;
//                current_row = current_row.next_column;
//            }
//        }
    }
    //----------------------------------------------------------------

    public boolean search(int data, Node current) {
        // searching at linkedList whit current header that is given
        head = current;
        while (current != null) {
            if (current.data == data) {
                return true;
            } else {
                current = current.next_row;
            }
        }
        return false;
    }

    //------------------------------------------------------------------
    public void update(int data, int index_column) {
        Node temp;
        Node privies = head;
        Node next;
        // Previous stays Previous position of target
        for (int i = 1; i < index_column; i++) {
            privies = privies.next_row;
        }
        // to update
        privies.next_row.data = data;
    }

    public void print() {

    }
}