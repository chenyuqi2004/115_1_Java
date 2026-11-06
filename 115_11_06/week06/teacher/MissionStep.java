// 一筆已發生的日誌。呼叫端提供行動後能源，之後只讀取、不修改。
// 契約：energy 為 0..20，result 為非 null 的既有結果代碼；command 可為任何 int。
public class MissionStep {
    private int command;
    private int energy;
    private String result;

    public MissionStep(int command, int energy, String result) {
        this.command = command;
        this.energy = energy;
        this.result = result;
    }

    public int getCommand() {
        return command;
    }

    public int getEnergy() {
        return energy;
    }

    public String getResult() {
        return result;
    }

    public boolean isSuccessful() {
        return "OK".equals(result);
    }
}
