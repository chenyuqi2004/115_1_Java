import java.util.ArrayList;

// 一次任務的流程與歷史。每個新任務只呼叫一次 run，script 非 null。
// Rover 由呼叫端交入；執行與讀取結果期間，不由外部另行操作同一 Rover。
public class Mission {
    private Rover rover;
    private ArrayList<MissionStep> history;
    private boolean returned;

    public Mission(Rover rover) {
        this.rover = rover;
        history = new ArrayList<>();
        returned = false;
    }

    public void run(int[] script) {
        for (int i = 0; i < script.length; i++) {
            if (rover.isStopped()) { break; }
            int command = script[i];
            String result = rover.execute(command);
            history.add(new MissionStep(command, rover.getEnergy(), result));
            if (command == 0) {
                returned = true;
                break;
            }
        }
    }

    // 優先順序由教師提供；同時成功與耗盡時，成功優先。
    public String getOutcome() {
        if (rover.isSuccess()) { return "SUCCESS"; }
        if (rover.isExhausted()) { return "EXHAUSTED"; }
        if (returned) { return "RETURNED"; }
        return "UNFINISHED";
    }

    public int getStepCount() { return history.size(); }
    // 對外只讀的單筆資料不提供 setter；不交出可變的 history 清單。
    public MissionStep getStep(int index) { return history.get(index); }

    public int[] countCommands() {
        int[] counts = new int[4];
        for (MissionStep step : history) {
            int command = step.getCommand();
            if (command >= 0 && command < counts.length) { counts[command]++; }
        }
        return counts;
    }

    public int countOk() {
        int ok = 0;
        for (MissionStep step : history) {
            if (step.isSuccessful()) { ok++; }
        }
        return ok;
    }

    public int countRejected() {
        int returns = 0;
        for (MissionStep step : history) {
            if ("RETURN".equals(step.getResult())) { returns++; }
        }
        return history.size() - countOk() - returns;
    }

    public int lowestEnergy() {
        if (history.isEmpty()) { return -1; }
        int lowest = history.get(0).getEnergy();
        for (MissionStep step : history) {
            if (step.getEnergy() < lowest) { lowest = step.getEnergy(); }
        }
        return lowest;
    }
}
