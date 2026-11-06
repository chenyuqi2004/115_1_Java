import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

// 沿用第四週15項完整輸出期望，驗證改為物件後行為不變。
// 此處的輸出擷取不是學生先備要求；不使用 JUnit，也不要求學生另寫工具。
public class MissionLogCheck {
    public static void main(String[] args) throws Exception {
        int passed = 0;
        passed += check("return", new int[] {0}, 20, 0,
                "=== replay ===\nstep 1: command=0, result=RETURN, energy=20\ncounts[0..3]=[1, 0, 0, 0]\nsteps=1, ok=0, rejected=0, ignored=0\nlowest energy=20\nfinal energy=20, samples=0, actionCount=0 -> RETURNED\nbattery=100.0\n");
        passed += check("mixed success", new int[] {1, 1, 2}, 20, 0,
                "=== replay ===\nstep 1: command=1, result=OK, energy=14\nstep 2: command=1, result=OK, energy=8\nstep 3: command=2, result=OK, energy=6\ncounts[0..3]=[0, 2, 1, 0]\nsteps=3, ok=3, rejected=0, ignored=0\nlowest energy=6\nfinal energy=6, samples=5, actionCount=3 -> SUCCESS\nbattery=30.0\n");
        passed += check("five scans", new int[] {2, 2, 2, 2, 2}, 20, 0,
                "=== replay ===\nstep 1: command=2, result=OK, energy=18\nstep 2: command=2, result=OK, energy=16\nstep 3: command=2, result=OK, energy=14\nstep 4: command=2, result=OK, energy=12\nstep 5: command=2, result=OK, energy=10\ncounts[0..3]=[0, 0, 5, 0]\nsteps=5, ok=5, rejected=0, ignored=0\nlowest energy=10\nfinal energy=10, samples=5, actionCount=5 -> SUCCESS\nbattery=50.0\n");
        passed += check("rejected and invalid", new int[] {3, 9, 0}, 20, 0,
                "=== replay ===\nstep 1: command=3, result=ALREADY_FULL, energy=20\nstep 2: command=9, result=UNKNOWN_COMMAND, energy=20\nstep 3: command=0, result=RETURN, energy=20\ncounts[0..3]=[1, 0, 0, 1]\nsteps=3, ok=0, rejected=2, ignored=0\nlowest energy=20\nfinal energy=20, samples=0, actionCount=0 -> RETURNED\nbattery=100.0\n");
        passed += check("stop after success", new int[] {1, 1, 1, 1}, 20, 0,
                "=== replay ===\nstep 1: command=1, result=OK, energy=14\nstep 2: command=1, result=OK, energy=8\nstep 3: command=1, result=OK, energy=2\ncounts[0..3]=[0, 3, 0, 0]\nsteps=3, ok=3, rejected=0, ignored=1\nlowest energy=2\nfinal energy=2, samples=6, actionCount=3 -> SUCCESS\nbattery=10.0\n");
        passed += check("script exhausted", new int[] {2, 1}, 20, 0,
                "=== replay ===\nstep 1: command=2, result=OK, energy=18\nstep 2: command=1, result=OK, energy=12\ncounts[0..3]=[0, 1, 1, 0]\nsteps=2, ok=2, rejected=0, ignored=0\nlowest energy=12\nfinal energy=12, samples=3, actionCount=2 -> UNFINISHED\nbattery=60.0\n");
        passed += check("rejected then charge", new int[] {1, 3, 0}, 5, 0,
                "=== replay ===\nstep 1: command=1, result=NOT_ENOUGH_ENERGY, energy=5\nstep 2: command=3, result=OK, energy=10\nstep 3: command=0, result=RETURN, energy=10\ncounts[0..3]=[1, 1, 0, 1]\nsteps=3, ok=1, rejected=1, ignored=0\nlowest energy=5\nfinal energy=10, samples=0, actionCount=1 -> RETURNED\nbattery=50.0\n");
        passed += check("success priority", new int[] {2}, 2, 4,
                "=== replay ===\nstep 1: command=2, result=OK, energy=0\ncounts[0..3]=[0, 0, 1, 0]\nsteps=1, ok=1, rejected=0, ignored=0\nlowest energy=0\nfinal energy=0, samples=5, actionCount=1 -> SUCCESS\nbattery=0.0\n");
        passed += check("initial zero energy", new int[] {3, 1}, 0, 0,
                "=== replay ===\ncounts[0..3]=[0, 0, 0, 0]\nsteps=0, ok=0, rejected=0, ignored=2\nlowest energy=N/A\nfinal energy=0, samples=0, actionCount=0 -> EXHAUSTED\nbattery=0.0\n");
        passed += check("initial success", new int[] {1}, 20, 5,
                "=== replay ===\ncounts[0..3]=[0, 0, 0, 0]\nsteps=0, ok=0, rejected=0, ignored=1\nlowest energy=N/A\nfinal energy=20, samples=5, actionCount=0 -> SUCCESS\nbattery=100.0\n");
        passed += check("empty script", new int[] {}, 20, 0,
                "=== replay ===\ncounts[0..3]=[0, 0, 0, 0]\nsteps=0, ok=0, rejected=0, ignored=0\nlowest energy=N/A\nfinal energy=20, samples=0, actionCount=0 -> UNFINISHED\nbattery=100.0\n");
        passed += check("exact energy", new int[] {1, 2}, 6, 0,
                "=== replay ===\nstep 1: command=1, result=OK, energy=0\ncounts[0..3]=[0, 1, 0, 0]\nsteps=1, ok=1, rejected=0, ignored=1\nlowest energy=0\nfinal energy=0, samples=2, actionCount=1 -> EXHAUSTED\nbattery=0.0\n");
        passed += check("charge cap", new int[] {3, 0}, 19, 0,
                "=== replay ===\nstep 1: command=3, result=OK, energy=20\nstep 2: command=0, result=RETURN, energy=20\ncounts[0..3]=[1, 0, 0, 1]\nsteps=2, ok=1, rejected=0, ignored=0\nlowest energy=20\nfinal energy=20, samples=0, actionCount=1 -> RETURNED\nbattery=100.0\n");
        passed += check("negative command", new int[] {-1, 9, 0}, 20, 0,
                "=== replay ===\nstep 1: command=-1, result=UNKNOWN_COMMAND, energy=20\nstep 2: command=9, result=UNKNOWN_COMMAND, energy=20\nstep 3: command=0, result=RETURN, energy=20\ncounts[0..3]=[1, 0, 0, 0]\nsteps=3, ok=0, rejected=2, ignored=0\nlowest energy=20\nfinal energy=20, samples=0, actionCount=0 -> RETURNED\nbattery=100.0\n");
        passed += check("initial success and zero", new int[] {3}, 0, 5,
                "=== replay ===\ncounts[0..3]=[0, 0, 0, 0]\nsteps=0, ok=0, rejected=0, ignored=1\nlowest energy=N/A\nfinal energy=0, samples=5, actionCount=0 -> SUCCESS\nbattery=0.0\n");
        System.out.println(passed + "/15 passed");
        if (passed != 15) {
            throw new AssertionError("MissionLog output checks failed");
        }
    }

    static int check(String label, int[] script, int energy, int samples,
            String expected) throws Exception {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream captured = new PrintStream(buffer, true, "UTF-8");
        try {
            System.setOut(captured);
            MissionLog.runMission(script, energy, samples);
        } finally {
            System.setOut(original);
            captured.close();
        }
        String actual = buffer.toString("UTF-8").replace("\r\n", "\n");
        if (actual.equals(expected)) {
            System.out.println("PASS " + label);
            return 1;
        }
        System.out.println("FAIL " + label + "\nEXPECTED:\n" + expected + "ACTUAL:\n" + actual);
        return 0;
    }
}
