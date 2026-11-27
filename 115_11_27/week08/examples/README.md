# 會員覆寫獨立範例

初稿由Codex生成。先填學生講義B1預測，再執行；這三檔為完整觀察範例，不是探測車答案。

把Member.java、VipMember.java、MemberDemo.java放自己的java-08/src。在java-08執行：

```powershell
javac -encoding UTF-8 -d out src/*.java
java -cp out MemberDemo
```

三檔已構成多檔合作；以VS Code檢查位置，再用IntelliJ IDEA開同一java-08，確認已安裝SDK、src來源根與MemberDemo入口。不建Maven／Gradle，不要求每個活動雙IDE重做。

## 完整預期輸出

先預測，才閱讀下列核對區。

<details>
<summary>完成預測後展開</summary>

```text
Member 建構子: Amy
Member 建構子: Bob
VipMember 建構子: Bob
會員 Amy / 點數=12
VIP 會員 Bob / 點數=28 / 獎勵次數=1
VIP 會員 Bob / 點數=28 / 獎勵次數=1
```

</details>

memberView與bob指向同一物件；Member型態參考可呼叫describe，實際執行VipMember的覆寫。memberView.claimBonus()則無法編譯，因為Member沒有宣告該方法。

B3的小修改由學生在experiments副本實作，不直接修改後跑程式而略過預測。super.describe()是延伸閱讀：在子類別方法中指定呼叫父類別版本，不代表建立第二個物件。範例不處理null、負價格以外的完整輸入防護、轉型或例外。
