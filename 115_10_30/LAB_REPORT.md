# 第四週學生實作：火星探測車任務日誌

姓名：陳昱齊　學號：A113221008　實際上課日期：2026-10-30　實際 JDK：Temurin 25.0.5

另存為當日 LAB_REPORT.md。A–H 必做，最後的延伸選做。先預測再執行；教師示範觀察與本人實測分開。作業完整需求見 [assignment.md](assignment.md)。

## A｜取回與先備診斷（10 分鐘）

1. 無 repo 時 clone，有副本先保存再 Fetch/Pull；在 115_1_Java/YYYY_MM_DD 建 java-04/src。
2. 放五個核心 examples 與 starter/MissionLog.java，勿放 teacher 同名類別；日期下放本報告及 AI_USAGE。
3. 執行 java -version、javac -version，寫下完整版本：`openjdk version "25.0.5" 2026-10-20 LTS`；`OpenJDK Runtime Environment Temurin-25.0.5+8 (build 25.0.5+8-LTS)`；`OpenJDK 64-Bit Server VM Temurin-25.0.5+8 (build 25.0.5+8-LTS, mixed mode, sharing)`；`javac 25.0.5`。不限定特定 JDK 版本。
4. 在 java-04 編譯 `javac -encoding UTF-8 -d out src/*.java`；成功後跑 MissionLog。此時能跑任務但日誌與摘要未完成，是預期起始狀態。（註：本專案放入的 MissionLog 已含 TODO 1–5 的實作，輸出會包含完整日誌與摘要，並非起始狀態。本人編譯執行結果：編譯成功無錯誤；MissionLog 回放三步 energy=14、8、6，`counts[0..3]=[0, 2, 1, 0]`，`steps=3, ok=3, rejected=0, ignored=0`，`lowest energy=6`，`final energy=6, samples=5, actionCount=3 -> SUCCESS`，`battery=30.0`。）

診斷：第三週 {1,1,2} 的最終能源/樣本/成功行動次數：6/5/3。{2,1,1} 是否相同：相同，也是 6/5/3（20−2−6−6=6；1+2+2=5；3 次成功）。只有最終三個數字，能否分辨路線：不能，兩條路線順序不同但最終數字一樣，所以需要逐步日誌。

## B｜固定資料：陣列與計數（30 分鐘）

教科書 §7.2–7.4、§7.7。先讀 ArrayBasics，再執行。

| 項目 | 預測 | 本人實際結果 |
|---|---|---|
| script.length | 3 | length=3 |
| new int[4] 內容 | 首次回答「算的次數」（答成用途，未預測內容） | [0, 0, 0, 0] |
| new String[2] 內容 | 首次回答「顯示掃描結果」（答成用途，且用途不正確） | [null, null] |
| 逐步指令輸出 | 首次回答不知道 | step 1: 1；step 2: 1；step 3: 2 |
| 指令數值加總（不是行動數） | 首次回答不知道 | sum=4 |

1. length 是欄位，不加括號；陣列合法索引範圍是：0 到 length−1；script 長度為 3，範圍是 0–2。（首次預測時回答不知道）
2. 在副本取消越界行註解，編譯並執行，記錯誤種類與索引：編譯成功，執行時印完前面內容後在第 23 行發生 `ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3`，索引 3 超出合法範圍 0–2。隨即恢復（只改 /tmp 副本，src 原檔未更動）。
3. 執行 CommandCounter：腳本 {3,9,0,1,1,-1}，預測 counts：執行前理解為「索引代表行動、值代表出現次數」，未算出最後數字；實際：[1, 2, 0, 1]。9 和 -1 為何不得當索引：本人回答「防止無效次數」；講解後補充：counts 只有索引 0–3，9 和 -1 都超出範圍，直接當索引會發生 ArrayIndexOutOfBoundsException，所以要先用 if 檢查，它們也不計入 counts。
4. 把 CommandCounter 的固定資料改為 {0,2,2,3}，預測：未提供；實際：counts=[1, 0, 2, 1]；恢復原示範（只改 /tmp 副本，src 原檔未更動）。
5. 一般 for 適合需要步號/索引；加強版 for 適合只讀每筆值。若在 `for (int command : script)` 內寫 command=0，是否改掉 script 的元素：本人預測會改掉（印出 [0, 0, 0]），實測結果相反，不會改掉，因為 command 只是元素值的複本。用副本檢查：在 /tmp 另寫 ForEachTest，script={1,1,2}，迴圈內 command=0 後印出 [1, 1, 2]。

