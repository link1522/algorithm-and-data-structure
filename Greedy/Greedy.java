package Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Greedy {
    public static void main(String[] args) {
        var broadcasts = initBroadCast();
        var allAreas = initAllAreas(broadcasts);

        var selectdBroadcast = new ArrayList<String>();
        var selectedAreas = new HashSet<String>();
        var tempSelectedAreas = new HashSet<String>();
        var maxIncrease = 0;
        var maxCoverBroadcast = "";
        while (selectedAreas.size() < allAreas.size()) {
            for(var entry : broadcasts.entrySet()) {
                tempSelectedAreas.clear();
                tempSelectedAreas.addAll(selectedAreas);
                tempSelectedAreas.addAll(entry.getValue());
                var curIncrease = tempSelectedAreas.size() - selectedAreas.size();
                if (curIncrease > maxIncrease) {
                    maxIncrease = curIncrease;
                    maxCoverBroadcast = entry.getKey();
                }
            }

            selectdBroadcast.add(maxCoverBroadcast);
            var maxCoverBroadcastSet = broadcasts.get(maxCoverBroadcast);
            selectedAreas.addAll(maxCoverBroadcastSet);
            maxIncrease = 0;
        }

        selectdBroadcast.forEach(broadcast -> System.out.println(broadcast));
    }

    private static HashMap<String, HashSet<String>> initBroadCast(){
        var broadcasts = new HashMap<String, HashSet<String>>();
        var broadcastSet1 = new HashSet<String>();
        broadcastSet1.add("北京");
        broadcastSet1.add("上海");
        broadcastSet1.add("天津");

        var broadcastSet2 = new HashSet<String>();
        broadcastSet2.add("廣州");
        broadcastSet2.add("北京");
        broadcastSet2.add("深圳");

        var broadcastSet3 = new HashSet<String>();
        broadcastSet3.add("成都");
        broadcastSet3.add("上海");
        broadcastSet3.add("杭州");

        var broadcastSet4 = new HashSet<String>();
        broadcastSet4.add("上海");
        broadcastSet4.add("天津");

        var broadcastSet5 = new HashSet<String>();
        broadcastSet5.add("杭州");
        broadcastSet5.add("大連");

        broadcasts.put("K1", broadcastSet1);
        broadcasts.put("K2", broadcastSet2);
        broadcasts.put("K3", broadcastSet3);
        broadcasts.put("K4", broadcastSet4);
        broadcasts.put("K5", broadcastSet5);

        return broadcasts;
    }

    private static HashSet<String> initAllAreas(HashMap<String, HashSet<String>> broadcasts) {
        var allAreas = new HashSet<String>();
        for(var entry : broadcasts.entrySet()) {
            var broadcastSet = entry.getValue();
            allAreas.addAll(broadcastSet);
        }
        return allAreas;
    }
}
