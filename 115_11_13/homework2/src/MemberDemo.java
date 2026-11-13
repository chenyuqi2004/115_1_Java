// 類別：MemberDemo（第 7 週範例）
// 意義：觀察繼承的程式入口。
// 作用：建立一般會員與 VIP 會員，觀察建構子的執行順序、整數除法，以及子類別新增的方法。
// 執行：在 java-07 先編譯 src/*.java，再 java -cp out MemberDemo。
// 先預測父／子建構順序、整數除法及新增方法的作用，再執行。
public class MemberDemo {
    // main：程式進入點。建立兩位會員、購物、領取加贈，最後印出點數。
    public static void main(String[] args) {
        Member amy = new Member("Amy");
        VipMember bob = new VipMember("Bob");
        amy.buy(120);
        bob.buy(85);
        bob.claimBonus();
        System.out.println("Amy 的點數=" + amy.getPoints());
        System.out.println("Bob 的點數=" + bob.getPoints());
        System.out.println("Bob 的獎勵次數=" + bob.getBonusCount());
    }
}
