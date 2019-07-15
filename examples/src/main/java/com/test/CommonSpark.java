package com.test;

import org.apache.spark.sql.SparkSession;

/**
 * -Dspark.master=local
 */
public abstract class CommonSpark {
    public static final SparkSession spark ;
    static {
        spark = SparkSession
                .builder()
                .appName("Java Spark SQL data sources example")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();
    }
}