## C｜參考與方法（10 分鐘）

教科書 §7.8–7.9。ArrayReference 的兩個變數先指向同一陣列；畫箭頭或用文字描述。

| 時間點 | 預測 energy 內容 | 本人實際 |
|---|---|---|
| alias[0]=19 後 | 19,14,18 | [19, 14, 8] |
| changeFirst 後 | 19,14,18 | [18, 14, 8] |
| replaceLocal 內的新陣列 | 19,14,18 | [20, 20, 20] |
| 回到 main 後 | 不知道 | [18, 14, 8] |
| == 與 Arrays.equals 的兩個結果 | 4、4 | false、true |

Java 傳值；陣列參數取得的是：參考值（的複本）。為何修改元素看得見，重新指定參數卻不改呼叫端：本人原先回答不懂；講解後理解：參數和呼叫端的變數指向同一個陣列，改元素是改這個共同的陣列，所以看得見；重新指定參數只讓方法內的變數改指新陣列，呼叫端的變數仍指向原陣列。

## D｜筆數會增加：ArrayList（15 分鐘）

教科書 §7.16、§16.3–16.4。C# List<T> 若尚未學過，以右欄 Java 為準。

先分清腳本與日誌：{1,1,1,1}有四筆預定指令，但從20/0出發第三次探索就達標，因此只記三筆。陣列可搭配有效筆數保存；ArrayList每記錄一步就add，size()直接取得已存筆數，本週用它練習逐筆記錄。

| 操作 | C# List<int> | Java ArrayList<Integer> |
|---|---|---|
| 筆數 | Count | size() |
| 讀第 i 筆 | list[i] | get(i) |
| 改第 i 筆 | list[i]=x | set(i,x) |
| 尾端新增 | Add(x) | add(x) |
| 刪索引 | RemoveAt(i) | remove(i) |

執行 ListBasics：

| 項目 | 預測 | 本人實際 |
|---|---|---|
| 原清單/size/first | 未自行預測（看講解示範：[1, 1, 2] / 3 / 1） | [1, 1, 2] / 3 / 1 |
| set(1,3) 後 | [3, 3, 2] | [1, 3, 2] |
| remove(0) 後 | [3, 2] | [3, 2] |
| remove(Integer.valueOf(3)) 後 | [2] | [2] |
| isEmpty() | false | false |

int 放入清單會裝箱，讀成 int 會拆箱；ArrayList<int> 為何不能編譯：本人回答「給的值和設定的形態不同」；講解後補充：泛型的型態參數必須是參考型態，int 是基本型態，應改用 Integer。副本實測編譯訊息：`error: unexpected type`、`required: reference`、`found: int`。可以在副本重現編譯訊息，但必須恢復可編譯版本。remove 的兩種用途只需能分辨，不要求說明完整過載規則。

## E｜新增、回放、計數（35 分鐘）

教師提供第三週的行動規則、迴圈與方法骨架，先只完成 TODO **2→3→1**。每完成一項就編譯。F1 再完成 4、5。

- TODO 2：每步執行後把 command、energy、result 同時 add；拒絕、無效與返回也要保存。
- TODO 3：同一索引讀三條清單，格式 `step 1: command=1, result=OK, energy=14`。
- TODO 1：建立 int[4]，計算日誌中的 0–3 指令；越界指令不進 counts。

{3,9,0} 的逐步追蹤：

| 步 | 指令 | 預測結果/執行後能源 | 本人實際結果/能源 |
|---|---|---|---|
| 1 | 3 | OK / 20 | ALREADY_FULL / 20 |
| 2 | 9 | UNKNOWN_COMMAND / 20 | UNKNOWN_COMMAND / 20 |
| 3 | 0 | RETURN / 20 | RETURN / 20 |

此時 commandLog/energyLog/resultLog 各有幾筆：本人預測 0 筆；實際各 3 筆。actionCount 是幾次：本人預測 1 次；實際 0 次。差異原因（對照輸出後理解）：日誌每一步都記錄，包含拒絕、無效與返回，所以有 3 筆；actionCount 只計成功行動，這三步都不是 OK，所以是 0。

完成 F1 的摘要後，補齊六條本人測試。每列修改 main 固定腳本並重新編譯，初始值 20/0；將**完整輸出**貼在本表下方，包含回放、counts、摘要、最低能源、最終狀態與 battery。

