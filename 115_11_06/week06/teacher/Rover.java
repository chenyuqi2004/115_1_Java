// 第六週提供的銜接類別：把第五週 MissionLog 的目前狀態與行動搬到物件內。
// 建構契約：energy 0..20、samples >= 0；不處理契約外參數。
public class Rover {
    private int energy;
    private int samples;
    private int actionCount;

    public Rover(int energy, int samples) {
        this.energy = energy;
        this.samples = samples;
        actionCount = 0;
    }

    public int getEnergy() { return energy; }
    public int getSamples() { return samples; }
    public int getActionCount() { return actionCount; }
    public boolean isSuccess() { return samples >= 5; }
    public boolean isExhausted() { return energy == 0; }
    public boolean isStopped() { return isSuccess() || isExhausted(); }

    // 外部只從這個入口執行；成功或耗盡後不再改變狀態。
    // RETURN 由 Mission 記住：探測車本身不保管任務的返回流程。
    public String execute(int command) {
        if (isStopped()) { return "STOPPED"; }
        switch (command) {
            case 1: return explore();
            case 2: return scan();
            case 3: return charge();
            case 0: return "RETURN";
            default: return "UNKNOWN_COMMAND";
        }
    }

    private String explore() {
        if (energy < 6) { return "NOT_ENOUGH_ENERGY"; }
        energy -= 6;
        samples += 2;
        actionCount++;
        return "OK";
    }

    private String scan() {
        if (energy < 2) { return "NOT_ENOUGH_ENERGY"; }
        energy -= 2;
        samples++;
        actionCount++;
        return "OK";
    }

    private String charge() {
        if (energy == 20) { return "ALREADY_FULL"; }
        energy += 5;
        if (energy > 20) { energy = 20; }
        actionCount++;
        return "OK";
    }
}
