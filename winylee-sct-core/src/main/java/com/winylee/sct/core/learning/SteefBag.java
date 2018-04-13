package com.winylee.sct.core.learning;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、有 N 件物品和一个容量为 V 的背包。第 i 件物品的费用（认为是容量之类的）是 w[i]，价值是 p[i]。
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 *
 * 2、有N种物品和一个容量为V的背包，每种物品都有无限件可用。第i种物品的费用是c（认为是容量之类的）[i]，价值是w[i]。
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 */
public class SteefBag {

    public static int count = 0;
    public static Integer[][] valueA = new Integer[100][100];

    /**
     * 1、
     * 2、for(){
     *     max(Contant(i),notC(i))
     * }
     *
     */
    public  int maxValue(List<SteelProduct> allList ,int index ,int weight) {
        if(allList.size() == 0){
            return 0;
        }
        List<SteelProduct> list = new ArrayList<>();
        list.addAll(allList);
        int value = 0 ;

        while(index < list.size()){
            if(++count > 10000){
                System.out.println("error");
                break;
            }
            SteelProduct product = list.get(index);
            if(product.getWeight()  > weight){
                index++;
                continue;
            }
            int rindex = index +1 ;
            //问题一的答案，商品数量唯一
            //解题思路，包含某一个商品的最大价值，和不包含该商品的最大价值进行对比
            int containtV = value + product.getValue() + maxValue(list, rindex,weight - product.getWeight());
            int notContaintV =  maxValue(list,rindex, weight);
            //问题二的答案：商品数量无限
//            int containtV = value + product.getValue() + maxValue(list, index,weight - product.getWeight());
//            int notContaintV =  maxValue(list,rindex, weight);

            int thisValue = Math.max(containtV,notContaintV);
            value = Math.max(value,thisValue);
            //不必循环，应为子问题已经计算了各种组合
            break;
        }
        return value;
    }

    public static void main(String[] args) {
        SteefBag steefBag = new SteefBag();
        List<SteelProduct> allList = new ArrayList<>();
        SteelProduct steelProduct = steefBag.getSteelProduct();
        steelProduct.setValue(1);
        steelProduct.setWeight(4);
        allList.add(steelProduct);

        SteelProduct steelProduct1 = steefBag.getSteelProduct();
        steelProduct1.setValue(4);
        steelProduct1.setWeight(4);
        allList.add(steelProduct1);

        SteelProduct steelProduct2 = steefBag.getSteelProduct();
        steelProduct2.setValue(4);
        steelProduct2.setWeight(4);
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

        int value = steefBag.maxValue(allList,0,34);
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
    private class Node{
        private Node next;
        private Node extra;

        public Node deepCopy(Node node){
            copyNext1(node);
            //还原旧链，生成新链
            Node newNode = node.next;
            Node temp = node;
            boolean newNodeFlg = false;
            //先让extra指向正确的 newNode, 应为extra可能指向前面的node，所以要单独的先设置。
            while (temp!= null){
                if(newNodeFlg){
                    newNodeFlg = false;
                    node.extra = node.extra.next ;
                    continue;
                }
                temp = node.next;
                newNodeFlg = true;
            }
            Node temp1 = node;
            while (temp1!= null){
                node.next = node.next.next;
                temp1 = node.next;
            }
            return newNode;
        }

        private Node copyNext1(Node node){
            if(node == null){
                return null;
            }
            Node newNode = new Node();
            //指向旧表的extra
            newNode.extra = node.extra;
            if (node.next != null){
                newNode.next = node.next;
                //new 放在node下一个...
                node.next = newNode;
                copyNext1(node.next);
            }
            return newNode;
        }
    }
}
