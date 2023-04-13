package 可信真题;

import java.util.ArrayList;
import java.util.List;

class EnergySupplySystem {
    static class EnergySupply {
        public EnergySupply(int unitSupply) {
            this.unitSupply = unitSupply;
        }

        int unitSupply;
    }

    static class StoreSystem {
        public StoreSystem(int storeRate, int storeLimit) {
            this.storeRate = storeRate;
            this.storeLimit = storeLimit;
            this.storeNow = 0;
        }

        public void store(int store) {
            int canStore = Math.min(store, storeRate);
            this.storeNow = Math.min(this.storeLimit, this.storeNow + canStore);
        }

        int storeRate;

        int storeLimit;

        int storeNow;
    }

    static class Machine {
        public Machine(int index, int time, int consume, int priority) {
            this.index = index;
            this.addTime = time;
            this.unitConsume = consume;
            this.priority = priority;
        }

        int index;

        int addTime;

        int unitConsume;

        int priority;
    }

    EnergySupply energySupply;

    StoreSystem storeSystem;

    List<Machine> machineList;

    int preCalcTime; // 当前计算到的点

    public EnergySupplySystem(int supplyRate, int storeLimit, int storeRate) { //
        energySupply = new EnergySupply(supplyRate);
        storeSystem = new StoreSystem(storeRate, storeLimit);
        machineList = new ArrayList<>();
        preCalcTime = 0;
    }

    public void add(int time, int index, int consume, int priority) { //
        useElectricity(time); // 从time时刻开始用电
        preCalcTime = time;
        Machine machine = new Machine(index, time, consume, priority);
        machineList.add(machine);
        machineList.sort((o1, o2) -> Integer.compare(o2.priority, o1.priority)); //小的排在前面，需要在加入的时候排序
    }

    public int remove(int time, int index) {//
        Machine machine = getMachine(index);
        if (machine == null) {
            return -1;
        }
        useElectricity(time);
        machineList.remove(machine);
        preCalcTime = time;
        return 1;
    }

    public int query(int time) {//
        useElectricity(time);
        preCalcTime = time;
        return storeSystem.storeNow;
    }

    private void useElectricity(int nowTime) {
        // 这里把preCalcTime 和 nowTime拉平
        // 计算时机是每次 query的是时候
        while (nowTime != preCalcTime) {
            unitConsumeAndStore();
            nowTime--;
        }
    }

    private Machine getMachine(int index) {
        for (Machine machine : machineList) {
            if (machine.index == index) {
                return machine;
            }
        }
        return null;
    }

    private void unitConsumeAndStore() {//存储和消耗同时进行
        int unitSupply = energySupply.unitSupply;
        int storeAndCanUseNow = Math.min(storeSystem.storeNow, storeSystem.storeRate);
        storeSystem.storeNow = storeSystem.storeNow - storeAndCanUseNow;
        for (Machine machine : machineList) {
            if (unitSupply + storeAndCanUseNow < machine.unitConsume) {
                break;
            }
            int unitConsume = machine.unitConsume;
            if (unitSupply >= unitConsume) {
                unitSupply = unitSupply - unitConsume;
                continue;
            }
            unitConsume = unitConsume - unitSupply;
            unitSupply = 0;
            if (storeAndCanUseNow >= unitConsume) {
                storeAndCanUseNow = storeAndCanUseNow - unitConsume;
                continue;
            }
            storeAndCanUseNow = 0;
            break;
        }
        if (storeAndCanUseNow > 0) {
            storeSystem.storeNow = storeSystem.storeNow + storeAndCanUseNow;
        }
        if (unitSupply > 0) {
            storeSystem.store(unitSupply);
        }
    }
}