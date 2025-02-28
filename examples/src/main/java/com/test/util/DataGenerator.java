package com.test.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import static com.test.util.RandomUtil.*;

public class DataGenerator {
	private static Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
	private static String filename = "dataTestTableFile.txt";

	public static void main(String[] args) throws IOException {
		long time = 0L;
		time = TimeUtil.toLong("2019-07-4 1:34:00:000");
//        testMethod2();
//		testMethod1(time);
//		testMethod1_1(time);
//		testMethod1_2(time);
//        testMethod3(1537390812000L + 6000L);
//        testMethod4(1537378980000L);
//        testMethod5(time);
		testMethod6(time);
//		testMethod8(time);
	}

	/**
	 * 制造正常的数据 非时间乱序
	 * @param time
	 */
	public static void testMethod1(long time) {
		String[] userName = {"小张", "小李", "小刘", "小刘", "小赵", "小吴", "小季"};
		Path logFile = Paths.get(URLUtil.baseUrl+ "dataTestTableFile.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
			for (int i = 0; i < 5; i++) {
				Map<String, Object> map = new HashMap<>();
//				map.put("user_name", userName[getRandom(1)]);
				map.put("user_name", getStringFromRandom(100,1));
//				if (i < 10) {
//					map.put("user_count", 100);
//				} else if (i < 20) {
//					map.put("user_count", 50);
//				} else if (i < 30) {
//					map.put("user_count", 100);
//				} else {
//					map.put("user_count", 50);
//				}
                map.put("user_count",1);
				map.put("_sysTime", time);
//                map.put("发生时间",time);
				time = time + 60000;
				String s = gson.toJson(map);
				writer.newLine();
				writer.write(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 制造1分钟内的乱序时间（time + i * 60000 + RandomUtil.getRandom(10,59000)）
	 * 制造2分钟内的乱序时间 time + i * 120000 + RandomUtil.getRandom(10,119000);
	 * 制造3分钟内的乱序时间 time + i * 180000 + RandomUtil.getRandom(10,179000);
	 * @param time
	 */
	public static void testMethod1_1(long time) {
		String[] userName = {"小张", "小李", "小刘", "小刘", "小赵", "小吴", "小季"};
		Path logFile = Paths.get(URLUtil.baseUrl+ filename);
		try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					Map<String, Object> map = new HashMap<>();
					map.put("user_name", userName[getRandom(1)]);
					map.put("user_count", 10);
					long ti = time + i * 120000 + RandomUtil.getRandom(10,119000);
					System.out.println(TimeUtil.toDate(ti));
					map.put("_sysTime", ti);
					String s = gson.toJson(map);
					writer.newLine();
					writer.write(s);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多字段
	 * @param time
	 */
	public static void testMethod1_2(long time) {
		String[] userName = {"小张", "小李", "小刘", "小刘", "小赵", "小吴", "小季"};
		Path logFile = Paths.get(URLUtil.baseUrl+ "dataTestTableFile.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
			for (int i = 0; i < 50; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("user_name", userName[getRandom(1)]);
				if (i < 10) {
					map.put("user_count", 100);
				} else if (i < 20) {
					map.put("user_count", 50);
				} else if (i < 30) {
					map.put("user_count", 100);
				} else {
					map.put("user_count", 50);
				}
				map.put("test1","ddd");
				map.put("test2","ddd");
//                map.put("user_count",getRandom(4) + 1);
				map.put("_sysTime", time);
//                map.put("发生时间",time);
				time = time + 60000;
				String s = gson.toJson(map);
				writer.newLine();
				writer.write(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void testMethod2(long time) {
		String[] provinces = {"河南", "浙江", "陕西", "辽宁", "安徽", "湖南"};
		String[] citys = {"井冈山", "上海", "杭州", "郑州", "徐州", "黑龙江"};
		Path logFile = Paths.get( URLUtil.baseUrl+ filename);
		try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
			for (int i = 0; i < 100; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("SUM_sales_index", getRandom(2000));
				time = time + 60000;
				map.put("datatime", time);
				map.put("city", citys[getRandom(6)]);
				map.put("province", provinces[getRandom(6)]);
				String s = gson.toJson(map);
				writer.newLine();
				writer.write(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testMethod4(long time) {
		for (int i = 0; i < 10; i++) {
			time = time + 60000;
			System.out.println(time);
		}
	}

	public static void testMethod3(long time) {
		String[] provinces = {"河南", "浙江", "陕西", "辽宁", "安徽", "湖南"};
		String[] citys = {"井冈山", "上海", "杭州", "郑州", "徐州", "黑龙江"};
		String[] sexs = {"男", "女"};
		Path logFile = Paths.get(".\\src\\main\\resources\\alarmJson.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
			for (int i = 0; i < 100; i++) {
				Map<String, Object> map = new HashMap<>();
				if (i < 10) {
					map.put("SUM_sales_index", 100);
				} else if (i < 20) {
					map.put("SUM_sales_index", 50);
				} else if (i < 30) {
					map.put("SUM_sales_index", 100);
				} else {
					map.put("SUM_sales_index", 50);
				}
//                map.put("SUM_sales_index",getRandomMaxAndMin(1000,10));
				map.put("MAX_sales_index", getRandom(2000));
				map.put("datatime", time);
				time = time + 60000;
				map.put("city", citys[getRandom(6)]);
				map.put("province", provinces[getRandom(6)]);
				map.put("sex", sexs[getRandom(2)]);
				String s = gson.toJson(map);
				writer.newLine();
				writer.write(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void testMethod5(long time) {
		Path logFile = Paths.get(URLUtil.baseUrl+ "dataTestTableFile.txt");
		long number = 1;
		try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
			for (int i = 0; i < 100000; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("timestamp", time);
				time = time + 1000;
				map.put("number", number);
				number++;
				String s = gson.toJson(map);
				writer.newLine();
				writer.write(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * name,age,sex
	 *
	 * name,title
	 * @param time
	 */
	public static void testMethod6(long time){
		Path logFile = Paths.get(URLUtil.baseUrl+ "people1.csv");
		String[] names = {"小张","小李","小孙","小侯","小马"};
		String[] sexs = {"男","女"};
		long number = 1;
		try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
			for (int i = 0; i < 100; i++) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(names[getRandom(names.length)]);
				stringBuilder.append(",").append(getRandom(3,100));
				stringBuilder.append(",").append(sexs[getRandom(sexs.length)]);
				writer.newLine();
				writer.write(stringBuilder.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
