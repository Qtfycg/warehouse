package com.qtfycg;
/*
给你一个长度为n的整数数组nums
请你构建一个长度为 2n 的答案数组 ans ，数组下标 从 0 开始计数 ，对于所有 0 <= i < n 的 i ，满足下述所有要求：
ans[i] == nums[i]
ans[i + n] == nums[i]
具体而言，ans 由两个 nums 数组 串联 形成。
返回数组 ans
*/
public class arrayConcatenation {
    public static void main(String[] args) {
        int[] nums ={9,8,5,6,1,2};
        int[] ans = new int[12];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[i+nums.length] = nums[i];
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i]+" ");
        }

    }
}
