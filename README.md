# PocketMoney　お小遣い管理アプリケーション


# 記事の概要

ご覧頂き誠にありがとうございます。

私が作成したポートフォリオの解説です。
作った背景から、作成手順、機能、工夫したところ、
課題をまとめました。

# 背景

私は愛媛県松山市にて妻と子供と計５人で暮らしています。

生活スタイルというのは、家庭ごと様々あるように、
世のお父様達の懐事情も様々だと思います。

我が家ではいわゆる"お小遣い制度"が導入されているのですが、
その形態も我が家ならではのスタイルとなっています。

一般的に最も多いスタイルは”定額制”と言われていますが、
我が家の場合は金額が決まっていません。

使い放題かと思われるかもしれませんが、そうではありません。仕組みはこうです。

![フローチャート.PNG](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/0fd2ce88-8fa9-b0a4-f038-72bcf29809f6.png)

ポイントは最下層の妻への申請時です。非常に心労を伴います。
おのずと無駄遣いを避け、堂々と主張できるお金の使い方に限られてきます。
我が家にとっては自然と余計な出費が抑えられる良いシステムです。

しかし何に使ったか記録していくのが大変です。
既存の管理アプリなど試しましたが、そもそも我が家の動的な
お小遣いシステムにピタッとハマるものはありませんでした。

そこで課題を解決するべく、アプリを作ろうと考えました。

また、現職を退職し、バックエンドエンジニアになろうと考えていたので、
このアプリを転職活動のポートフォリオとすることに決めました。

# 目的

- Javaを用いたアプリケーション開発経験を積む
- フルスクラッチ開発によってアプリの基本的な構成、動作を知る
- わずかでもIT企業様の即戦力になれる技術を身につける
- 作成アプリによって我が家のお小遣い運営を円滑にする

# スペック

<dl>
 <dt>言語</dt>
 <dd>Java SE 11.0.9</dd>
</dl>
<dl>
 <dt>DBMS</dt>
 <dd>MySQL 15.1</dd>
</dl>
<dl>
</dl>
<dl>
 <dt>開発環境</dt>
 <dd>Windows10 pro 20H2</dd>
 <dd>Eclipse 4.17.0</dd>
 <dd>e(fx)clipse 3.5.0</dd>
 <dd>SceneBuilder 2.0</dd>
</dl>
<dl>
 <dt>バージョン管理</dy>
 <dd>Git 2.29.2</dd>
</dl>
<dl>
 <dt>本番環境</dt>
 <dd>ローカル</dd>
</dl>

# 主な機能

お小遣いを動的に管理することができます。

- 支出登録

日付とカテゴリーと金額を入力することで、支出を登録できます。
妻への報告のため、空欄があると困るので入力がないとエラーメッセージがでます。
メモは必要に応じて自由に登録することができます。

![支出登録.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/cbb14a9d-1866-d3f2-b9fe-6068811d49e1.gif)


- お小遣い（収入）登録

日付と金額を入力することで、新たなお小遣いを登録できます。
空欄があると妻に聞かれた場合に困るので、入力がないとエラーメッセージがでます。
必要に応じて自由にメモも登録することができます。

![お小遣い登録.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/8077cdc7-9996-571c-c26f-573a98947fb4.gif)

- 残高表示

収入-支出の残高をホーム画面で確認することができます。
新たに収入もしくは支出登録を行い、ホーム画面へ戻ると反映されます。

![残高表示.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/ea924b41-6ed8-1a9e-aa2a-97b9988b9507.gif)


- お小遣い履歴表示

お小遣い履歴を表形式で確認することができます。

![お小遣い履歴.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/6da405d3-9785-7f14-0821-58008de4ea33.gif)



- 支出検索機能

登録した支出履歴に対して、複数の検索条件で検索することができます。

![支出検索.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/c84c212c-dbee-6036-6e16-f5a3734c7b05.gif)


- 削除機能

各登録完了画面にて、直前の登録を削除することができます。

# 開発手順

1. 要件定義
1. GUI設計
1. 環境選定
1. GUI設計
1. データベース設計
1. コーディング
1. ローカル環境へデプロイ

### 1.要件定義

今回作成するアプリに必要な機能は

- お小遣い登録
- 支出登録
- 残高表示
- 履歴表示
- 履歴検索

の為、それらの情報が保存できるデータベースと、
データベースからユーザーが分かりやすいように
まとめて表示できる動的なビューの機能が必要となる。

また、ユーザーが登録〜削除まで行えるよう
削除機能を設けるが、基本的に全データを保存していく方針。
よってユーザーが誤った登録をしてしまった場合に、
直前のデータのみ削除できる機能を設ける。

