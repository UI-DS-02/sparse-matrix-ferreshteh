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


        matrix.add(3, 1, 5);

        matrix.add(2, 2, 1);

        matrix.add(4, 1, 1);

        matrix.add(1, 0, 1);
//            matrix.addRow(4, 1, 0);
//            matrix.addRow(6, 1, 2);

        matrix.add(7, 0, 0);

        matrix.add(8, 1, 7);

        matrix.add(11, 2, 2);
        matrix.add(15,3,0);

        matrix.add(18,4,2);
        matrix.add(19,4,3);
        matrix.add(20,4,4);
//            matrix.addRow(5, 1, 1);
//            matrix.addRow(10,2,2);
        //-------------------------------------------------------
//        matrix.add(3, 1, 5);
//        matrix.add(2, 2, 1);
//        matrix.add(4, 1, 1);
//        matrix.add(1, 0, 1);
//            matrix.addRow(4, 1, 0);
//            matrix.addRow(6, 1, 2);
//        matrix.add(7, 0, 0);
//        matrix.add(8, 1, 7);
//        matrix.add(11, 2, 2);

        //---------------------------------------------------------
//        matrix.addColumn(1, 0, 0);
//        matrix.addColumn(2, 0, 1);
//        matrix.addColumn(3, 0, 2);
//        matrix.addColumn(4, 1, 0);
//        matrix.addColumn(6, 1, 2);
//        matrix.addColumn(7, 2, 0);
//        matrix.addColumn(8, 2, 1);
//        matrix.addColumn(9, 2, 2);
//        matrix.addColumn(5, 1, 1);
//        matrix.addColumn(10, 2, 2);
        //-------------------------------------------------------
        // matrix.deleteRow(0, 0);
        //matrix.deleteColumn(0, 0);
