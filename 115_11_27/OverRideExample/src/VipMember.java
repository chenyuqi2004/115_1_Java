// 類別：VipMember（第 8 週範例：VIP 會員）
// 意義：VIP 是一種會員（extends Member），多了「領取加贈點數」的能力。
// 作用：示範覆寫：用和父類別相同的簽名重新定義 describe()，讓 VIP 有自己的說明文字。
// Member 的 private 狀態仍在同一個物件內，但不是子類別繼承的成員。
public class VipMember extends Member {
    // bonusCount：已領取加贈點數的次數；是 VIP 自己才有的欄位。
    private int bonusCount;

    // 建構子：第一行 super(name) 先執行父類別 Member 的建構子，再設定 VIP 自己的欄位。
    public VipMember(String name) {
        super(name);
        bonusCount = 0;
        System.out.println("VipMember 建構子: " + name);
    }

    // describe：回傳 VIP 的說明文字（多了獎勵次數）。
    // 覆寫父類別 Member 的 describe()：方法名稱、參數（沒有參數）、回傳型態都和父類別相同。
    // 加上 @Override，表示「這個方法要覆寫父類別的方法」，請編譯器幫忙確認。
    @Override
    //public String describe() {
        //return "VIP 會員 " + getName() + " / 點數=" + getPoints()
                //+ " / 獎勵次數=" + bonusCount;
    //}

    public String describe(){
        return "VIP " + super.describe() +"/獎勵次數=" + bonusCount;
    }

    // getBonusCount：回傳已領取加贈的次數。
    public int getBonusCount() { return bonusCount; }

    // claimBonus：領取加贈 20 點，並把領取次數加 1；透過父類別的 addPoints 加點數。
    public void claimBonus() {
        addPoints(20);
        bonusCount++;
    }
}
