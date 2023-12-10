package com.connor.java;

import java.util.Arrays;
import java.util.List;

public class IntervieBak {

    static class Order{
        private Character type;
        private int[] nums;

        public Character getType() {
            return type;
        }

        public void setType(Character type) {
            this.type = type;
        }

        public int[] getNums() {
            return nums;
        }

        public void setNums(int[] nums) {
            this.nums = nums;
        }

        public Order(Character type, int[] nums) {
            this.type = type;
            this.nums = nums;
        }
    }
    static class SalesException extends  Exception{
        public SalesException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws SalesException {
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order('Q', new int[]{1, 6}));
//        orders.add(new Order('U', new int[]{2, 6}));
//        orders.add(new Order('U', new int[]{4, 3}));
//        orders.add(new Order('Q', new int[]{2, 4}));
//
//        calSales(6,8,new int[]{1, 2, 3, 4, 5, 6}, orders);


        int[] params = new int[]{4,5,6,1,2};//new int[]{3, 2, 5, 8, 1};
        quickSort(params);
        System.out.println(Arrays.toString(params));
    }

    public static void calSales(int goodNums, int orderNums, int[] goods, List<Order> orders) throws SalesException {
        if (goods.length == 0 || orders.isEmpty()){
            return;
        }


        // 计算指令
        for (Order order : orders) {
            if (order.getNums().length != 2){
                throw new SalesException("orders 指令错误,请检查nums长度.");
            }

            if (order.type == 'Q'){
                // goods下标的平均值
                int startIndex  = order.getNums()[0] - 1;
                int endIndex  = order.getNums()[1] - 1;
                int total = endIndex - startIndex + 1;
                if (startIndex < 0 || endIndex > goodNums - 1 || startIndex > endIndex){
                    throw new SalesException("order Q index outbound.");
                }
                int result = 0;
                for (; startIndex <= endIndex; startIndex++) {
                    result += goods[startIndex];
                }
                System.out.println(result / total);
            } else if (order.type == 'U'){
                int uIndex  = order.getNums()[0] - 1;
                if (uIndex < 0 || uIndex > goodNums - 1){
                    throw new SalesException("order Q index outbound.");
                }

                int uNums = order.getNums()[1];
                goods[uIndex] += uNums;

            } else {
                throw new SalesException("unsupport order");
            }
        }

    }

//    public static void main(String[] args) {
//        int[] params = new int[]{3, 2, 5, 8, 1};
//        quickSort(params);
//
//        // 打印param
//        for (int i = 0; i < params.length; i++) {
//            System.out.println(params[i]);
//        }
//
//    }

    private static void quickSort(int[] params){
        if (params.length <= 1){
            return;
        }
        doQuickSort(params, 0, params.length-1);
    }

    private static void doQuickSort(int[] params, int left, int right){
        if (left >= right){
            return;
        }

        // 选了左边的基准,要从右边开始找.
        int pix = params[left]; //中心比较点
        int pixIndex = left;
        int oRight = right;

        while (left < right){
            // 从右边开始找比pix小的数
            while (left < right && pix <= params[right]){
                // 当前数比pix大,right--
                right--;
            }

            // 从左边开始找比pix大的数
            while (left < right && pix >= params[left]){
                // 当前数比pix小,left++
                left++;
            }

            // 交换
            if (left != right){
                swap(params, left, right);
            }
        }

        //pix移动到中心
        swap(params, left, pixIndex);

        // 左部排序
        doQuickSort(params, pixIndex, left - 1);
        // 右部排序
        doQuickSort(params, left + 1, oRight);
    }

    private static void swap(int[] params, int left, int right) {
        int temp = params[left];
        params[left] = params[right];
        params[right] = temp;
    }
}
