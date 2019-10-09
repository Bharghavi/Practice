package com.bhar;

import java.math.BigInteger;
import java.util.*;

public class Solution {
    public static void main(String arg[]) {
        //System.out.println(longestNonRepeatingSubstring("geeksforgeeks"));

      // System.out.println(reverseInteger(1563847412));

        //System.out.println(longestPalindrome("a"));

        System.out.println(convert("PAYPALISHIRING", 3));

        /*int[] nums1 = {1,3,4};
        int[] nums2 = {};
        System.out.println(findMedianSortedArrays(nums1, nums2))*/;

    }

    private static int longestNonRepeatingSubstring (String s) {
        int result = 0;
        HashMap<Character, Integer> charIndexMap = new HashMap<Character, Integer>();
        int from=0, to = 0, currentLength = 0;

        while(to < s.length()) {
            Integer index = charIndexMap.get(s.charAt(to));
            if (index == null || index < from) {
                charIndexMap.put(s.charAt(to), to);
                to++;
                currentLength = to - from;
            }
            else if (index != null) {
                currentLength = to - from;
                from = index + 1;
            }
            if (currentLength > result)
                result = currentLength;
        }
        return result;
    }

    private static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();

        for (Character each : s1.toCharArray()) {
            Integer count = charCount.get(each);
            if (count != null)
                charCount.put(each, count + 1);
            else
                charCount.put(each, 1);
        }

        for (Character each : s2.toCharArray()) {
            Integer count = charCount.get(each);
            if (count == null)
                return false;
            if (count == 1)
                charCount.remove(each);
            else
                charCount.put(each, count - 1);
        }
        return true;
    }

    private static int reverseInteger(int n) {
       int result = 0;
       Stack<Integer> integerStack = new Stack<Integer>();
       while (Math.abs(n)>0) {
           int digit = n%10;
           integerStack.push(digit);
           n = n/10;
       }
       long factor = 1;
       while (!integerStack.isEmpty()) {
           long digit = integerStack.pop();
           long tem = digit * factor;
           if (tem >= Integer.MAX_VALUE || tem <= Integer.MIN_VALUE)
                return 0;
           if ((result + tem) >= Integer.MAX_VALUE || (result + tem) <= Integer.MIN_VALUE)
               return 0;
           result += tem;
           factor = factor * 10;

       }
        return result;
    }

    private static String longestPalindrome(String s) {
       if (s.length() == 0 || s.length() == 1)
           return s;

        String result = s.substring(0,1);

        int left, right;

        for (int i = 1; i< s.length(); i++) {
            left = i-1;
            right = i+1;

            while (left >= 0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
                String substring = s.substring(left, right+1);
                if (substring.length() > result.length())
                    result = substring;
                left--;
                right++;
            }

            left = i-1;
            right = i;
            while (left >= 0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
                String substring = s.substring(left, right+1);
                if (substring.length() > result.length())
                    result = substring;
                left--;
                right++;
            }
        }
        return result;
    }

    private static String convert(String s, int numRows) {
        if (numRows >= s.length() || numRows <= 1 || s.length() == 0)
            return s;

        Queue<Integer> queue = new LinkedList<Integer>();

       int left = 0, right = 0;
       while (left < s.length()) {
           right += (2 * (numRows - 2) ) + 2;
           queue.add(left);
           queue.add(right);
           left = right;
       }

       StringBuilder result = new StringBuilder();
       int previousIndex = -1;
       while (!queue.isEmpty()) {
           left = queue.remove();
           right = queue.remove();

           if (left != right) {
               if (previousIndex != left && left < s.length()) {
                   result.append(s.charAt(left));
                   previousIndex = left;
               }
               if (right < s.length()) {
                   result.append(s.charAt(right));
                   previousIndex = right;
               }
               queue.add(left + 1);
               queue.add(right - 1);
           } else if (left < s.length()) {
               result.append(s.charAt(left));
               previousIndex = left;
           }
       }
        return result.toString();

    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        double result;

        int l1 = nums1.length;
        int l2 = nums2.length;

        int size = l1 + l2;
        int[] mergedArray = new int[size];

        int i = 0, j = 0, curr = 0;

        while (i < l1 || j < l2) {
            if (i >= l1) {
                while (j < l2) {
                    mergedArray[curr++] = nums2[j++];
                }
            }

            if (j >= l2) {
                while (i < l1)
                    mergedArray[curr++] = nums1[i++];
            }

            if (i < l1 && j < l2 ) {
                if (nums1[i] < nums2[j])
                    mergedArray[curr++] = nums1[i++];
                else
                    mergedArray[curr++] = nums2[j++];
            }
        }


        if (size % 2 == 0) {
            result = (mergedArray[size / 2] + mergedArray[(size / 2) -1]) / 2.0;

        } else
            result = mergedArray[size / 2];

        return result;

    }



}
