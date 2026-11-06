// 教師觀察工具；不是另一份學生必寫作業。先給能源與樣本，再給整數指令。
public class FixedScriptMission {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: FixedScriptMission energy samples [commands ...]");
            return;
        }
        int energy = Integer.parseInt(args[0]);
        int samples = Integer.parseInt(args[1]);
        if (energy < 0 || energy > 20 || samples < 0) {
            System.out.println("INVALID_INITIAL_STATE");
            return;
        }
        int[] script = new int[args.length - 2];
        for (int i = 0; i < script.length; i++) {
            script[i] = Integer.parseInt(args[i + 2]);
        }
        MissionLog.runMission(script, energy, samples);
    }
}