| script | 預期狀態/結果 | 本人實際狀態/結果 | counts、摘要、每筆回放均相符？ |
|---|---|---|---|
| {0} | 20/0/0 RETURNED | 20/0/0 RETURNED | 相符 |
| {1,1,2} | 6/5/3 SUCCESS | 6/5/3 SUCCESS | 相符 |
| {2,2,2,2,2} | 10/5/5 SUCCESS | 10/5/5 SUCCESS | 相符 |
| {3,9,0} | 20/0/0 RETURNED | 20/0/0 RETURNED | 相符 |
| {1,1,1,1} | 2/6/3 SUCCESS | 2/6/3 SUCCESS | 相符（第 4 筆指令未執行，ignored=1） |
| {2,1} | 12/3/2 UNFINISHED | 12/3/2 UNFINISHED | 相符 |

完整輸出（在 /tmp 副本依序替換腳本、重新編譯後執行，src 原檔未更動）：

```text
##### script={0}
=== replay ===
step 1: command=0, result=RETURN, energy=20
counts[0..3]=[1, 0, 0, 0]
steps=1, ok=0, rejected=0, ignored=0
lowest energy=20
final energy=20, samples=0, actionCount=0 -> RETURNED
battery=100.0
##### script={1, 1, 2}
=== replay ===
step 1: command=1, result=OK, energy=14
step 2: command=1, result=OK, energy=8
step 3: command=2, result=OK, energy=6
counts[0..3]=[0, 2, 1, 0]
steps=3, ok=3, rejected=0, ignored=0
lowest energy=6
final energy=6, samples=5, actionCount=3 -> SUCCESS
battery=30.0
##### script={2, 2, 2, 2, 2}
=== replay ===
step 1: command=2, result=OK, energy=18
step 2: command=2, result=OK, energy=16
step 3: command=2, result=OK, energy=14
step 4: command=2, result=OK, energy=12
step 5: command=2, result=OK, energy=10
counts[0..3]=[0, 0, 5, 0]
steps=5, ok=5, rejected=0, ignored=0
lowest energy=10
final energy=10, samples=5, actionCount=5 -> SUCCESS
battery=50.0
##### script={3, 9, 0}
=== replay ===
step 1: command=3, result=ALREADY_FULL, energy=20
step 2: command=9, result=UNKNOWN_COMMAND, energy=20
step 3: command=0, result=RETURN, energy=20
counts[0..3]=[1, 0, 0, 1]
steps=3, ok=0, rejected=2, ignored=0
lowest energy=20
final energy=20, samples=0, actionCount=0 -> RETURNED
battery=100.0
##### script={1, 1, 1, 1}
=== replay ===
step 1: command=1, result=OK, energy=14
step 2: command=1, result=OK, energy=8
step 3: command=1, result=OK, energy=2
counts[0..3]=[0, 3, 0, 0]
steps=3, ok=3, rejected=0, ignored=1
lowest energy=2
final energy=2, samples=6, actionCount=3 -> SUCCESS
battery=10.0
##### script={2, 1}
=== replay ===
step 1: command=2, result=OK, energy=18
step 2: command=1, result=OK, energy=12
counts[0..3]=[0, 1, 1, 0]
steps=2, ok=2, rejected=0, ignored=0
lowest energy=12
final energy=12, samples=3, actionCount=2 -> UNFINISHED
battery=60.0
```

繳交前恢復 {1,1,2}（src 原檔一直維持 {1,1,2}）。課堂沒完成的路線，課後補齊本人實測。

## F｜摘要、需求與反例（30 分鐘）

### F1 Collections 與 TODO 4–5（10 分鐘）

先執行 CollectionsDemo，預測 min/max/frequency：6/20/1；實際：6/20/1。排序後 original：預測與實際皆為 [14, 8, 20, 6]；sorted：預測與實際皆為 [6, 8, 14, 20]。為何不能只排序 energyLog 來做回放：本人回答（引自程式註解）「避免排序破壞原始任務的時間順序」；講解後補充：例如日誌 [14, 8, 6] 排序後變成 [6, 8, 14]，step 1 會印 6，但第一步實際能源是 14，回放就錯了。

- TODO 4：ok=frequency(resultLog,"OK")，returned=frequency(resultLog,"RETURN")；steps=size，rejected=steps-ok-returned，ignored=scriptLength-steps。
- TODO 5：isEmpty 為 true 時印 `lowest energy=N/A`；否則才用 min。
- 最低能源只看每步結束後的 energyLog，不含初始能源。

