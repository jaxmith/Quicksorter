package cse214hw5;

//Jack Smith 110366081

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Laptop {

    private String brand;
    private double procSpeed;
    private int memory;
    private int hdd;
    public static intComparator<Integer> intComparator = new intComparator<Integer>();
    public static brandComparator<Laptop> brandComparator = new brandComparator<Laptop>();
    public static procComparator<Laptop> procComparator = new procComparator<Laptop>();
    public static memoryComparator<Laptop> memoryComparator = new memoryComparator<Laptop>();
    public static hddComparator<Laptop> hddComparator = new hddComparator<Laptop>();

    public Laptop(String s, double d, int i1, int i2) {
        brand = s;
        procSpeed = d;
        memory = i1;
        hdd = i2;
    }

    public String getBrand() {
        return brand;
    }

    public double getProcSpeed() {
        return procSpeed;
    }

    public int getMemory() {
        return memory;
    }

    public int getHDD() {
        return hdd;
    }

    @Override
    public String toString() {
        return (brand + ": " + procSpeed + " processor, " + memory + " GB RAM, " + hdd + " GB HDD");
    }

    public static class intComparator<E> implements Comparator<E> {

        @Override
        public int compare(Object o1, Object o2) {
            if ((Integer) o1 > (Integer) o2) {
                return 1;
            } else if (((Integer) o1).equals((Integer) o2)) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static class brandComparator<E> implements Comparator<E> {

        @Override
        public int compare(Object o1, Object o2) {
            String s1 = ((Laptop) o1).getBrand();
            String s2 = ((Laptop) o2).getBrand();
            return s1.compareTo(s2);
        }
    }

    public static class procComparator<E> implements Comparator<E> {

        @Override
        public int compare(Object o1, Object o2) {
            double d1 = ((Laptop) o1).getProcSpeed();
            double d2 = ((Laptop) o2).getProcSpeed();
            if (d1 > d2) {
                return 1;
            } else if (d1 == d2) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static class hddComparator<E> implements Comparator<E> {

        @Override
        public int compare(Object o1, Object o2) {
            int i1 = ((Laptop) o1).getHDD();
            int i2 = ((Laptop) o2).getHDD();
            if (i1 > i2) {
                return 1;
            } else if (i1 == i2) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static class memoryComparator<E> implements Comparator<E> {

        @Override
        public int compare(Object o1, Object o2) {
            int i1 = ((Laptop) o1).getMemory();
            int i2 = ((Laptop) o2).getMemory();
            if (i1 > i2) {
                return 1;
            } else if (i1 == i2) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    private static ArrayList<Integer> readIntegerInputs(Scanner scanner) {
        ArrayList<Integer> list = new ArrayList<>();
        String input = "";
        do {
            input = scanner.nextLine();
            if (!input.equals("end")) {
                try{
                    list.add(Integer.parseInt(input));
                }catch(Exception e){
                    System.out.println("Incorrect input. Enter 1 number per line.");
                }
            }
        } while (!input.equals("end"));
        return list;
    }

    private static String getStringJoinedBy(ArrayList list, String s) {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                result += list.get(i) + s;
            } else {
                result += list.get(i);
            }
        }
        return result;
    }

    private static ArrayList<Laptop> readLaptopInputs(Scanner scanner) {
        ArrayList<Laptop> list = new ArrayList<>();
        String input = "";
        do {
            input = scanner.nextLine();
            if (!input.equals("end")) {
                try{
                    String[] laptop = input.split(",");
                    list.add(new Laptop(laptop[0], Double.parseDouble(laptop[1]), Integer.parseInt(laptop[2]), Integer.parseInt(laptop[3])));
                }catch(Exception e){
                    System.out.println("Incorrect input. Enter in the format 'brand,processor-speed,memory,hard-disk-capacity'");
                }
            }
        } while (!input.equals("end"));
        return list;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input data type (must be 'int' or 'laptop'):");
        String inputDataType = scanner.nextLine().trim();
        if (!inputDataType.equals("int") && !inputDataType.equals("laptop")) {
            throw new IllegalArgumentException("Invalid data type specified.");
        }
        switch (inputDataType) {
            case "int":
                System.out.println("Provide integers (one per line) [type 'end' to finish list]:");
                ArrayList<Integer> integers = readIntegerInputs(scanner);
                Sorter<Integer> intSorter = new Quicksorter<>(intComparator, integers);
                intSorter.sort();
                System.out.println("Sorted output: " + getStringJoinedBy(integers, ", "));
                break;
            case "laptop":
                System.out.println("Provide laptops (one per line) in the format 'brand,processor-speed,memory,hard-disk-capacity' [type 'end' to finish list]:");
                ArrayList<Laptop> laptops = readLaptopInputs(scanner);
                Sorter<Laptop> laptopSorter = new Quicksorter<>(brandComparator, laptops);
                laptopSorter.sort();
                System.out.print("Sorted by brand name:\n\t");
                System.out.println(getStringJoinedBy(laptops, "\n\t"));
                System.out.println();
                laptopSorter.setComparator(procComparator);
                laptopSorter.sort();
                System.out.print("Sorted by processor speed:\n\t");
                System.out.println(getStringJoinedBy(laptops, "\n\t"));
                System.out.println();
                laptopSorter.setComparator(memoryComparator);
                laptopSorter.sort();
                System.out.print("Sorted by RAM:\n\t");
                System.out.println(getStringJoinedBy(laptops, "\n\t"));
                System.out.println();
                laptopSorter.setComparator(hddComparator);
                laptopSorter.sort();
                System.out.print("Sorted by hard disk capacity:\n\t");
                System.out.println(getStringJoinedBy(laptops, "\n\t"));
                break;
            default:
                throw new IllegalArgumentException("Invalid data type specified.");
        }

    }

}
