# 第六週AI使用紀錄

| 欄位 | 本人紀錄 |
|---|---|
| 使用工具／方式 | 真實AI：Claude Code |
| 目的與提問摘要 | 請 Claude Code 直接完成 starter/Mission.java 的 TODO 1–3（run、countOk、lowestEnergy）；SampleCase 設計本次未進行 |
| 建議與本人處理 | TODO 1–3 由 Claude Code 撰寫，我全部採納，未修改：run 每筆先檢查 isStopped，執行後以 rover.getEnergy() 建立行動後快照加入 history，指令 0 記錄後設 returned=true 並 break；countOk 以 isSuccessful 計數；lowestEnergy 空清單回傳 -1，否則以第一筆為起點迴圈求最小。類別圖未修改 |
| 本人實際驗證 | 由 Claude Code 代為編譯與執行（非本人親自執行）：RoverDemo 輸出 OK/14/2/1、STOPPED/0、STOPPED/20；將此 Mission 與 teacher 資料夾檢查程式一起編譯，MissionLogCheck 15/15、MissionStateCheck 16/16 全部 PASS。E3 反例：未做 |

觀看教師操作只能記「教師示範觀察」。

## 我的反思

哪一句需求幫我判斷AI建議：待填。只看類別圖還不能證明什麼：待填。
