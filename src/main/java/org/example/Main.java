package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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

    List<String> letterCombinationsRet = new ArrayList<>();
    List<Character> letterCombinationsPath = new ArrayList<>();
    HashMap<Character, String> mapToLitter = new HashMap<Character, String>() {
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return Collections.emptyList();
        }
        getLetterCombinations(digits.toCharArray(), 0);
        return letterCombinationsRet;
    }

    public void getLetterCombinations(char[] digits, int i) {
        if (letterCombinationsPath.size() == digits.length) {
            letterCombinationsRet.add(letterCombinationsPath.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }
        for (int j = 0; j < mapToLitter.get(digits[i]).length(); j++) {
            letterCombinationsPath.add(mapToLitter.get(digits[i]).charAt(j));
            getLetterCombinations(digits, i + 1);
            letterCombinationsPath.remove(letterCombinationsPath.size() - 1);
        }
    }

    @Test
    public void should_return_list_as_insepect_when_given_digits() {
        List<String> inspect_res = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        List<String> result = letterCombinations("23");

        Assert.assertEquals(inspect_res, result);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return ret;
    }

}