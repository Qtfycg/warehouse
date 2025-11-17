// File: TwoSum/src/main/java/TwoSum.java
import java.util.Scanner;

public class TwoSum {
    /*
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回它们的数组下标。
     * */

    public static void main(String[] args) {
        System.out.println("请输入整数数组（以空格分隔）：");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().trim().split("\\s+");
        int[] nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        System.out.println("请输入目标整数：");
        int target = scanner.nextInt();

        int[] result = new TwoSum().twoSum(nums, target);
        if (result[0] == -1) {
            System.out.println("没有找到满足条件的两个数。");
        } else {
            System.out.printf("找到索引: %d, %d%n", result[0], result[1]);
        }
        scanner.close();
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

}
