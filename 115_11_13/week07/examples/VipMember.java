// 類別：VipMember（第 7 週範例：VIP 會員）
// 意義：VIP 是一種會員（is-a Member），所以用 extends Member 繼承名字、點數與購物規則。
// 作用：在一般會員之外，新增「領取加贈點數」的能力，並記錄領了幾次。
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

    // getBonusCount：回傳已領取加贈的次數。
    public int getBonusCount() { return bonusCount; }

    // claimBonus：領取加贈 20 點，並把領取次數加 1；透過父類別的 addPoints 加點數。
    public void claimBonus() {
        addPoints(20);
        bonusCount++;
    }
}
