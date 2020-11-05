# お小遣い管理アプリ
## 【開発動機】 
自分自身の日常の課題解決の一環として 
既存のお小遣いアプリケーションでは、自身の状況に合った適切な管理が出来ない為
***
## 【課題】 
1. 毎月お小遣い金額が定まっておらず、不定期に入るお小遣いの管理 
1. 現時点の残金を一目で把握したい 
1. 支出登録をシンプルに使いやすく 
1. 支出用途を申告する必要がある為、履歴を残して行きたい
***
## 【解決する為に求められる機能】 
1. →不定期にお小遣いを追加登録できる機能 
1. →メイン画面に残金表示する機能 
1. →メイン画面で、必要最小限のステップで支出登録できる機能 
1. →データベースの連携
***
## 【具体的な実装方法】 
1. →月ごとでなく、anytimeに追加登録できるbutton設置 
1. →DBとリレーションし(収入－支出)で残金表示するオブジェクトの設置 
1. →日付,メモ,金額,カテゴリーに絞った支出登録ステップ
    	（入力動作をより短縮する為に日付はDatePicker,カテゴリーはToggleButtonによる選択肢とする)
1. →Connector/J によるMySQLとの接続
***
## 【開発を終えて学んだ事】 
