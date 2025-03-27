package com.qtfycg;



/*
*给定一个整数数组nums和一个整数目标值target
*请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。
*你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
*你可以按任意顺序返回答案。
* */

public class twoSum {
    public static void main(String[] args) {
        int[] nums ={9,6,7,2,1,4,};
        int target = 5;
        for(int i = 0; i < nums.length;i++){
            for(int j = i+1; j < nums.length;j++){
                if(nums[i] + nums[j] == target){
                    System.out.println("["+i+","+j+"]");
                }
            }
        }
    }
}
