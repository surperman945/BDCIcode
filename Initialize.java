package com.nuaa;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.ints.IntArrays;
import it.unimi.dsi.fastutil.ints.IntComparator;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;

import java.io.*;

/**
 * @ClassName Initialize
 * @Author wyj
 * @DateTime 2021-10-07 19:15
 * @Version 1.0
 */
public class Initialize {
    public static void main(String[] args) {
//        long start = System.currentTimeMillis();

        // TODO 取出数据的方法封装成函数
        // 读取transfer.csv文件
        String csvFile = "input/transfer_test.csv";
        BufferedReader br = null;
        String line = "";

        // 创建对应的集合
        LongArrayList srcArray = new LongArrayList();
        LongArrayList dstArray = new LongArrayList();
        LongArrayList timeArray = new LongArrayList();
        DoubleArrayList moneyArray = new DoubleArrayList();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // 使用逗号切分
                String[] strings = line.split(",");

                srcArray.add(Long.parseLong(strings[0]));
                dstArray.add(Long.parseLong(strings[1]));
                timeArray.add(Long.parseLong(strings[2]));
                moneyArray.add(Float.parseFloat(strings[3]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 创建一个index数组 并且定义下标值
        int[] index = new int[srcArray.size()];
        for (int i = 0; i < srcArray.size(); i++) {
            index[i] = i;
        }

        for (int i = 0; i < index.length; i++) {
            System.out.print(index[i] + " ");
        }
        System.out.println(" ");

        // 排序
        IntArrays.parallelQuickSort(index, new IntComparator() {
            @Override
            public int compare(int i1, int i2) {
                // TODO 判断条件的确定
                if (srcArray.get(i1) == srcArray.get(i2)) {
                    // System.out.println((int) Math.signum(moneyArray.get(i1) - moneyArray.get(i2)));
                    return (int) Math.signum(moneyArray.get(i1) - moneyArray.get(i2));
                }
                // System.out.println((int) (srcArray.get(i1) - srcArray.get(i2)));
                return (int) (srcArray.get(i1) - srcArray.get(i2));
            }
        });

        for (int i = 0; i < index.length; i++) {
            System.out.print(index[i] + " ");
        }
        System.out.println(" ");

        // 创建TransactionGraph对象
        TransactionGraph tg = new TransactionGraph();

        // TODO 需要优化
        for (int i = 0; i < index.length; i++) {
            tg.getSrcArray().add(srcArray.get(index[i]));
            tg.getDstArray().add(dstArray.get(index[i]));
            tg.getMoneyArray().add(moneyArray.get(index[i]));
            tg.getTimeArray().add(timeArray.get(index[i]));
        }

        // 测试结果没有问题
//        System.out.println(tg.getSrcArray());
//        System.out.println(tg.getDstArray());
//        System.out.println(tg.getMoneyArray());
//        System.out.println(tg.getTimeArray());

        // 将结果输出到
        try {
            File outcsv = new File("output/transfer_out.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(outcsv));
            for(int i = 0; i < index.length; i++){
                bw.write(tg.getSrcArray().get(i) + "," + tg.getDstArray().get(i) + "," + tg.getTimeArray().get(i) +"," + tg.getMoneyArray().get(i));
                bw.newLine();
            }
            bw.flush();//使用缓冲区的方法将数据刷到目的地中
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // File对象的创建过程中的异常捕获
        }catch (IOException e) {
            e.printStackTrace();
            // BuffeeredWriter在关闭对象捕获异常
        }


        // 封装Long2ObjectOpenHashMap
        // TODO 数据存放的方式 ?
        Long2ObjectOpenHashMap<LongOpenHashSet> startIndex = tg.getStartIndex();
        LongOpenHashSet startIndexSet = new LongOpenHashSet();

        Long2ObjectOpenHashMap<LongOpenHashSet> endIndex = tg.getEndIndex();
        LongOpenHashSet endIndexSet = new LongOpenHashSet();

        for (int i = 0; i < index.length; i++) {
            if (!startIndex.containsKey(srcArray.get(index[i]))) {
               startIndex.put(srcArray.get(index[i]), new LongOpenHashSet() );
                startIndex.get(srcArray.get(index[i])).add(i);
            }

//            if (!endIndex.containsKey(dstArray.get(index[i]))) {
//                endIndex.put(dstArray.get(index[i]), endIndexSet);
//                endIndex.get(dstArray.get(index[i])).add()
//            }
    }

        System.out.println(startIndex);

    }
}


