package com.test;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.mortbay.log.Log;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestMain1 {
    public static void main(String[] args) {
        testMethod1();
//        testMethod2();
    }

    public static void testMethod1(){
        List<Integer> numbers = IntStream.range(1,4).boxed().collect(Collectors.toList());
        numbers.stream().map(value1->value1+1).filter(value->value>0).forEach(value-> System.out.println(value));

    }

    public static void testMethod2(){
        List<Integer> numbers = IntStream.range(1,4).boxed().collect(Collectors.toList());
        SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("test app");
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        JavaRDD<Integer> javaRDD = javaSparkContext.parallelize(numbers,2);
        javaRDD.map(value1->value1+1).filter(value->value>0).foreach(value->Log.info("value{}",value));
    }

    public static void testMethod3(){
        SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("test app");
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
    }
}