//        System.out.println("7" + matrix.search(7));
//        System.out.println("4" + matrix.search(4));
//        System.out.println("3" + matrix.search(3));
//        System.out.println("11" + matrix.search(11));
        //   matrix.update(15, 1, 5);
        matrix.print_matrix();

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

    public void add(int value, int index_row, int index_column) {
        DoublyLinkedList.Node node = new DoublyLinkedList.Node(value, index_row, index_column);
        row.head = header_row[index_row];
        if (row.head == null) {
            row.head = node;
            header_row[index_row] = node;
            row.tail = node;
            // row.tail.setIndex_row(index_column);
        } else if (row.head.getIndex_column() > index_column) {
            node.next_column = row.head;
            row.head = node;
            header_row[index_row] = node;
        } else {
            DoublyLinkedList.Node current = row.head;

            while (current.getIndex_column() < index_column - 1 && current.next_column != null && current.next_column.getIndex_column() < index_column - 1) {
                current = current.next_column;
            }
            if (current.next_column != null) {
                DoublyLinkedList.Node next = current.next_column;
                node.next_column = next;
            }
            current.next_column = node;
            row.tail = node;
            //row.tail.setIndex_column(index_column);
        }
        // adding column
        column.head = header_column[index_column];
        if (column.head == null) {
            column.head = node;
            header_column[index_column] = node;
            column.tail = node;
            // column.tail.setIndex_row(index_row);
        } else if (column.head.getIndex_row() > index_row) {
            node.next_row = column.head;
            column.head = node;
            header_column[index_column] = node;
        } else {
            DoublyLinkedList.Node current = column.head;

            while (current.getIndex_row() < index_row - 1&& current.next_row!=null) {
                current = current.next_row;
            }
            if (current.next_row != null) {
                DoublyLinkedList.Node next = current.next_row;
                //DoublyLinkedList.Node nextColumn=current.next_column;
                node.next_row = next;
                //next.next_column=nextColumn;
            }
            current.next_row = node;
            column.tail = node;
            // column.tail.setIndex_row(index_row);
        }
//        if (index_row != 0) {
//            row.head = header_row[index_row - 1];
//            DoublyLinkedList.Node next;
//            DoublyLinkedList.Node current = row.head;
//            while (current != null && node != null) {
//                if (current.getIndex_column() == node.getIndex_column()) {
//                    current.next_row = node;
//                    current = current.next_column;
//                    node = node.next_column;
//                    if (node != null)
//                        node.setIndex_column(node.getIndex_column() + 1);
//                } else {
//                    current = current.next_column;
//                }
//            }
//        }
        //  DoublyLinkedList.Node temp=node;
//        for(int i=index_row+1;i<header_row.length;i++){
//            if(header_row[i]!=null){
//                row.head=header_row[i];
//                DoublyLinkedList.Node current = column.head;
//                while (current != null && temp != null) {
//                    if (current.getIndex_column() == temp.getIndex_column()) {
//                        temp.next_row=current;
//                        current = current.next_column;
//                        temp = temp.next_column;
////                        if (temp != null)
////                            temp.setIndex_row(temp.getIndex_row() + 1);
//                    } else {
//                        current = current.next_column;
//                    }
//                }
//            }
//        }
    }

    public void addColumn(int value, int index_row, int index_column) {
        DoublyLinkedList.Node node = new DoublyLinkedList.Node(value, index_row, index_column);

//        DoublyLinkedList.Node temp=node;
//        if (index_column != 0) {
//            column.head = header_column[index_column - 1];
//            DoublyLinkedList.Node current = column.head;
//            while (current != null && node != null) {
//                if (current.getIndex_row() == node.getIndex_row()) {
//                    current.next_column = node;
//                    current = current.next_row;
//                    node = node.next_row;
//                    if (node != null)
//                        node.setIndex_row(node.getIndex_row() + 1);
//                } else {
//                    current = current.next_row;
//                }
//            }
//        }
//        for(int i=index_column+1;i<header_column.length;i++){
//            if(header_column[i]!=null){
//                column.head=header_column[i];
//                DoublyLinkedList.Node current = column.head;
//                while (current != null && temp != null) {
//                    if (current.getIndex_row() == temp.getIndex_row()) {
//                        temp.next_column=current;
//                        current = current.next_row;
//                        temp = temp.next_row;
////                        if (temp != null)
////                            temp.setIndex_row(temp.getIndex_row() + 1);
//                    } else {
//                        current = current.next_row;
//                    }
//                }
//            }
//        }

    }

    public void deleteRow(int index_row, int index_column) {
        row.head = header_row[index_row];
        DoublyLinkedList.Node current = row.head;
        if (row.head.getIndex_column() == index_column) {
            if (row.head.next_column != null) {
                row.head = row.head.next_column;
                header_row[index_row] = row.head;
            } else {
                header_row[index_row] = null;
            }
        } else {
            while (current.getIndex_column() < index_column - 1 && current.next_column.getIndex_column() < index_column - 1) {
                current = current.next_column;
            }
            DoublyLinkedList.Node next = current.next_column.next_column;
            current.next_column = next;

        }
    }

    public void deleteColumn(int index_row, int index_column) {
        column.head = header_column[index_column];
        DoublyLinkedList.Node current = column.head;
        if (column.head.getIndex_row() == index_row) {
            if (column.head.next_row != null) {
                column.head = column.head.next_row;
                header_column[index_column] = column.head;
            } else {
                header_column[index_column] = null;
            }
        } else {
            while (current.getIndex_row() < index_row - 1 && current.next_row.getIndex_row() < index_row - 1) {
                current = current.next_row;
            }
            DoublyLinkedList.Node next = current.next_row.next_row;
            current.next_row = next;
        }
    }

    public boolean search(int data) {
        boolean find = false;
        // searching at row linkedList
        for (int i = 0; i < header_row.length; i++) {
            if (header_row[i] != null) {
                DoublyLinkedList.Node current = header_row[i];
                while (current != null) {
                    if (current.getData() == data) {
                        return true;
                    } else {
                        current = current.next_column;
                    }
                }
            }
        }
        return false;
    }

    //--------------------------------------------------
    public void update(int data, int index_row, int index_column) {
        row.head = header_row[index_row];
        DoublyLinkedList.Node current = row.head;

        while (current.getIndex_column() < index_column && current.next_column != null) {
            current = current.next_column;
        }
        current.setData(data);

        // for column
        column.head = header_column[index_column];
        current = column.head;

        while (current.getIndex_row() < index_row && current.next_row != null) {
            current = current.next_row;
        }
        current.setData(data);

    }

    public void print_complex() {
        for (int i = 0; i < header_row.length; i++) {
            row.head = header_row[i];
            DoublyLinkedList.Node current = row.head;
            while (current != null) {
                System.out.println(current.getIndex_row() + "  " + current.getIndex_column() + "  " + current.getData());
                current = current.next_column;
            }

        }
    }

    public void print_matrix() {
        int len;
        for (int i = 0; i < header_row.length; i++) {
            row.head = header_row[i];
            if (row.head != null) {
                DoublyLinkedList.Node pir = row.head;
                DoublyLinkedList.Node next = row.head.next_column;
                for (int j = 0; j < pir.getIndex_column() ; j++) {
                    System.out.print("0" + " ");
                }
                System.out.print(pir.getData() + " ");
                while (next != null) {
                    for (int j = 0; j < next.getIndex_column() - pir.getIndex_column() - 1; j++) {
                        System.out.print("0" + " ");
                    }
                    if(next!=pir)
                        System.out.print(""+next.getData() + " ");
                   // pir = pir.next_column;
                    if (next.next_column == null) {
                        for (int j = next.getIndex_column()+1; j < header_column.length ; j++) {
                            System.out.print("0" + " ");
                        }
                        next = next.next_column;
                    } else {
                        next = next.next_column;
                        pir=pir.next_column;
                    }
                }
                if(row.head.next_column==null){
                    for (int j = pir.getIndex_column()+1; j < header_column.length ; j++) {
                        System.out.print("0" + " ");
                    }
                }

            } else {
                for (int j = 0; j < header_column.length; j++) {
                    System.out.print("0" + " ");
                }
            }
            System.out.print("\n");
        }
    }
}


class DoublyLinkedList {

    static class Node {
        private int data;
        private int index_row;
        private int index_column;
        Node next_row;
        Node next_column;

        public Node(int item, int index_row, int index_column) {
            this.data = item;
            this.index_row = index_row;
            this.index_column = index_column;
        }

        public int getIndex_row() {
            return index_row;
        }

        public void setIndex_row(int index_row) {
            this.index_row = index_row;
        }

        public int getIndex_column() {
            return index_column;
        }

        public void setIndex_column(int index_column) {
            this.index_column = index_column;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    Node head, tail = null;

    //---------------------------------------------------------------------------
    public void delete(int data, int column) {
        Node temp;
        Node privies = head;
        Node next;
        // Previous stays Previous position of target
        for (int i = 1; i < column; i++) {
            privies = privies.next_row;
        }
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