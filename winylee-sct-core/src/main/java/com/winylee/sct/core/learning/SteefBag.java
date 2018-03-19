package com.winylee.sct.core.learning;

import java.util.ArrayList;
import java.util.List;

/**
 * 小偷偷 东西  商品 价值Vi,重 Wi, 包裹只有 Wn重
 */
public class SteefBag {

    public static int count = 0;

    /**
     * 1、
     * 2、for(){
     *     max(Contant(i),notC(i))
     * }
     *
     */
    public  int maxValue(List<SteelProduct> allList ,int weight,int currentV) {
        if(allList.size() == 0){
            return currentV;
        }
        List<SteelProduct> list = new ArrayList<>();
        list.addAll(allList);
        int i = 0;
        int value = 0 ;
        while(i < list.size()){
            if(++count > 100000){
                System.out.println("error");
                break;
            }
            SteelProduct product = list.get(i);
            if(product.getWeight()  > weight){
                i++;
                continue;
            }
            List<SteelProduct> list1 = list.subList(i+1,list.size());
            int containtV = maxValue(list1, weight - product.getWeight(),currentV + product.getValue());
            int notContaintV =  maxValue(list1, weight,currentV);
            int thisValue = Math.max(containtV,notContaintV);
            value = Math.max(value,thisValue);
            i++;
        }
        return value;
    }

    public static void main(String[] args) {
        SteefBag steefBag = new SteefBag();
        List<SteelProduct> allList = new ArrayList<>();
        SteelProduct steelProduct = steefBag.getSteelProduct();
        steelProduct.setValue(1);
        steelProduct.setWeight(3);
        allList.add(steelProduct);

        SteelProduct steelProduct1 = steefBag.getSteelProduct();
        steelProduct1.setValue(3);
        steelProduct1.setWeight(7);
        allList.add(steelProduct1);

        SteelProduct steelProduct2 = steefBag.getSteelProduct();
        steelProduct2.setValue(5);
        steelProduct2.setWeight(13);
        allList.add(steelProduct2);
         steelProduct2 = steefBag.getSteelProduct();
        steelProduct2.setValue(5);
        steelProduct2.setWeight(13);
        allList.add(steelProduct2);
         steelProduct2 = steefBag.getSteelProduct();
        steelProduct2.setValue(5);
        steelProduct2.setWeight(13);
        allList.add(steelProduct2);
         steelProduct2 = steefBag.getSteelProduct();
        steelProduct2.setValue(5);
        steelProduct2.setWeight(13);
        allList.add(steelProduct2);
         steelProduct2 = steefBag.getSteelProduct();
        steelProduct2.setValue(5);
        steelProduct2.setWeight(13);
        allList.add(steelProduct2);
         steelProduct2 = steefBag.getSteelProduct();
        steelProduct2.setValue(5);
        steelProduct2.setWeight(13);
        allList.add(steelProduct2);
         steelProduct2 = steefBag.getSteelProduct();
        steelProduct2.setValue(5);
        steelProduct2.setWeight(13);
        allList.add(steelProduct2);
         steelProduct2 = steefBag.getSteelProduct();
        steelProduct2.setValue(5);
        steelProduct2.setWeight(13);
        allList.add(steelProduct2);

        int value = steefBag.maxValue(allList,20,0);
        System.out.println(value);
        System.out.println(count);
    }

    public SteelProduct getSteelProduct(){
        return new SteelProduct();
    }

    public class SteelProduct{
        int value;
        int weight;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }



}
