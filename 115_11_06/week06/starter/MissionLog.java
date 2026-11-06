import java.util.Arrays;

// 本檔已完成：固定腳本入口與報表。保留第五週 Codex 的完整輸出格式。
public class MissionLog {
    public static void main(String[] args) {
        int[] script = {1, 1, 2};
        runMission(script, 20, 0);
    }

    // 教師固定狀態測試入口，沿用第五週的簽名；不列學生 TODO。
    static void runMission(int[] script, int energy, int samples) {
        Rover rover = new Rover(energy, samples);
        Mission mission = new Mission(rover);
        mission.run(script);
        printReplay(mission);
        System.out.println("counts[0..3]=" + Arrays.toString(mission.countCommands()));
        printSummary(script.length, mission);
        System.out.println("final energy=" + rover.getEnergy() + ", samples=" + rover.getSamples()
                + ", actionCount=" + rover.getActionCount() + " -> " + mission.getOutcome());
        System.out.println("battery=" + rover.getEnergy() / 20.0 * 100);
    }

    static void printReplay(Mission mission) {
        System.out.println("=== replay ===");
        for (int i = 0; i < mission.getStepCount(); i++) {
            MissionStep step = mission.getStep(i);
            System.out.println("step " + (i + 1) + ": command=" + step.getCommand()
                    + ", result=" + step.getResult() + ", energy=" + step.getEnergy());
        }
    }

    static void printSummary(int scriptLength, Mission mission) {
        System.out.println("steps=" + mission.getStepCount() + ", ok=" + mission.countOk()
                + ", rejected=" + mission.countRejected()
                + ", ignored=" + (scriptLength - mission.getStepCount()));
        if (mission.getStepCount() == 0) {
            System.out.println("lowest energy=N/A");
        } else {
            System.out.println("lowest energy=" + mission.lowestEnergy());
        }
    }
}
