// 類別：MemberDemo（第 8 週範例）
// 意義：觀察覆寫的程式入口。
// 作用：建立一般會員與 VIP 會員，並用 Member 型態的變數指向 VIP，觀察實際執行的是哪個 describe()。
// 先在講義 B1 預測，才執行。會員範例與探測車完全獨立。
public class MemberDemo {
    // main：程式進入點。建立兩位會員、購物、領取加贈，最後印出三次說明文字。
    public static void main(String[] args) {
        Member amy = new Member("Amy");
        VipMember bob = new VipMember("Bob");
        amy.buy(120);
        bob.buy(80);
        bob.claimBonus();

        // 同一物件由不同型態參考指向，不會產生另一份點數。
        Member memberView = bob;
        System.out.println(amy.describe());
        System.out.println(bob.describe());
        System.out.println(memberView.describe());
    }
}
