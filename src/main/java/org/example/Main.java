package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    /*
    * 示例 1：

        输入：n = 4, k = 2
        输出：
        [
          [2,4],
          [3,4],
          [2,3],
          [1,2],
          [1,3],
          [1,4],
        ]
    * 1,2,3,4
    * */
    public List<List<Integer>> ret = new ArrayList<>();
    public List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public List<List<Integer>> combine(int n, int k) {
        getCombine(n, k, 1);
        return ret;
    }

    private void getCombine(int n, int k, int i) {
        if (path.size() == k) {
            ret.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j <= n; j++) {
            path.add(j);
            getCombine(n, k, j + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void should_return_list_as_insepect_when_given_n_and_k() {
        List<List<Integer>> inspect_res = new ArrayList<>(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(1, 4), Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(3, 4)));
        List<List<Integer>> result = combine(4, 2);

        Assert.assertEquals(inspect_res, result);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return ret;
    }

}