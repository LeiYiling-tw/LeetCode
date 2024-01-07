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
        getcombinationSum(candidates, target, 0);
        return ret;
    }

    public void getcombinationSum(int[] candidates, int target, int j) {
        if (sumPath(path) > target) {
            return;
        }
        if (sumPath(path) == target) {
            ret.add(new ArrayList<>(path));
            return;
        }
        for (int i = j; i < candidates.length; i++) {
            path.add(candidates[i]);
            getcombinationSum(candidates, target, i);
            path.remove(path.size() - 1);
        }
    }

    public int sumPath(List<Integer> path) {
        int sum = 0;
        for (int i = 0; i < path.size(); i++) {
            sum += path.get(i);
        }
        return sum;
    }

    /*
    * 输入: candidates = [2,3,5], target = 8
        输出: [[2,2,2,2],[2,3,3],[3,5]]
    * */
    @Test
    public void should_return_list_as_insepect_when_given_targe_and_candidates() {
        int[] candidates = {2, 3, 5};
        List<List<Integer>> inspect_res = Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(3, 5));
        List<List<Integer>> result = combinationSum(candidates, 8);

        Assert.assertEquals(inspect_res, result);
    }

    /*
    * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
        输出:
        [
        [1,1,6],
        [1,2,5],
        [1,7],
        [2,6]
        ]
    *
    * */
    Map<Integer, Boolean> used = new HashMap<Integer, Boolean>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length >= 1) {
            int[] array = Arrays.stream(candidates).sorted().toArray();
            for (int i = 0; i < candidates.length; i++) {
                used.put(i, false);
            }
            getcombinationSum2(array, target, 0);
            return ret;
        }
        return Collections.emptyList();
    }

    public void getcombinationSum2(int[] candidates, int target, int j) {
        if (sumPath(path) > target) {
            return;
        }
        if (sumPath(path) == target) {
            ret.add(new ArrayList<>(path));
            return;
        }

        for (int i = j; i < candidates.length; i++) {
            used.replace(i, true);
            if (i >= 1 && !used.get(i - 1) && (candidates[i] == candidates[i - 1])) {
                continue;
            }
            path.add(candidates[i]);
            getcombinationSum2(candidates, target, i + 1);
            path.remove(path.size() - 1);
            used.replace(i, false);
        }
    }

    @Test
    public void should_return_list_as_insepect_when_given_targe_and_candidates2() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> inspect_res = Arrays.asList(Arrays.asList(1, 1, 6), Arrays.asList(1, 2, 5), Arrays.asList(1, 7), Arrays.asList(2, 6));
        List<List<Integer>> result = combinationSum2(candidates, 8);

        Assert.assertEquals(inspect_res, result);
    }


    List<List<String>> partitionRet = new ArrayList<>();
    List<String> partitionPath = new ArrayList<>();

    // 分割回文串
    //输入：s = "aab"
    //输出：[["a","a","b"],["aa","b"]]
    @Test
    public void should_return_list_as_insepect_when_given_s() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        List<List<String>> inspect_res = Arrays.asList(Arrays.asList("a", "a", "b"), Arrays.asList("aa", "b"));
        List<List<String>> result = partition("aab");

        Assert.assertEquals(inspect_res, result);
    }

    public List<List<String>> partition(String s) {
        getPartition(s, 0);
        return partitionRet;
    }

    private void getPartition(String s, int i) {
        if (i >= 1 && !isPalindrome(partitionPath.get(partitionPath.size() - 1))) {
            return;
        }
        if (i >= 1 && isPalindrome(partitionPath.get(partitionPath.size() - 1)) && pathSize(partitionPath) == s.length()) {
            partitionRet.add(new ArrayList<>(partitionPath));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            partitionPath.add(s.substring(i, j + 1));
            getPartition(s, j + 1);
            partitionPath.remove(partitionPath.size() - 1);
        }
    }

    private int pathSize(List<String> path) {
        if (path.isEmpty()) {
            return 0;
        }
        return path.stream().mapToInt(String::length).sum();
    }

    private Boolean isPalindrome(String s) {
        int length = s.length();
        if (length == 1) {
            return true;
        }
        int j = length - 1;
        for (int i = 0; i < length / 2; i++) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            j--;
        }
        return true;

    }
}