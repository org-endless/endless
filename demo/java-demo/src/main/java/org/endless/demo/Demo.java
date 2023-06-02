package org.endless.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo {
  public static void main(String[] args) {

    List<Double> nums = new ArrayList<>();

    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(1.0);
    nums.add(2.0);
    nums.add(3.0);
    nums.add(4.0);
    nums.add(500.0);
    nums.add(600000.0);

    System.out.println("avg is " + avg(nums));
    System.out.println("avg2 is " + avg2(nums));
    System.out.println("median is " + median(nums));
  }

  public static double avg(List<Double> nums) {

    double total = nums.stream().mapToDouble(num -> num).sum();
    return total / nums.size();
  }

  public static double avg2(List<Double> nums) {
    List<Double> nums2 = new ArrayList<>();
    double total = 0;
    for (Double num : nums) {
      if (nums2.contains(num)) {
        continue;
      }
      double count = Collections.frequency(nums, num);
      nums2.add(num);
      double gain = count / nums.size();
      total = total + num * gain;
    }
    return total;
  }

  public static double median(List<Double> list) {

    // Sort the list
    Collections.sort(list);

    int size = list.size();
    double median;

    // Check if size of list is even or odd
    if (size % 2 == 0) {
      median = (list.get(size / 2) + list.get(size / 2 - 1)) / 2.0;
    } else {
      median = list.get(size / 2);
    }
    System.out.println("test");
    return median;
  }
}