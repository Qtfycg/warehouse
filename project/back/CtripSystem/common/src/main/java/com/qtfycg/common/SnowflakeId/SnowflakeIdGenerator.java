package com.qtfycg.common.SnowflakeId;

public class SnowflakeIdGenerator {
    // 起始时间戳（2023-01-01），用于计算相对时间
    private final static long START_TIMESTAMP = 1672531200000L; // 自定义起始时间戳（2023-01-01）
    // 数据中心 ID 和机器 ID 的位数
    private final static long MACHINE_ID_BITS = 5L;
    private final static long DATACENTER_ID_BITS = 5L;
    // 序列号的位数
    private final static long SEQUENCE_BITS = 12L;
    // 数据中心 ID 和机器 ID 的最大值
    private final static long MAX_MACHINE_ID = ~(-1L << MACHINE_ID_BITS);
    private final static long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);
    // 位移值，用于将各部分数据拼接成最终的 ID
    private final static long MACHINE_SHIFT = SEQUENCE_BITS;
    private final static long DATACENTER_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS;
    private final static long TIMESTAMP_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS + DATACENTER_ID_BITS;

    private final long machineId;// 当前机器 ID
    private final long datacenterId;// 当前数据中心 ID
    private long sequence = 0L;// 当前序列号
    private long lastTimestamp = -1L;// 上一次生成 ID 的时间戳


    /**
     * 构造方法，初始化数据中心 ID 和机器 ID
     *
     * @param machineId 当前机器 ID
     * @param datacenterId 当前数据中心 ID
     */
    public SnowflakeIdGenerator(long machineId, long datacenterId) {

        // 检查机器 ID 和数据中心 ID 是否超出范围
        if (machineId > MAX_MACHINE_ID || datacenterId > MAX_DATACENTER_ID) {
            throw new IllegalArgumentException("ID exceeds max");
        }
        this.machineId = machineId;
        this.datacenterId = datacenterId;
    }

    /**
     * 生成下一个唯一 ID
     *
     * @return 唯一 ID
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        // 如果当前时间戳小于上一次时间戳，说明时钟回拨，抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards");
        }

        // 如果当前时间戳与上一次时间戳相同，则递增序列号
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & ~(-1L << SEQUENCE_BITS);
            // 如果序列号达到最大值，等待下一毫秒
            if (sequence == 0) {
                while ((timestamp = System.currentTimeMillis()) <= lastTimestamp);
            }
        } else {
            // 如果时间戳不同，重置序列号
            sequence = 0L;
        }

        // 更新上一次时间戳
        lastTimestamp = timestamp;
        // 生成最终的 ID，通过位移和或运算拼接各部分数据
        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_SHIFT)// 时间戳部分
                | (datacenterId << DATACENTER_SHIFT)// 数据中心 ID 部分
                | (machineId << MACHINE_SHIFT)// 机器 ID 部分
                | sequence;// 序列号部分
    }
}
