# 第三週 AI 使用紀錄

| 欄位 | 記錄 |
|---|---|
| 使用工具 | Claude Code |
| 使用目的與提問摘要 | 請 Claude Code 檢查各週作業完成度，並帶我逐步完成 RoverMission 的 TODO D1–D3（探索、掃描、充電）：講解規則與 if/else 寫法、給提示、檢查我寫的程式 |
| AI 建議與本人處理 | D1–D3 程式由我自己撰寫，Claude Code 只講解與提示，不代寫。（Claude Code 曾先代寫一版，我決定還原成空白後自己重寫，未採用代寫版。）Claude Code 指出的問題與我的修改：D1 原本寫 `energy-6,sample+2`，它指出算式沒存回變數、變數名應為 samples、敘述要用分號，且漏了計次，我改成三行指定敘述；D3 漏了兩個分號與計次，我補上。它說明 `>=20` 與 `>20` 結果相同，我保留 `>=20`。最後由 Claude Code 將我改好的程式原樣放入檔案 |
| 實際驗證結果 | 由 Claude Code 代為編譯與執行測試（非本人親自執行）：五次 2 → 10/5/5 SUCCESS；3、9、0 → ALREADY_FULL、UNKNOWN_COMMAND，能源維持 20 → RETURNED；2、3、0 → 18 充電封頂為 20、actionCount=2 → RETURNED；1、1、1 → 2/6/3 SUCCESS。皆符合規則。預設初始 20/0 無法觸發 NOT_ENOUGH_ENERGY，未測 |

只記錄實際發生的使用與測試；AI 說「正確」不能代替實際執行。不要上傳個資、密碼或未授權資料。
