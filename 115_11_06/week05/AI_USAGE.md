# 第五週 AI 使用紀錄

| 欄位 | 本人紀錄 |
|---|---|
| 使用工具／方式 | 真實AI：Claude Code |
| 目的與提問摘要 | 請 Claude Code 帶我逐步完成 HomeWork 的 MissionStep TODO 1–3 與 MissionLog TODO 4–6：講解建構子、this、getter、字串內容比較，給提示並檢查我寫的程式；後段請它直接完成剩餘部分 |
| 建議與本人處理 | （Claude Code 曾先代寫一版，我決定還原成空白後重寫，未採用該版。）**本人撰寫**：TODO 1 建構子三行 this 指定；TODO 2 三個 getter；TODO 3 `return "OK".equals(result);`——過程中它指出我少了 return、誤寫 `return boolean;`、`return result;`（String 不能轉 boolean），我依提示修正。TODO 4 的 `history.add(step);` 由我寫，`new MissionStep(...)` 那行是它示範的。TODO 5 迴圈、取出 step 與 `step.getCommand()` 由我寫；它指出直接印 `history.get(i)` 會印出物件代號，最後兩格 `step.getResult()`、`step.getEnergy()` 由我請它代填。**Claude Code 撰寫**：TODO 6（走訪 history，以 isSuccessful 與 "RETURN".equals 計數）由我請它直接完成，我採納未修改 |
| 本人實際驗證 | 由 Claude Code 代為編譯與執行（非本人親自執行）：{1,1,2} 得 14→8→6、counts=[0,2,1,0]、steps=3 ok=3、6/5/3 SUCCESS；{3,9,0} 得 ALREADY_FULL、UNKNOWN_COMMAND、RETURN，rejected=2、20/0/0 RETURNED；空腳本 lowest energy=N/A、UNFINISHED。三條輸出與第四週 MissionLog 相同 |

AI 的正確宣稱不能代替編譯執行。觀看教師展示只能記「教師示範觀察」；本人複驗才可記本人測試。
