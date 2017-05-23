/*
 * Japanese translation
 * By tyama
 * 04-08-2007, 05:49 AM
 */

Ext.UpdateManager.defaults.indicatorText = '<div class="loading-indicator">読み込み中...</div>';

if(Ext.View){
  Ext.View.prototype.emptyText = "";
}

if(Ext.grid.GridPanel){
  Ext.grid.GridPanel.prototype.ddText = "{0} 行選択";
}

if(Ext.TabPanelItem){
  Ext.TabPanelItem.prototype.closeText = "このタブを閉じる";
}

if(Ext.form.Field){
  Ext.form.Field.prototype.invalidText = "フィールドの値が不正です。";
}

if(Ext.LoadMask){
    Ext.LoadMask.prototype.msg = "読み込み中...";
}

Date.monthNames = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月','11月','12月'];

Date.dayNames = [
 "日",
 "月",
 "火",
 "水",
 "木",
 "金",
 "土"];

if(Ext.MessageBox){
  Ext.MessageBox.buttonText = {
    ok : "OK",
    cancel : "キャンセル",
    yes : "はい",
    no : "いいえ"
  };
}

if(Ext.util.Format){
  Ext.util.Format.date = function(v, format){
     if(!v) return "";
     if(!(v instanceof Date)) v = new Date(Date.parse(v));
     return v.dateFormat(format || "Y/m/d");
  };
}

if(Ext.DatePicker){
  Ext.apply(Ext.DatePicker.prototype, {
     todayText         : "今日",
     minText           : "選択した日付は最小値以下です。",
     maxText           : "選択した日付は最大値以上です。",
     disabledDaysText  : "",
     disabledDatesText : "",
     monthNames	       : Date.monthNames,
     dayNames	       : Date.dayNames,
     nextText          : '次月へ (コントロール+右)',
     prevText          : '前月へ (コントロール+左)',
     monthYearText     : '月選択 (コントロール+上/下で年移動)',
     todayTip          : "{0} (スペースキー)",
     format            : "Y/m/d"
  });
}

if(Ext.PagingToolbar){
  Ext.apply(Ext.PagingToolbar.prototype, {
     beforePageText : "ページ",
     afterPageText  : "/ {0}",
     firstText      : "最初のページ",
     prevText       : "前のページ",
     nextText       : "次のページ",
     lastText       : "最後のページ",
     refreshText    : "更新",
     displayMsg     : "{2} 件中 {0} - {1} を表示",
     emptyMsg       : '表示するデータがありません。'
  });
}

if(Ext.form.TextField){
  Ext.apply(Ext.form.TextField.prototype, {
     minLengthText : "このフィールドの最小値は {0} です。",
     maxLengthText : "このフィールドの最大値は {0} です。",
     blankText     : "必須項目です。",
     regexText     : "",
     emptyText     : null
  });
}

if(Ext.form.NumberField){
  Ext.apply(Ext.form.NumberField.prototype, {
     minText : "このフィールドの最小値は {0} です。",
     maxText : "このフィールドの最大値は {0} です。",
     nanText : "{0} は数値ではありません。"
  });
}

if(Ext.form.DateField){
  Ext.apply(Ext.form.DateField.prototype, {
     disabledDaysText  : "無効",
     disabledDatesText : "無効",
     minText           : "このフィールドの日付は、 {0} 以降の日付に設定してください。",
     maxText           : "このフィールドの日付は、 {0} 以前の日付に設定してください。",
     invalidText       : "{0} は間違った日付入力です。 - 入力形式は「{1}」です。",
     format            : "Y/m/d"
  });
}

if(Ext.form.ComboBox){
  Ext.apply(Ext.form.ComboBox.prototype, {
     loadingText       : "読み込み中...",
     valueNotFoundText : undefined
  });
}

if(Ext.form.VTypes){
  Ext.apply(Ext.form.VTypes, {
     emailText    : 'メールアドレスを"user@domain.com"の形式で入力してください。',
     urlText      : 'URLを"http:/'+'/www.domain.com"の形式で入力してください。',
     alphaText    : '半角英字と"_"のみです。',
     alphanumText : '半角英数と"_"のみです。'
  });
}

if(Ext.grid.GridView){
  Ext.apply(Ext.grid.GridView.prototype, {
     sortAscText  : "昇順",
     sortDescText : "降順",
     lockText     : "カラムロック",
     unlockText   : "カラムロック解除",
     columnsText  : "Columns"
  });
}

if(Ext.grid.PropertyColumnModel){
  Ext.apply(Ext.grid.PropertyColumnModel.prototype, {
     nameText   : "名称",
     valueText  : "値",
     dateFormat : "Y/m/d"
  });
}

if(Ext.layout.BorderLayout && Ext.layout.BorderLayout.SplitRegion){
  Ext.apply(Ext.layout.BorderLayout.SplitRegion.prototype, {
     splitTip            : "ドラッグするとリサイズできます。",
     collapsibleSplitTip : "ドラッグでリサイズ。 ダブルクリックで隠す。"
  });
}