教師使用 FixedScriptMission 示範以下案例；學生填預測與觀察，不需另寫入口。未觀察請註明待補；自己另跑者再註明「本人複驗」。

| 初始 energy/samples；script | 預測結果/日誌筆數/最低能源 | 教師實際示範觀察 |
|---|---|---|
| 5/0；{1,3,0} | 待填 | 待補（未觀察到教師示範） |
| 2/4；{2} | 待填 | 待補（未觀察到教師示範） |
| 0/0；{3,1} | 待填 | 待補（未觀察到教師示範） |
| 20/5；{1} | 待填 | 待補（未觀察到教師示範） |
| 20/0；{} | 待填 | 待補（未觀察到教師示範） |

### F2 Prompt 設計（10 分鐘）

目的：把剛才實作的日誌轉成清楚的資料需求，作為判斷 AI 建議是否適合的依據。

先填下表，再自行組成可直接提問的一段 Prompt。接著用這份需求判讀下方兩個模擬建議。沿用原本的 MissionLog，本段產出為一段 Prompt 與兩項判斷；真實 AI 服務可選。

| 要交代的需求 | 本人的內容 |
|---|---|
| 欄位與型態 | 待填 |
| 筆數是否事先固定 | 待填 |
| 是否保留順序 | 待填 |
| 是否容許重複 | 待填 |
| 新增、回放、計數與最低值等操作 | 待填 |
| 允許的技術與驗證案例 | 待填 |

完整 Prompt：待填。

判讀下列**模擬建議**：

| 建議 | 採納/拒絕/補充條件與理由 |
|---|---|
| 歷程最多用 int[100] 保存就好 | 待填：上限、有效筆數、超過時如何處理？ |
| ArrayList 保存日誌，HashSet 保存指令，避免重複 | 待填：任務可重複探索，順序與重複是否必要？ |

不要求實作 HashSet；依 List/Set/Map 的概觀判讀即可。使用固定腳本時筆數有上限，但日誌仍逐步成長且將來可能回到互動輸入；不能一概宣稱陣列無法記錄歷程。

### F3 模擬 AI 除錯二選一（10 分鐘）

先 commit 人工版，在副本測試，最後恢復正確版。

1. 刪掉 countCommands 的範圍檢查，理由「指令只有 0–3」；反例可用 {3,9,0}。
2. 把 printReplay 的 `< commandLog.size()` 改成 `<= commandLog.size()`，理由「最後一步也要印」；反例可用 {0}。

| 證據 | 本人紀錄 |
|---|---|
| 選哪種建議與反例；預期行為 | 待填 |
| 修改前實際結果/錯誤訊息 | 待填 |
| 最小修正與採納/拒絕理由 | 待填 |
| 修正後反例實際結果 | 待填 |
| 修正後 {1,1,2} | 待填，可引用 E 的本人輸出 |
| 修正後 {3,9,0} | 待填，可引用 E 的本人輸出 |

若時間不足，課後補測；AI_USAGE 的驗證欄可引用本表，不必重抄。

## G｜課末檢核與第五週入口（5 分鐘）

1. length、size()、length() 分別用於什麼：待填。
2. 三筆日誌是否必定代表三次成功行動：待填。
3. 三條清單漏加其中一筆會發生什麼：待填。
4. 為何固定四種指令計數用陣列，逐步增加的日誌用 ArrayList：待填。
5. 下週如何把同一步三個欄位整合在一起：待填，本週不要求寫類別。

## H｜保存、推送與取回（15 分鐘）

- 恢復預設腳本，重新編譯執行；LAB_REPORT、AI_USAGE、專案 README 完成。
- commit、push，核對 GitHub main 最新 commit 與來源檔。只 commit 或 Changes 空白不代表已推送。
- 在另一位置 clone，同一專案重新編譯並跑 MissionLog；課堂不足可課後完成。

GitHub repo/作業目錄：待填；最終 commit：待填。
本人新副本 JDK/編譯/預設輸出：待填，未做標示待補。
推送若失敗先請教師協助，仍無法推送時保存完整 repo 至不會還原的位置，確認可讀並後續補推。

## 課後選做

- ScriptArgs：執行 `java -cp out ScriptArgs 3 9 0`，說明 args 的元素型態；非整數例外不列必做。
- 新增自訂路線，比較達標時剩餘能源與成功行動數，不要求最佳化演算法。
- 只顯示成功步驟時，以 if 跳過顯示；保留原日誌，驗證 counts 和摘要仍相同。
