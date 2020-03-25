import com.pwsmart.entity.mes.pd.design.PdDesign;
import com.pwsmart.service.mes.pd.design.PdDesignService;
import java8Test.Apple;
import java8Test.AppleAsc;
import org.apache.xerces.util.SynchronizedSymbolTable;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class test{
    public static String sorting(List<Integer> Arr){
        Arrays.sort(new List[]{Arr});
        int key = 0;
        String result = "";
        for (int i = 0; i < Arr.size(); i++) {
            if ((i + 1 != Arr.size()) && (Arr.get(i) + 1 == Arr.get(i + 1))) {
                if (key == 0) {
                    result += Arr.get(i);
                    key++;
                }
            } else if ((i + 1 == Arr.size())) {
                if (Arr.size() == 1) {
                    //只有一个数时
                    result += Arr.get(i);
                } else if (Arr.get(i - 1) + 1 == Arr.get(i)) {
                    //抹尾数判断
                    result += "-" + Arr.get(i) + ",";
                } else {
                    result += Arr.get(i);
                }
            } else {
                if (key != 0) {
                    result += "-" + Arr.get(i) + ",";
                } else {
                    result += Arr.get(i) + ",";
                }
                key = 0;
            }
        }

        return result;
    }
    @Autowired
    private  PdDesignService PdDesignService;
    private static PdDesignService pdDesignService;
    private static String firstDay;
    public @PostConstruct void init(){
        pdDesignService=PdDesignService;
    }
    public static void main(String[] args) {
        //获取年月日

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNow = sdf.format(new Date());
        System.out.println(dateNow.substring(0,4)+"年"+dateNow.substring(5,7)+"月"+dateNow.substring(8,10)+"日");
        //1.获取double型的intvalue,1.20->1.2
        int aa =6%3;
        int bb=6/3;
        Double num1=1.20400;
        num1.intValue();
        //2.切割，数组->list
        String waferStr="C-1945-0410-00001";
        String[] wafer= waferStr.split("-");
        List<String> waferLsit= new ArrayList<>(Arrays.asList(wafer));
        List<Integer> waferLot=new ArrayList<>();
        waferLot.add(1);
        waferLot.add(2);
        waferLot.add(3);
        waferLot.add(5);
        waferLot.add(7);
        waferLot.add(8);
        String res=sorting(waferLot);
        int key = 0;
        String beg = "";
        System.out.println(beg);
        //sort
        AppleAsc appleAsc=new AppleAsc("green",155,1);
        AppleAsc appleAscb=new AppleAsc("blue",165,3);
        AppleAsc appleAscy=new AppleAsc("yellow",145,2);
        new AppleAsc("green",0.0,null);
        List<AppleAsc> appleAsc1 = new ArrayList<>(Arrays.asList(appleAsc,appleAscb,appleAscy));
        Collections.sort(appleAsc1);
        //比较stream 和 parallelStrea 的不同
        List<AppleAsc> ascList= appleAsc1.stream().filter((AppleAsc a) -> a.getWeight() > 160).collect(toList());
        List<AppleAsc> ascList1 = appleAsc1.stream().filter((AppleAsc a) -> a.getWeight() >160).collect(toList());
        System.out.println("输出对象信息");
        for (AppleAsc appleAsc2:appleAsc1){
            System.out.println(appleAsc2.toString());
        }
        Apple apple=new Apple();
        Apple apple1=new Apple();
        apple.setColor("green");
        apple.setWeight(155);
        apple1.setColor("blue");
        apple1.setWeight(145);
        List<Apple> apples =new ArrayList<>();
        apples.add(apple);
        apples.add(apple1);
        //Comparable<Apple> byWeight =(apple1,apple1)->apple1.getWeight().
        //apples.sort(Comparator.comparingDouble(Apple::getWeight));
        for (int i=1;i<4;i++){

        }

        apples.forEach(a-> {
            System.out.println(a.toString());

        });

        Collections.shuffle(apples);
        apples.forEach(a-> {
            System.out.println(a.toString());

        });
        List<Apple> appleList=filterApples(apples,Apple::isGreenApple);
        List<Apple> c=filterApples(apples,(Apple a) ->"green".equals(a.getColor()) &&a.getWeight()<160);
        List<Apple> heavyApples = apples.parallelStream().filter((Apple a)-> a.getWeight()>140).collect(toList());
        List<Apple> apples2 = new ArrayList<>();
        //java 8 学习
        double a=11.0;
        int ii=new Double(a).intValue();

        int k=26/13;
            String WaferCode="1-3,4,5-7";
            int waferNum=0;
            String desc="";
            String strings [ ] = WaferCode.split(",");//wafer片数
            if (strings.length>0){
                for (int i=0;i<strings.length;i++){
                    if (strings[i].contains("-")){
                        String strings1[] = strings[i].split("-");
                        waferNum = waferNum + (Integer.valueOf(strings1[1]) - Integer.valueOf(strings1[0]) +1) ;
                        for (int j=Integer.valueOf(strings1[0]);j<=Integer.valueOf(strings1[1]);j++){
                            desc = desc + String.valueOf(j)+",";
                        }
                    }else {
                        waferNum++;
                        desc = desc + strings[i]+",";
                    }
                }
            }


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//date->String
        //获取前月的第一天
        Calendar cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        PdDesign pdDesign=new PdDesign();
        pdDesign.setPddCode("PDD20020005");
        //pdDesignService.getEntity("f28c385293d94f9080cc718e59c0180d");
        firstDay = format.format(cal_1.getTime());
        System.out.println("-----1------firstDay:"+firstDay);
    }

    public interface Predicate<T>{
        boolean test(T t);
    }

    static List<Apple> filterApples(List<Apple> inventory,Predicate<Apple> p){
        List<Apple> result=new ArrayList<>();
        for (Apple apple : inventory){
            if (p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }


}
