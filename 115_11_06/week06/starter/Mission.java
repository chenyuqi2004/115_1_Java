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
        // TODO 1：逐筆執行。先檢查 isStopped；每讀一筆就保存行動後快照。
        // 指令 0 也先記錄，再把 returned 設為 true 並離開迴圈。
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
        // TODO 2：走訪 history，以 isSuccessful 計算 OK 筆數。
        return 0;
    }

    public int countRejected() {
        int returns = 0;
        for (MissionStep step : history) {
            if ("RETURN".equals(step.getResult())) { returns++; }
        }
        return history.size() - countOk() - returns;
    }

    public int lowestEnergy() {
        // TODO 3：空清單回傳 -1；非空先取第一筆，再以迴圈求最低能源。
        return -1;
    }
}