### 2.環境選定

もともと機械学習にも興味があり、流行りのPythonも考えていたのですが

- 情報量の多さ
- 言語として体系化されている
- Pythonよりも内部の処理の流れが把握しやすくしっかり基本を身に付けられそう

という観点から見て、すでに学習を始めていたJavaを使用言語としました。

DBMSは、ProgateでSQLを学んでいた際に環境構築の
チュートリアルで紹介されていた流れでMySQLの使用を決めました。

GUI実装方法ですが、JavaSwingでToDoアプリの模写をやっていたので、
その時と違う環境でやりたかったのと、単純にSceneBuilderという
非常に便利なGUIツールに出会った嬉しさにより、そうなりました。


### 3.データベース設計

必要機能よりどんな情報を保存するか、
それらをどう関連付けて管理するか考えながら
データベース設計を行いました。

データベース設計のノウハウは、Progate。
接続方法は書籍「スッキリわかるJava入門実践編」にて学習。

ただ、テーブル作成の段階で不便さに気づき、そこから立ち返って
修正することもありました。

使用ソフト:[MySQLデータベース定義書作成ツール](https://www.softel.co.jp/labs/tools/mysql/)
![テーブル定義書.PNG](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/014cb250-d7de-9782-c58f-d6bfd8944317.png)



使用ソフト:[WWW SQL Designer](https://ondras.zarovi.cz/sql/demo/?keyword=default)
![table_definition.PNG](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/39041821-2f35-cf29-ab7a-94663637991a.png)


### 4.GUI設計

やってみないと解らない所が多々あったので、
SceneBuilderを触りながら画面遷移図をまとめました。
ただ、実装の段階で思ったものと違っていたり、
こんな事もできるんだという発見などもあり、何度も修正が加わりました。
従って以下の図は作成し直したものです。

使用ソフト:[diagrams.net(Draw.io)](https://app.diagrams.net/)
![fxml画面遷移図 (2).png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/07ca913b-c599-8741-b246-44c41eb2a63a.png)



### 5.コーディング

正直これもやってみないと解らない所も多々あったので、
設計が固まりきっていなかったですが、コーディングをスタート。

#### 5.1データベース作成

ローカル環境のMySQLサーバに
専用ユーザ作成し、GitBashでデータベースを作成。
その後、テーブルを作成。

<details><summary>テーブル作成SQL</summary>
<div>

```sql

CREATE TABLE imcomes(
    id INT AUTO_INCREMENT,
    imcome INT(10) not null, memo VARCHAR(20),
    imcomed_at DATE not null, PRIMARY KEY(id)
);

CREATE TABLE pays(
    id INT AUTO_INCREMENT, 
    category_id INT(10) not null, money INT(10) not null,
    memo VARCHAR(20), 
    paid_at DATE not null, PRIMARY KEY(id)
);

CREATE TABLE categorys(
    id INT AUTO_INCREMENT,
    category VARCHAR(20) not null, PRIMARY KEY(id)
);
```
</div>
</details>

#### 5.2 全体の形、フォームをSceneBuilderで作成

GUI設計を見直しつつ、画面を構築。

#### 5.3 fxmlとコントローラークラスのバインド

各コントローラークラスを作成。SceneBuilderにてバインドし、
そのままfx:idとアクションイベント名を指定。
サンプル・コントローラー・スケルトン表示機能を用い
タイプミスを低減させながらコントローラークラスに実装。

#### 5.4 Githubのissueによるタスク管理

個人開発でもチーム開発を意識するためにissue管理開始。

・タスクを数時間〜1日程度で完了する程度に細かく分けissueにする
・issueとプルリクを連携させ、マージされたらIssueもclose。

どのようにプルリクを記述したら全員が読みやすいか？
必要な情報が伝えられるか？現場ではどんなルールがあるのか？
様々に自問自答しながら、あらゆる書き方で試行錯誤を続けました。

#### 5.5 ToggleButtonの選択状況の監視

トグルボタンをグループに組み込み、
setUserDataメソッドで各特定の値を設定。
どのトグルボタンが押されたか？
またはいずれも押されてないか？を知るラムダ式関数で
押されたボタンの値を取得、押されない場合は別途値を代入。

<details>
<summary>(一例)トグルボタン関数</summary>
<div>

```Java:トグルボタン関数
		//トグルボタンへの値の割り当て
		FoodExpenses.setUserData(1);
		DailyNecessities.setUserData(2);
		SkillUp.setUserData(3);
		Other.setUserData(4);

		//どのトグルボタンが押されたか？または、どのトグルボタンも押されていないか？監視
		ToggleGroup.selectedToggleProperty().addListener(
			(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {
				
				//選択されていれば
				if (new_toggle != null)
					//該当のトグルボタンに割り当てられた値を代入
					catchNumber =((int) new_toggle.getUserData()); 		
				
				//どれも選択されてなければ
				else 
					//トグルボタンに割り当てられていない値を代入
					catchNumber = 0;
		});
```

</div>
</details>
#### 5.6 入力フォームの処理

入力ボタンが押された際にフォームに
必要な入力が無ければアラート表示させる。
（Memoは任意のため除外。）

入力が確認できれば各データ型に合わせて
ローカル変数に代入。

接続クラスのINSERTメソッドを呼び出し渡す。

<details>
<summary>(一例)入力フォームの処理</summary>
<div>

```Java:入力フォームの処理一部抜粋
	@FXML
	public void onAddPayButtonCliked(ActionEvent event)  {

		//例外処理
		try {
		//入力が無ければアラートを表示
		Window owner = AddPayButton.getScene().getWindow();

		if (DatePicker.getValue() == null) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null, "日付を入力して下さい。");
			return;
		}
		if (PayMoneyTextField.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null, "金額を入力して下さい。");
			return;
		}
		if (catchNumber == 0) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null, "カテゴリーを選択して下さい。");
			return;
		}

		//入力内容を各データ型で取得し、Daoクラスへ渡す
		LocalDate paid_at = DatePicker.getValue();
		String memo = MemoTextField.getText();
		String stmoney = PayMoneyTextField.getText();
		int money = Integer.parseInt(stmoney);		
		int category_id = catchNumber;

		//DaoクラスのINSERTメソッドを呼び出し渡す
		MySQLDao.insertRecord(paid_at, memo, money, category_id);

		/*
		* 画面遷移部分省略
		*/

		}catch(IOException | SQLException e) {
			e.printStackTrace();
		}
	}
```
```Java:showAlartメソッド
	/**
	 * showAlertメソッド
	 * アラート表示内容定義
	 */
	private void showAlert(Alert.AlertType alertType, Window owner, String title, String header, String message) {
		
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}


```
</div>
</details>

#### 5.7 データベース入力

接続確認も兼ねて、入力フォームからのデータを入力。
保守性を考慮し、接続情報とSQLはDaoクラスにまとめる。

SQLインジェクション対策として、カラム名に
プレースホルダーを代入する形でSQL文を準備。

JDBCオブジェクトを生成し、データベースに接続。
try~catchを用い、成功しているかどうか判断。

その後入力フォームからのパラメータを
プレースホルダにバインドし、MySQLに挿入。

<details>
<summary>(一例)データベースINSERT部分</summary>
<div>

```Java:データベースINSERT部分
	//paysテーブルへデータ挿入
	public static void insertRecord(LocalDate paid_at, String memo, int money, int category_id) throws SQLException {
		
		//SQL文はカラム名にプレースホルダーを代入する形で準備
		final String INSERT_QUERY = "INSERT INTO pays (paid_at, memo, money, category_id) "
				                  + "VALUES (?, ?, ?, ?)";
		//例外処理
		try (
	            //データベース接続
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = conn.prepareStatement(INSERT_QUERY)) {

			    //入力フォームからのパラメータをプレースホルダにバインドし、データベースへ挿入
				ps.setObject(1, paid_at);
				ps.setString(2, memo);
				ps.setInt(3, money);
				ps.setInt(4, category_id);
				ps.executeUpdate();
				System.out.println(ｐｓ);

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
```

</div>
</details>

#### 5.8 画面遷移

GUI設計を確認しながら、全体の画面遷移を実装。
現在表示されている画面を閉じて、新しい画面を生成。

<details>
<summary>(一例)画面遷移</summary>
<div>

```java:画面遷移
		//getScene()メソッドによってシーンを取得
		Scene s = ((Node)event.getSource()).getScene();
		//Windowクラスのhide()メソッドで現在の画面を閉じる。
		Window window = s.getWindow();
		window.hide();

		//例外処理
		try {
			//入力完了画面へ遷移

			//Parentクラスの変数に、FXMLLoaderクラスのloadメソッドの引数で指定したfxmlファイルを入れる。
			Parent parent = FXMLLoader.load(getClass().getResource("/PayDone.fxml"));
			//Sceneクラスのインスタンスにセット
			Scene scene = new Scene(parent);
			//Stageクラスのインスタンスにセット
			Stage stage = new Stage();
			stage.setScene(scene);
			//タイトル設定
			stage.setTitle("登録完了");
			//showメソッドで新しい画面を表示させる。
			stage.show();

		}catch(IOException e) {
			e.printStackTrace();
		}
```

</div>
</details>


#### 5.9プロパティクラス作成

データベースをTableViewに表示させるため、
カラム名のフィールド保持したプロパティクラス作成。

クエリの結果をResultsetから
フィールドにセットできるよう準備。

<details>
<summary>(一例)プロパティクラス作成</summary>
<div>

```java:TableViewPropertyクラス一部抜粋
public class TableViewProperty {
	
	private StringProperty category;
	private StringProperty imcome;
	
	public StringProperty categoryProperty() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = new SimpleStringProperty(category);
	}
	
	public StringProperty imcomeProperty() {
		return imcome;
	}
	
	public void setImcome(String imcome) {
		this.imcome = new SimpleStringProperty(imcome);
	}
}
```
</div>
</details>

#### 5.10 データベース出力

プロパティクラスをインスタンス化し、
クエリ実行結果をプロパティクラスの
フィールドにセット。

インスタンスを格納したlistを
戻り値として呼び出し元へ返す。

呼び出し元で戻り値を
TableViewにセットし表示させる。

<details>
<summary>データベース出力</summary>
<div>

```Java:呼び出し先メソッド
	//”支出”履歴画面のTableViewに表示させるデータの取得
	public ObservableList<TableViewProperty> selectTableViewPays() throws SQLException {
		final String SUM_QUERY = "SELECT paid_at, category, memo, money "
							   + "FROM pays "
							   + "JOIN categorys "
							   + "ON pays.category_id = categorys.id "
							   + "ORDER BY paid_at;";
		
        //プロパティクラスのインスタンスを格納するlist
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		
        //例外処理
        try (
            //データベース接続
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SUM_QUERY)) {

            //ResultSetからプロパティクラスのフィールドへセット
			while(rs.next()){
				
				//プロパティクラスのインスタンス生成
				TableViewProperty tbl = new TableViewProperty();
                //フィールドにセット
				tbl.setPaid_at(rs.getString("paid_at"));
				tbl.setCategory(rs.getString("category"));
				tbl.setMemo(rs.getString("memo"));
				tbl.setMoney(rs.getString("money"));
				//インスタンスを格納
				list.add(tbl);
			}
		}catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//呼び出し元へ返す
		return list;
	}
```
```java:呼び出し元メソッド
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//例外処理
		try {
            //Daoクラスのインスタンス生成
			MySQLDao mysq = new MySQLDao();
			
			//TableViewに表示させる
			table.setItems(mysq.selectTableViewPays());
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
```
</div>
</details>

#### 5.11 検索機能実装

コンボボックスの検索条件はfxmlで設定。

検索ボタンが押されたら
getSelectionModel()、getSelectedItem()メソッド
で文字列を取得し、Daoクラスメソッドを呼び出し渡す。

受け取った文字列をswitch文で判別し、各SOLとバインド。
クエリ実行し、結果を戻り値で返す。

戻り値を呼び出し元でTableViewにセットし表示。

<details>
<summary>検索機能</summary>
<div>

```xml:fxmlによるコンボボックス(一部抜粋)
		<ComboBox fx:id="cbBox" layoutX="107.0" layoutY="115.0" prefHeight="30.0" prefWidth="214.0" AnchorPane.bottomAnchor="440.0" AnchorPane.leftAnchor="107.0">
			<items>
				<FXCollections fx:factory="observableArrayList">
					<String fx:value="日ごとにいくら使ったか" />
					<String fx:value="一回当たり平均額" />
					<String fx:value="3000円以上の支出" />
					<String fx:value="カテゴリーごとの合計額" />
					<String fx:value="今月の合計額" />
					<String fx:value="総合計額" />
				</FXCollections>
			</items>
		</ComboBox>
```

```java:検索ボタンアクションメソッド
	@FXML
	void onSearchButton(ActionEvent event) {

		//検索条件ごとの文字列をコンボボックスから取得
		String search = cbBox.getSelectionModel().getSelectedItem();
		
		//例外処理
		try {
			//接続クラスのインスタンス生成
			MySQLDao mysq = new MySQLDao();
			//呼び出し先からの戻り値データをTableViewに表示させる
			table.setItems(mysq.searchTableView(search));
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
```

```java:検索結果の取得メソッド(一部抜粋)
	public ObservableList<TableViewProperty> searchTableView(String search) throws SQLException {
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		String SERACH_QUERY = null;
		
		//それぞれ選択された検索ワードに応じて、SQLをバインド
		switch(search) {		
			
		//文字列判別
		case "カテゴリーごとの合計額":
			SERACH_QUERY = "SELECT category, "
						 + "SUM(money) "
						 + "FROM pays "
						 + "JOIN categorys "
						 + "ON pays.category_id = categorys.id "
						 + "GROUP BY category_id;";
	        //データベース接続
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
	            //ResultSetからプロパティクラスのフィールドへセット
				while(rs.next()){
					//プロパティクラスのインスタンス生成
					TableViewProperty tbl = new TableViewProperty();
	                //フィールドにセット
					tbl.setCategory(rs.getString("category"));
					tbl.setMoney(rs.getString("SUM(money)"));
					//インスタンスを格納
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
			
		//文字列判別
		case "今月の合計額":
			SERACH_QUERY = "SELECT SUM(money) "
						 + "FROM pays "
						 + "WHERE paid_at > "
						 + "DATE_SUB(NOW(), INTERVAL 1 MONTH) "
						 + "ORDER BY paid_at ASC;";
	        //データベース接続
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
	            //ResultSetからプロパティクラスのフィールドへセット
				while(rs.next()){
					//プロパティクラスのインスタンス生成
					TableViewProperty tbl = new TableViewProperty();
	                //フィールドにセット
					tbl.setMoney(rs.getString("SUM(money)"));
					//インスタンスを格納
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		return list;
	}
```
</div>
</details>

#課題点

一区切りついたところで完成としましたが、
課題点は多数あるのでリストアップします。
今後の学習の指標とします。

###データベース接続情報が別ファイル化されていない。

ハードコーディングにより保守性を損なうだけでなく
安全性の為にもデータベース接続情報は別ファイルから
読み出すのが望ましい。

###バリデーションされていない

現状、入力有無だけは網を張れるが、基本的な
バリデーションが皆無なので脆弱性に繋がる。

###カテゴリーに拡張性がない

支出のカテゴリーの選択肢が、デフォルトのままで
追加できない。今回の自身のニーズに照らし合わせれば
必要はなかったが、エンジニアとして不特定多数が
利用するユーザビリティを常に意識しなければならない。
![カテゴリ.PNG](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/1d29f8aa-b03b-74a8-dd1a-8f9dfe791886.png)

###外出先でスマートフォンから操作出来ない

ポートフォリオとして基本的な技術であるWeb機能がない。
あくまでポートフォリオとしての欠陥であるが、
著者に構築方法が身についていないという事。
これからエンジニアとして成長するために
必ず習得する必要がある。

###コードが冗長

上記と同様の理由。
特にデータベース接続とSQLをまとめた
Daoクラスが肥大化してしまった。

SQL文が違うだけで同じ処理を行う重複メソッドが
整理されていない。保守性を高めバグを減らす
ためにも、スキルを必ず身に付けていく必要がある。
![冗長コード.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/914469/a682a992-a866-80a8-16c5-289f3d8c32d1.gif)

###設計精度が低い

設計に工数をかけずに見切り発車してしまった。
返って手直しや設計に立ち返る必要にかられ、
工数をがかかってしまった。
ウォーターフォール開発であれば、
手間と労力を惜しまず設計精度向上に努めたい。

#今後

以上の課題を踏まえた上で更なる技術を身につける為、
次はWeb機能を用いた自作アプリを制作します。

クラウドサービスの需要が伸びていますが、
Webアプリケーションの全体的な動作の理解を深めるため、
Apacheにてサーバー構築から行い、セキュリティ
対策もできるよう、学習を進めていきます。

お忙しいところ、長文にも関わらず最後まで
ご覧頂き、誠にありがとうございました。

# 参考文献

- [オラクル認定資格教科書 Javaプログラマ Silver SE11](https://www.shoeisha.co.jp/book/detail/9784798162041)
- [徹底攻略 Java SE11 Silver 問題集「1ZOー808」対応徹底攻略シリーズ](https://book.impress.co.jp/books/1118101186)
- [スッキリわかる Java入門 実践編 第2版](https://sukkiri.jp/books/sukkiri_javap2)

GitHub

Daoクラス
https://github.com/KeisukeFukuoka/PocketMoney/blob/master/src_mysql/mysql/MySQLDao.java
その他のクラス一覧
https://github.com/KeisukeFukuoka/PocketMoney/tree/master/src/controller
fxmlファイル一覧
https://github.com/KeisukeFukuoka/PocketMoney/tree/master/fxml
