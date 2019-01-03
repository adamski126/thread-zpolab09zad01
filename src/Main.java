import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class Main {
    private static String[] strings = {"aaaa", "bb", "ccccccccccccc", "dddddd"};
    //private static String[] strings = {"aaaa","bb","cccc","ddddddddd"};
    private static volatile Integer index = 0;
    private static Map<Integer, Integer> indexMap = new HashMap<>();

    private static Integer temp;

    //0,1
    //1,2
    //2,0

    public static void changeIndex(Integer givenIndex, Integer nextIndex){
        indexMap.forEach((k,v)->{
            if(v == givenIndex) temp = k;
        });
        indexMap.replace(temp,nextIndex);
    }

    public static void main(String[] args) {

        indexMap.put(0,1);
        indexMap.put(1,2);
        indexMap.put(2,3);
        indexMap.put(3,0);


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                String myString = strings[0];
                Integer myIndex = 0;
                Integer nextIndex = 1;
                for(int i=0; i<myString.length(); i++){
                    while(index != myIndex){}
                    System.out.print(myString.charAt(i));

                    if(myString.length()-i == 1){
                        changeIndex(myIndex,nextIndex);
                    }

                        nextIndex = indexMap.get(myIndex);
                        index = nextIndex;



                }
                indexMap.remove(myIndex);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String myString = strings[1];
                Integer myIndex = 1;
                Integer nextIndex = 2;
                for(int i=0; i<myString.length(); i++){

                    while(index != myIndex){}
                    System.out.print(myString.charAt(i));

                    if(myString.length()-i == 1){
                        changeIndex(myIndex,nextIndex);
                    }

                        nextIndex = indexMap.get(myIndex);
                        index = nextIndex;


                }

                //changeIndex(myIndex,nextIndex);
                indexMap.remove(myIndex);
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                String myString = strings[2];
                Integer myIndex = 2;
                Integer nextIndex = 3;
                for(int i=0; i<myString.length(); i++){

                    while(index != myIndex){}
                    System.out.print(myString.charAt(i));

                    if(myString.length()-i == 1){
                        changeIndex(myIndex,nextIndex);
                    }

                        nextIndex = indexMap.get(myIndex);
                        index = nextIndex;


                }
                //changeIndex(myIndex,nextIndex);
                indexMap.remove(myIndex);
            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                String myString = strings[3];
                Integer myIndex = 3;
                Integer nextIndex = 0;
                for(int i=0; i<myString.length(); i++){

                    while(index != myIndex){}
                    System.out.print(myString.charAt(i));

                    if(myString.length()-i == 1){
                        changeIndex(myIndex,nextIndex);
                    }

                        nextIndex = indexMap.get(myIndex);
                        index = nextIndex;


                }
                //changeIndex(myIndex,nextIndex);
                indexMap.remove(myIndex);
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();


    }

}