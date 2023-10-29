import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        Matrix matrix = null;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("M(10,5).csv"));
            int numberOfLines = (int) Files.lines(Path.of("M(10,5).csv")).count();
            if ((line = bf.readLine()) != null) {
                String[] row = line.split(",");
                matrix = new Matrix(row.length, (int) numberOfLines);
            }
            int lineNumber = 0;
            while (line != null) {
                String[] row = line.split(",");
                for (int i = 0; i < row.length; i++) {
                    if (Integer.parseInt(row[i]) != 0) {
                        matrix.add(Integer.parseInt(row[i]), lineNumber, i);
                    }
                }
                line = bf.readLine();
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //----------------------------------------------------------
        int function = 1;
        while (function != 7) {
            System.out.println("1- add node ");
            System.out.println("2- delete node");
            System.out.println("3- search node");
            System.out.println("4- update ");
            System.out.println("5- compact matrix");
            System.out.println("6- The initial matrix");
            System.out.println("7- save");
            function = sc.nextInt();

            //----------------------------------------------------
            //----------------------------------------------------

            if (function == 1) {
                System.out.println(" data && index_row && index_column");
                int data = sc.nextInt();
                int index_row = sc.nextInt();
                int index_column = sc.nextInt();
                assert matrix != null;
                matrix.add(data, index_row, index_column);
            } else if (function == 2) {
                System.out.println(" data && index_row && index_column");
                int index_row = sc.nextInt();
                int index_column = sc.nextInt();
                int data = sc.nextInt();
                assert matrix != null;
                matrix.add(data, index_row, index_column);
            } else if (function == 3) {
                System.out.println(" Enter data");
                int data = sc.nextInt();
                assert matrix != null;
                matrix.search(data);
            } else if (function == 4) {
                System.out.println(" data && index_row && index_column");
                int index_row = sc.nextInt();
                int index_column = sc.nextInt();
                int data = sc.nextInt();
                assert matrix != null;
                matrix.update(data, index_row, index_column);
            } else if (function == 5) {
                assert matrix != null;
                matrix.print_complex();
            } else if(function==6) {
                assert matrix != null;
                matrix.print_matrix();
            }
            else if(function==7){
                assert matrix != null;
                matrix.save();
            }
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

            while (current.getIndex_column() < index_column - 1 && current.next_column != null && current.next_column.getIndex_column() < index_column) {
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

            while (current.getIndex_row() < index_row - 1 && current.next_row != null) {
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
        }
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
                for (int j = 0; j < pir.getIndex_column(); j++) {
                    System.out.print("0" + " ");
                }
                System.out.print(pir.getData() + " ");
                while (next != null) {
                    for (int j = 0; j < next.getIndex_column() - pir.getIndex_column() - 1; j++) {
                        System.out.print("0" + " ");
                    }
                    if (next != pir)
                        System.out.print("" + next.getData() + " ");
                    // pir = pir.next_column;
                    if (next.next_column == null) {
                        for (int j = next.getIndex_column() + 1; j < header_column.length; j++) {
                            System.out.print("0" + " ");
                        }
                        next = next.next_column;
                    } else {
                        next = next.next_column;
                        pir = pir.next_column;
                    }
                }
                if (row.head.next_column == null) {
                    for (int j = pir.getIndex_column() + 1; j < header_column.length; j++) {
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

    public void save() {
        File file = new File("M(10,5).csv");
        try {
            FileWriter csv = new FileWriter(file);
            PrintWriter writer = new PrintWriter(csv);
            for (int i = 0; i < header_row.length; i++) {
                if (header_row[i] == null) {
                    for (int j = 0; j < header_column.length; j++) {
                        writer.write(0 + "");
                    }

                } else {
                    row.head = header_row[i];
                    row.save(writer, header_column.length);
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            ;
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

    public void save(PrintWriter writer, int sizeOfColumn) {
        DoublyLinkedList.Node pointer = head;
        for (int i = 0; i < sizeOfColumn; i++) {
            if (pointer.getIndex_column() == i) {
                writer.write(pointer.getData() + ",");
                pointer = pointer.next_column;
                if (pointer == null) {
                    for (int j = i + 1; j > sizeOfColumn; j++) {
                        writer.write(0 + ",");
                    }
                    return;
                }
            } else {
                writer.write(0 + ",");
            }
        }
    }
}