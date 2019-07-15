package com.test;

import com.test.util.URLUtil;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

public class TestMain2 extends CommonSpark{
    private static final Dataset<Row> peopleDFCsv;
    static {
        String path = URLUtil.baseUrl + "people1.csv";
        peopleDFCsv = spark.read().format("csv")
                .option("sep", ",")
                .option("inferSchema", "true")
                .option("header", "true")
                .load(path);
    }
    public static void main(String[] args) {
//        testMethod1();
//        testMethod2();
        testMethod3();
        spark.stop();
    }
    public static void testMethod1(){
        peopleDFCsv.show();
        peopleDFCsv.printSchema();
//        peopleDFCsv.select("name").show();
//        // 选择每一个人 并让他们的岁数加1
        peopleDFCsv.select(col("name"), col("age").plus(1)).show();
        // 选择大于21的人
//        peopleDFCsv.filter(col("age").gt(21)).show();
        // 根据name进行分组并计算个数
        peopleDFCsv.groupBy("name").count().show();
    }


    public static void testMethod2(){
        peopleDFCsv.createOrReplaceTempView("people");
        Dataset<Row> sqlDF = spark.sql("SELECT name,age FROM people");
        sqlDF.show(100);
    }
//    学习去重
    public static void testMethod3(){
        peopleDFCsv.distinct().show(100);
    }
}
