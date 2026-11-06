// 契約與教師版相同；佔位值只是讓骨架能啟動，不代表完成需求。
public class MissionStep {
    private int command;
    private int energy;
    private String result;

    public MissionStep(int command, int energy, String result) {
        // TODO 1：以 this 將三個參數保存到目前物件的三個欄位。
    }

    // TODO 2：三個 getter 應讀取本物件對應欄位，取代以下佔位回傳值。
    public int getCommand() {
        return 0;
    }

    public int getEnergy() {
        return 0;
    }

    public String getResult() {
        return "PENDING";
    }

    public boolean isSuccessful() {
        // TODO 3：以內容比較判斷本物件的 result 是否為 OK。
        return false;
    }
}
