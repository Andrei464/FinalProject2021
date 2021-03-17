package ia;

import java.util.LinkedList;
/**
* Data.java - 
*
* @author Andrei S. 
* @param <Object> 
* @since Mar. 2, 2021 
*/
public class DataLinkedList<Object> extends LinkedList<LinkedList<Object>> {
    
    
    public DataLinkedList(){
//        LinkedList<LinkedList<String>> data = this;
//        LinkedList<String> strings1 = new LinkedList<>();
//        for (int j = 0; j < 10; j++) {
//            strings1.addLast((String) ("1_word " + j));
//        }
//        LinkedList<String> strings2 = new LinkedList<>();
//        for (int j = 0; j < 10; j++) {
//            strings2.addLast((String) ("2_word " + j));
//        }
//        LinkedList<String> strings3 = new LinkedList<>();
//        for (int j = 0; j < 10; j++) {
//            strings3.addLast((String) ("3_word " + j));
//        }
//        LinkedList<String> strings4 = new LinkedList<>();
//        for (int j = 0; j < 10; j++) {
//            strings4.addLast((String) ("4_word " + j));
//        }
//        data.add(strings1);
//        data.add(strings2);
//        data.add(strings3);
//        data.add(strings4);
//        System.out.println(data.size());
//        read2DList(data);
//        
//        
//        LinkedList<LinkedList<Object>> data = this;
//        LinkedList<String> strings1 = new LinkedList<>();
//        for (int j = 0; j < 10; j++) {
//            strings1.addLast("1_word " + j);
//        }
//        LinkedList<String> strings2 = new LinkedList<>();
//        for (int j = 0; j < 10; j++) {
//            strings2.addLast("2_word " + j);
//        }
//        LinkedList<String> strings3 = new LinkedList<>();
//        for (int j = 0; j < 10; j++) {
//            strings3.addLast("3_word " + j);
//        }
//        LinkedList<String> strings4 = new LinkedList<>();
//        for (int j = 0; j < 10; j++) {
//            strings4.addLast("4_word " + j);
//        }
//        data.add((LinkedList<Object>) strings1);
//        data.add((LinkedList<Object>) strings2);
//        data.add((LinkedList<Object>) strings3);
//        data.add((LinkedList<Object>) strings4);
//        read2DList(data);
        int size = 7;

        LinkedList<LinkedList<Object>> data = this;
        LinkedList<String> list1 = new LinkedList<>();
        for (int j = 0; j < size; j++) {
            list1.addLast("1_word " + j);
        }
        LinkedList<Integer> list2 = new LinkedList<>();
        for (int j = 0; j < size; j++) {
            list2.addLast(j);
        }
        LinkedList<Double> list3 = new LinkedList<>();
        for (int j = 0; j < size; j++) {
            double number = .7812749169;
            list3.addLast(number*j);
        }
        LinkedList<Character> list4 = new LinkedList<>();
        for (int j = 0; j < size; j++) {
            list4.addLast('h');
        }
        
        LinkedList<String> list5 = new LinkedList<>();
        
        data.add((LinkedList<Object>) list1);
        data.add((LinkedList<Object>) list2);
        data.add((LinkedList<Object>) list3);
        data.add((LinkedList<Object>) list4);
        read2DList(data);
        LinkedList sampleList = data.get(2);
        read1DList(sampleList);
    }
    
    private void read1DList(LinkedList list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    
    private void read2DList(LinkedList list){
        for (int i = 0; i < list.size(); i++) {
            LinkedList second = (LinkedList) list.get(i);
            System.out.println("\nList " + i);
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||");
            for (int j = 0; j < second.size(); j++) {
                System.out.println(second.get(j));
            }
        }
    }
}
