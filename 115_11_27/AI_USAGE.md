# 第八週AI使用與驗證紀錄

學生姓名／學號：陳昱齊／a113221008

| 使用目的與來源 | Prompt／建議摘要 | 採納或拒絕及理由 | 人工驗證方式與結果 | 最終修改 |
|---|---|---|---|---|
| 課堂Code Review，教師設計的模擬AI（AI_Check_Inheritance/src/HeavyRover.java，非真實對話） | 模擬AI的HeavyRover另外宣告private energy、samples，drill()直接改這兩個欄位，也沒先檢查isStopped() | 拒絕：子類別欄位遮蔽父類別欄位，getter讀的是父類別狀態，drill改的卻是另一份；沒有停止檢查，任務結束後仍能鑽探；也沒有計入actionCount | 由Claude Code代為編譯執行HeavyRoverTry（非本人親自執行）：15行中9行「不符」，例如「鑽探後」實際20/0/0/1、預期12/3/1/1；「任務完成後鑽探」實際「成功」、預期「任務已停止」 | 模擬AI的HeavyRover依活動設計保留不改；正確做法是刪除重複欄位、先檢查isStopped()，再用spendEnergy、addSamples、countAction |
| 真實AI：Claude Code（2026-11-27），完成AI_Check_Inheritance的Rover TODO 1 | 請Claude Code完成TODO：實作spendEnergy（amount≤0或超過能源回false，否則扣除回true），再把explore、scan改成spendEnergy＋addSamples＋countAction | 全部採納，本人未修改：符合註解「只守住扣能源規則」，explore、scan不再直接改private欄位 | 由Claude Code代為編譯，能源不足的案例「7/0/0/0」相符；其餘不符都來自模擬AI的HeavyRover，不是Rover | Rover.java：spendEnergy完成，explore改用spendEnergy(6)、scan改用spendEnergy(2) |
| 真實AI：Claude Code（2026-11-27），檢查HomeWork作業 | 請Claude Code檢查HomeWork的問題 | 採納兩項修正：(1) CompareMissions誤把IntelliJ的參數名稱提示打進程式（如enegry:20、label:"一般探測車"），無法編譯，改成new Rover(20, 0)等一般寫法；(2) HeavyRover的exploreYield()補上@Override，符合TODO 2要求 | 由Claude Code代為編譯執行CompareMissions（非本人親自執行）：一般探測車最終6/5/3、任務成功；重型探測車探索兩次即6份樣本，略過1筆，最終8/6/2、任務成功。DrillMissionTry未執行 | CompareMissions.java（TODO 3）、HeavyRover.java（@Override）；Rover TODO 1、HeavyRover TODO 4原本已正確，未修改 |

本人E1預測（未執行前）：模擬AI加同名private explore的HeavyRover能編譯；new HeavyRover(20, 0)鑽探後再execute(1)，samples是2；execute(1)會走Rover的explore。
修正前commit／證據：待填（本次尚未commit）。
修正後commit／相同案例重測：待填（本次尚未commit；DrillMissionTry尚未執行）。
三至五句補強Prompt：待填。

AI宣稱通過不算人工驗證；以上執行結果皆由Claude Code代為執行，本人仍需親自重跑確認。
