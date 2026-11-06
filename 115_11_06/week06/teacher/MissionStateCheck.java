// 教師檢查，不列入學生必寫語法；每個案例建立新的任務。
public class MissionStateCheck {
    private static int passed;
    public static void main(String[] args) {
        Rover empty = new Rover(0, 0);
        check("zero cannot charge", "STOPPED".equals(empty.execute(3)) && empty.getEnergy() == 0);
        Rover done = new Rover(20, 5);
        check("success cannot explore", "STOPPED".equals(done.execute(1)) && done.getEnergy() == 20);
        Rover edge = new Rover(19, 0);
        check("cap charge", "OK".equals(edge.execute(3)) && edge.getEnergy() == 20);
        check("full rejected", "ALREADY_FULL".equals(edge.execute(3)) && edge.getActionCount() == 1);
        Rover low = new Rover(5, 0);
        check("reject keeps state", "NOT_ENOUGH_ENERGY".equals(low.execute(1)) && low.getEnergy() == 5 && low.getActionCount() == 0);
        Mission mission = new Mission(new Rover(20, 0));
        mission.run(new int[] {3, 9, 0, 1});
        check("return stops reading", mission.getStepCount() == 3 && "RETURNED".equals(mission.getOutcome()));
        check("rejected excludes return", mission.countOk() == 0 && mission.countRejected() == 2);
        check("counts copy", mission.countCommands()[3] == 1);
        int[] counts = mission.countCommands();
        counts[3] = 99;
        check("copy cannot rewrite log", mission.countCommands()[3] == 1);
        Mission history = new Mission(new Rover(20, 0));
        history.run(new int[] {1, 3, 0});
        check("first snapshot", history.getStepCount() == 3 && history.getStep(0).getEnergy() == 14);
        check("later snapshot", history.getStepCount() == 3 && history.getStep(1).getEnergy() == 19);
        check("lowest not final", history.lowestEnergy() == 14);
        Mission blank = new Mission(new Rover(20, 0));
        blank.run(new int[] {});
        check("empty minimum sentinel", blank.lowestEnergy() == -1 && blank.getStepCount() == 0);
        check("independent histories", history.getStepCount() == 3 && blank.getStepCount() == 0);
        Mission priority = new Mission(new Rover(2, 4));
        priority.run(new int[] {2, 3});
        check("success before exhausted", "SUCCESS".equals(priority.getOutcome()) && priority.getStepCount() == 1);
        check("String content", new MissionStep(1, 14, new String("OK")).isSuccessful());
        System.out.println(passed + "/16 passed");
        if (passed != 16) { throw new AssertionError("state checks failed"); }
    }
    private static void check(String label, boolean result) {
        System.out.println((result ? "PASS " : "FAIL ") + label);
        if (result) { passed++; }
    }
}
