# 會員繼承獨立範例

先預測再執行MemberDemo。Member保管姓名與點數，VipMember新增領取加贈點數及bonusCount；不接主任務，不要求社團系統先備。

```powershell
javac -encoding UTF-8 -d out examples/*.java
java -cp out MemberDemo
```

上面在教材目錄執行；學生在自己的java-07改編譯src/*.java。

完整預期輸出：

```text
Member 建構子: Amy
Member 建構子: Bob
VipMember 建構子: Bob
Amy 的點數=12
Bob 的點數=28
Bob 的獎勵次數=1
```

buy每滿10元得1點，非正數不加點；claimBonus每次加20且bonusCount加1。沒有次數或每日限制，不要自行推論一次後不能再領。先用全新物件測試，避免前一個實驗更動點數。

父類別建構子處理父類別狀態，子類別建構子接著處理自己的狀態。本例把super寫在第一個敘述，建構子不被繼承。points是Member的private成員，不由VipMember繼承，但同一個VipMember物件仍有這份狀態，Member的方法負責存取。

兩個刻意編譯錯誤實驗與同套件protected實驗見student_lab C；請放隔離副本，實驗後還原。Object只簡介：Member沒寫extends時直接父類別是Object；不要求本週學反射或覆寫toString。
