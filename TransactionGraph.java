package com.nuaa;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2LongArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TransactionGraph
 * @Author wyj
 * @DateTime 2021-10-07 21:08
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionGraph {
    private LongArrayList srcArray = new LongArrayList();
    private LongArrayList dstArray = new LongArrayList();
    private LongArrayList timeArray = new LongArrayList();
    private DoubleArrayList moneyArray = new DoubleArrayList();
    private Long2ObjectOpenHashMap<LongOpenHashSet> startIndex = new Long2ObjectOpenHashMap<LongOpenHashSet>();
    private Long2ObjectOpenHashMap<LongOpenHashSet> endIndex = new Long2ObjectOpenHashMap<LongOpenHashSet>();
}
